package ru.ilka.wgforge.testtask.entity;

import java.util.Objects;

public class Cat {
    private String name;
    private CatColorEnum color;
    private int tailLength;
    private int whiskersLength;

    public Cat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CatColorEnum getColor() {
        return color;
    }

    public void setColor(CatColorEnum color) {
        this.color = color;
    }

    public int getTailLength() {
        return tailLength;
    }

    public void setTailLength(int tailLength) {
        this.tailLength = tailLength;
    }

    public int getWhiskersLength() {
        return whiskersLength;
    }

    public void setWhiskersLength(int whiskersLength) {
        this.whiskersLength = whiskersLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return tailLength == cat.tailLength &&
                whiskersLength == cat.whiskersLength &&
                Objects.equals(name, cat.name) &&
                color == cat.color;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, color, tailLength, whiskersLength);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", tailLength=" + tailLength +
                ", whiskersLength=" + whiskersLength +
                '}';
    }
}
