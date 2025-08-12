package com.milypol.security.cartItem;

import java.util.List;

public interface CartItemService {
    CartItem saveCartItem(CartItem cartItem);
    List<CartItem> getAllCartItemsByCartId(Integer id);
    CartItem getById(Integer id);
    void deleteById(Integer id);
}
