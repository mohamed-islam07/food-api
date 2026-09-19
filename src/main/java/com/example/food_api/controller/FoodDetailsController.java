package com.example.food_api.controller;

import com.example.food_api.model.Food;
import com.example.food_api.model.Dip;
import com.example.food_api.model.AddOn;
import com.example.food_api.model.Customization;

import com.example.food_api.repository.FoodRepository;
import com.example.food_api.repository.DipRepository;
import com.example.food_api.repository.AddOnRepository;
import com.example.food_api.repository.CustomizationRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodDetailsController {

    private final FoodRepository foodRepository;
    private final DipRepository dipRepository;
    private final AddOnRepository addOnRepository;
    private final CustomizationRepository customizationRepository;

    public FoodDetailsController(
            FoodRepository foodRepository,
            DipRepository dipRepository,
            AddOnRepository addOnRepository,
            CustomizationRepository customizationRepository) {

        this.foodRepository = foodRepository;
        this.dipRepository = dipRepository;
        this.addOnRepository = addOnRepository;
        this.customizationRepository = customizationRepository;
    }

    @GetMapping("/{id}/details")
    public FoodDetailsResponse getFoodDetails(@PathVariable Long id) {

        Food food = foodRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        List<Dip> dips = food.getDipIds() == null
                ? List.of()
                : food.getDipIds().stream()
                .map(dipRepository::findByNumericId)
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .toList();

        List<AddOn> addOns = food.getAddOnIds() == null
                ? List.of()
                : food.getAddOnIds().stream()
                .map(addOnRepository::findByNumericId)
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .toList();

        List<Customization> customizations = food.getCustomizationIds() == null
                ? List.of()
                : food.getCustomizationIds().stream()
                .map(customizationRepository::findByNumericId)
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .toList();

        return new FoodDetailsResponse(
                food,
                dips,
                addOns,
                customizations
        );
    }

    public record FoodDetailsResponse(
            Food food,
            List<Dip> dips,
            List<AddOn> addOns,
            List<Customization> customizations
    ) {
    }
}