package com.example.testapi.service;

import com.example.testapi.entity.Lunch;
import com.example.testapi.entity.Order;
import com.example.testapi.reposity.LunchRepository;
import com.example.testapi.reposity.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<Lunch> findAllLunchs(){
        return (List<Lunch>) LunchRepository.findAll();
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
