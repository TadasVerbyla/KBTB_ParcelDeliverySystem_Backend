package com.example.demo.repositories;

import com.example.demo.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    public Optional<Customer> findById(Long id) {
        Customer customer = entityManager.find(Customer.class, id);
        return customer != null ? Optional.of(customer) : Optional.empty();
    }

    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    public void deleteById(Long id) {
        Customer customer = entityManager.find(Customer.class, id);
        if (customer != null) {
            entityManager.remove(customer);
        }
    }

    public boolean existsById(Long id) {
        Long count = entityManager.createQuery("SELECT COUNT(c) FROM Customer c WHERE c.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }

    public Optional<Customer> findByUsername(String username) {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.username = :username", Customer.class);
        query.setParameter("username", username);
        List<Customer> customers = query.getResultList();
        if (customers.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(customers.get(0));
        }
    }
}