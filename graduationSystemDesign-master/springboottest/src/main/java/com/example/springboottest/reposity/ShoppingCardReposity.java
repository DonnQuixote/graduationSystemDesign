package com.example.springboottest.reposity;

import com.example.springboottest.entity.Shopping_cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCardReposity extends JpaRepository<Shopping_cart,Integer> {

    List<Shopping_cart> findByNameContaining(String name);
    List<Shopping_cart> findByProviderContaining(String name);
    List<Shopping_cart> findByMedicineid(Integer mid);
}
