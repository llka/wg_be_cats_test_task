package ru.ilka.wgforge.cats.service.entity.enums;

import org.springframework.http.HttpStatus;
import ru.ilka.wgforge.cats.service.entity.Cat;
import ru.ilka.wgforge.cats.service.exception.RestException;

import java.util.function.Function;
import java.util.stream.Stream;

public enum CatSortingAttributeEnum {
    NAME("name", Cat::getName),
    COLOR("color", Cat::getColor),
    TAIL_LENGTH("tail_length", cat -> String.valueOf(cat.getTailLength())),
    WHISKERS_LENGTH("whiskers_length", cat -> String.valueOf(cat.getWhiskersLength()));

    private final String name;
    private final Function<Cat, String> compareBy;

    CatSortingAttributeEnum(String name, Function<Cat, String> compareBy) {
        this.name = name;
        this.compareBy = compareBy;
    }

    public String getName() {
        return name;
    }

    public Function<Cat, String> getCompareBy() {
        return compareBy;
    }

    public static CatSortingAttributeEnum getByName(String name) {
        for (CatSortingAttributeEnum value : values()) {
            if (value.name.equals(name)) {
                return value;
            }
        }
        throw new RestException("Unknown attribute! Available attributes list: " + String.join(", ",
                Stream.of(values()).map(CatSortingAttributeEnum::getName).toArray(String[]::new)),
                HttpStatus.BAD_REQUEST);
    }

}
