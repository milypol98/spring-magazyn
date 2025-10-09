package com.milypol.security.car;

import com.milypol.security.carCost.CarCost;
import com.milypol.security.carCost.CarCostService;
import com.milypol.security.cart.Cart;
import com.milypol.security.cart.CartService;
import com.milypol.security.productEvent.ProductEventService;
import com.milypol.security.task.Task;
import com.milypol.security.task.TaskService;
import com.milypol.security.task.TaskStatus;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final TaskService taskService;
    private final CartService cartService;
    private final ProductEventService productEventService;

    public CarController(CarService carService, TaskService taskService, CartService cartService, ProductEventService productEventService) {
        this.carService = carService;
        this.taskService = taskService;
        this.cartService = cartService;
        this.productEventService = productEventService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Cart.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isBlank()) {
                    setValue(null);
                    return;
                }
                setValue(cartService.getCartById(Integer.valueOf(text)));
            }
        });
    }

    @GetMapping
    public String showCarPage(Model model) {
        List<Car> cars = carService.getAllCars();
        CarStatusEvaluator evaluator = new CarStatusEvaluator();
        Map<CarStatus, List<Car>> groupedCars = cars.stream()
                .collect(Collectors.groupingBy(evaluator::evaluate));
        model.addAttribute("criticalCars", groupedCars.getOrDefault(CarStatus.CRITICAL, List.of()));
        model.addAttribute("warningCars", groupedCars.getOrDefault(CarStatus.WARNING, List.of()));
        model.addAttribute("normalCars", groupedCars.getOrDefault(CarStatus.NORMAL, List.of()));

        Map<Integer, String> currentTaskNameByCarId = new HashMap<>();
        Map<Integer, String> currentTaskUsersByCarId = new HashMap<>();
        LocalDate today = LocalDate.now();

        for (var car : cars) {
            Optional<Task> currentOpt = taskService.getTaskByCarIdAndDate(car.getId(), today);
            Task current = currentOpt.orElse(null);

            if (current == null) {
                List<Task> tasks = taskService.getAllTasksByCarId(car.getId());
                if (tasks != null) {
                    current = tasks.stream()
                            .filter(t -> t.getStatus() == TaskStatus.IN_PROGRESS)
                            .max(Comparator.comparing(Task::getDateFrom, Comparator.nullsLast(Comparator.naturalOrder())))
                            .orElse(null);
                }
            }

            if (current != null) {
                currentTaskNameByCarId.put(car.getId(), Optional.ofNullable(current.getName()).orElse("—"));
                String usersJoined = (current.getUsers() == null || current.getUsers().isEmpty())
                        ? "—"
                        : current.getUsers().stream()
                        .map(u -> ((u.getFirstname() != null ? u.getFirstname() : "") + " " + (u.getLastName() != null ? u.getLastName() : "")).trim())
                        .filter(s -> !s.isBlank())
                        .collect(Collectors.joining(", "));
                currentTaskUsersByCarId.put(car.getId(), usersJoined.isBlank() ? "—" : usersJoined);
            } else {
                currentTaskNameByCarId.put(car.getId(), "—");
                currentTaskUsersByCarId.put(car.getId(), "—");
            }
        }

        model.addAttribute("currentTaskNameByCarId", currentTaskNameByCarId);
        model.addAttribute("currentTaskUsersByCarId", currentTaskUsersByCarId);

        return "cars/list";
    }
    @GetMapping("/info/{id}")
    public String info(@PathVariable Integer id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        return "cars/info";
    }
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("allCarts", cartService.getAllCarts());
        return "cars/edit";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        model.addAttribute("allCarts", cartService.getAllCarts());
        return "cars/edit";
    }
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Integer id, Model model) {
        Car car = carService.getCarById(id);
        CarUpdateDto carUpdateDto = new CarUpdateDto();
        carUpdateDto.setId(id);
        model.addAttribute("carUpdateDto",carUpdateDto);
        model.addAttribute("car", car);
        return "cars/car-update";
    }
    @PostMapping("/update")
    public String updateCar(@Valid @ModelAttribute("car") CarUpdateDto carUpdateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cars/car-update";
        }
        carService.applyQuickUpdate(carUpdateDto);
        return "redirect:/cars";
    }
    @PostMapping("/save")
    public String saveCar(@Valid @ModelAttribute("car") Car car,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allCarts", cartService.getAllCarts());
            return "cars/edit";
        }
        carService.saveCar(car);
        return "redirect:/cars";
    }
    @PostMapping("/update-course")
    public ResponseEntity<String> updateCourse(
            @RequestParam("id") Integer carId,
            @RequestParam("course") Integer newCourse) {
        Car car = carService.getCarById(carId);
        car.setCourse(newCourse);
        car.setCourseDate(LocalDate.now());
        carService.saveCar(car);
        return ResponseEntity.ok("Przebieg zaktualizowany dla auta ID: " + carId + " na wartość: " + newCourse + " KM.");
    }

    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable Integer id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }

    @GetMapping("/item/{id}")
    public String costInfo(@PathVariable Integer id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        model.addAttribute("carItem", productEventService.getAllProductCountInCar(id));
        return "cars/car-item";
    }

}
