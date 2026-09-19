package com.example.food_api.controller;

import com.example.food_api.model.Fruit;
import com.example.food_api.repository.FruitRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fruits")
public class FruitController {

    private final FruitRepository fruitRepository;

    public FruitController(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @PostMapping
    public List<Fruit> addFruits(@RequestBody List<Fruit> fruits) {
        return fruitRepository.saveAll(fruits);
    }

    @GetMapping
    public List<Fruit> getAllFruits() {
        return fruitRepository.findAll();
    }

    @PutMapping("/{id}")
    public Fruit updateFruit(
            @PathVariable Long id,
            @RequestBody Fruit fruit) {

        Fruit existingFruit = fruitRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Fruit not found"));

        existingFruit.setName(fruit.getName());
        existingFruit.setPrice(fruit.getPrice());

        return fruitRepository.save(existingFruit);
    }
}