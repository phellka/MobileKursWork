package com.example.testapi.controller;

import com.example.testapi.entity.Lunch;
import com.example.testapi.service.LunchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LunchController {
    private final LunchService lunchService;

    @GetMapping(value = "api/v1/lunch/{id}")
    public ResponseEntity<Lunch> getLunchById(@PathVariable Long id, @RequestParam("userlogin") String userlogin) {
        var lunch = lunchService.getLunch(id, userlogin);
        return ResponseEntity.ok(lunch);
    }

    @GetMapping(value = "api/v1/lunch/")
    public List<Lunch> getLunches(@RequestParam("userlogin") String userlogin) {
        return lunchService.findAllLunchs(userlogin);
    }

    @GetMapping(value = "api/v1/lunch/all/")
    public List<Lunch> getAllLunches(@RequestParam("userlogin") String userlogin) {
        return lunchService.findAllUsersLunchs(userlogin);
    }

    @PostMapping(value = "api/v1/lunch/")
    public Lunch createLunch(@RequestParam("price") int price,
                               @RequestParam("weight") int weight,
                               @RequestParam("userlogin") String userlogin,
                               @RequestParam("orderId") long orderId) {
        return lunchService.addLunch(price, weight, userlogin, orderId);
    }

    @PatchMapping("api/v1/lunch/{id}")
    public Lunch updateLunch(@PathVariable Long id,
                                       @RequestParam("price") int price,
                                       @RequestParam("weight") int weight,
                                       @RequestParam("userlogin") String userlogin,
                                       @RequestParam("orderId") long orderId) {
        return lunchService.updateLunch(id, price, weight, userlogin, orderId);
    }

    @DeleteMapping("api/v1/lunch/{id}")
    public Lunch deleteLunch(@PathVariable Long id, @RequestParam("userlogin") String userlogin) {
        return lunchService.deleteLunch(id, userlogin);
    }
}
