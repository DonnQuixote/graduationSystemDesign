package com.example.springboottest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Demand {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Integer id;
    private String name;
    private String provider;
    private double price;
    private Integer count;
}
