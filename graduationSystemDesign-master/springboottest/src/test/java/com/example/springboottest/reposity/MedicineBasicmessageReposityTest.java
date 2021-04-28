package com.example.springboottest.reposity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MedicineBasicmessageReposityTest {

    @Autowired
    private MedicineReposity medicineReposity;
    @Test
    void findAll(){
    System.out.println((medicineReposity.findAll()));
    }
}
