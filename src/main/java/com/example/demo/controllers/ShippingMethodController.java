package com.example.demo.controllers;

import com.example.demo.entities.Parcel;
import com.example.demo.services.ParcelService;
import com.example.demo.services.ShippingMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/V1/shipping-methods")
public class ShippingMethodController {
    private final ShippingMethodService shippingMethodService;
    @Autowired
    public ShippingMethodController(ShippingMethodService shippingMethodService){
        this.shippingMethodService = shippingMethodService;
    }
    @GetMapping
    public List<String> getShippingMethods() {

        return shippingMethodService.getShippingMethods();
    }

}
