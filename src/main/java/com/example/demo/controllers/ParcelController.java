package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Parcel;
import com.example.demo.enums.ParcelEnum;
import com.example.demo.services.CustomerService;
import com.example.demo.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/V1/parcel")
public class ParcelController {

    private final ParcelService parcelService;
    private final CustomerService customerService;

    @Autowired
    public ParcelController(ParcelService parcelService, CustomerService customerService){
        this.parcelService = parcelService;
        this. customerService = customerService;
    }

    @GetMapping
    public List<Parcel> getParcels(){
        return parcelService.getParcels();
    }
    @GetMapping(path = "{parcelId}")
    public Parcel getParcel(@PathVariable("parcelId") Long parcelId){
        return parcelService.getParcel(parcelId);
    }

    @PostMapping
    public void addNewParcel(@RequestBody Parcel parcel) {
        Customer sender = customerService.getUser(parcel.getSenderId());
        Customer receiver =customerService.getUser(parcel.getReceiverId());
        //if (sender != null && receiver != null) {
        parcel.setSender(sender);
        parcel.setReceiver(receiver);
        //} else {}
        parcelService.addNewParcel(parcel);
    }

    @DeleteMapping(path = "{parcelId}")
    public void deleteParcel(@PathVariable("parcelId") Long parcelId) {
        parcelService.deleteParcel(parcelId);
    }

    /*@PutMapping(path = "{parcelId}")
    public void updateParcel( //bad
            @PathVariable("parcelId") Long parcelId,
            @RequestParam(required = false) Customer senderId,
            @RequestParam(required = false) Customer receiverId,
            @RequestParam(required = false) String deliveryAddress,
            @RequestParam(required = false) ParcelEnum.Size size) {
        parcelService.updateParcel(parcelId, senderId, receiverId, deliveryAddress, size);
    }*/
}
