package com.example.total.enumm;

public enum Category {
    SENSOR("ДАТЧИК"), PLUG("РОЗЕТКА"),SWITCH("ВЫКЛЮЧАТЕЛЬ"),BULB("ЛАМПА"),HUB("ХАБ"),BUTTON("КНОПКА");
    private String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
