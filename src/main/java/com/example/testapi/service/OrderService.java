package com.example.testapi.service;

import com.example.testapi.dto.UserDto;
import com.example.testapi.entity.Lunch;
import com.example.testapi.entity.Order;
import com.example.testapi.entity.User;
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
    private final UserService userService;

    public Order getOrder(Long orderId, String login){
        List<Order> list = findAllOrders(login);
        for( Order order : list){
            if (order.getId() == orderId && Objects.equals(order.getUserlogin(), login)){
                return order;
            }
        }
        return null;
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

    public List<Order> findAllUsersOrders(String userlogin){
        List<Order> list =  (List<Order>) orderRepository.findAll();
        UserDto user = userService.getUser(userlogin);
        if (user == null){
            return null;
        }
        if (Objects.equals(user.getRole(), "admin")){
            return list;
        }
        List<Order> retList = new ArrayList<Order>();
        for(Order order : list){
            if (Objects.equals(order.getUserlogin(), userlogin)){
                retList.add(order);
            }
        }
        return retList;
    }

    public Order updateOrder(Long id, int calorie, String wishes, String userLogin){
        final Order currentOrder = getOrder(id, userLogin);
        if (currentOrder == null){
            return null;
        }
        currentOrder.setCalorie(calorie);
        currentOrder.setWishes(wishes);
        currentOrder.setUserlogin(userLogin);
        return orderRepository.save(currentOrder);
    }

    public Order deleteOrder(Long id, String login){
        final Order currentOrder = getOrder(id, login);
        if (currentOrder == null){
            return null;
        }
        orderRepository.delete(currentOrder);
        return currentOrder;
    }
}
