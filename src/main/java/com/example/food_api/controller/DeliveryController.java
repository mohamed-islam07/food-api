package com.example.food_api.controller;

import com.example.food_api.model.Delivery;
import com.example.food_api.repository.DeliveryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;

    public DeliveryController(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    // POST - Add delivery settings
    @PostMapping
    public Delivery addDelivery(@RequestBody Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    // GET - Get delivery settings
    @GetMapping
    public List<Delivery> getAllDelivery() {
        return deliveryRepository.findAll();
    }

    // PUT - Update delivery settings
    @PutMapping("/{id}")
    public Delivery updateDelivery(
            @PathVariable Long id,
            @RequestBody Delivery delivery) {

        Delivery existingDelivery = deliveryRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Delivery settings not found"));

        existingDelivery.setAvailable(delivery.isAvailable());
        existingDelivery.setDeliveryCharge(delivery.getDeliveryCharge());
        existingDelivery.setFreeDeliveryAbove(
                delivery.getFreeDeliveryAbove()
        );
        existingDelivery.setEstimatedTime(
                delivery.getEstimatedTime()
        );
        existingDelivery.setNote(delivery.getNote());

        return deliveryRepository.save(existingDelivery);
    }
}