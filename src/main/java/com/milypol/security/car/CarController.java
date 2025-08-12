package com.milypol.security.car;

import com.milypol.security.carCost.CarCost;
import com.milypol.security.carCost.CarCostService;

import com.milypol.security.cart.Cart;
import com.milypol.security.cart.CartService;
import com.milypol.security.productEvent.ProductEvent;
import com.milypol.security.productEvent.ProductEventService;
import com.milypol.security.task.TaskService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // Umożliwia bindowanie elementów List<Cart> po ich ID z formularza (select multiple)
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
        model.addAttribute("cars", carService.getAllCars());
        return "cars/list";
    }

    @GetMapping("/info/{id}")
    public String infoCar(@PathVariable Integer id, Model model) {
        ProductEvent productEvent = new ProductEvent();
        model.addAttribute("car", carService.getCarById(id));
        model.addAttribute("tasks", taskService.getAllTasksByCarsId(id));
        model.addAttribute("productInCar", productEventService.getProductCountInCar(id));
        model.addAttribute("productEvent", productEvent);
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
            // Przy błędach trzeba ponownie dostarczyć listę koszyków do selecta
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
        // Przekierowanie z powrotem do edycji konkretnego auta (aby formularz się prawidłowo załadował)
        return "redirect:/cars/edit/" + carId;
    }
}
