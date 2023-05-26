package com.example.demo.controllers;

import com.example.demo.dto.CustomerEditDTO;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Parcel;
import com.example.demo.services.CustomerService;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/V1/customer")
@CrossOrigin("*")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {this.customerService = customerService; }
    @GetMapping
    public List<Customer> getUsers() { return customerService.getUsers(); }
    @GetMapping("/{userId}")
    public Customer getUser(@PathVariable("userId") Long userId){
        return customerService.getUser(userId);
    }
    @GetMapping("/name/{username}")
    public ResponseEntity<Long> getUserIdByUsername(@PathVariable("username") String username) {
        Optional<Customer> user = customerService.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user.get().getId());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public void addNewUser(@RequestBody Customer customer) { customerService.addNewUser(customer); }
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) { customerService.deleteUser(userId); }
    @PutMapping(path = "{userId}")
    public ResponseEntity<String> updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody CustomerEditDTO customerEditDTO) {
        try {
            customerService.updateUser(userId, customerEditDTO.getPassword(), customerEditDTO.getUsername(), customerEditDTO.getEmail(), customerEditDTO.getAddress(), customerEditDTO.getVersion());
            return ResponseEntity.ok("User updated successfully");
        }catch (OptimisticLockException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict occurred while updating user");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating user");
        }

    }
}
