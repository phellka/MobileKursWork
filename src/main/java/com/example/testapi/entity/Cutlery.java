package com.example.testapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cutlery")
@Getter
@Setter
public class Cutlery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int count;
    private String name;
    private String userlogin;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Cutlery(){}

    public Cutlery(int count, String name, String userLogin, Order order){
        this.count = count;
        this.name = name;
        this.userlogin = userLogin;
        this.order = order;
    }
}
