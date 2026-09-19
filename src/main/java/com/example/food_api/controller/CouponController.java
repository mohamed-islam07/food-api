package com.example.food_api.controller;

import com.example.food_api.model.Coupon;
import com.example.food_api.service.CouponService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    public Coupon addCoupon(@RequestBody Coupon coupon) {
        return couponService.saveCoupon(coupon);
    }

    @GetMapping
    public List<Coupon> getAllCoupons() {
        return couponService.getAllCoupons();
    }

    @GetMapping("/{id}")
    public Coupon getCoupon(@PathVariable Long id) {
        return couponService.getCoupon(id);
    }

    @GetMapping("/code/{code}")
    public Coupon getCouponByCode(@PathVariable String code) {
        return couponService.getCouponByCode(code);
    }

    @PutMapping("/{id}")
    public Coupon updateCoupon(
            @PathVariable Long id,
            @RequestBody Coupon coupon) {

        return couponService.updateCoupon(id, coupon);
    }

    @DeleteMapping("/{id}")
    public String deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return "Coupon deleted successfully";
    }

    @GetMapping("/suggestions")
public java.util.Map<String, Object> getCouponSuggestions(
        @RequestParam double subtotal) {

    List<Coupon> eligibleCoupons =
            couponService.getEligibleCoupons(subtotal);

    Coupon nextCoupon =
            couponService.getNextCoupon(subtotal);

    java.util.Map<String, Object> response =
            new java.util.HashMap<>();

    response.put("subtotal", subtotal);
    response.put("availableCoupons", eligibleCoupons);

    if (nextCoupon != null) {

        double amountNeeded =
                nextCoupon.getMinimumOrderAmount() - subtotal;

        response.put("nextCoupon", nextCoupon);
        response.put("amountNeeded", amountNeeded);

    } else {

        response.put("nextCoupon", null);
        response.put("amountNeeded", 0);
    }

    return response;
}
}