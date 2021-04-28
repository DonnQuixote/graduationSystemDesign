package com.example.springboottest.reposity;

import com.example.springboottest.entity.Overdue_reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OverdueReposity extends JpaRepository<Overdue_reminder,Integer> {
}
