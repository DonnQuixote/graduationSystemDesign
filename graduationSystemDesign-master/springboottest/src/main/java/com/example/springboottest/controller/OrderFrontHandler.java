package com.example.springboottest.controller;
import com.example.springboottest.entity.Order_front;
import com.example.springboottest.entity.Order_front_details;
import com.example.springboottest.reposity.OrderFrontReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orderFront")
public class OrderFrontHandler {

    @Autowired
    private OrderFrontReposity orderFrontReposity;

    @PostMapping("/save/{ordertime}")
    public String save(@PathVariable("ordertime") Date ordertime){
        Order_front order_front = new Order_front();
        order_front.setOrdertime(ordertime);
        Order_front result = orderFrontReposity.save(order_front);
        if(result !=null){
            return "success";
        }else{
            return "error";
        }
    }

    @GetMapping("/test")
    public Integer test(){
        return orderFrontReposity.getOne(5).getOid();
    }

    @GetMapping("/getLatestOrder")
    public List<Order_front> getLatestOrder(){
        return orderFrontReposity.findLatestData();
    }

    @PostMapping("/getLatestOrder2")
    public List<Order_front> getLatestOrder2(){
        return orderFrontReposity.findLatestData2();
    }

    @PostMapping("/orderFront")
    public String orderFront(@RequestBody List<Order_front_details> list){
        Double temp = 0.0;
        for(Integer i=0;i<list.size();i++){
            Order_front_details t = list.get(i);
            temp += t.getMedicinecount() * t.getPrice();
        }
        Order_front order_front = orderFrontReposity.findLatestData().get(0);
        order_front.setTotalprice(temp);
        order_front.setOrderstate("等待处理");
        Order_front result = orderFrontReposity.save(order_front);
        if(result !=null){
            return "success";
        }else{
            return "error";
        }
    }

    @PostMapping("/orderFrontPrice/{price}/{oid}")
    public void orderFarontPrice(@PathVariable("price") Double price,@PathVariable("oid") Integer oid){
        Order_front order_front = orderFrontReposity.getOne(oid);
        order_front.setTotalprice(price);
        order_front.setOrderstate("等待处理");
        orderFrontReposity.save(order_front);
    }

    @DeleteMapping("/deleteByOid/{oid}")
    public void deleteByOid(@PathVariable("oid")Integer oid){
        orderFrontReposity.deleteById(oid);
    }

    @GetMapping("/findAll")
    public List<Order_front> findAll(){
        return orderFrontReposity.findAll();
    }

    @GetMapping("/findByOrderState/{state}")
    public List<Order_front> findByOrderState(@PathVariable("state")String state){
        return orderFrontReposity.findByOrderstate(state);
    }

    @GetMapping("/findByOid/{oid}")
    public Order_front findByOid(@PathVariable("oid")Integer oid) {
       return orderFrontReposity.findByOid(oid);
    }

    @GetMapping("/updateOrderState/{oid}/{state}")
    public void updateOrderState(@PathVariable("oid")Integer oid,@PathVariable("state")String state){
        Order_front order_front = orderFrontReposity.findByOid(oid);
        order_front.setOrderstate(state);
        orderFrontReposity.save(order_front);
    }
}
