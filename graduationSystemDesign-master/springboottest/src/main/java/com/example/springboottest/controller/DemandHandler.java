package com.example.springboottest.controller;

import com.example.springboottest.entity.Demand;
import com.example.springboottest.reposity.DemandReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demand")
public class DemandHandler {

    @Autowired
    private DemandReposity demandReposity;

    @GetMapping("/findAll/{page}/{size}")
    public Page<Demand> findAll(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return demandReposity.findAll(pageable);
    }

    @PostMapping("/save")
    public String save(@RequestBody Demand demand){
        Demand result = demandReposity.save(demand);
        if(result !=null){
            return "success";
        }else{
            return "error";
        }
    }

    @GetMapping("/findById/{id}")
    public Demand findById(@PathVariable("id") Integer id){
        return demandReposity.findById(id).get();
    }

    @PutMapping("/update")
    public String update(@RequestBody Demand demad){
        Demand result = demandReposity.save(demad);
        if(result!=null){
            return "success";
        }else{
            return "error";
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        demandReposity.deleteById(id);
    }

    @GetMapping("demandFindProvider/{provider}")
    public List<Demand> demandFindProvider(@PathVariable("provider")String provider){
        List<Demand> results = demandReposity.findByProviderContaining(provider);
        return results;
    }

    @GetMapping("demandFindName/{name}")
    public List<Demand> demandFindName(@PathVariable("name")String name){
        List<Demand> results = demandReposity.findByNameContaining(name);
        return results;
    }

    @GetMapping("/findAll")
    public List<Demand> findAll(){
        return demandReposity.findAll();
    }
}
