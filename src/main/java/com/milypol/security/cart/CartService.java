package com.milypol.security.cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllCarts();
    Cart getCartById(Integer id);
    Cart saveCart(Cart cart);
    void deleteCart(Integer id);
    Cart updateCart(Cart updatedCart);
}
