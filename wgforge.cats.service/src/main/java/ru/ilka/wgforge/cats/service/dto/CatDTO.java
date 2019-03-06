package ru.ilka.wgforge.cats.service.dto;

import java.util.Objects;

public class CatDTO {
    private String name;
    private String color;
    private int tailLength;
    private int whiskersLength;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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
        if (!(o instanceof CatDTO)) return false;
        CatDTO catDTO = (CatDTO) o;
        return tailLength == catDTO.tailLength &&
                whiskersLength == catDTO.whiskersLength &&
                name.equals(catDTO.name) &&
                color.equals(catDTO.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color, tailLength, whiskersLength);
    }
}
