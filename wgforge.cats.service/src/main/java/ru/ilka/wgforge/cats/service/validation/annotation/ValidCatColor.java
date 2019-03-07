package ru.ilka.wgforge.cats.service.validation.annotation;

import ru.ilka.wgforge.cats.service.validation.validator.CatColorValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CatColorValidator.class)
public @interface ValidCatColor {
    String message() default "Unknown cat color!";

    String value() default "Unknown cat color!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
