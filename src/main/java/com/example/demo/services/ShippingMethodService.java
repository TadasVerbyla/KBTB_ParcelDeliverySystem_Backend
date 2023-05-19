package com.example.demo.services;

import com.example.demo.components.interfaces.ShippingMethodResolver;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingMethodService {
    private final ShippingMethodResolver shippingMethodResolver;
    @Autowired
    public ShippingMethodService(ShippingMethodResolver shippingMethodResolver)
    {
        this.shippingMethodResolver = shippingMethodResolver;
    }
    public List<String> getShippingMethods() {

        return shippingMethodResolver.resolveShippingMethods();
    }
}
