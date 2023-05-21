package com.example.demo.enums;

public enum ShippingMethodEnum {
    TERMINAL_TERMINAL(1,"Terminal-to-terminal"),
    COURIER_TERMINAL(2,"Home-to-terminal"),
    COURIER_COURIER(3,"Home-to-home"),
    TERMINAL_COURIER(4,"Terminal-to-home");

    private int code;
    private String description;


    ShippingMethodEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }

}
