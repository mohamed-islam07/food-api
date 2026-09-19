package com.example.food_api.controller;

import com.example.food_api.model.Combo;
import com.example.food_api.repository.ComboRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/combos")
public class ComboController {

    private final ComboRepository comboRepository;

    public ComboController(ComboRepository comboRepository) {
        this.comboRepository = comboRepository;
    }

    // POST - Add combo
    @PostMapping
    public List<Combo> addCombos(@RequestBody List<Combo> combos) {
        return comboRepository.saveAll(combos);
    }

    // GET - Get all combos
    @GetMapping
    public List<Combo> getAllCombos() {
        return comboRepository.findAll();
    }

    // PUT - Update combo using numeric ID
    @PutMapping("/{id}")
    public Combo updateCombo(@PathVariable Long id, @RequestBody Combo combo) {

        Combo existingCombo = comboRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Combo not found"));

        existingCombo.setName(combo.getName());
        existingCombo.setDescription(combo.getDescription());
        existingCombo.setPrice(combo.getPrice());

        existingCombo.setFoodIds(combo.getFoodIds());
        existingCombo.setDrinkIds(combo.getDrinkIds());

        return comboRepository.save(existingCombo);
    }
}