package ru.ilka.wgforge.cats.service.entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.ilka.wgforge.cats.service.entity.enums.CatColorEnum;
import ru.ilka.wgforge.cats.service.util.CatColorEnumConverter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Entity
@Table(name = "cats",
        indexes = {@Index(name = "INDEX_NAME_UNIQUE", unique = true, columnList = "name")})
@TypeDef(name = "CatColorEnumConverter", typeClass = CatColorEnumConverter.class)
public class Cat implements DatabaseEntity {
    @Id
    @NotBlank(message = "Name must be not blank!")
    @Column(name = "name")
    private String name;
    @Column(name = "color")
    @NotNull(message = "Color must be not null!")
    @Type(type = "CatColorEnumConverter")
    private CatColorEnum color;
    @Column(name = "tail_length")
    @PositiveOrZero(message = "Length cannot have negative value!")
    private int tailLength;
    @Column(name = "whiskers_length")
    @PositiveOrZero(message = "Length cannot have negative value!")
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
