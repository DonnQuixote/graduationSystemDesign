package com.example.springboottest.reposity;

import com.example.springboottest.entity.Order_front_details;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderFrontDetailsReposity extends JpaRepository<Order_front_details,Integer> {
    //List<Order_front_details> findByNameContaining(String name);
    List<Order_front_details> findByProviderContaining(String name);
    List<Order_front_details> findByOid(Integer oid);
}
