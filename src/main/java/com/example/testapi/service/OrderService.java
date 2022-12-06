package com.example.testapi.service;

import com.example.testapi.entity.Lunch;
import com.example.testapi.entity.Order;
import com.example.testapi.reposity.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order getOrder(Long orderId){
        var order = orderRepository.findById(orderId).orElseThrow(
                () -> new IllegalArgumentException(String.format("Order with id=%s not found", orderId)));
        return order;
    }

    public Order addOrder(int calorie, String wishes, String userLogin){
        final Order order = new Order(calorie, wishes, userLogin);
        return orderRepository.save(order);
    }

    public List<Order> findAllOrders(String userlogin){
        List<Order> list =  (List<Order>) orderRepository.findAll();
        List<Order> retList = new ArrayList<Order>();
        for(Order order : list){
            if (Objects.equals(order.getUserlogin(), userlogin)){
                retList.add(order);
            }
        }
        return retList;
    }

    public Order updateOrder(Long id, int calorie, String wishes, String userLogin){
        final Order currentOrder = getOrder(id);
        currentOrder.setCalorie(calorie);
        currentOrder.setWishes(wishes);
        currentOrder.setUserlogin(userLogin);
        return orderRepository.save(currentOrder);
    }

    public Order deleteOrder(Long id){
        final Order currentOrder = getOrder(id);
        orderRepository.delete(currentOrder);
        return currentOrder;
    }
}
