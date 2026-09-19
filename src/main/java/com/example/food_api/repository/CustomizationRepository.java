package com.example.food_api.repository;

import com.example.food_api.model.Customization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CustomizationRepository
        extends MongoRepository<Customization, String> {

    @Query("{ 'id': ?0 }")
    Optional<Customization> findByNumericId(Long id);
}