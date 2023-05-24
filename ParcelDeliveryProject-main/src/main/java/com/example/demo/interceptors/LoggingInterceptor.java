package com.example.demo.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Log the username, time, and method name before the request is processed
        String username = request.getRemoteUser();
        String methodName = request.getMethod();
        long startTime = System.currentTimeMillis();
        System.out.println("User: " + username + " | Method: " + methodName + " | Start Time: " + startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // additional logging or modify the model and view if needed
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Log memory usage statistics
        MemoryUsage memoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        long usedMemory = memoryUsage.getUsed();
        long maxMemory = memoryUsage.getMax();

        System.out.println("Memory Usage: " + usedMemory + " bytes / " + maxMemory + " bytes");
    }
}