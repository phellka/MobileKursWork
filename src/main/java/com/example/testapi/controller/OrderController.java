package com.example.testapi.controller;

import com.example.testapi.entity.Order;
import com.example.testapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping(value = "api/v1/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        var order = orderService.getOrder(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping(value = "api/v1/orders/")
    public Order createOrder(@RequestParam("calorie") int calorie,
                             @RequestParam("wishes") String wishes,
                             @RequestParam("userlogin") String userlogin) {
        return orderService.addOrder(calorie, wishes, userlogin);
    }

    @GetMapping(value = "api/v1/orders/")
    public List<Order> getOrders(@RequestParam("userlogin") String userlogin) {
        return orderService.findAllOrders(userlogin);
    }

    @PatchMapping("api/v1/orders/{id}")
    public Order updateQualification(@PathVariable Long id,
                                     @RequestParam("calorie") int calorie,
                                     @RequestParam("wishes") String wishes,
                                     @RequestParam("userlogin") String userlogin) {
        return orderService.updateOrder(id, calorie, wishes, userlogin);
    }

    @DeleteMapping("api/v1/orders/{id}")
    public Order deleteQualification(@PathVariable Long id) {
        return orderService.deleteOrder(id);
    }
}
