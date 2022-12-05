package com.example.testapi.service;

import com.example.testapi.entity.Cutlery;
import com.example.testapi.entity.Order;
import com.example.testapi.reposity.CutleryRepository;
import com.example.testapi.reposity.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Cutlery> findAllCutlerys(){
        return (List<Cutlery>) cutleryRepository.findAll();
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
