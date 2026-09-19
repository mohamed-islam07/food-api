package com.example.food_api.controller;

import com.example.food_api.model.Favorite;
import com.example.food_api.repository.FavoriteRepository;
import com.example.food_api.service.FavoriteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final FavoriteRepository favoriteRepository;

    public FavoriteController(
            FavoriteService favoriteService,
            FavoriteRepository favoriteRepository) {

        this.favoriteService = favoriteService;
        this.favoriteRepository = favoriteRepository;
    }

    @PostMapping("/{foodId}")
    public Favorite addFavorite(@PathVariable Long foodId) {
        return favoriteService.addFavorite(foodId);
    }

    @GetMapping
    public List<Favorite> getAllFavorites() {
        return favoriteService.getAllFavorites();
    }

    @GetMapping("/{id}")
    public Favorite getFavorite(@PathVariable Long id) {
        return favoriteRepository.findByNumericId(id)
                .orElseThrow(() ->
                        new RuntimeException("Favorite not found"));
    }

    @DeleteMapping("/{foodId}")
    public String removeFavorite(@PathVariable Long foodId) {
        favoriteService.removeFavorite(foodId);
        return "Favorite removed successfully";
    }
}