package com.example.testapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int calorie;
    private String wishes;
    private String userlogin;

    public Order(){

    }

    public Order(int calorie, String wishes, String userLogin){
        this.calorie = calorie;
        this.wishes = wishes;
        this.userlogin = userLogin;
    }
}
