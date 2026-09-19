package com.example.food_api.service;

import com.example.food_api.model.Cart;
import com.example.food_api.model.CartItem;
import com.example.food_api.model.Food;
import com.example.food_api.model.Order;
import com.example.food_api.repository.CartRepository;
import com.example.food_api.repository.FoodRepository;
import com.example.food_api.repository.OrderRepository;
import org.springframework.stereotype.Service;
import com.example.food_api.model.Coupon;
import com.example.food_api.repository.CouponRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final FoodRepository foodRepository;
    private final CouponRepository couponRepository;

    public OrderService(
            OrderRepository orderRepository,
            CartRepository cartRepository,
            FoodRepository foodRepository,
            CouponRepository couponRepository) {

        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.foodRepository = foodRepository;
        this.couponRepository = couponRepository;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order placeOrder(Long cartId) {

        Cart cart = cartRepository.findByNumericId(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        long nextOrderId = orderRepository.findAll()
                .stream()
                .map(Order::getId)
                .filter(id -> id != null)
                .max(Long::compareTo)
                .orElse(0L) + 1;

        List<CartItem> orderItems = new ArrayList<>(cart.getItems());

        Order order = new Order();

        order.setId(nextOrderId);
        order.setCartId(cartId);
        order.setItems(orderItems);

        order.setSubtotal(cart.getSubtotal());
        order.setDeliveryCharge(cart.getDeliveryCharge());
        order.setCouponCode(cart.getCouponCode());
        order.setDiscountAmount(cart.getDiscountAmount());
        order.setTotal(cart.getTotal());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

        order.setOrderDate(
                LocalDateTime.now().format(formatter));

        order.setStatus("PLACED");

        Order savedOrder = orderRepository.save(order);

        if (cart.getCouponCode() != null
                && !cart.getCouponCode().isEmpty()) {

            Coupon coupon = couponRepository
                    .findByCodeIgnoreCase(cart.getCouponCode())
                    .orElse(null);

            if (coupon != null) {
                coupon.setUsedCount(coupon.getUsedCount() + 1);
                couponRepository.save(coupon);
            }
        }

        cart.setItems(new ArrayList<>());
        cart.setSubtotal(0);
        cart.setDeliveryCharge(0);
        cart.setFreeDeliveryAbove(200);
        cart.setAmountForFreeDelivery(200);
        cart.setCouponCode(null);
        cart.setDiscountAmount(0);
        cart.setTotal(0);

        cartRepository.save(cart);

        return savedOrder;
    }

    public Order reorder(Long orderId) {

        // Find old order
        Order oldOrder = orderRepository.findByNumericId(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (oldOrder.getItems() == null || oldOrder.getItems().isEmpty()) {
            throw new RuntimeException("Old order has no items");
        }

        List<CartItem> newItems = new ArrayList<>();

        double subtotal = 0;

        // Recheck every old item
        for (CartItem oldItem : oldOrder.getItems()) {

            if (!"FOOD".equals(oldItem.getItemType())) {
                throw new RuntimeException(
                        "Reorder currently supports FOOD items only");
            }

            Food food = foodRepository.findByNumericId(oldItem.getItemId())
                    .orElseThrow(() -> new RuntimeException(
                            "Food not found: " + oldItem.getItemId()));

            double price;

            if (oldItem.getSelectedSize() != null
                    && !oldItem.getSelectedSize().isEmpty()
                    && food.getPrices().containsKey(oldItem.getSelectedSize())) {

                price = food.getPrices()
                        .get(oldItem.getSelectedSize());

            } else {

                price = food.getPrices()
                        .values()
                        .iterator()
                        .next();
            }

            CartItem newItem = new CartItem();

            newItem.setId(oldItem.getId());
            newItem.setItemType("FOOD");
            newItem.setItemId(food.getId());
            newItem.setName(food.getName());
            newItem.setQuantity(oldItem.getQuantity());
            newItem.setSelectedSize(oldItem.getSelectedSize());
            newItem.setSelectedDipIds(oldItem.getSelectedDipIds());
            newItem.setSelectedAddOnIds(oldItem.getSelectedAddOnIds());
            newItem.setSelectedCustomizationIds(
                    oldItem.getSelectedCustomizationIds());
            newItem.setSelectedFruitIds(oldItem.getSelectedFruitIds());

            newItem.setUnitPrice(price);
            newItem.setTotalPrice(
                    price * oldItem.getQuantity());

            newItems.add(newItem);

            subtotal += newItem.getTotalPrice();
        }

        // Calculate delivery
        double freeDeliveryAbove = 200;
        double deliveryCharge = 30;

        if (subtotal >= freeDeliveryAbove) {
            deliveryCharge = 0;
        }

        double total = subtotal + deliveryCharge;

        // Generate new order ID
        long nextOrderId = orderRepository.findAll()
                .stream()
                .map(Order::getId)
                .filter(id -> id != null)
                .max(Long::compareTo)
                .orElse(0L) + 1;

        // Create new order
        Order newOrder = new Order();

        newOrder.setId(nextOrderId);
        newOrder.setCartId(null);
        newOrder.setItems(newItems);
        newOrder.setSubtotal(subtotal);
        newOrder.setDeliveryCharge(deliveryCharge);
        newOrder.setTotal(total);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

        newOrder.setOrderDate(
                LocalDateTime.now().format(formatter));

        newOrder.setStatus("PLACED");

        return orderRepository.save(newOrder);
    }
}