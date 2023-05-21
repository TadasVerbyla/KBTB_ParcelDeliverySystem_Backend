package com.example.demo.entities;

import com.example.demo.enums.ShippingMethodEnum;
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
    @JsonIgnore
    private Customer sender;
    @ManyToOne
    @JsonIgnore
    private Customer receiver;
    @Transient
    @JsonProperty("senderId")
    private Long senderId;
    @Transient
    @JsonProperty("receiverId")
    private Long receiverId;

    private String deliveryAddress;
    @Enumerated
    private ParcelEnum.Size size;
    @Enumerated
    private ShippingMethodEnum deliveryMethod;
    /*@JsonProperty("_senderId")
    public Long getSenderId() {
        return sender != null ? sender.getId() : null;
    }

    @JsonProperty("_receiverId")
    public Long getReceiverId() {
        return receiver != null ? receiver.getId() : null;
    }*/

    public Parcel() {
    }

    public Parcel(Long id, Customer sender, Customer receiver, ShippingMethodEnum deliveryMethod, String deliveryAddress, ParcelEnum.Size size) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.deliveryMethod = deliveryMethod;
        this.deliveryAddress = deliveryAddress;
        this.size = size;
    }

    public Parcel(Customer sender, Customer receiver, ShippingMethodEnum deliveryMethod, String deliveryAddress, ParcelEnum.Size size) {
        this.sender = sender;
        this.receiver = receiver;
        this.deliveryMethod = deliveryMethod;
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

    public Long getSenderId() {

        return this.senderId == null ? (sender != null ? sender.getId() : null) : this.senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return this.receiverId == null ? (receiver != null ? receiver.getId() : null) : this.receiverId;
       //return receiver != null ? receiver.getId() : null;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
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

    public ShippingMethodEnum getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(ShippingMethodEnum deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
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
