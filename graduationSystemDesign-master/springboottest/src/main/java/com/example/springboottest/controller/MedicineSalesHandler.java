package com.example.springboottest.controller;

import com.example.springboottest.entity.Medicine_sales_record;
import com.example.springboottest.entity.Order_front_details;
import com.example.springboottest.reposity.MedicineSalesRecordReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicineSalesHandler")
public class MedicineSalesHandler {

    @Autowired
    private MedicineSalesRecordReposity medicineSalesRecordReposity;

    @PostMapping("/saveAll")
    public void save(@RequestBody List<Medicine_sales_record> list){
        Integer count = list.size();
        for(Integer i =0;i<count;i++){
            Integer temp = list.get(i).getMedicineid();
            if(medicineSalesRecordReposity.findByMedicineId(temp).size()>0){
                Medicine_sales_record medicine_sales_record = medicineSalesRecordReposity.findByMedicineId(temp).get(0);
                Integer changeCount = medicine_sales_record.getMedicinecount()+list.get(i).getMedicinecount();
                medicine_sales_record.setMedicinecount(changeCount);
                medicineSalesRecordReposity.save(medicine_sales_record);

            }else{
                medicineSalesRecordReposity.save(list.get(i));
            }
        }
    }

    @PostMapping("/findAll")
    public List<Medicine_sales_record> findAll(){
        return medicineSalesRecordReposity.findAll();
    }

    @PostMapping("/findByProvider/{provider}")
    public List<Medicine_sales_record> findByProvider(@PathVariable("provider")String provider){
        return medicineSalesRecordReposity.findByProviderContaining(provider);
    }

    @PostMapping("/findByName/{name}")
    public List<Medicine_sales_record> findByName(@PathVariable("name")String name){
        return medicineSalesRecordReposity.findByNameContaining(name);
    }


}
