package com.example.demo.enums;

public enum ShippingMethodEnum {
    TERMINAL_TERMINAL(1),
    COURIER_TERMINAL(2),
    COURIER_COURIER(3),
    TERMINAL_COURIER(4),
    EXPRESS(5);
    private int id;

    ShippingMethodEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
