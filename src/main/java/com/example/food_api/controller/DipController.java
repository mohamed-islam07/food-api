package com.example.food_api.controller;

import com.example.food_api.model.Dip;
import com.example.food_api.repository.DipRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dips")
public class DipController {

    private final DipRepository dipRepository;

    public DipController(DipRepository dipRepository) {
        this.dipRepository = dipRepository;
    }

    // POST - Add dip
    @PostMapping
    public List<Dip> addDips(@RequestBody List<Dip> dips) {
        return dipRepository.saveAll(dips);
    }

    // GET - Get all dips
    @GetMapping
    public List<Dip> getAllDips() {
        return dipRepository.findAll();
    }

    // PUT - Update dip using numeric ID
    @PutMapping("/{id}")
    public Dip updateDip(
            @PathVariable Long id,
            @RequestBody Dip dip) {

        Dip existingDip = dipRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Dip not found"));

        existingDip.setName(dip.getName());
        existingDip.setPrice(dip.getPrice());

        return dipRepository.save(existingDip);
    }
}