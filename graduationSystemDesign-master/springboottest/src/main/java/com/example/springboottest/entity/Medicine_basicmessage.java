package com.example.springboottest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Medicine_basicmessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicineid;
    private  String name;
    private Date storagedate;
    private Integer count;
    private Integer expirydates;
    private Double purchaseprice;
    private Double unitprice;
    private String provider;
    private Date enddate;
}
