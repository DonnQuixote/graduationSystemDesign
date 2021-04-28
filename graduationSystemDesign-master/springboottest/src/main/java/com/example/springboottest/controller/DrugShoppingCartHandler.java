package com.example.springboottest.controller;

import com.example.springboottest.entity.Drug_entered_details;
import com.example.springboottest.entity.Drug_shopping_cart;
import com.example.springboottest.reposity.DrugShoppingCartReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/drugShoppingCart")
public class DrugShoppingCartHandler {
    @Autowired
    DrugShoppingCartReposity drugShoppingCartReposity;

    @GetMapping("/save/{eid}/{name}/{count}/{price}/{provider}")
    public String save(@PathVariable("eid")Integer eid,@PathVariable("name")String name,
                       @PathVariable("count")Integer count,@PathVariable("price")Double price,
                       @PathVariable("provider")String provider){
        Drug_shopping_cart drug_shopping_cart = new Drug_shopping_cart();
        List<Drug_shopping_cart> list;
        list = drugShoppingCartReposity.findByName(name);
        if(list.size()==1){//更新数量
            drug_shopping_cart = list.get(0);
            Integer temp = drug_shopping_cart.getCount();
            temp +=count;
            drug_shopping_cart.setEid(eid);
//            Integer id = list.get(0).getId();
//            shopping_cart.setId(id);
            drug_shopping_cart.setCount(temp);
        }else{
            drug_shopping_cart.setCount(count);
            //shopping_cart.setId(1);
            drug_shopping_cart.setEid(eid);
            drug_shopping_cart.setPrice(price);
            drug_shopping_cart.setProvider(provider);
            drug_shopping_cart.setName(name);
        }
        Drug_shopping_cart test = drugShoppingCartReposity.save(drug_shopping_cart);
        if(test!=null){
            return "success";
        }else {
            return "false";
        }
    }


    @GetMapping("/findAll")
    public  List<Drug_shopping_cart> findAll(){
        return drugShoppingCartReposity.findAll();
    }

    @GetMapping("/findByName/{name}")
    public List<Drug_shopping_cart> findByName(@PathVariable("name")String name){
        return drugShoppingCartReposity.findByNameContaining(name);
    }

    @GetMapping("/findByProvider/{provider}")
    public List<Drug_shopping_cart> findByProvider(@PathVariable("provider")String provider){
        return drugShoppingCartReposity.findByProviderContaining(provider);
    }

    @GetMapping("/deleteByEid/{eid}")
    public void deleteByEid(@PathVariable("eid")Integer eid){
        drugShoppingCartReposity.deleteById(eid);
    }

    @GetMapping("/deleteAll")
    public void deleteAll(){
        drugShoppingCartReposity.deleteAll();
    }
}
