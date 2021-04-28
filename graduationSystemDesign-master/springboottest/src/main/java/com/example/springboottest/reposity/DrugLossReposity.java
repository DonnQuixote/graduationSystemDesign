package com.example.springboottest.reposity;

import com.example.springboottest.entity.Drug_loss_report;
import com.example.springboottest.entity.Medicine_basicmessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrugLossReposity extends JpaRepository<Drug_loss_report,Integer> {
    List<Drug_loss_report> findByProviderContaining(String provider);
    List<Drug_loss_report> findByNameContaining(String name);
    List<Drug_loss_report> findByReasonContaining(String reason);

    @Query(nativeQuery = true,value="select * from drug_loss_report d where d.type=2 ")
    List<Drug_loss_report> findType2();
}
