package com.example.demo.enums;

public enum HolidayShippingMethodEnum {
    TERMINAL_TERMINAL(6,"Terminal-to-terminal"),
    TERMINAL_COURIER(8,"Terminal-to-home"),//or the other way
    EXPRESS(9,"Express delivery by courier");
    private int code;
    private String description;


    HolidayShippingMethodEnum(int code, String description){
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
