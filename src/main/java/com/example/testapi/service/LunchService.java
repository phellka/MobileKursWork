package com.example.testapi.service;

import com.example.testapi.entity.Lunch;
import com.example.testapi.entity.Order;
import com.example.testapi.reposity.LunchRepository;
import com.example.testapi.reposity.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LunchService {
    private final LunchRepository LunchRepository;
    private final OrderService orderService;

    public Lunch getLunch(Long LunchId){
        var Lunch = LunchRepository.findById(LunchId).orElseThrow(
                () -> new IllegalArgumentException(String.format("Lunch with id=%s not found", LunchId)));
        return Lunch;
    }

    public Lunch addLunch(int price, int weight, String userLogin, Order order){
        final Lunch Lunch = new Lunch(price, weight, userLogin, order);
        return LunchRepository.save(Lunch);
    }

    public Lunch addLunch(int price, int weight, String userLogin, Long order){
        final Lunch Lunch = new Lunch(price, weight, userLogin, orderService.getOrder(order));
        return LunchRepository.save(Lunch);
    }

    public List<Lunch> findAllLunchs(String userlogin){
        List<Lunch> list =  (List<Lunch>) LunchRepository.findAll();
        List<Lunch> retList = new ArrayList<Lunch>();
        for(Lunch lunch : list){
            if (Objects.equals(lunch.getUserlogin(), userlogin)){
                retList.add(lunch);
            }
        }
        return retList;
    }

    public Lunch updateLunch(Long id, int price, int weight, String userLogin, Order order){
        final Lunch currentLunch = getLunch(id);
        currentLunch.setPrice(price);
        currentLunch.setWeight(weight);
        currentLunch.setUserlogin(userLogin);
        currentLunch.setOrder(order);
        return LunchRepository.save(currentLunch);
    }
    public Lunch updateLunch(Long id, int price, int weight, String userLogin, Long order){
        final Lunch currentLunch = getLunch(id);
        currentLunch.setPrice(price);
        currentLunch.setWeight(weight);
        currentLunch.setUserlogin(userLogin);
        currentLunch.setOrder(orderService.getOrder(order));
        return LunchRepository.save(currentLunch);
    }

    public Lunch deleteLunch(Long id){
        final Lunch currentLunch = getLunch(id);
        LunchRepository.delete(currentLunch);
        return currentLunch;
    }
}
