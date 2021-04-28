package com.example.springboottest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Drug_entered {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Integer eid;
    private Date ordertime;
    private Double price;

}
