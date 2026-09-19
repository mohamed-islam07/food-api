package com.example.food_api.service;

import com.example.food_api.model.Cart;
import com.example.food_api.model.CartItem;
import com.example.food_api.model.Food;
import com.example.food_api.repository.CartRepository;
import com.example.food_api.repository.FoodRepository;
import com.example.food_api.model.Coupon;
import com.example.food_api.repository.CouponRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final FoodRepository foodRepository;
    private final CouponRepository couponRepository;

    public CartService(
            CartRepository cartRepository,
            FoodRepository foodRepository,
            CouponRepository couponRepository) {

        this.cartRepository = cartRepository;
        this.foodRepository = foodRepository;
        this.couponRepository = couponRepository;
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart addFoodToCart(Long cartId, CartItem cartItem) {

        // Find cart
        Cart cart = cartRepository.findByNumericId(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Find food
        Food food = foodRepository.findByNumericId(cartItem.getItemId())
                .orElseThrow(() -> new RuntimeException("Food not found"));

        // Get price
        double price = food.getPrices().values().iterator().next();

        // Set food details
        cartItem.setItemType("FOOD");
        cartItem.setName(food.getName());
        cartItem.setUnitPrice(price);

        // Calculate item total
        cartItem.setTotalPrice(price * cartItem.getQuantity());

        // Get existing items
        List<CartItem> items = cart.getItems();

        if (items == null) {
            items = new java.util.ArrayList<>();
        }

        // Add item
        items.add(cartItem);

        cart.setItems(items);

        // Calculate cart subtotal
        double subtotal = 0;

        for (CartItem item : items) {
            subtotal += item.getTotalPrice();
        }

        cart.setSubtotal(subtotal);

        // Delivery settings
        double freeDeliveryAbove = 200;
        double deliveryCharge = 30;

        cart.setFreeDeliveryAbove(freeDeliveryAbove);

        if (subtotal >= freeDeliveryAbove) {
            deliveryCharge = 0;
        }

        cart.setDeliveryCharge(deliveryCharge);

        // Amount needed for free delivery
        double amountForFreeDelivery = freeDeliveryAbove - subtotal;

        if (amountForFreeDelivery < 0) {
            amountForFreeDelivery = 0;
        }

        cart.setAmountForFreeDelivery(amountForFreeDelivery);

        // Final total
        cart.setTotal(subtotal + deliveryCharge);

        return cartRepository.save(cart);
    }

    public Cart updateItemQuantity(
            Long cartId,
            Long itemId,
            int quantity) {

        Cart cart = cartRepository.findByNumericId(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem item = cart.getItems()
                .stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        item.setQuantity(quantity);

        item.setTotalPrice(
                item.getUnitPrice() * quantity);

        double subtotal = 0;

        for (CartItem cartItem : cart.getItems()) {
            subtotal += cartItem.getTotalPrice();
        }

        cart.setSubtotal(subtotal);

        double freeDeliveryAbove = 200;
        double deliveryCharge = 30;

        cart.setFreeDeliveryAbove(freeDeliveryAbove);

        if (subtotal >= freeDeliveryAbove) {
            deliveryCharge = 0;
        }

        cart.setDeliveryCharge(deliveryCharge);

        double amountForFreeDelivery = freeDeliveryAbove - subtotal;

        if (amountForFreeDelivery < 0) {
            amountForFreeDelivery = 0;
        }

        cart.setAmountForFreeDelivery(
                amountForFreeDelivery);

        cart.setTotal(
                subtotal + deliveryCharge);

        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(
            Long cartId,
            Long itemId) {

        Cart cart = cartRepository.findByNumericId(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartItem> items = cart.getItems();

        if (items == null || items.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        boolean removed = items.removeIf(
                item -> item.getId().equals(itemId));

        if (!removed) {
            throw new RuntimeException("Cart item not found");
        }

        // Recalculate subtotal
        double subtotal = 0;

        for (CartItem item : items) {
            subtotal += item.getTotalPrice();
        }

        cart.setSubtotal(subtotal);

        // Delivery settings
        double freeDeliveryAbove = 200;
        double deliveryCharge = 30;

        cart.setFreeDeliveryAbove(freeDeliveryAbove);

        if (subtotal >= freeDeliveryAbove) {
            deliveryCharge = 0;
        }

        cart.setDeliveryCharge(deliveryCharge);

        // Amount needed for free delivery
        double amountForFreeDelivery = freeDeliveryAbove - subtotal;

        if (amountForFreeDelivery < 0) {
            amountForFreeDelivery = 0;
        }

        cart.setAmountForFreeDelivery(
                amountForFreeDelivery);

        // Final total
        cart.setTotal(
                subtotal + deliveryCharge);

        return cartRepository.save(cart);
    }

    public Cart clearCart(Long cartId) {

        Cart cart = cartRepository.findByNumericId(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.setItems(new java.util.ArrayList<>());

        cart.setSubtotal(0);
        cart.setDeliveryCharge(0);
        cart.setFreeDeliveryAbove(200);
        cart.setAmountForFreeDelivery(200);
        cart.setTotal(0);

        return cartRepository.save(cart);
    }

    public Cart applyCoupon(Long cartId, String couponCode) {

        Cart cart = cartRepository.findByNumericId(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Coupon coupon = couponRepository
                .findByCodeIgnoreCase(couponCode)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        double discount = calculateCouponDiscount(coupon, cart.getSubtotal());

        cart.setCouponCode(coupon.getCode());
        cart.setDiscountAmount(discount);

        double total = cart.getSubtotal()
                + cart.getDeliveryCharge()
                - discount;

        if (total < 0) {
            total = 0;
        }

        cart.setTotal(total);

        return cartRepository.save(cart);
    }

    private double calculateCouponDiscount(
            Coupon coupon,
            double subtotal) {

        if (!coupon.isActive()) {
            throw new RuntimeException("Coupon is inactive");
        }

        if (coupon.getUsageLimit() > 0
                && coupon.getUsedCount() >= coupon.getUsageLimit()) {

            throw new RuntimeException(
                    "Coupon usage limit reached");
        }

        if (subtotal < coupon.getMinimumOrderAmount()) {

            double amountNeeded = coupon.getMinimumOrderAmount() - subtotal;

            throw new RuntimeException(
                    "Add ₹" + amountNeeded
                            + " more to use this coupon");
        }

        double discount;

        if ("PERCENTAGE".equalsIgnoreCase(
                coupon.getDiscountType())) {

            discount = subtotal
                    * coupon.getDiscountValue()
                    / 100;

        } else if ("FIXED".equalsIgnoreCase(
                coupon.getDiscountType())) {

            discount = coupon.getDiscountValue();

        } else {

            throw new RuntimeException(
                    "Invalid discount type");
        }

        if (coupon.getMaximumDiscount() > 0
                && discount > coupon.getMaximumDiscount()) {

            discount = coupon.getMaximumDiscount();
        }

        if (discount > subtotal) {
            discount = subtotal;
        }

        return discount;
    }
}
