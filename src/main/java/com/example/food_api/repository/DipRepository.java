package com.example.food_api.repository;

import com.example.food_api.model.Dip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface DipRepository extends MongoRepository<Dip, String> {

    @Query("{ 'id': ?0 }")
    Optional<Dip> findByNumericId(Long id);
}