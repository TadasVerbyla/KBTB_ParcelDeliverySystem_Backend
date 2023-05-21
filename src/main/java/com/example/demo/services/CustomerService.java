package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Parcel;
import com.example.demo.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {this.customerRepository = customerRepository; }
    public List<Customer> getUsers() { return customerRepository.findAll(); }
    public Customer getUser(Long userId){
        return customerRepository.findById(userId).orElseThrow(() -> new IllegalStateException("Specified customer does not exist. "));
    };
    public void addNewUser(Customer customer) { customerRepository.save(customer); }
    public void deleteUser(Long userId) {
        if (!customerRepository.existsById(userId)) {
            throw new IllegalStateException("Specified entry does not exist. ");
        }
        customerRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, List<Parcel> sentParcels, List<Parcel> incomingParcels, String password, String username, String email, String address) {

        Customer customer = customerRepository.findById(userId).orElseThrow(() -> new IllegalStateException("Specified entry does not exist. "));

        if (sentParcels != null) {
            customer.setSentParcels(sentParcels);
        }
        if (incomingParcels != null) {
            customer.setIncomingParcels(incomingParcels);
        }
        if (password != null &&
                password.length() > 0 &&
                !Objects.equals(customer.getPassword(), password)) {
            customer.setPassword(password);
        }
        if (username != null &&
                username.length() > 0 &&
                !Objects.equals(customer.getUsername(), username)) {
            customer.setUsername(username);
        }
        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(customer.getEmail(), email)) {
            customer.setEmail(email);
        }
        if (address != null &&
                address.length() > 0 &&
                !Objects.equals(customer.getAddress(), address)) {
            customer.setAddress(address);
        }

    }
}
