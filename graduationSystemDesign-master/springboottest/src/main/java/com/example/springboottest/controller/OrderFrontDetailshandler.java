package com.example.springboottest.controller;

import com.example.springboottest.entity.Medicine_basicmessage;
import com.example.springboottest.entity.Order_front_details;
import com.example.springboottest.reposity.OrderFrontDetailsReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderFrontDetails")
public class OrderFrontDetailshandler {

    @Autowired
    private OrderFrontDetailsReposity orderFrontDetailsReposity;

    @PutMapping("/update")
    public String update(@RequestBody Order_front_details order_front_details){
        Order_front_details result = orderFrontDetailsReposity.save(order_front_details);
        if(result!=null){
            return "success";
        }else{
            return "error";
        }
    }

    @PostMapping("/save")
    public String save(@RequestBody Order_front_details order_front_details){
        Order_front_details result = orderFrontDetailsReposity.save(order_front_details);
        if(result !=null){
            return "success";
        }else{
            return "error";
        }
    }

    @PostMapping("/saveAll")
    public String save(@RequestBody List<Order_front_details> list){
        List<Order_front_details> result = orderFrontDetailsReposity.saveAll(list);
        if(result !=null){
            return "success";
        }else{
            return "error";
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        orderFrontDetailsReposity.deleteById(id);
    }

    @GetMapping("orderDetailsFindProvider/{provider}")
    public List<Order_front_details> medicineFindProvider(@PathVariable("provider")String provider){
        List<Order_front_details> results = orderFrontDetailsReposity.findByProviderContaining(provider);
        return results;
    }

//    @GetMapping("medineFindProvider/{provider}")
//    public Page<Medicine_basicmessage> medicineFindProvider(@PathVariable("provider")String provider){
//        Pageable pageable = PageRequest.of(0,6);
//        List<Medicine_basicmessage> results = medicineReposity.findByProviderContaining(provider);
//        int start = (int)pageable.getOffset();
//        int end = (start + pageable.getPageSize()) > results.size() ? results.size() : ( start + pageable.getPageSize());
//        Page<Medicine_basicmessage> page = new PageImpl<>(results.subList(start, end),pageable,results.size());
//        return page;
//    }

//    @GetMapping("orderDetailsFindName/{name}")
//    public List<Order_front_details> medicineFindName(@PathVariable("name")String name){
//        List<Order_front_details> results = orderFrontDetailsReposity.findByNameContaining(name);
//        return results;
//    }

    @GetMapping("/findAll")
    public List<Order_front_details> findAll(){
        return orderFrontDetailsReposity.findAll();
    }

    @GetMapping("/findByOid/{oid}")
    public List<Order_front_details> findByOid(@PathVariable("oid")Integer oid){
        return orderFrontDetailsReposity.findByOid(oid);
    }

}
