package com.example.food_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "carts")
public class Cart {

    @Id
    @JsonIgnore
    private String mongoId;

    private Long id;

    private List<CartItem> items;

    private double subtotal;

    private double deliveryCharge;

    private double freeDeliveryAbove;

    private double amountForFreeDelivery;

    private double total;

    private String couponCode;
    private double discountAmount;

    public Cart() {
    }

    public Cart(Long id, List<CartItem> items) {
        this.id = id;
        this.items = items;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public double getFreeDeliveryAbove() {
        return freeDeliveryAbove;
    }

    public void setFreeDeliveryAbove(double freeDeliveryAbove) {
        this.freeDeliveryAbove = freeDeliveryAbove;
    }

    public double getAmountForFreeDelivery() {
        return amountForFreeDelivery;
    }

    public void setAmountForFreeDelivery(double amountForFreeDelivery) {
        this.amountForFreeDelivery = amountForFreeDelivery;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
}