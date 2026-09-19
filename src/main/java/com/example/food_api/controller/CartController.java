package com.example.food_api.controller;

import com.example.food_api.model.Cart;
import com.example.food_api.model.CartItem;
import com.example.food_api.repository.CartRepository;

import com.example.food_api.service.CartService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartRepository cartRepository;
    private final CartService cartService;

    public CartController(
            CartRepository cartRepository,
            CartService cartService) {

        this.cartRepository = cartRepository;
        this.cartService = cartService;
    }

    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.saveCart(cart);
    }

    @PostMapping("/{cartId}/items")
    public Cart addFoodToCart(
            @PathVariable Long cartId,
            @RequestBody CartItem cartItem) {

        return cartService.addFoodToCart(cartId, cartItem);
    }

    @PutMapping("/{cartId}/items/{itemId}")
    public Cart updateItemQuantity(
            @PathVariable Long cartId,
            @PathVariable Long itemId,
            @RequestBody CartItem cartItem) {

        return cartService.updateItemQuantity(
                cartId,
                itemId,
                cartItem.getQuantity());
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    public Cart removeItemFromCart(
            @PathVariable Long cartId,
            @PathVariable Long itemId) {

        return cartService.removeItemFromCart(
                cartId,
                itemId);
    }

    @DeleteMapping("/{cartId}")
    public Cart clearCart(@PathVariable Long cartId) {

        return cartService.clearCart(cartId);
    }

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id) {
        return cartRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @PostMapping("/{cartId}/coupon")
    public Cart applyCoupon(
            @PathVariable Long cartId,
            @RequestParam String couponCode) {

        return cartService.applyCoupon(cartId, couponCode);
    }
}