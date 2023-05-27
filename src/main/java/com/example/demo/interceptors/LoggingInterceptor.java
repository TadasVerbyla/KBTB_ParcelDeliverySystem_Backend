package com.example.demo.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Value("${interceptor.logging.enabled:true}")
    private boolean loggingEnabled;

    private Logger logger = Logger.getLogger("MyLog");
    private FileHandler fh;

    public LoggingInterceptor() {
        try {
            // This block configure the logger with handler and formatter
            fh = new FileHandler("MyLogFile.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!loggingEnabled) {
            return true; // Skip interceptor if logging is disabled
        }
        // Log the username, user's rights, time, and method name before the request is processed
        String username = request.getRemoteUser();
        String rights = ""; // Replace with the logic to fetch the user's rights
        String methodName = getMethodName(handler);
        String className = getClassName(handler);
        String startTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logger.info("User: " + username + " | Rights: " + rights + " | Class: " + className +
                " | Method: " + methodName + " | Start Time: " + startTime);
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

        logger.info("Memory Usage: " + usedMemory + " bytes / " + maxMemory + " bytes");
    }

    private String getMethodName(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            return handlerMethod.getMethod().getName();
        }
        return "";
    }

    private String getClassName(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            return handlerMethod.getBeanType().getSimpleName();
        }
        return "";
    }
}