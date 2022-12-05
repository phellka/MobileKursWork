package com.example.testapi.controller;

import com.example.testapi.entity.Cutlery;
import com.example.testapi.service.CutleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CutleryController {
    private final CutleryService cutleryService;

    @GetMapping(value = "api/v1/cutlerys/{id}")
    public ResponseEntity<Cutlery> getCutleryById(@PathVariable Long id) {
        var Cutlery = cutleryService.getCutlery(id);
        return ResponseEntity.ok(Cutlery);
    }

    @GetMapping(value = "api/v1/cutlerys/")
    public List<Cutlery> getCutlerys() {
        return cutleryService.findAllCutlerys();
    }

    @PostMapping(value = "api/v1/cutlerys/")
    public Cutlery createCutlery(@RequestParam("calorie") int calorie,
                                 @RequestParam("wishes") String wishes,
                                 @RequestParam("userlogin") String userlogin,
                                 @RequestParam("orderId") long orderId) {
        return cutleryService.addCutlery(calorie, wishes, userlogin, orderId);
    }

    @PatchMapping("api/v1/cutlerys/{id}")
    public Cutlery updateCutlery(@PathVariable Long id,
                                     @RequestParam("calorie") int calorie,
                                     @RequestParam("wishes") String wishes,
                                     @RequestParam("userlogin") String userlogin,
                                     @RequestParam("orderId") long orderId) {
        return cutleryService.updateCutlery(id, calorie, wishes, userlogin, orderId);
    }

    @DeleteMapping("api/v1/cutlerys/{id}")
    public Cutlery deleteCutlery(@PathVariable Long id) {
        return cutleryService.deleteCutlery(id);
    }
}
