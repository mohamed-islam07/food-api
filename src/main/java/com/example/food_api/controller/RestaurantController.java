package com.example.food_api.controller;

import com.example.food_api.model.Restaurant;
import com.example.food_api.repository.RestaurantRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // GET - Get restaurant details
    @GetMapping
    public List<Restaurant> getRestaurant() {
        return restaurantRepository.findAll();
    }

    // PUT - Update restaurant details
    @PutMapping
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant) {

        List<Restaurant> restaurants = restaurantRepository.findAll();

        if (restaurants.isEmpty()) {
            return restaurantRepository.save(restaurant);
        }

        Restaurant existingRestaurant = restaurants.get(0);

        existingRestaurant.setName(restaurant.getName());
        existingRestaurant.setTagline(restaurant.getTagline());
        existingRestaurant.setStatus(restaurant.getStatus());
        existingRestaurant.setPhone(restaurant.getPhone());
        existingRestaurant.setInstagram(restaurant.getInstagram());
        existingRestaurant.setCustomizationAvailable(
                restaurant.isCustomizationAvailable()
        );

        return restaurantRepository.save(existingRestaurant);
    }
}