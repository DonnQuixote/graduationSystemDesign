package com.example.springboottest;

import com.example.springboottest.entity.*;
import com.example.springboottest.reposity.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringboottestApplicationTests {
    @Autowired
    ProviderReposity providerReposity;
    @Autowired
    MedicineReposity medicineReposity;
    @Autowired
    DrugLossReposity drugLossReposity;
    @Autowired
    OverdueReposity overdueReposity;
    @Autowired
    DataSource dataSource;
    @Autowired
    OrderFrontReposity orderFrontReposity;
    @Autowired
    ShoppingCardReposity shoppingCardReposity;
    @Autowired
    OrderFrontDetailsReposity orderFrontDetailsReposity;
    @Autowired
    DrugEnteredReposity drugEnteredReposity;
    @Autowired
    MedicineSalesRecordReposity medicineSalesRecordReposity;
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
    void save(){
//        Provider_message provider_message;
//        Overdue_reminder overdue_reminder;
//        Medicine_basicmessage medicineBasicmessage = new Medicine_basicmessage();
//        medicineBasicmessage.setName("测试用药");
//        medicineBasicmessage.setCount(30);
//        medicineBasicmessage.setExpirydates(36);
//        medicineBasicmessage.setPurchaseprice(20.3);
//        medicineBasicmessage.setUnitprice(33.9);
//        medicineBasicmessage.setProvider("测试单位");

        Order_front order_front = new Order_front();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "2021-03-30";
        ParsePosition pos = new ParsePosition(0);
        Date date = sdf.parse(dateInString,pos);
        //medicineBasicmessage.setStoragedate(date);

//        Medicine_basicmessage medicineBasicmessage1 = medicineReposity.save(medicineBasicmessage);
//        System.out.println(medicineBasicmessage1);


        order_front.setOrdertime(date);

        Order_front order_front1 = orderFrontReposity.save(order_front);
        System.out.println(order_front1);
    }
    @Test
    void findById(){
        //Medicine_basicmessage medicineBasicmessage = medicineReposity.findById(3).get();
       // Provider_message provider_message = providerReposity.findById(2).get();
        //Overdue_reminder overdue_reminder = overdueReposity.findById(1).get();

//        Drug_loss_report drug_loss_report = drugLossReposity.findById(1).get();
//        System.out.println(drug_loss_report);
    }

    @Test
     void update(){
        Medicine_basicmessage medicineBasicmessage = new Medicine_basicmessage();
        medicineBasicmessage.setMedicineid(8);
        medicineBasicmessage.setName("更改法外狂徒");
        Medicine_basicmessage medicineBasicmessage1 = medicineReposity.save(medicineBasicmessage);
        System.out.println(medicineBasicmessage1);
     }

     @Test
    void delete(){
        medicineReposity.deleteById(21);
     }


     @Test
    void TestDrugLossQuery(){
         List<Drug_loss_report> results = drugLossReposity.findByProviderContaining("海");
         //assertEquals(2,results.size());
         System.out.println(results.size());
     }

    @Test
    void TestOrderQuery(){
        List<Order_front> results = orderFrontReposity.findLatestData();
        //assertEquals(2,results.size());
        System.out.println(results.size());
        System.out.println(results.get(0).getOid());
        System.out.println(results.get(0).getOrdertime());
    }

    @Test
    void TestShoppingCartFindByMId(){
        List<Shopping_cart> results = shoppingCardReposity.findByMedicineid(13);
        System.out.println(results.size());
        System.out.println(results.get(0).getOid());
        System.out.println(results.get(0).getProvider());
    }

    @Test
    void TestOrderDetailsFindByOid(){
        List<Order_front_details> results =  orderFrontDetailsReposity.findByOid(28);
        System.out.println(results.size());
        System.out.println(results.get(0).getPrice());
    }

    @Test
    void TestDrugEnteredAdd(){
        Drug_entered drug_entered = new Drug_entered();
        drug_entered.setPrice(300.0);
        drugEnteredReposity.save(drug_entered);
    }

    @Test
    void TestTimeDiff(){
        List<Medicine_basicmessage> list = medicineReposity.timeDiffMonths();
        System.out.println(list.size());
        for(Integer i=0;i<list.size();i++){
            System.out.println(list.get(i).getEnddate());
        }
    }

    @Test
    void TestFindType2(){
        List<Drug_loss_report> list = drugLossReposity.findType2();
        System.out.println(list.size());
    }

    @Test
    void TestFindByMedicineId(){
        List<Medicine_sales_record> list = medicineSalesRecordReposity.findByMedicineId(2);
        System.out.println(list.size());
    }
}
