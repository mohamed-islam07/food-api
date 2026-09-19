package com.example.food_api.controller;

import com.example.food_api.model.Combo;
import com.example.food_api.model.Food;
import com.example.food_api.model.Drink;

import com.example.food_api.repository.ComboRepository;
import com.example.food_api.repository.FoodRepository;
import com.example.food_api.repository.DrinkRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/combos")
public class ComboDetailsController {

    private final ComboRepository comboRepository;
    private final FoodRepository foodRepository;
    private final DrinkRepository drinkRepository;

    public ComboDetailsController(
            ComboRepository comboRepository,
            FoodRepository foodRepository,
            DrinkRepository drinkRepository) {

        this.comboRepository = comboRepository;
        this.foodRepository = foodRepository;
        this.drinkRepository = drinkRepository;
    }

    @GetMapping("/{id}/details")
    public ComboDetailsResponse getComboDetails(@PathVariable Long id) {

        Combo combo = comboRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Combo not found"));

        List<Food> foods = combo.getFoodIds() == null
                ? List.of()
                : combo.getFoodIds().stream()
                .map(foodRepository::findByNumericId)
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .toList();

        List<Drink> drinks = combo.getDrinkIds() == null
                ? List.of()
                : combo.getDrinkIds().stream()
                .map(drinkRepository::findByNumericId)
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .toList();

        return new ComboDetailsResponse(
                combo,
                foods,
                drinks
        );
    }

    public record ComboDetailsResponse(
            Combo combo,
            List<Food> foods,
            List<Drink> drinks
    ) {
    }
}