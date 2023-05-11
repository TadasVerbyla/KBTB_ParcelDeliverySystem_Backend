package com.example.demo.services;

import com.example.demo.entities.Parcel;
import com.example.demo.entities.Customer;
import com.example.demo.enums.ParcelEnum;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.ParcelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public ParcelService(ParcelRepository parcelRepository, CustomerRepository customerRepository)
    {
        this.parcelRepository = parcelRepository;
        this.customerRepository = customerRepository;
    }
    public List<Parcel> getParcels(){
        return parcelRepository.findAll();
    }

    public void addNewParcel(Parcel parcel) {
        parcelRepository.save(parcel);
    }

    public void deleteParcel(Long parcelId) {
        if (!parcelRepository.existsById(parcelId)) {
            throw new IllegalStateException("Specified entry does not exist. ");
        }
        parcelRepository.deleteById(parcelId);
    }

    @Transactional
    public void updateParcel(Long parcelId, Customer sender, Customer receiver, String deliveryAddress, ParcelEnum.Size size) {

        Parcel parcel = parcelRepository.findById(parcelId).orElseThrow(() -> new IllegalStateException("Specified entry does not exist. "));

        if (sender != null &&
                !Objects.equals(parcel.getSender(), sender)) {
            parcel.setSender(sender);
        }

        if (receiver != null &&
                !Objects.equals(parcel.getReceiver(), receiver)) {
            parcel.setReceiver(receiver);
        }

        if (deliveryAddress != null &&
                deliveryAddress.length() > 0 &&
                !Objects.equals(parcel.getDeliveryAddress(), deliveryAddress)) {
            parcel.setDeliveryAddress(deliveryAddress);
        }

        if (size != null &&
                !(Objects.equals(parcel.getSize(), size))) {
            parcel.setSize(size);
        }
    }
}
