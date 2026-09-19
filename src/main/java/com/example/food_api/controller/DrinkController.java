package com.example.food_api.controller;

import com.example.food_api.model.Drink;
import com.example.food_api.repository.DrinkRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {

    private final DrinkRepository drinkRepository;

    public DrinkController(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    // POST - Add drink
    @PostMapping
    public List<Drink> addDrinks(@RequestBody List<Drink> drinks) {
        return drinkRepository.saveAll(drinks);
    }

    // GET - Get all drinks
    @GetMapping
    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    // PUT - Update drink using numeric ID
    @PutMapping("/{id}")
    public Drink updateDrink(
            @PathVariable Long id,
            @RequestBody Drink drink) {

        Drink existingDrink = drinkRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Drink not found"));

        existingDrink.setName(drink.getName());
        existingDrink.setPrices(drink.getPrices());

        return drinkRepository.save(existingDrink);
    }
}