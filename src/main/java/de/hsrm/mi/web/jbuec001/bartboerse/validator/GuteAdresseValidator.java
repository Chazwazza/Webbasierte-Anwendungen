package de.hsrm.mi.web.jbuec001.bartboerse.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.*;

public class GuteAdresseValidator implements ConstraintValidator<GuteAdresse, String> {

    protected String adr;
    
    @Override
    public void initialize(GuteAdresse constraintAnnotation) {
        this.adr = constraintAnnotation.toString();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //Regex 3-15 zeichen - 1-4 zahlen - komma - genau 5 zahlen - 2 bis 15 zeichen
        Pattern.matches("[a-zA-Z]{3,15}[0-9]{1,4}, [0-9]{5}[a-zA-Z]{2,15}", this.adr);
        return false;
    }
    
}