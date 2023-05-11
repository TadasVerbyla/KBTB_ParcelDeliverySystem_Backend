package com.example.demo.entities;

import com.example.demo.enums.ParcelEnum;
import jakarta.persistence.*;

@Entity
@Table
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sender;
    private String receiver;
    private String deliveryAddress;
    private ParcelEnum.Size size;

    public Parcel() {
    }

    public Parcel(Long id, String sender, String receiver, String deliveryAddress, ParcelEnum.Size size) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.deliveryAddress = deliveryAddress;
        this.size = size;
    }

    public Parcel(String sender, String receiver, String deliveryAddress, ParcelEnum.Size size) {
        this.sender = sender;
        this.receiver = receiver;
        this.deliveryAddress = deliveryAddress;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public ParcelEnum.Size getSize() {
        return size;
    }

    public void setSize(ParcelEnum.Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", size=" + size +
                '}';
    }
}
