package com.example.springboottest.controller;

import com.example.springboottest.entity.Overdue_reminder;
import com.example.springboottest.reposity.OverdueReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/overdue")
public class OverdueHandler {
    @Autowired
    private OverdueReposity overdueReposity;

    @GetMapping("/findAll/{page}/{size}")
    public Page<Overdue_reminder> findAll(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return overdueReposity.findAll(pageable);
    }

    @PostMapping("/save")
    public String save(@RequestBody Overdue_reminder overdue_reminder){
        Overdue_reminder result = overdueReposity.save(overdue_reminder);
        if(result !=null){
            return "success";
        }else{
            return "error";
        }
    }

    @GetMapping("/findById/{id}")
    public Overdue_reminder findById(@PathVariable("id") Integer id){
        return overdueReposity.findById(id).get();
    }

    @PutMapping("/update")
    public String update(@RequestBody Overdue_reminder overdue_reminder){
        Overdue_reminder result = overdueReposity.save(overdue_reminder);
        if(result!=null){
            return "success";
        }else{
            return "error";
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        overdueReposity.deleteById(id);
    }
}
