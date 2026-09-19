package com.example.food_api.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "combos")
public class Combo {

    @Id
    @JsonIgnore
    private String mongoId;

    private Long id;
    private String name;
    private String description;
    private double price;
    
    private List<Long> foodIds;
    private List<Long> drinkIds;

    public Combo() {
    }

    public Combo(Long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Long> getFoodIds() {
        return foodIds;
    }

    public void setFoodIds(List<Long> foodIds) {
        this.foodIds = foodIds;
    }

    public List<Long> getDrinkIds() {
        return drinkIds;
    }

    public void setDrinkIds(List<Long> drinkIds) {
        this.drinkIds = drinkIds;
    }
}