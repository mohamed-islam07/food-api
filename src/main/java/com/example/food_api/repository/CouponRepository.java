package com.example.food_api.repository;

import com.example.food_api.model.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CouponRepository extends MongoRepository<Coupon, String> {

    @Query("{ 'id': ?0 }")
    Optional<Coupon> findByNumericId(Long id);

    Optional<Coupon> findByCodeIgnoreCase(String code);
}