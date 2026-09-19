package com.example.food_api.service;

import com.example.food_api.model.Favorite;
import com.example.food_api.model.Food;
import com.example.food_api.repository.FavoriteRepository;
import com.example.food_api.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final FoodRepository foodRepository;

    public FavoriteService(
            FavoriteRepository favoriteRepository,
            FoodRepository foodRepository) {

        this.favoriteRepository = favoriteRepository;
        this.foodRepository = foodRepository;
    }

    public Favorite addFavorite(Long foodId) {

        Food food = foodRepository.findByNumericId(foodId)
                .orElseThrow(() ->
                        new RuntimeException("Food not found"));

        if (favoriteRepository.findByFoodId(foodId).isPresent()) {
            throw new RuntimeException("Food is already in favorites");
        }

        long nextFavoriteId = favoriteRepository.findAll()
                .stream()
                .map(Favorite::getId)
                .filter(id -> id != null)
                .max(Long::compareTo)
                .orElse(0L) + 1;

        Favorite favorite = new Favorite();

        favorite.setId(nextFavoriteId);
        favorite.setFoodId(food.getId());
        favorite.setFoodName(food.getName());

        return favoriteRepository.save(favorite);
    }

    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    public void removeFavorite(Long foodId) {

        Favorite favorite = favoriteRepository.findByFoodId(foodId)
                .orElseThrow(() ->
                        new RuntimeException("Favorite not found"));

        favoriteRepository.delete(favorite);
    }
}