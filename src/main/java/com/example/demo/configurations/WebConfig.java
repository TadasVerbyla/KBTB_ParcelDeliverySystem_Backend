package com.example.demo.configurations;

import com.example.demo.interceptors.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoggingInterceptor loggingInterceptor;

    @Autowired
    public WebConfig(LoggingInterceptor loggingInterceptor) {

        this.loggingInterceptor = loggingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/api/V1/shipping-methods/**");
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/api/V1/customer/**");
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/api/V1/parcel/**");
    }
}