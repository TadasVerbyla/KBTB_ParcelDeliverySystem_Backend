package com.example.demo.services;

import com.example.demo.entities.Parcel;
import com.example.demo.entities.Customer;
import com.example.demo.enums.ParcelEnum;
import com.example.demo.repositories.ParcelRepository;
import com.example.demo.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.Objects;

@Service
@RequestScope
public class ParcelService {

    private final ParcelRepository parcelRepository;
    private final CustomerRepository customerRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParcelService(ParcelRepository parcelRepository, CustomerRepository customerRepository, JdbcTemplate jdbcTemplate) {
        this.parcelRepository = parcelRepository;
        this.customerRepository = customerRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Parcel> getParcels(){
        return parcelRepository.findAll();
    }

    public Parcel getParcel(Long parcelId){
        return parcelRepository.findById(parcelId).orElseThrow(() -> new IllegalStateException("Specified entry does not exist. "));
    };

    public void addNewParcel(Parcel parcel) {
        parcelRepository.save(parcel);
    }

    @Transactional
    public void deleteParcel(Long parcelId) {
        if (!parcelRepository.existsById(parcelId)) {
            throw new IllegalStateException("Specified entry does not exist. ");
        }
        parcelRepository.deleteById(parcelId);
    }

    @Transactional
    public void updateParcel(Long parcelId, Customer sender, Customer receiver, String deliveryAddress, ParcelEnum.Size size) {
        Parcel parcel = parcelRepository.findById(parcelId)
                .orElseThrow(() -> new IllegalStateException("Specified entry does not exist. "));

        String sql = "UPDATE Parcel SET sender_id = ?, receiver_id = ?, deliveryAddress = ?, size = ? WHERE id = ?";

        jdbcTemplate.update(sql, ps -> {
            ps.setLong(1, sender.getId());
            ps.setLong(2, receiver.getId());
            ps.setString(3, deliveryAddress);
            ps.setString(4, size.toString());
            ps.setLong(5, parcelId);
        });

        if (sender != null) parcel.setSender(sender);
        if (receiver != null) parcel.setReceiver(receiver);
        if (deliveryAddress != null) parcel.setDeliveryAddress(deliveryAddress);
        if (size != null) parcel.setSize(size);
    }

}
