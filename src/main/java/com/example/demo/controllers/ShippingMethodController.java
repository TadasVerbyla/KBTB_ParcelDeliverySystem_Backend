package com.example.demo.controllers;

import com.example.demo.entities.Parcel;
import com.example.demo.interceptors.LoggingInterceptor;
import com.example.demo.services.ParcelService;
import com.example.demo.services.ShippingMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Lazy
@RestController
@RequestMapping("api/V1/shipping-methods")
@CrossOrigin("*")
public class ShippingMethodController {
    private final ShippingMethodService shippingMethodService;
    private final LoggingInterceptor loggingInterceptor;
    @Autowired
    public ShippingMethodController(LoggingInterceptor loggingInterceptor, ShippingMethodService shippingMethodService){
        this.loggingInterceptor = loggingInterceptor;
        this.shippingMethodService = shippingMethodService;
    }
    @GetMapping
    public List<String> getShippingMethods() {

        return shippingMethodService.getShippingMethods();
    }

}
