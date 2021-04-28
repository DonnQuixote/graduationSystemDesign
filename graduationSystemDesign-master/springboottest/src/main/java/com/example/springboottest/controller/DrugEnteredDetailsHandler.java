package com.example.springboottest.controller;


import com.example.springboottest.entity.Drug_entered_details;
import com.example.springboottest.entity.Shopping_cart;
import com.example.springboottest.reposity.DrugEnteredDetailsReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drugEnteredDetails")
public class DrugEnteredDetailsHandler {

    @Autowired
    DrugEnteredDetailsReposity drugEnteredDetailsReposity;

    @GetMapping("/save/{eid}/{name}/{count}/{price}/{provider}")
    public String save(@PathVariable("eid")Integer eid,@PathVariable("name")String name,
                     @PathVariable("count")Integer count,@PathVariable("price")Double price,
                     @PathVariable("provider")String provider){
        Drug_entered_details drug_entered_details = new Drug_entered_details();
        List<Drug_entered_details> list;
        list = drugEnteredDetailsReposity.findByName(name);
        if(list.size()==1){//更新数量
            drug_entered_details = list.get(0);
            Integer temp = drug_entered_details.getCount();
            temp+=count;
            drug_entered_details.setEid(eid);
//            Integer id = list.get(0).getId();
//            shopping_cart.setId(id);
            drug_entered_details.setCount(temp);
        }else{
            drug_entered_details.setCount(count);
            //shopping_cart.setId(1);
            drug_entered_details.setEid(eid);
            drug_entered_details.setPrice(price);
            drug_entered_details.setProvider(provider);
            drug_entered_details.setName(name);
        }
        Drug_entered_details test = drugEnteredDetailsReposity.save(drug_entered_details);
        if(test!=null){
            return "success";
        }else {
            return "false";
        }
    }


    @GetMapping("/findAll")
    public  List<Drug_entered_details> findAll(){
        return drugEnteredDetailsReposity.findAll();
    }

    @GetMapping("/findByNameContaining/{name}")
    public List<Drug_entered_details> findByNameContaining(@PathVariable("name")String name){
        return drugEnteredDetailsReposity.findByNameContaining(name);
    }

    @GetMapping("/findByProviderContaining/{provider}")
    public List<Drug_entered_details> findByProviderContaining(@PathVariable("provider")String provider){
        return drugEnteredDetailsReposity.findByProviderContaining(provider);
    }

    @GetMapping("/findByName/{name}")
    public List<Drug_entered_details> findByName(@PathVariable("name")String name){
        return drugEnteredDetailsReposity.findByName(name);
    }

    @GetMapping("/findByProvider/{provider}")
    public List<Drug_entered_details> findByProvider(@PathVariable("provider")String provider){
        return drugEnteredDetailsReposity.findByProvider(provider);
    }

    @GetMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id")Integer id){
         drugEnteredDetailsReposity.deleteById(id);
    }

    @GetMapping("/deleteAll")
    public void deleteAll(){
        drugEnteredDetailsReposity.deleteAll();
    }

    @PostMapping("/saveAll")
    public String saveAll(@RequestBody List<Drug_entered_details> list){
        List<Drug_entered_details> result = drugEnteredDetailsReposity.saveAll(list);
        if(result !=null){
            return "success";
        }else{
            return "error";
        }
    }

    @GetMapping("/findByEid/{eid}")
    public List<Drug_entered_details> findByEid(@PathVariable("eid")Integer eid){
        return drugEnteredDetailsReposity.findByEid(eid);
    }
}
