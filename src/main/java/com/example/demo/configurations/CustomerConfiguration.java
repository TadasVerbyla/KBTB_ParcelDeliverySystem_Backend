package com.example.demo.configurations;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Parcel;
import com.example.demo.enums.ParcelEnum;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.ParcelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfiguration {
    @Bean
    CommandLineRunner commandLineRunner (CustomerRepository customerRepository, ParcelRepository parcelRepository) {
        return args -> {
            Customer A = new Customer("abc", "Petras", "p@mail.com", "ParcelEnum.Size.SMALL");
            Customer B = new Customer("def", "Jonas", "j@mail.com", "ParcelEnum.Size.LARGE");
            customerRepository.saveAll(List.of(A, B));
            Parcel X = new Parcel(A, B, "aaa", ParcelEnum.Size.LARGE);
        };
    }
}
