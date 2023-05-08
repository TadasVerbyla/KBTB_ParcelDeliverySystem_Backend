package com.example.demo.configurations;

import com.example.demo.entities.Parcel;
import com.example.demo.repositories.ParcelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ParcelConfiguration {
    @Bean
    CommandLineRunner commandLineRunner (ParcelRepository repository) {
        return args -> {
            Parcel A = new Parcel("AAAA", "1", 2555);
            Parcel B = new Parcel("BBBB", "2", 3221);
            repository.saveAll(List.of(A, B));
        };
    }
}
