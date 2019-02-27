package ru.ilka.wgforge.testtask.entity;

public enum CatColorEnum {
    RED("red"),
    RED_BLACK_WHITE("red & black & white"),
    WHITE("white"),
    BLACK_WHITE("black & white"),
    RED_WHITE("red & white"),
    BLACK("black");

    private final String name;

    CatColorEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CatColorEnum getByName(String name) {
        for (CatColorEnum value : values()) {
            if (value.name.equals(name)) {
                return value;
            }
        }
        return getDefault();
    }

    public static CatColorEnum getDefault() {
        return BLACK;
    }
}
