package com.example.food_api.controller;

import com.example.food_api.model.Food;
import com.example.food_api.repository.FoodRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodRepository foodRepository;

    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @PostMapping
    public List<Food> addFoods(@RequestBody List<Food> foods) {
        return foodRepository.saveAll(foods);
    }

    @GetMapping
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<Food> getFoodsByCategory(@PathVariable Long categoryId) {
        return foodRepository.findByCategoryId(categoryId);
    }

    @GetMapping("/recommended")
    public List<Food> getRecommendedFoods() {
        return foodRepository.findByRecommendedTrue();
    }

    @GetMapping("/{id}")
    public Food getFoodById(@PathVariable Long id) {
        return foodRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));
    }

    @PutMapping("/{id}")
    public Food updateFood(
            @PathVariable Long id,
            @RequestBody Food food) {

        Food existingFood = foodRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        existingFood.setCategoryId(food.getCategoryId());
        existingFood.setName(food.getName());
        existingFood.setPrices(food.getPrices());
        existingFood.setRecommended(food.isRecommended());

        existingFood.setDipIds(food.getDipIds());
        existingFood.setAddOnIds(food.getAddOnIds());
        existingFood.setCustomizationIds(food.getCustomizationIds());

        existingFood.setFruitIds(food.getFruitIds());
        return foodRepository.save(existingFood);
    }
}