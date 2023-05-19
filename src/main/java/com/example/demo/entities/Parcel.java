package com.example.demo.entities;

import com.example.demo.enums.ParcelEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    //@JoinColumn(name = "sender_id")
    @JsonIgnore
    private Customer sender;
    @ManyToOne
    //@JoinColumn(name = "receiver_id")
    @JsonIgnore
    private Customer receiver;

    private String deliveryAddress;
    private ParcelEnum.Size size;
    @JsonProperty("_senderId")
    public Long getSenderId() {
        return sender != null ? sender.getId() : null;
    }

    // Getter method for receiverId
    @JsonProperty("_receiverId")
    public Long getReceiverId() {
        return receiver != null ? receiver.getId() : null;
    }

    public Parcel() {
    }

    public Parcel(Long id, Customer sender, Customer receiver, String deliveryAddress, ParcelEnum.Size size) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.deliveryAddress = deliveryAddress;
        this.size = size;
    }

    public Parcel(Customer sender, Customer receiver, String deliveryAddress, ParcelEnum.Size size) {
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

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public Customer getReceiver() {
        return receiver;
    }

    public void setReceiver(Customer receiver) {
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
   /* @PostLoad
    private void populateSenderAndReceiverIds() {
        if (sender != null) {
            senderId = sender.getId();
        }
        if (receiver != null) {
            receiverId = receiver.getId();
        }
    }*/

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
