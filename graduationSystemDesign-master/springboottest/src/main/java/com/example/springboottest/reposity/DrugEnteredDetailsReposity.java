package com.example.springboottest.reposity;

import com.example.springboottest.entity.Drug_entered_details;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrugEnteredDetailsReposity extends JpaRepository<Drug_entered_details,Integer> {
                List<Drug_entered_details> findByName(String name);
                List<Drug_entered_details> findByProvider(String provider);
                List<Drug_entered_details> findByEid(Integer eid);
                List<Drug_entered_details> findByNameContaining(String name);
                List<Drug_entered_details> findByProviderContaining(String provider);
}
