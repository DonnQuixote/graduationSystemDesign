package com.example.springboottest.controller;

import com.example.springboottest.entity.Drug_entered;
import com.example.springboottest.entity.Order_front;
import com.example.springboottest.entity.Order_front_details;
import com.example.springboottest.reposity.DrugEnteredReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/drugEntered")
public class DrugEnteredHandler {

    @Autowired
    DrugEnteredReposity drugEnteredReposity;
    @GetMapping("/getLatestOrder")
    public List<Drug_entered> getLatestOrder(){
        return drugEnteredReposity.findLatestData();
    }

    @PostMapping("/save/{ordertime}")
    public String save(@PathVariable("ordertime") Date ordertime){
        Drug_entered drug_entered = new Drug_entered();
        drug_entered.setOrdertime(ordertime);
        Drug_entered result = drugEnteredReposity.save(drug_entered);
        if(result !=null){
            return "success";
        }else{
            return "error";
        }
    }

    @GetMapping("/test")
    public Integer test(){
        return drugEnteredReposity.getOne(5).getEid();
    }


//    @PostMapping("/orderFront")
//    public String orderFront(@RequestBody List<Order_front_details> list){
//        Double temp = 0.0;
//        for(Integer i=0;i<list.size();i++){
//            Order_front_details t = list.get(i);
//            temp += t.getMedicinecount() * t.getPrice();
//        }
//        Drug_entered drug_entered = drugEnteredReposity.findLatestData().get(0);
//        drug_entered.setTotalprice(temp);
//        order_front.setOrderstate("等待处理");
//        Order_front result = drugEnteredReposity.save(order_front);
//        if(result !=null){
//            return "success";
//        }else{
//            return "error";
//        }
//    }

    @PostMapping("/orderFrontPrice/{price}/{eid}")
    public void orderFarontPrice(@PathVariable("price") Double price,@PathVariable("eid") Integer eid){
        Drug_entered drug_entered = drugEnteredReposity.getOne(eid);
        drug_entered.setPrice(price);
        drugEnteredReposity.save(drug_entered);
    }

    @DeleteMapping("/deleteByEid/{eid}")
    public void deleteByEid(@PathVariable("eid")Integer eid){
        drugEnteredReposity.deleteById(eid);
    }

    @GetMapping("/findAll")
    public List<Drug_entered> findAll(){
        return drugEnteredReposity.findAll();
    }


    @GetMapping("/findByEid/{eid}")
    public Drug_entered findByEid(@PathVariable("eid")Integer eid) {
        return drugEnteredReposity.findByEid(eid);
    }

}
