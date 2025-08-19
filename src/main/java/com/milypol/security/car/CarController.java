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
    private final CarCostService carCostService;
    private final TaskService taskService;
    private final CartService cartService;
    private final ProductEventService productEventService;

    public CarController(CarService carService, CarCostService carCostService, TaskService taskService, CartService cartService, ProductEventService productEventService) {
        this.carService = carService;
        this.carCostService = carCostService;
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
        var cars = carService.getAllCars();
        model.addAttribute("cars", cars);

        Map<Integer, String> currentTaskNameByCarId = new HashMap<>();
        Map<Integer, String> currentTaskUsersByCarId = new HashMap<>();
        LocalDate today = LocalDate.now();

        for (var car : cars) {
            Optional<Task> currentOpt = taskService.getTaskByCarIdAndDate(car.getId(), today);
            Task current = currentOpt.orElse(null);

            if (current == null) {
                List<Task> tasks = taskService.getAllTasksByCarsId(car.getId());
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

    @PostMapping("/save")
    public String saveCar(@Valid @ModelAttribute("car") Car car,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allCarts", cartService.getAllCarts());
            return "cars/edit";
        }
        carService.saveCar(car);
        return "redirect:/cars/info/" + car.getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable Integer id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }

    @GetMapping("cost/add/{carId}")
    public String carCostAdd(@PathVariable Integer carId, Model model) {
        CarCost carCost = new CarCost();
        carCost.setCar(carService.getCarById(carId));
        model.addAttribute("car_cost", carCost);
        return "cars/costEdit";
    }

    @GetMapping("cost/edit/{id}")
    public String carCostEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("car_cost", carCostService.getCarCostById(id));
        return "cars/costEdit";
    }

    @PostMapping("cost/save")
    public String saveCostCar(@ModelAttribute CarCost carCost) {
        carCostService.saveCarCost(carCost);
        return "redirect:/cars/edit/" + carCost.getCar().getId();
    }

    @PostMapping("/cost/delete/{id}")
    public String deleteCostCar(@PathVariable Integer id, @RequestParam("carId") Integer carId){
        carCostService.deleteCarCost(id);
        return "redirect:/cars/edit/" + carId;
    }

    // NOWE: szczegóły pojazdu + koszty
    @GetMapping("/info/{id}")
    public String info(@PathVariable Integer id, Model model) {
        Car car = carService.getCarById(id);
        List<CarCost> costs = Optional.ofNullable(car.getCosts()).orElseGet(List::of)
                .stream()
                .sorted(Comparator.comparing(CarCost::getDateFrom, Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(CarCost::getId, Comparator.nullsLast(Comparator.naturalOrder())))
                .toList();

        double total = costs.stream()
                .map(CarCost::getCost)
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .sum();

        model.addAttribute("car", car);
        model.addAttribute("costs", costs);
        model.addAttribute("totalCost", total);

        return "cars/info";
    }
}
