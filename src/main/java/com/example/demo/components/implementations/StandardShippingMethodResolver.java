package com.example.demo.components.implementations;

import com.example.demo.components.interfaces.ShippingMethodResolver;
import com.example.demo.enums.ShippingMethodEnum;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty(name = "holiday.enabled", havingValue = "false", matchIfMissing = true)
public class StandardShippingMethodResolver implements ShippingMethodResolver {
    public List<String> resolveShippingMethods() {
        List<String> shippingMethods = Arrays.stream(ShippingMethodEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return shippingMethods;
    }
}
