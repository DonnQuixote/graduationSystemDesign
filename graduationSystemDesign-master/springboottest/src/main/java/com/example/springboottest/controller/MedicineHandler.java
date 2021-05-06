package com.example.springboottest.controller;

import com.example.springboottest.entity.Medicine_basicmessage;
import com.example.springboottest.entity.Order_front_details;
import com.example.springboottest.reposity.MedicineReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class MedicineHandler {
    @Autowired
    private MedicineReposity medicineReposity;

    @GetMapping("/findAll/{page}/{size}")
    public Page<Medicine_basicmessage> findAll(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return medicineReposity.findAll(pageable);
    }

    @PostMapping("/save")
    public String save(@RequestBody Medicine_basicmessage medicineBasicmessage){
        medicineBasicmessage.setState(0);
        Medicine_basicmessage result = medicineReposity.save(medicineBasicmessage);
        if(result !=null){
            return "success";
        }else{
            return "error";
        }
    }

    @GetMapping("/findById/{id}")
    public Medicine_basicmessage findById(@PathVariable("id") Integer id){
        return medicineReposity.findById(id).get();
    }

    @PutMapping("/update")
    public String update(@RequestBody Medicine_basicmessage medicineBasicmessage){
        Medicine_basicmessage result = medicineReposity.save(medicineBasicmessage);
        if(result!=null){
            return "success";
        }else{
            return "error";
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        //medicineReposity.deleteById(id);
        Medicine_basicmessage medicine_basicmessage = medicineReposity.getOne(id);
        medicine_basicmessage.setState(1);
        medicineReposity.save(medicine_basicmessage);
    }

    @GetMapping("medineFindProvider/{provider}")
    public List<Medicine_basicmessage> medicineFindProvider(@PathVariable("provider")String provider){
        List<Medicine_basicmessage> results = medicineReposity.findByProviderContaining(provider);
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

    @GetMapping("medineFindName/{name}")
    public List<Medicine_basicmessage> medicineFindName(@PathVariable("name")String name){
        List<Medicine_basicmessage> results = medicineReposity.findByNameContaining(name);
        return results;
    }

    @GetMapping("medineLikeName/{name}")
    public List<Medicine_basicmessage> medicineLikeName(@PathVariable("name")String name){
        List<Medicine_basicmessage> results = medicineReposity.findLikeName(name);
        return results;
    }

    @GetMapping("medineLikeProvider/{provider}")
    public List<Medicine_basicmessage> medicineLikeProvider(@PathVariable("provider")String provider){
        List<Medicine_basicmessage> results = medicineReposity.findLikeProvider(provider);
        return results;
    }


    @GetMapping("/findAll")
    public List<Medicine_basicmessage> findAll(){
        return medicineReposity.findAll();
    }

    @GetMapping("/findType2")
    public List<Medicine_basicmessage> Type2(){
        return medicineReposity.findType2();
    }

    @PostMapping("/updateCount")
    public void updateCount(@RequestBody List<Order_front_details> list){
        Integer temp = list.size();
        for(Integer t=0;t<temp;t++){
            Medicine_basicmessage medicine_basicmessage = medicineReposity.getOne(list.get(t).getMedicineid());
            Integer count = medicine_basicmessage.getCount();
            System.out.println(count);
            count -= list.get(t).getMedicinecount();
            medicine_basicmessage.setCount(count);
            System.out.println(count);
            medicineReposity.save(medicine_basicmessage);
            System.out.println(medicine_basicmessage.getCount());
        }
    }

    @GetMapping("/timeDiffWeak")
    public List<Medicine_basicmessage> timeDiffWeak(){
        return medicineReposity.timeDiffWeak();
    }

    @GetMapping("/timeDiffMonth")
    public List<Medicine_basicmessage> timeDiffMonth(){
        return medicineReposity.timeDiffMonth();
    }

    @GetMapping("/timeDiffMonths")
    public List<Medicine_basicmessage> timeDiffMonths(){
        return medicineReposity.timeDiffMonths();
    }
    @GetMapping("/timeDiffOutOfDate")
    public List<Medicine_basicmessage> timeDiffOutOfDate(){
        return medicineReposity.timeDiffOutOfDate();
    }

    @PostMapping("/deleteOutOfDate")
    public Integer deleteOutOfDate(@RequestBody List<Medicine_basicmessage> list){
        Integer temp = list.size();
        for(Integer i =0;i<temp;i++){
            Medicine_basicmessage medicine_basicmessage = list.get(i);
            //medicineReposity.delete(medicine_basicmessage);
            medicine_basicmessage.setState(1);
            medicineReposity.save(medicine_basicmessage);
        }
        if(temp>0){
            return temp;
        }else{
            return 0;
        }
    }

    @GetMapping("/getCountByMid/{mid}")
    public Integer getCountByMid(@PathVariable("mid")Integer mid){
        return medicineReposity.getOne(mid).getCount();
    }
}
