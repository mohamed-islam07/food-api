package com.example.food_api.repository;

import com.example.food_api.model.Favorite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface FavoriteRepository extends MongoRepository<Favorite, String> {

    @Query("{ 'id': ?0 }")
    Optional<Favorite> findByNumericId(Long id);

    @Query("{ 'foodId': ?0 }")
    Optional<Favorite> findByFoodId(Long foodId);
}