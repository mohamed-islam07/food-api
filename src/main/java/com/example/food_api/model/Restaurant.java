package com.example.food_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "restaurant")
public class Restaurant {

    @Id
    @JsonIgnore
    private String mongoId;

    private String name;
    private String tagline;
    private String status;
    private String phone;
    private String instagram;
    private boolean customizationAvailable;

    public Restaurant() {
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public boolean isCustomizationAvailable() {
        return customizationAvailable;
    }

    public void setCustomizationAvailable(boolean customizationAvailable) {
        this.customizationAvailable = customizationAvailable;
    }
}