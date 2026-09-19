package com.example.food_api.repository;

import com.example.food_api.model.AddOn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface AddOnRepository extends MongoRepository<AddOn, String> {

    @Query("{ 'id': ?0 }")
    Optional<AddOn> findByNumericId(Long id);
}