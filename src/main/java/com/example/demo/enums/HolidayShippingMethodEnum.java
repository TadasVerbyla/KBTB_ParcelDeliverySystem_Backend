package com.example.demo.enums;

public enum HolidayShippingMethodEnum {
    TERMINAL_TERMINAL(6),
    COURIER_COURIER(7),
    TERMINAL_COURIER(8),
    EXPRESS(9);
    private int id;

    HolidayShippingMethodEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
