package com.example.food_api.controller;

import com.example.food_api.model.AddOn;
import com.example.food_api.repository.AddOnRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addons")
public class AddOnController {

    private final AddOnRepository addOnRepository;

    public AddOnController(AddOnRepository addOnRepository) {
        this.addOnRepository = addOnRepository;
    }

    // POST - Add add-ons
    @PostMapping
    public List<AddOn> addAddOns(@RequestBody List<AddOn> addOns) {
        return addOnRepository.saveAll(addOns);
    }

    // GET - Get all add-ons
    @GetMapping
    public List<AddOn> getAllAddOns() {
        return addOnRepository.findAll();
    }

    // PUT - Update add-on using numeric ID
    @PutMapping("/{id}")
    public AddOn updateAddOn(
            @PathVariable Long id,
            @RequestBody AddOn addOn) {

        AddOn existingAddOn = addOnRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Add-on not found"));

        existingAddOn.setName(addOn.getName());
        existingAddOn.setQuantity(addOn.getQuantity());
        existingAddOn.setPrice(addOn.getPrice());

        return addOnRepository.save(existingAddOn);
    }
}