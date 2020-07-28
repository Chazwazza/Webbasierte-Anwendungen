package de.hsrm.mi.web.jbuec001.login;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestMapping;

import de.hsrm.mi.web.jbuec001.bartboerse.benutzer.BenutzerService;

@Controller
@RequestMapping
public class LoginController {

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    @Autowired private Validator validator = factory.getValidator();
    @Autowired private BenutzerService benutzerService;
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    //Erstes Abrufen der Page
    @GetMapping("/login")
    public String login(Model m) {
        m.addAttribute("loginform", new Loginbean(null, null));
        return "login";
    }
    
    @PostMapping(value = "/login")
    public String login(@ModelAttribute("loginform")Loginbean benutzerForm, 
                        BindingResult result,  Model m) {
        Loginbean neuerBenutzer = new Loginbean(benutzerForm.getUsername(), benutzerForm.getPassword());
        logger.info("new USER: " + neuerBenutzer.toString());
        if  (benutzerForm != null) {
            Set<ConstraintViolation<Loginbean>> violations = validator.validate(benutzerForm);
            if (!violations.isEmpty()) {
                for (ConstraintViolation<Loginbean> violation : violations) {
                    //logger.error("ERROR", violation.getMessage() + " bei " + violation.getPropertyPath());
                    String attribute = violation.getPropertyPath().toString();
                    String msg = violation.getMessage();
                    ObjectError error = new ObjectError(attribute, msg);
                    result.addError(error);
                }
            }
        }
        
        logger.info("Die Userdata " + neuerBenutzer.toString());
        logger.info("Binding Result", result);
        Boolean checkLogin = benutzerService.pruefeLogin(neuerBenutzer.getUsername(), neuerBenutzer.getPassword());
        if (checkLogin) {
            return "angebote/liste";
        } else {
            return "login";
        }
    }
}