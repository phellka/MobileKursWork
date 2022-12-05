package com.example.testapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lunch")
@Getter
@Setter
public class Lunch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int price;
    private int weight;


    @CreationTimestamp
    @Column(name = "date", columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    private String userlogin;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Lunch(){}

    public Lunch(int price, int weight, String userLogin, Order order){
        this.price = price;
        this.weight = weight;
        this.userlogin = userLogin;
        this.order = order;
    }
}
