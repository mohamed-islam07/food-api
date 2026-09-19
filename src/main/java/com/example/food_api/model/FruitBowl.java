package com.example.food_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "fruit_bowls")
public class FruitBowl {

    @Id
    @JsonIgnore
    private String mongoId;

    private Long id;
    private Long foodId;
    private List<Long> selectedFruitIds;

    public FruitBowl() {
    }

    public FruitBowl(Long id, Long foodId, List<Long> selectedFruitIds) {
        this.id = id;
        this.foodId = foodId;
        this.selectedFruitIds = selectedFruitIds;
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

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public List<Long> getSelectedFruitIds() {
        return selectedFruitIds;
    }

    public void setSelectedFruitIds(List<Long> selectedFruitIds) {
        this.selectedFruitIds = selectedFruitIds;
    }
}