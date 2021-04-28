package com.example.springboottest.reposity;

import com.example.springboottest.entity.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandReposity extends JpaRepository<Demand,Integer> {
    List<Demand> findByNameContaining(String name);
    List<Demand> findByProviderContaining(String provider);
}
