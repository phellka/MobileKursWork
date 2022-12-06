package com.example.testapi.service;

import com.example.testapi.entity.Cutlery;
import com.example.testapi.entity.Order;
import com.example.testapi.reposity.CutleryRepository;
import com.example.testapi.reposity.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CutleryService {
    private final CutleryRepository cutleryRepository;
    private final OrderService orderService;

    public Cutlery getCutlery(Long CutleryId){
        var Cutlery = cutleryRepository.findById(CutleryId).orElseThrow(
                () -> new IllegalArgumentException(String.format("Cutlery with id=%s not found", CutleryId)));
        return Cutlery;
    }

    public Cutlery addCutlery(int count, String name, String userLogin, Order order){
        final Cutlery Cutlery = new Cutlery(count, name, userLogin, order);
        return cutleryRepository.save(Cutlery);
    }

    public Cutlery addCutlery(int count, String name, String userLogin, Long order){
        final Cutlery Cutlery = new Cutlery(count, name, userLogin, orderService.getOrder(order));
        return cutleryRepository.save(Cutlery);
    }

    public List<Cutlery> findAllCutlerys(String userlogin){
        List<Cutlery> list =  (List<Cutlery>) cutleryRepository.findAll();
        List<Cutlery> retList = new ArrayList<Cutlery>();
        for(Cutlery cutlery : list){
            if (Objects.equals(cutlery.getUserlogin(), userlogin)){
                retList.add(cutlery);
            }
        }
        return retList;
    }

    public Cutlery updateCutlery(Long id, int count, String name, String userLogin, Order order){
        final Cutlery currentCutlery = getCutlery(id);
        currentCutlery.setCount(count);
        currentCutlery.setName(name);
        currentCutlery.setUserlogin(userLogin);
        currentCutlery.setOrder(order);
        return cutleryRepository.save(currentCutlery);
    }
    public Cutlery updateCutlery(Long id, int count, String name, String userLogin, long order){
        final Cutlery currentCutlery = getCutlery(id);
        currentCutlery.setCount(count);
        currentCutlery.setName(name);
        currentCutlery.setUserlogin(userLogin);
        currentCutlery.setOrder(orderService.getOrder(order));
        return cutleryRepository.save(currentCutlery);
    }

    public Cutlery deleteCutlery(Long id){
        final Cutlery currentCutlery = getCutlery(id);
        cutleryRepository.delete(currentCutlery);
        return currentCutlery;
    }
}
