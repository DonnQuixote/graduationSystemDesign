package com.example.springboottest.reposity;

import com.example.springboottest.entity.Order_front;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottest.entity.Medicine_basicmessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface MedicineReposity extends JpaRepository<Medicine_basicmessage,Integer> {
    List<Medicine_basicmessage> findByNameContaining(String name);
    //Page<Medicine_basicmessage> findByProviderConntaining(String name);
    List<Medicine_basicmessage> findByProviderContaining(String name);

    @Query(nativeQuery = true,value="select * from medicine_basicmessage m WHERE abs(TIMESTAMPDIFF(day ,m.enddate,NOW()))<7 ")
    List<Medicine_basicmessage> timeDiffWeak();
    @Query(nativeQuery = true,value="select * from medicine_basicmessage m WHERE abs(TIMESTAMPDIFF(day ,m.enddate,NOW()))<31 ")
    List<Medicine_basicmessage> timeDiffMonth();
    @Query(nativeQuery = true,value="select * from medicine_basicmessage m WHERE abs(TIMESTAMPDIFF(day ,m.enddate,NOW()))<93 ")
    List<Medicine_basicmessage> timeDiffMonths();
    @Query(nativeQuery = true,value="select * from medicine_basicmessage m WHERE TIMESTAMPDIFF(day ,m.enddate,NOW())>0")
    List<Medicine_basicmessage> timeDiffOutOfDate();

}
