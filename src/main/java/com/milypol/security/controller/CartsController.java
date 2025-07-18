package com.milypol.security.controller;

import com.milypol.security.cart.Cart;
import com.milypol.security.cart.CartService;
import com.milypol.security.stockPosition.StockPositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carts")
public class CartsController {
    private final CartService cartService;
    private final StockPositionService stockPositionService;

    public CartsController(CartService cartService, StockPositionService stockPositionService) {
        this.cartService = cartService;
        this.stockPositionService = stockPositionService;
    }

    @GetMapping( "/add")
    public String addCart(Model model) {
        model.addAttribute("cart", new Cart());
        model.addAttribute("allStackPositions", stockPositionService.getAllStockPositions());
        return "carts/edit";
    }
    @GetMapping( "/edit/{id}")
    public String editCart(@PathVariable Integer id, Model model) {
        model.addAttribute("cart", cartService.getCartById(id));
        model.addAttribute("allStackPositions", stockPositionService.getAllStockPositions());
        return "carts/edit";
    }
    @PostMapping( "/save")
    public String saveCart(@ModelAttribute Cart cart) {
        cartService.saveCart(cart);
        return "redirect:/warehouses";
    }

    @PostMapping( "/delete/{id}")
    public String deleteCart(@PathVariable Integer id){
        cartService.deleteCart(id);
        return "redirect:/warehouses";
    }
}
