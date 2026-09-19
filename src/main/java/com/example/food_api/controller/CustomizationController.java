package com.example.food_api.controller;

import com.example.food_api.model.Customization;
import com.example.food_api.repository.CustomizationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customizations")
public class CustomizationController {

    private final CustomizationRepository customizationRepository;

    public CustomizationController(CustomizationRepository customizationRepository) {
        this.customizationRepository = customizationRepository;
    }

    // POST - Add customizations
    @PostMapping
    public List<Customization> addCustomizations(
            @RequestBody List<Customization> customizations) {
        return customizationRepository.saveAll(customizations);
    }

    // GET - Get all customizations
    @GetMapping
    public List<Customization> getAllCustomizations() {
        return customizationRepository.findAll();
    }

    // PUT - Update customization using numeric ID
    @PutMapping("/{id}")
    public Customization updateCustomization(
            @PathVariable Long id,
            @RequestBody Customization customization) {

        Customization existingCustomization =
                customizationRepository.findByNumericId(id)
                        .orElseThrow(() ->
                                new RuntimeException("Customization not found"));

        existingCustomization.setName(customization.getName());
        existingCustomization.setDescription(customization.getDescription());
        existingCustomization.setPrice(customization.getPrice());

        return customizationRepository.save(existingCustomization);
    }
}