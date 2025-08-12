package com.milypol.security.cartItem;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepo cartItemRepo;

    public CartItemServiceImpl(CartItemRepo cartItemRepo) {
        this.cartItemRepo = cartItemRepo;
    }

    @Override
    @Transactional
    public CartItem saveCartItem(CartItem cartItem) {
        // Normalizacja: puste obiekty zależności (bez ID) traktujemy jako null
        if (cartItem.getProduct() != null && cartItem.getProduct().getId() == null) {
            cartItem.setProduct(null);
        }
        if (cartItem.getStockPosition() != null && cartItem.getStockPosition().getId() == null) {
            cartItem.setStockPosition(null);
        }

        if ((cartItem.getProduct() == null && cartItem.getStockPosition() == null)
                || (cartItem.getProduct() != null && cartItem.getStockPosition() != null)) {
            throw new IllegalArgumentException("Pozycja koszyka musi wskazywać dokładnie na jeden typ: Product albo StockPosition.");
        }
        if (cartItem.getQuantity() == null || cartItem.getQuantity() <= 0) {
            throw new IllegalArgumentException("Ilość musi być dodatnia.");
        }
        if (cartItem.getCart() == null || cartItem.getCart().getId() == null) {
            throw new IllegalArgumentException("Koszyk musi być wskazany.");
        }

        if (cartItem.getProduct() != null) {
            return cartItemRepo.findByCart_IdAndProduct_Id(cartItem.getCart().getId(), cartItem.getProduct().getId())
                    .map(existing -> {
                        // NADPISZ ilość zamiast sumować
                        existing.setQuantity(cartItem.getQuantity());
                        return cartItemRepo.save(existing);
                    })
                    .orElseGet(() -> cartItemRepo.save(cartItem));
        } else {
            return cartItemRepo.findByCart_IdAndStockPosition_Id(cartItem.getCart().getId(), cartItem.getStockPosition().getId())
                    .map(existing -> {
                        // NADPISZ ilość zamiast sumować
                        existing.setQuantity(cartItem.getQuantity());
                        return cartItemRepo.save(existing);
                    })
                    .orElseGet(() -> cartItemRepo.save(cartItem));
        }
    }

    @Override
    public List<CartItem> getAllCartItemsByCartId(Integer id) {
        return cartItemRepo.findAllByCart_Id(id);
    }

    @Override
    public CartItem getById(Integer id) {
        return cartItemRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Brak pozycji koszyka o id=" + id));
    }

    @Override
    public void deleteById(Integer id) {
        cartItemRepo.deleteById(id);
    }
}
