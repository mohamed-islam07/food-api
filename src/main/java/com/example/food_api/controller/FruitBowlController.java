package com.example.food_api.controller;

import com.example.food_api.model.FruitBowl;
import com.example.food_api.repository.FruitBowlRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fruit-bowls")
public class FruitBowlController {

    private final FruitBowlRepository fruitBowlRepository;

    public FruitBowlController(FruitBowlRepository fruitBowlRepository) {
        this.fruitBowlRepository = fruitBowlRepository;
    }

    @PostMapping
    public FruitBowl addFruitBowl(@RequestBody FruitBowl fruitBowl) {
        return fruitBowlRepository.save(fruitBowl);
    }

    @GetMapping
    public List<FruitBowl> getAllFruitBowls() {
        return fruitBowlRepository.findAll();
    }

    @PutMapping("/{id}")
    public FruitBowl updateFruitBowl(
            @PathVariable Long id,
            @RequestBody FruitBowl fruitBowl) {

        FruitBowl existingFruitBowl = fruitBowlRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Fruit bowl not found"));

        existingFruitBowl.setFoodId(fruitBowl.getFoodId());
        existingFruitBowl.setSelectedFruitIds(fruitBowl.getSelectedFruitIds());

        return fruitBowlRepository.save(existingFruitBowl);
    }
}