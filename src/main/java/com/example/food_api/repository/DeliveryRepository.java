package com.example.food_api.repository;

import com.example.food_api.model.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {

    @Query("{ 'id': ?0 }")
    Optional<Delivery> findByNumericId(Long id);
}