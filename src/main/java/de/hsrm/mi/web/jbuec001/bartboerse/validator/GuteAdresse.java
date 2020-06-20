package de.hsrm.mi.web.jbuec001.bartboerse.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Min;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GuteAdresseValidator.class)
@Min(1)
@Documented
public @interface GuteAdresse {
    String message() default "Keine Gültige Adresse";
    Class<? extends Payload>[] payload() default { };
    Class<?>[] groups() default { };
    //String value();
}