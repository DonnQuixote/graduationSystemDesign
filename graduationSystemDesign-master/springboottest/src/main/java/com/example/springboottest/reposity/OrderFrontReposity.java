package com.example.springboottest.reposity;

import com.example.springboottest.entity.Order_front;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderFrontReposity extends JpaRepository<Order_front,Integer> {
        @Query(nativeQuery = true,value="select * from order_front order by ordertime desc limit 1 ")
        List<Order_front> findLatestData();
        @Query(nativeQuery = true,value="select * from order_front order by ordertime desc limit 1 ")
        List<Order_front> findLatestData2();

        List<Order_front> findByOrderstate(String str);
        Order_front findByOid(Integer oid);
}
