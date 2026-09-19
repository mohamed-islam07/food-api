package com.example.food_api.repository;

import com.example.food_api.model.Timing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface TimingRepository extends MongoRepository<Timing, String> {

    @Query("{ 'id': ?0 }")
    Optional<Timing> findByNumericId(Long id);
}