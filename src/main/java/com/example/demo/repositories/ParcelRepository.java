package com.example.demo.repositories;
import com.example.demo.entities.Parcel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ParcelRepository {
        @PersistenceContext
        private EntityManager entityManager;

        private final Logger log = LoggerFactory.getLogger(ParcelRepository.class);

        public List<Parcel> findAll() {
            TypedQuery<Parcel> query = entityManager.createQuery("SELECT p FROM Parcel p", Parcel.class);
            return query.getResultList();
        }

        public Optional<Parcel> findById(Long id) {
            Parcel parcel = entityManager.find(Parcel.class, id);
            return parcel != null ? Optional.of(parcel) : Optional.empty();
        }

        public void save(Parcel parcel) {
            try {
                entityManager.persist(parcel);
            } catch(Exception e) {
                log.error("Error occurred while saving parcel", e);
                throw e;
            }
        }

        public void deleteById(Long id) {
            try {
                Parcel parcel = entityManager.find(Parcel.class, id);
                if (parcel != null) {
                    entityManager.remove(parcel);
                }
            } catch (EmptyResultDataAccessException e) {
                log.error("Error occurred while deleting parcel with id: " + id, e);
                throw e;
            }
        }

        public boolean existsById(Long id) {
            Long count = entityManager.createQuery("SELECT COUNT(p) FROM Parcel p WHERE p.id = :id", Long.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return count > 0;
        }
    }