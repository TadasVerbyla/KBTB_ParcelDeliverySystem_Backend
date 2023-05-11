package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/V1/customer")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {this.customerService = customerService; }
    @GetMapping
    public List<Customer> getUsers() { return customerService.getUsers(); }
    @PostMapping
    public void addNewUser(@RequestBody Customer customer) { customerService.addNewUser(customer); }
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) { customerService.deleteUser(userId); }
    @PutMapping(path = "{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String sentParcels,
            @RequestParam(required = false) String incomingParcels,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address) {
        customerService.updateUser(userId, sentParcels, incomingParcels, password, username, email, address);
    }
}
