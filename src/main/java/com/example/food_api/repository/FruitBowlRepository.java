package com.example.food_api.repository;

import com.example.food_api.model.FruitBowl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface FruitBowlRepository extends MongoRepository<FruitBowl, String> {

    @Query("{ 'id': ?0 }")
    Optional<FruitBowl> findByNumericId(Long id);
}