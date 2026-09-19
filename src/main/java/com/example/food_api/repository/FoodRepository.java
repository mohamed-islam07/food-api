package com.example.food_api.repository;

import com.example.food_api.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends MongoRepository<Food, String> {

    List<Food> findByCategoryId(Long categoryId);

    List<Food> findByRecommendedTrue();

    @Query("{ 'id': ?0 }")
    Optional<Food> findByNumericId(Long id);
}