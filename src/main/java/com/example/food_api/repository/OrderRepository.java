package com.example.food_api.repository;

import com.example.food_api.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

    @Query("{ 'id': ?0 }")
    Optional<Order> findByNumericId(Long id);
}