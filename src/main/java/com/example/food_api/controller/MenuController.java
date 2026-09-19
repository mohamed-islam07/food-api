package com.example.food_api.controller;

import com.example.food_api.model.*;
import com.example.food_api.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;
    private final FoodRepository foodRepository;
    private final DipRepository dipRepository;
    private final AddOnRepository addOnRepository;
    private final CustomizationRepository customizationRepository;
    private final FruitRepository fruitRepository;
    private final FruitBowlRepository fruitBowlRepository;
    private final DrinkRepository drinkRepository;
    private final ComboRepository comboRepository;
    private final TimingRepository timingRepository;
    private final DeliveryRepository deliveryRepository;

    public MenuController(
            RestaurantRepository restaurantRepository,
            CategoryRepository categoryRepository,
            FoodRepository foodRepository,
            DipRepository dipRepository,
            AddOnRepository addOnRepository,
            CustomizationRepository customizationRepository,
            FruitRepository fruitRepository,
            FruitBowlRepository fruitBowlRepository,
            DrinkRepository drinkRepository,
            ComboRepository comboRepository,
            TimingRepository timingRepository,
            DeliveryRepository deliveryRepository) {

        this.restaurantRepository = restaurantRepository;
        this.categoryRepository = categoryRepository;
        this.foodRepository = foodRepository;
        this.dipRepository = dipRepository;
        this.addOnRepository = addOnRepository;
        this.customizationRepository = customizationRepository;
        this.fruitRepository = fruitRepository;
        this.fruitBowlRepository = fruitBowlRepository;
        this.drinkRepository = drinkRepository;
        this.comboRepository = comboRepository;
        this.timingRepository = timingRepository;
        this.deliveryRepository = deliveryRepository;
    }

    @GetMapping
    public MenuResponse getMenu() {

        return new MenuResponse(
                restaurantRepository.findAll(),
                categoryRepository.findAll(),
                foodRepository.findAll(),
                dipRepository.findAll(),
                addOnRepository.findAll(),
                customizationRepository.findAll(),
                fruitRepository.findAll(),
                fruitBowlRepository.findAll(),
                drinkRepository.findAll(),
                comboRepository.findAll(),
                timingRepository.findAll(),
                deliveryRepository.findAll()
        );
    }
}