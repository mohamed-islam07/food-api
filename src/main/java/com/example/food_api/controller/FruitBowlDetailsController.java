package com.example.food_api.controller;

import com.example.food_api.model.Fruit;
import com.example.food_api.model.FruitBowl;
import com.example.food_api.repository.FruitBowlRepository;
import com.example.food_api.repository.FruitRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fruit-bowls")
public class FruitBowlDetailsController {

    private final FruitBowlRepository fruitBowlRepository;
    private final FruitRepository fruitRepository;

    public FruitBowlDetailsController(
            FruitBowlRepository fruitBowlRepository,
            FruitRepository fruitRepository) {

        this.fruitBowlRepository = fruitBowlRepository;
        this.fruitRepository = fruitRepository;
    }

    @GetMapping("/{id}/details")
    public FruitBowlDetailsResponse getFruitBowlDetails(
            @PathVariable Long id) {

        FruitBowl fruitBowl = fruitBowlRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Fruit bowl not found"));

        List<Fruit> fruits = fruitBowl.getSelectedFruitIds() == null
                ? List.of()
                : fruitBowl.getSelectedFruitIds().stream()
                .map(fruitRepository::findByNumericId)
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .toList();

        return new FruitBowlDetailsResponse(
                fruitBowl,
                fruits
        );
    }

    public record FruitBowlDetailsResponse(
            FruitBowl fruitBowl,
            List<Fruit> selectedFruits
    ) {
    }
}