package com.example.springboottest.reposity;

import com.example.springboottest.entity.Provider_message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderReposity extends JpaRepository<Provider_message,Integer> {
    List<Provider_message> findByDirectorContaining(String director);
    List<Provider_message> findByProviderContaining(String provider);
}
