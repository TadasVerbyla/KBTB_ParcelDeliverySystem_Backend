package com.example.demo.controllers;

import com.example.demo.dto.CustomerEditDTO;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Parcel;
import com.example.demo.interceptors.LoggingInterceptor;
import com.example.demo.services.CustomerService;
import jakarta.persistence.OptimisticLockException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/V1/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final LoggingInterceptor loggingInterceptor;


    @Autowired
    public CustomerController(CustomerService customerService, LoggingInterceptor loggingInterceptor) {
        this.customerService = customerService;
        this.loggingInterceptor = loggingInterceptor;
    }
    @GetMapping
    public List<Customer> getUsers() { return customerService.getUsers(); }
    @GetMapping(path = "{userId}")
    public Customer getUser(@PathVariable("userId") Long userId){
        return customerService.getUser(userId);
    }
    @PostMapping
    public void addNewUser(@RequestBody Customer customer) { customerService.addNewUser(customer); }
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) { customerService.deleteUser(userId); }
    @PutMapping(path = "{userId}")
    public ResponseEntity<String> updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody CustomerEditDTO customerEditDTO,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            loggingInterceptor.preHandle(request, response, this);

            customerService.updateUser(userId, customerEditDTO.getPassword(), customerEditDTO.getUsername(), customerEditDTO.getEmail(), customerEditDTO.getAddress());

            loggingInterceptor.postHandle(request, response, this, null);
            loggingInterceptor.afterCompletion(request, response, this, null);

            return ResponseEntity.ok("User updated successfully");
        } catch (OptimisticLockException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict occurred while updating user");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
