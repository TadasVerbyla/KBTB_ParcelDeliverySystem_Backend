package com.example.demo.configurations;

import com.example.demo.entities.Parcel;
import com.example.demo.enums.ParcelEnum;
import com.example.demo.repositories.ParcelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ParcelConfiguration {
    @Bean
    CommandLineRunner commandLineRunner2 (ParcelRepository repository) {
        return args -> {
            //Parcel A = new Parcel("Jonas", "Petras", "A g. 127", ParcelEnum.Size.SMALL);
            //Parcel B = new Parcel("Tomas", "Jonas", "A g. 128", ParcelEnum.Size.LARGE);
            //repository.saveAll(List.of(A, B));
        };
    }
}
