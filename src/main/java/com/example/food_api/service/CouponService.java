package com.example.food_api.service;

import com.example.food_api.model.Coupon;
import com.example.food_api.repository.CouponRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {

    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public Coupon saveCoupon(Coupon coupon) {

        if (coupon.getCode() == null
                || coupon.getCode().trim().isEmpty()) {

            throw new RuntimeException("Coupon code is required");
        }

        if (couponRepository
                .findByCodeIgnoreCase(coupon.getCode())
                .isPresent()) {

            throw new RuntimeException("Coupon code already exists");
        }

        long nextCouponId = couponRepository.findAll()
                .stream()
                .map(Coupon::getId)
                .filter(id -> id != null)
                .max(Long::compareTo)
                .orElse(0L) + 1;

        coupon.setId(nextCouponId);

        coupon.setCode(coupon.getCode().trim().toUpperCase());

        return couponRepository.save(coupon);
    }

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    public Coupon getCoupon(Long id) {

        return couponRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
    }

    public Coupon getCouponByCode(String code) {

        return couponRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
    }

    public Coupon updateCoupon(Long id, Coupon updatedCoupon) {

        Coupon coupon = couponRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        coupon.setCode(updatedCoupon.getCode().trim().toUpperCase());
        coupon.setDiscountType(updatedCoupon.getDiscountType());
        coupon.setDiscountValue(updatedCoupon.getDiscountValue());
        coupon.setMinimumOrderAmount(
                updatedCoupon.getMinimumOrderAmount());
        coupon.setMaximumDiscount(
                updatedCoupon.getMaximumDiscount());
        coupon.setActive(updatedCoupon.isActive());
        coupon.setUsageLimit(updatedCoupon.getUsageLimit());
        coupon.setUsedCount(updatedCoupon.getUsedCount());

        return couponRepository.save(coupon);
    }

    public void deleteCoupon(Long id) {

        Coupon coupon = couponRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        couponRepository.delete(coupon);
    }

    public double calculateDiscount(
            Coupon coupon,
            double subtotal) {

        if (!coupon.isActive()) {
            throw new RuntimeException("Coupon is inactive");
        }

        if (coupon.getUsageLimit() > 0
                && coupon.getUsedCount() >= coupon.getUsageLimit()) {

            throw new RuntimeException(
                    "Coupon usage limit reached");
        }

        if (subtotal < coupon.getMinimumOrderAmount()) {

            throw new RuntimeException(
                    "Minimum order amount is ₹"
                            + coupon.getMinimumOrderAmount());
        }

        double discount;

        if ("PERCENTAGE".equalsIgnoreCase(
                coupon.getDiscountType())) {

            discount = subtotal
                    * coupon.getDiscountValue()
                    / 100;

        } else if ("FIXED".equalsIgnoreCase(
                coupon.getDiscountType())) {

            discount = coupon.getDiscountValue();

        } else {

            throw new RuntimeException(
                    "Invalid discount type");
        }

        if (coupon.getMaximumDiscount() > 0
                && discount > coupon.getMaximumDiscount()) {

            discount = coupon.getMaximumDiscount();
        }

        if (discount > subtotal) {
            discount = subtotal;
        }

        return discount;
    }

    public List<Coupon> getEligibleCoupons(double subtotal) {

        List<Coupon> eligibleCoupons = new java.util.ArrayList<>();

        for (Coupon coupon : couponRepository.findAll()) {

            if (!coupon.isActive()) {
                continue;
            }

            if (coupon.getUsageLimit() > 0
                    && coupon.getUsedCount() >= coupon.getUsageLimit()) {
                continue;
            }

            if (subtotal >= coupon.getMinimumOrderAmount()) {
                eligibleCoupons.add(coupon);
            }
        }

        return eligibleCoupons;
    }

    public Coupon getNextCoupon(double subtotal) {

        return couponRepository.findAll()
                .stream()
                .filter(Coupon::isActive)
                .filter(coupon -> coupon.getMinimumOrderAmount() > subtotal)
                .filter(coupon -> coupon.getUsageLimit() == 0
                        || coupon.getUsedCount() < coupon.getUsageLimit())
                .min((coupon1, coupon2) -> Double.compare(
                        coupon1.getMinimumOrderAmount(),
                        coupon2.getMinimumOrderAmount()))
                .orElse(null);
    }
}