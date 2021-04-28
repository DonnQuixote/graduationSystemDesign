package com.example.springboottest.controller;

import com.example.springboottest.entity.Provider_message;
import com.example.springboottest.reposity.ProviderReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/provider")
public class ProviderHandler {
    @Autowired
    private ProviderReposity providerReposity;

    @GetMapping("findAll/{page}/{size}")
    public Page<Provider_message> findAll(@PathVariable("page")Integer page,@PathVariable("size")Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return providerReposity.findAll(pageable);
    }

    @PostMapping("/save")
    public String save(@RequestBody Provider_message provider_message){
        Provider_message result = providerReposity.save(provider_message);
        if(result !=null){
            return "success";
        }else{
            return "error";
        }
    }

    @GetMapping("/findById/{id}")
    public Provider_message findById(@PathVariable("id") Integer id){
        return providerReposity.findById(id).get();
    }

    @PutMapping("/update")
    public String update(@RequestBody Provider_message provider_message){
        Provider_message result = providerReposity.save(provider_message);
        if(result!=null){
            return "success";
        }else{
            return "error";
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        providerReposity.deleteById(id);
    }

    @GetMapping("medineFindProvider/{provider}")
    public List<Provider_message> medicineFindProvider(@PathVariable("provider")String provider){
        List<Provider_message> results = providerReposity.findByProviderContaining(provider);
        return results;
    }

    @GetMapping("medineFindName/{director}")
    public List<Provider_message> medicineFindDirector(@PathVariable("director")String director){
        List<Provider_message> results = providerReposity.findByDirectorContaining(director);
        return results;
    }

    @GetMapping("/findAll")
    public List<Provider_message> findAll(){
        return providerReposity.findAll();
    }
}
