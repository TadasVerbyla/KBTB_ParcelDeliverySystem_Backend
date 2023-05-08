package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String deliveryAddress;
    private String recipient;
    private Integer weight;

    public Parcel() {
    }

    public Parcel(Long id, String deliveryAddress, String recipient, Integer weight) {
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.recipient = recipient;
        this.weight = weight;
    }

    public Parcel(String deliveryAddress, String recipient, Integer weight) {
        this.deliveryAddress = deliveryAddress;
        this.recipient = recipient;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "parcel{" +
                "id=" + id +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", recipient='" + recipient + '\'' +
                ", weight=" + weight +
                '}';
    }
}
