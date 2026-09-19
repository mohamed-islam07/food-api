package com.example.food_api.model;

import java.util.List;

public class MenuResponse {

    private List<Restaurant> restaurants;
    private List<Category> categories;
    private List<Food> foods;
    private List<Dip> dips;
    private List<AddOn> addOns;
    private List<Customization> customizations;
    private List<Fruit> fruits;
    private List<FruitBowl> fruitBowls;
    private List<Drink> drinks;
    private List<Combo> combos;
    private List<Timing> timings;
    private List<Delivery> delivery;

    public MenuResponse(
            List<Restaurant> restaurants,
            List<Category> categories,
            List<Food> foods,
            List<Dip> dips,
            List<AddOn> addOns,
            List<Customization> customizations,
            List<Fruit> fruits,
            List<FruitBowl> fruitBowls,
            List<Drink> drinks,
            List<Combo> combos,
            List<Timing> timings,
            List<Delivery> delivery) {

        this.restaurants = restaurants;
        this.categories = categories;
        this.foods = foods;
        this.dips = dips;
        this.addOns = addOns;
        this.customizations = customizations;
        this.fruits = fruits;
        this.fruitBowls = fruitBowls;
        this.drinks = drinks;
        this.combos = combos;
        this.timings = timings;
        this.delivery = delivery;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public List<Dip> getDips() {
        return dips;
    }

    public List<AddOn> getAddOns() {
        return addOns;
    }

    public List<Customization> getCustomizations() {
        return customizations;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public List<FruitBowl> getFruitBowls() {
        return fruitBowls;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<Combo> getCombos() {
        return combos;
    }

    public List<Timing> getTimings() {
        return timings;
    }

    public List<Delivery> getDelivery() {
        return delivery;
    }
}