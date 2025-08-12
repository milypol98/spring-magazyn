package com.milypol.security.cartItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
    List<CartItem> findAllByCart_Id(Integer cartId);

    // Upsert wg produktu
    Optional<CartItem> findByCart_IdAndProduct_Id(Integer cartId, Integer productId);

    // Upsert wg stock position
    Optional<CartItem> findByCart_IdAndStockPosition_Id(Integer cartId, Integer stockPositionId);
}
