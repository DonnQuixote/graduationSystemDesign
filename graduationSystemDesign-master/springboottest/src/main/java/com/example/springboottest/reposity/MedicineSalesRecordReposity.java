package com.example.springboottest.reposity;

import com.example.springboottest.entity.Medicine_basicmessage;
import com.example.springboottest.entity.Medicine_sales_record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicineSalesRecordReposity extends JpaRepository<Medicine_sales_record,Integer>{

    @Query(nativeQuery = true,value="select * from medicine_sales_record m WHERE m.medicineid=?1")
    List<Medicine_sales_record> findByMedicineId(Integer medicineid);

    List<Medicine_sales_record> findByProviderContaining(String provider);
    List<Medicine_sales_record> findByNameContaining(String name);
}
