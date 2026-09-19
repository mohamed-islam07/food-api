package com.example.food_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "delivery")
public class Delivery {

    @Id
    @JsonIgnore
    private String mongoId;

    private Long id;
    private boolean available;
    private double deliveryCharge;
    private double freeDeliveryAbove;
    private String estimatedTime;
    private String note;

    public Delivery() {
    }

    public Delivery(Long id, boolean available, double deliveryCharge,
                    double freeDeliveryAbove, String estimatedTime,
                    String note) {
        this.id = id;
        this.available = available;
        this.deliveryCharge = deliveryCharge;
        this.freeDeliveryAbove = freeDeliveryAbove;
        this.estimatedTime = estimatedTime;
        this.note = note;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}