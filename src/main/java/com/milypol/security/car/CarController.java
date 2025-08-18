package com.milypol.security.car;

import com.milypol.security.carCost.CarCost;
import com.milypol.security.carCost.CarCostService;
import com.milypol.security.cart.Cart;
import com.milypol.security.cart.CartService;
import com.milypol.security.productEvent.ProductEvent;
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

        // Proste mapy do szablonu (unikamy wywołań metod na obiektach w Thymeleaf)
        Map<Integer, String> currentTaskNameByCarId = new HashMap<>();
        Map<Integer, String> currentTaskUsersByCarId = new HashMap<>();
        LocalDate today = LocalDate.now();

        for (var car : cars) {
            // 1) Spróbuj „bieżące wg dat” (repo: BETWEEN dateFrom AND dateTo)
            Optional<Task> currentOpt = taskService.getTaskByCarIdAndDate(car.getId(), today);
            Task current = currentOpt.orElse(null);

            // 2) Jeśli brak, weź najświeższe IN_PROGRESS z listy zadań auta
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

    @GetMapping("/info/{id}")
    public String infoCar(@PathVariable Integer id, Model model) {
        ProductEvent productEvent = new ProductEvent();
        var car = carService.getCarById(id);
        model.addAttribute("car", car);
        model.addAttribute("tasks", taskService.getAllTasksByCarsId(id));
        model.addAttribute("productInCar", productEventService.getProductCountInCar(id));
        model.addAttribute("productEvent", productEvent);

        // Bieżące zadanie (IN_PROGRESS) + użytkownicy w tym zadaniu (string)
        var tasks = taskService.getAllTasksByCarsId(id);
        if (tasks != null) {
            tasks.stream()
                 .filter(t -> t.getStatus() == TaskStatus.IN_PROGRESS)
                 .max(Comparator.comparing(Task::getDateFrom, Comparator.nullsLast(Comparator.naturalOrder())))
                 .ifPresent(t -> {
                     model.addAttribute("currentTaskName", Optional.ofNullable(t.getName()).orElse("—"));
                     String users = (t.getUsers() == null || t.getUsers().isEmpty())
                             ? "—"
                             : t.getUsers().stream()
                                 .map(u -> ((u.getFirstname() != null ? u.getFirstname() : "") + " " + (u.getLastName() != null ? u.getLastName() : "")).trim())
                                 .filter(s -> !s.isBlank())
                                 .reduce((a,b) -> a + ", " + b)
                                 .orElse("—");
                     model.addAttribute("currentTaskUsers", users);
                 });
        }

        // Koszty związane z pojazdem (lista + liczba)
        model.addAttribute("costs", car.getCosts());
        model.addAttribute("costsCount", car.getCosts() != null ? car.getCosts().size() : 0);

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
}
