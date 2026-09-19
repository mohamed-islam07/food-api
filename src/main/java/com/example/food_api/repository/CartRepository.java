package com.example.food_api.repository;

import com.example.food_api.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart, String> {

    @Query("{ 'id': ?0 }")
    Optional<Cart> findByNumericId(Long id);
}