package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Parcel;
import com.example.demo.enums.ParcelEnum.Size;
import com.example.demo.enums.ShippingMethodEnum;
import com.example.demo.services.CustomerService;
import com.example.demo.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/V1/parcel")
@CrossOrigin("*")
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
    public void addNewParcel(@RequestBody Map<String, Object> requestBody) {
        Parcel parcel = new Parcel();
        Integer senderId = (Integer) requestBody.get("senderId");
        Integer receiverId = (Integer) requestBody.get("receiverId");
        String deliveryString = (String) requestBody.get("deliveryMethod");
        ShippingMethodEnum deliveryMethod = ShippingMethodEnum.valueOf(deliveryString);
        String deliveryAddress = (String) requestBody.get("deliveryAddress");
        String sizeString = (String) requestBody.get("size");
        Size size = Size.valueOf(sizeString);


        Customer sender = customerService.getUser(senderId.longValue());
        parcel.setSender(sender);
        if (receiverId != 0) {
            Customer receiver = customerService.getUser(receiverId.longValue());
            parcel.setReceiver(receiver);
            deliveryAddress = receiver.getAddress();
        }
        parcel.setDeliveryMethod(deliveryMethod);
        parcel.setDeliveryAddress(deliveryAddress);
        parcel.setSize(size);

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
