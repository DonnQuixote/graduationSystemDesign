package com.example.springboottest.reposity;

import com.example.springboottest.entity.Drug_entered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrugEnteredReposity extends JpaRepository<Drug_entered,Integer> {
    @Query(nativeQuery = true,value="select * from drug_entered order by ordertime desc limit 1 ")
    List<Drug_entered> findLatestData();

    Drug_entered findByEid(Integer eid);
}
