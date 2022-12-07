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
    public ResponseEntity<Cutlery> getCutleryById(@PathVariable Long id, @RequestParam("userlogin") String userlogin) {
        var Cutlery = cutleryService.getCutlery(id, userlogin);
        return ResponseEntity.ok(Cutlery);
    }

    @GetMapping(value = "api/v1/cutlerys/")
    public List<Cutlery> getCutlerys(@RequestParam("userlogin") String userlogin) {
        return cutleryService.findAllCutlerys(userlogin);
    }

    @GetMapping(value = "api/v1/cutlerys/all/")
    public List<Cutlery> getAllCutlerys(@RequestParam("userlogin") String userlogin) {
        return cutleryService.findAllUsersCutlerys(userlogin);
    }

    @PostMapping(value = "api/v1/cutlerys/")
    public Cutlery createCutlery(@RequestParam("count") int count,
                                 @RequestParam("name") String name,
                                 @RequestParam("userlogin") String userlogin,
                                 @RequestParam("orderId") long orderId) {
        return cutleryService.addCutlery(count, name, userlogin, orderId);
    }

    @PatchMapping("api/v1/cutlerys/{id}")
    public Cutlery updateCutlery(@PathVariable Long id,
                                     @RequestParam("count") int count,
                                     @RequestParam("name") String name,
                                     @RequestParam("userlogin") String userlogin,
                                     @RequestParam("orderId") long orderId) {
        return cutleryService.updateCutlery(id, count, name, userlogin, orderId);
    }

    @DeleteMapping("api/v1/cutlerys/{id}")
    public Cutlery deleteCutlery(@PathVariable Long id, @RequestParam("userlogin") String userlogin) {
        return cutleryService.deleteCutlery(id, userlogin);
    }
}
