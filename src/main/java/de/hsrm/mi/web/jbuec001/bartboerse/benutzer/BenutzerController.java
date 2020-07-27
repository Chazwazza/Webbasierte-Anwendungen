package de.hsrm.mi.web.jbuec001.bartboerse.benutzer;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BenutzerController {
    
    private Logger logger = LoggerFactory.getLogger(BenutzerController.class);
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    @Autowired private Validator validator = factory.getValidator();
    @Autowired private BenutzerService benutzerService;
    
    @GetMapping("/benutzer")
    public String benutzer(Model m) {
        m.addAttribute("benutzerform", new Benutzer(null, null, null, false));
        return "benutzerui/benutzerregistrieren";
    } 

    @PostMapping("/benutzer")
    public String benutzer(@ModelAttribute("benutzerform")Benutzer benutzerForm, 
                            BindingResult result, Model m) {
    logger.info("benutzerForm: " + benutzerForm.toString());
    Set<ConstraintViolation<Benutzer>> violations = validator.validate(benutzerForm);
    Benutzer b = new Benutzer(benutzerForm.getLoginname(), benutzerForm.getPasswort(), (benutzerForm.getLoginname() + " Ried"), benutzerForm.isNutzungsbedingungenok());
    if(!violations.isEmpty()) {
        for(ConstraintViolation<Benutzer> violation : violations) {
            logger.error("Error found: " + violation.getMessage() + " at " + violation.getPropertyPath());
            String attribute = violation.getPropertyPath().toString();
            String msg = violation.getMessage();
            ObjectError error = new ObjectError(attribute, msg);
            result.addError(error);
        }
    }

    logger.info("Die Bindungresults = {}", result);
    if(result.hasErrors() || b.isNutzungsbedingungenok()) {
        logger.error("Validationserror: " + result.getFieldError());
        return "benutzerui/benutzerregistrieren";
    } else {

        benutzerService.registriereBenutzer(b);
        return "login";
    }

    }
}