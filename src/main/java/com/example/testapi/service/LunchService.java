package com.example.testapi.service;

import com.example.testapi.dto.UserDto;
import com.example.testapi.entity.Cutlery;
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
    private final UserService userService;

    public Lunch getLunch(Long LunchId, String login){
        List<Lunch> list = findAllLunchs(login);
        for( Lunch lunch : list){
            if (lunch.getId() == LunchId && Objects.equals(lunch.getUserlogin(), login)){
                return lunch;
            }
        }
        return null;
    }

    public Lunch addLunch(int price, int weight, String userLogin, Order order){
        final Lunch Lunch = new Lunch(price, weight, userLogin, order);
        return LunchRepository.save(Lunch);
    }

    public Lunch addLunch(int price, int weight, String userLogin, Long order){
        final Lunch Lunch = new Lunch(price, weight, userLogin, orderService.getOrder(order, userLogin));
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

    public List<Lunch> findAllUsersLunchs(String userlogin){
        List<Lunch> list =  (List<Lunch>) LunchRepository.findAll();
        UserDto user = userService.getUser(userlogin);
        if (user == null){
            return null;
        }
        if (Objects.equals(user.getRole(), "admin")){
            return list;
        }
        List<Lunch> retList = new ArrayList<Lunch>();
        for(Lunch lunch : list){
            if (Objects.equals(lunch.getUserlogin(), userlogin)){
                retList.add(lunch);
            }
        }
        return retList;
    }

    public Lunch updateLunch(Long id, int price, int weight, String userLogin, Order order){
        final Lunch currentLunch = getLunch(id, userLogin);
        if(currentLunch == null){
            return null;
        }
        currentLunch.setPrice(price);
        currentLunch.setWeight(weight);
        currentLunch.setUserlogin(userLogin);
        currentLunch.setOrder(order);
        return LunchRepository.save(currentLunch);
    }
    public Lunch updateLunch(Long id, int price, int weight, String userLogin, Long order){
        final Lunch currentLunch = getLunch(id, userLogin);
        if(currentLunch == null){
            return null;
        }
        currentLunch.setPrice(price);
        currentLunch.setWeight(weight);
        currentLunch.setUserlogin(userLogin);
        currentLunch.setOrder(orderService.getOrder(order, userLogin));
        return LunchRepository.save(currentLunch);
    }

    public Lunch deleteLunch(Long id, String login){
        final Lunch currentLunch = getLunch(id, login);
        if(currentLunch == null){
            return null;
        }
        LunchRepository.delete(currentLunch);
        return currentLunch;
    }
}
