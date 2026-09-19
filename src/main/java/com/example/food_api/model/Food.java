package com.example.food_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "foods")
public class Food {

    @Id
    @JsonIgnore
    private String mongoId;

    private Long id;
    private Long categoryId;
    private String name;

    private Map<String, Double> prices;

    private boolean recommended;

    private List<Long> dipIds;
    private List<Long> addOnIds;
    private List<Long> customizationIds;
    private List<Long> fruitIds;

    public Food() {
    }

    public Food(
            Long id,
            Long categoryId,
            String name,
            Map<String, Double> prices,
            boolean recommended) {

        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.prices = prices;
        this.recommended = recommended;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Double> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, Double> prices) {
        this.prices = prices;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public List<Long> getDipIds() {
        return dipIds;
    }

    public void setDipIds(List<Long> dipIds) {
        this.dipIds = dipIds;
    }

    public List<Long> getAddOnIds() {
        return addOnIds;
    }

    public void setAddOnIds(List<Long> addOnIds) {
        this.addOnIds = addOnIds;
    }

    public List<Long> getCustomizationIds() {
        return customizationIds;
    }

    public void setCustomizationIds(List<Long> customizationIds) {
        this.customizationIds = customizationIds;
    }

    public List<Long> getFruitIds() {
        return fruitIds;
    }

    public void setFruitIds(List<Long> fruitIds) {
        this.fruitIds = fruitIds;
    }

}