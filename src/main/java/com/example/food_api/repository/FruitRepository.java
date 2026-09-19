package com.example.food_api.repository;

import com.example.food_api.model.Fruit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface FruitRepository extends MongoRepository<Fruit, String> {

    @Query("{ 'id': ?0 }")
    Optional<Fruit> findByNumericId(Long id);
}