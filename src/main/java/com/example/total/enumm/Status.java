package com.example.total.enumm;

public enum Status {
    ORDER("Оформлен"), ACCEPT("Принят"), AWAIT("Ожидает"), DONE("Получен");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
