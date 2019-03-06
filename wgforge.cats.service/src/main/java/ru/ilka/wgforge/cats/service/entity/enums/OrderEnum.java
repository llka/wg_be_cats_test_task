package ru.ilka.wgforge.cats.service.entity.enums;

public enum OrderEnum {
    ASC("asc"),
    DESC("desc");

    private final String name;

    OrderEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static OrderEnum getByName(String name) {
        for (OrderEnum value : values()) {
            if (value.name.equals(name)) {
                return value;
            }
        }
        return getDefault();
    }

    public static OrderEnum getDefault() {
        return ASC;
    }
}
