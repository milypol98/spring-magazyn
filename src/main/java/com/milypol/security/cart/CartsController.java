package com.milypol.security.cart;

import com.milypol.security.cartItem.CartItem;
import com.milypol.security.cartItem.CartItemService;
import com.milypol.security.product.ProductService;
import com.milypol.security.stockPosition.StockPositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carts")
public class CartsController {
    private final CartService cartService;
    private final StockPositionService stockPositionService;
    private final ProductService productService;
    private final CartItemService cartItemService;

    public CartsController(CartService cartService, StockPositionService stockPositionService, ProductService productService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.stockPositionService = stockPositionService;
        this.productService = productService;
        this.cartItemService = cartItemService;
    }

    @GetMapping("/add")
    public String addCart(Model model) {
        model.addAttribute("cart", new Cart());
        model.addAttribute("cartItem", new CartItem());
        model.addAttribute("allStackPositions", stockPositionService.getAllStockPositions());
        model.addAttribute("allProduct", productService.getAllProducts());
        return "carts/edit";
    }

    @GetMapping("/edit/{id}")
    public String editCart(@PathVariable Integer id, Model model) {
        model.addAttribute("cart", cartService.getCartById(id));
        model.addAttribute("cartItem", new CartItem());
        model.addAttribute("allStackPositions", stockPositionService.getAllStockPositions());
        model.addAttribute("allProduct", productService.getAllProducts());
        return "carts/edit";
    }

    @PostMapping("/save")
    public String saveCart(@ModelAttribute Cart cart) {
        cartService.saveCart(cart);
        return "redirect:/warehouses";
    }

    // Jeden endpoint do dodawania z obu tabel – upsert w serwisie
    @PostMapping("/items/save")
    public String saveCartItem(@ModelAttribute CartItem cartItem) {
        CartItem saved = cartItemService.saveCartItem(cartItem);
        return "redirect:/carts/edit/" + saved.getCart().getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteCart(@PathVariable Integer id) {
        cartService.deleteCart(id);
        return "redirect:/warehouses";
    }

    @PostMapping("/items/delete/{id}")
    public String deleteCartItem(@PathVariable Integer id, @RequestParam("cartId") Integer cartId) {
        cartItemService.deleteById(id);
        return "redirect:/carts/edit/" + cartId;
    }
}