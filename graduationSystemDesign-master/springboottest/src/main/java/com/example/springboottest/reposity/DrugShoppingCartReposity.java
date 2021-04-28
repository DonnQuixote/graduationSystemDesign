package com.example.springboottest.reposity;

import com.example.springboottest.entity.Drug_shopping_cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrugShoppingCartReposity extends JpaRepository<Drug_shopping_cart,Integer> {
    List<Drug_shopping_cart> findByNameContaining(String name);
    List<Drug_shopping_cart> findByProviderContaining(String name);
    List<Drug_shopping_cart> findByName(String name);
}
