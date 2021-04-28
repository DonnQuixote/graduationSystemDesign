package com.example.springboottest.entity;

import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Drug_loss_report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date storagedate;
    private Date enddate;
    private  Integer count;
    private  String provider;
    private Integer type;
    private String reason;
    private Double unitprice;
    private Double purchaseprice;
    private  Integer expirydates;
}
