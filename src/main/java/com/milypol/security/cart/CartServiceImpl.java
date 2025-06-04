package com.milypol.security.cart;

import com.milypol.security.car.Car;
import com.milypol.security.car.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepo cartRepo;

    public CartServiceImpl(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }


    @Override
    public List<Cart> getAllCarts() {
        return cartRepo.findAll();
    }

    @Override
    public Cart getCartById(Integer id) {
        return cartRepo.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public void deleteCart(Integer id) {
        cartRepo.deleteById(id);
    }

    @Override
    public Cart updateCart(Cart updatedCart) {
        return cartRepo.save(updatedCart);
    }
}
