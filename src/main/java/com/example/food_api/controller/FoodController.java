package com.example.food_api.controller;

import com.example.food_api.model.Food;
import com.example.food_api.repository.FoodRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodRepository foodRepository;

    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GetMapping
    public List<Food> getAll() {
        return foodRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getById(@PathVariable String id) {
        return foodRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Food create(@RequestBody Food food) {
        food.setId(null);
        return foodRepository.save(food);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> update(@PathVariable String id, @RequestBody Food updated) {
        return foodRepository.findById(id)
                .map(existing -> {
                    existing.setName(updated.getName());
                    existing.setPrice(updated.getPrice());
                    return ResponseEntity.ok(foodRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!foodRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        foodRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
