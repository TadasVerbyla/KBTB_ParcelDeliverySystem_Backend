package com.example.demo.configurations;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfiguration {
    @Bean
    CommandLineRunner commandLineRunner2 (CustomerRepository repository) {
        return args -> {
            Customer A = new Customer("abc", "Petras", "p@mail.com", "ParcelEnum.Size.SMALL");
            Customer B = new Customer("def", "Jonas", "j@mail.com", "ParcelEnum.Size.LARGE");
            repository.saveAll(List.of(A, B));
        };
    }
}
