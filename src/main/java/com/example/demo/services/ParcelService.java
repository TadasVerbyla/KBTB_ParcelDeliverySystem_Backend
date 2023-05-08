package com.example.demo.services;

import com.example.demo.entities.Parcel;
import com.example.demo.repositories.ParcelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;

    @Autowired
    public ParcelService(ParcelRepository parcelRepository)
    {
        this.parcelRepository = parcelRepository;
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
    public void updateParcel(Long parcelId, String deliveryAddress, String recipient, Integer weight) {

        Parcel parcel = parcelRepository.findById(parcelId).orElseThrow(() -> new IllegalStateException("Specified entry does not exist. "));

        if (deliveryAddress != null &&
                deliveryAddress.length() > 0 &&
                !Objects.equals(parcel.getDeliveryAddress(), deliveryAddress)) {
            parcel.setDeliveryAddress(deliveryAddress);
        }

        if (recipient != null &&
                recipient.length() > 0 &&
                !Objects.equals(parcel.getRecipient(), recipient)) {
            parcel.setRecipient(recipient);
        }

        if (weight != null &&
                weight > 0 &&
                weight < 10000 &&
                !(Objects.equals(parcel.getWeight(), weight))) {
            parcel.setWeight(weight);
        }
    }
}
