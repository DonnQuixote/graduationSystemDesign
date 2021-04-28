package com.example.springboottest.controller;

import com.example.springboottest.entity.Drug_loss_report;
import com.example.springboottest.reposity.DrugLossReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/drugLoss")
public class DrugLossHandler {

    @Autowired
    private DrugLossReposity drugLossReposity;

    @GetMapping("/findAll/{page}/{size}")
    public Page<Drug_loss_report> findAll(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return drugLossReposity.findAll(pageable);
    }

    @PostMapping("/save")
    public String save(@RequestBody Drug_loss_report drug_loss_report){
        Drug_loss_report result = drugLossReposity.save(drug_loss_report);
        if(result !=null){
            return "success";
        }else{
            return "error";
        }
    }

    @PostMapping("/saveMuti")
    public String saveMuti(@RequestBody List<Drug_loss_report> list ){
        for(Integer i=0;i<list.size();i++){
            Drug_loss_report drug_loss_report = list.get(i);
            drug_loss_report.setReason("药品过期");
            drug_loss_report.setType(2);
            drugLossReposity.save(drug_loss_report);
        }
        if(list.size()>0){
            return "success";
        }else{
            return "false";
        }
    }

    @GetMapping("/findById/{id}")
    public Drug_loss_report findById(@PathVariable("id") Integer id){
        return drugLossReposity.findById(id).get();
    }

//    @GetMapping("/timeReminder/{date}")
//    public Drug_loss_report timeReminder(@PathVariable("date")Date date){
//        DrugLossReposity drugLossReposity2;
//        Integer count = drugLossReposity.findAll().size();
//        for(Integer i=0;i<count;i++){
//            if(date<drugLossReposity.get)
//        }
//    }

    @PutMapping("/update")
    public String update(@RequestBody Drug_loss_report drug_loss_report){
        Drug_loss_report result = drugLossReposity.save(drug_loss_report);
        if(result!=null){
            return "success";
        }else{
            return "error";
        }
    }

    @PutMapping("/update/{id}/{reason}")
    public String update(@PathVariable("id")Integer id,@PathVariable("reason")String reason){
         Drug_loss_report drug_loss_report= drugLossReposity.findById(id).get();
         drug_loss_report.setReason(reason);
         drug_loss_report.setType(1);
        if(drug_loss_report!=null){
            return "success";
        }else{
            return "error";
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        drugLossReposity.deleteById(id);
    }

    @GetMapping("/findAll")
    public List<Drug_loss_report> findAll(){
        return drugLossReposity.findAll();
    }

    @GetMapping("medineFindProvider/{provider}")
    public List<Drug_loss_report> medicineFindProvider(@PathVariable("provider")String provider){
        List<Drug_loss_report> results = drugLossReposity.findByProviderContaining(provider);
        return results;
    }

    @GetMapping("medineFindName/{director}")
    public List<Drug_loss_report> medicineFindName(@PathVariable("director")String director){
        List<Drug_loss_report> results = drugLossReposity.findByNameContaining(director);
        return results;
    }

    @GetMapping("medineFindReason/{reason}")
    public List<Drug_loss_report> medicineFindReason(@PathVariable("reason")String reason){
        List<Drug_loss_report> results = drugLossReposity.findByReasonContaining(reason);
        return results;
    }

    @GetMapping("/findType2")
    public List<Drug_loss_report> findType2(){
        return drugLossReposity.findType2();
    }
}
