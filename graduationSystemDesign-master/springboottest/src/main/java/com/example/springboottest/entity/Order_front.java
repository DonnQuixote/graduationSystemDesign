package com.example.springboottest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Order_front {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oid;
    private String orderstate;
    private Date ordertime;
    private Double totalprice;
}
