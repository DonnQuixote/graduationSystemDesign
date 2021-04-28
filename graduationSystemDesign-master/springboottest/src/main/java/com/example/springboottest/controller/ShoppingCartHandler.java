package com.example.springboottest.controller;

import com.example.springboottest.entity.Medicine_basicmessage;
import com.example.springboottest.entity.Order_front_details;
import com.example.springboottest.entity.Shopping_cart;
import com.example.springboottest.reposity.ShoppingCardReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping_cart")
public class ShoppingCartHandler {

    @Autowired
    private ShoppingCardReposity shoppingCardReposity;

    @PutMapping("/update")
    public String update(@RequestBody Shopping_cart shopping_cart){
        Shopping_cart result = shoppingCardReposity.save(shopping_cart);
        if(result!=null){
            return "success";
        }else{
            return "error";
        }
    }

    @PostMapping("/save")
    public String save(@RequestBody Shopping_cart shopping_cart){
        Shopping_cart result = shoppingCardReposity.save(shopping_cart);
        if(result !=null){
            return "success";
        }else{
            return "error";
        }
    }

//    @PostMapping("/save/{list}")
//    public String save(@PathVariable("list")List<String> list){
//        Shopping_cart shopping_cart = new Shopping_cart();
//        shopping_cart.setCount((Integer) list.get(4));
//    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        shoppingCardReposity.deleteById(id);
    }


    @GetMapping("/shoppingCartTest/{oid}/{medicinecount}/{medicineid}/{price}/{provider}/{name}")
    public String test2(@PathVariable("oid")Integer oid,@PathVariable("medicinecount")Integer medicinecount,
                        @PathVariable("medicineid")Integer medicineid,@PathVariable("price")Double price,
                        @PathVariable("provider")String provider,@PathVariable("name")String name){
        Shopping_cart shopping_cart = new Shopping_cart();
        List<Shopping_cart> list;
        list = shoppingCardReposity.findByMedicineid(medicineid);
        if(list.size()==1){//更新数量
            shopping_cart = list.get(0);
            Integer temp = shopping_cart.getMedicinecount();
            temp+=medicinecount;
            shopping_cart.setOid(oid);
//            Integer id = list.get(0).getId();
//            shopping_cart.setId(id);
            shopping_cart.setMedicinecount(temp);
        }else{
            shopping_cart.setMedicinecount(medicinecount);
            shopping_cart.setMedicineid(medicineid);
            //shopping_cart.setId(1);
            shopping_cart.setOid(oid);
            shopping_cart.setPrice(price);
            shopping_cart.setProvider(provider);
            shopping_cart.setName(name);
        }
        Shopping_cart test = shoppingCardReposity.save(shopping_cart);
        if(test!=null){
            return "success";
        }else {
            return "false";
        }
    }


    @GetMapping("shoppingCartFindName/{name}")
    public List<Shopping_cart> medicineFindName(@PathVariable("name")String name){
        List<Shopping_cart> results = shoppingCardReposity.findByNameContaining(name);
        return results;
    }

    @GetMapping("shoppingCartFindProvider/{provider}")
    public List<Shopping_cart> medicineFindProvider(@PathVariable("provider")String provider){
        List<Shopping_cart> results = shoppingCardReposity.findByProviderContaining(provider);
        return results;
    }

    @GetMapping("/findAll")
    public List<Shopping_cart> findAll(){
        return shoppingCardReposity.findAll();
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll(){
         shoppingCardReposity.deleteAll();
    }


}
