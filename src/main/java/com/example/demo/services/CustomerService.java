package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, JdbcTemplate jdbcTemplate, BCryptPasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Customer> getUsers() { return customerRepository.findAll(); }

    public Customer getUser(Long userId){
        return customerRepository.findById(userId).orElseThrow(() -> new IllegalStateException("Specified customer does not exist. "));
    };
    public Optional<Customer> getUserByUsername(String username) {
        Optional<Customer> customer = customerRepository.findByUsername(username);
        return customer;
    }

    @Transactional
    public void addNewUser(Customer customer) {
        String encryptedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encryptedPassword);
        customerRepository.save(customer);
    }

    public void deleteUser(Long userId) {
        if (!customerRepository.existsById(userId)) {
            throw new IllegalStateException("Specified entry does not exist. ");
        }
        customerRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String password, String username, String email, String address) {
        Customer customer = customerRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("Specified customer does not exist. "));

        String encryptedPassword = passwordEncoder.encode(password);

        String sql = "UPDATE Customer SET password = ?, username = ?, email = ?, address = ? WHERE id = ?";

        jdbcTemplate.update(sql, ps -> {
            ps.setString(1, encryptedPassword);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setLong(5, userId);
        });

        if (password != null) customer.setPassword(encryptedPassword);
        if (username != null) customer.setUsername(username);
        if (email != null) customer.setEmail(email);
        if (address != null) customer.setAddress(address);
    }

}
