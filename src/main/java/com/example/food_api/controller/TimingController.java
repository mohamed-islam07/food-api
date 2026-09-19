package com.example.food_api.controller;

import com.example.food_api.model.Timing;
import com.example.food_api.repository.TimingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timings")
public class TimingController {

    private final TimingRepository timingRepository;

    public TimingController(TimingRepository timingRepository) {
        this.timingRepository = timingRepository;
    }

    // POST - Add timing
    @PostMapping
    public Timing addTiming(@RequestBody Timing timing) {
        return timingRepository.save(timing);
    }

    // GET - Get all timings
    @GetMapping
    public List<Timing> getAllTimings() {
        return timingRepository.findAll();
    }

    // PUT - Update timing
    @PutMapping("/{id}")
    public Timing updateTiming(
            @PathVariable Long id,
            @RequestBody Timing timing) {

        Timing existingTiming = timingRepository.findByNumericId(id)
                .orElseThrow(() -> new RuntimeException("Timing not found"));

        existingTiming.setDays(timing.getDays());
        existingTiming.setTime(timing.getTime());
        existingTiming.setClosed(timing.isClosed());
        existingTiming.setHolidayReason(timing.getHolidayReason());
        existingTiming.setNotice(timing.getNotice());

        return timingRepository.save(existingTiming);
    }
}