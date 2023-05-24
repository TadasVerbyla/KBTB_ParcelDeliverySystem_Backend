package com.example.demo.controllers;

import com.example.demo.entities.Parcel;
import com.example.demo.interceptors.LoggingInterceptor;
import com.example.demo.services.ParcelService;
import com.example.demo.services.ShippingMethodService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/V1/shipping-methods")
public class ShippingMethodController {
    private final ShippingMethodService shippingMethodService;
    private final LoggingInterceptor loggingInterceptor;
    @Autowired
    public ShippingMethodController(ShippingMethodService shippingMethodService, LoggingInterceptor loggingInterceptor) {
        this.shippingMethodService = shippingMethodService;
        this.loggingInterceptor = loggingInterceptor;
    }
    @GetMapping
    public List<String> getShippingMethods(HttpServletRequest request, HttpServletResponse response) {
        try {
            loggingInterceptor.preHandle(request, response, this);

            List<String> shippingMethods = shippingMethodService.getShippingMethods();

            loggingInterceptor.postHandle(request, response, this, null);
            loggingInterceptor.afterCompletion(request, response, this, null);

            return shippingMethods;
        } catch (Exception e) {
            // Handle exceptions if needed
            return Collections.emptyList(); // Or any appropriate error handling
        }
    }

}
