package ru.ilka.wgforge.cats.service.validation.validator;

import ru.ilka.wgforge.cats.service.entity.enums.CatColorEnum;
import ru.ilka.wgforge.cats.service.validation.annotation.ValidCatColor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CatColorValidator implements ConstraintValidator<ValidCatColor, String> {

    @Override
    public void initialize(ValidCatColor constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        if (CatColorEnum.getByName(value) == CatColorEnum.getDefault()
                && !CatColorEnum.getDefault().getName().equalsIgnoreCase(value)) {
            return false;
        }
        return true;
    }
}
