package com.example.demo.components.interfaces;

import com.example.demo.enums.ShippingMethodEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface ShippingMethodResolver {
    public List<String> resolveShippingMethods() ;
}
