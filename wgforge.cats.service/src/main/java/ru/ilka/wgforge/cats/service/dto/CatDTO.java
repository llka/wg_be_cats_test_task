package ru.ilka.wgforge.cats.service.dto;

import ru.ilka.wgforge.cats.service.validation.annotation.ValidCatColor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

public class CatDTO {
    @NotBlank(message = "Name must be not blank!")
    private String name;
    @NotNull(message = "Color must be not null!")
    @ValidCatColor
    private String color;
    @PositiveOrZero(message = "Length cannot have negative value!")
    private int tailLength;
    @PositiveOrZero(message = "Length cannot have negative value!")
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
