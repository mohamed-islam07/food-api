package com.example.food_api.repository;

import com.example.food_api.model.Drink;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface DrinkRepository extends MongoRepository<Drink, String> {

    @Query("{ 'id': ?0 }")
    Optional<Drink> findByNumericId(Long id);
}