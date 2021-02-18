package ru.appline.rencreditFramework.utils;

public enum Currencies {
    RUB("Рубли"),
    USD("Доллары США");

    private final String value;
    Currencies(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
