package com.example.food_api.repository;

import com.example.food_api.model.Combo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ComboRepository extends MongoRepository<Combo, String> {

    @Query("{ 'id': ?0 }")
    Optional<Combo> findByNumericId(Long id);
}