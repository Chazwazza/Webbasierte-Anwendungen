package de.hsrm.mi.web.jbuec001.login;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.hsrm.mi.web.jbuec001.bartboerse.benutzer.Benutzer;
import de.hsrm.mi.web.jbuec001.bartboerse.benutzer.BenutzerService;

@Controller
@RequestMapping
public class LoginController {

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    @Autowired private Validator validator = factory.getValidator();
    @Autowired BenutzerService benutzerService;
    Logger logger = LoggerFactory.getLogger(LoginController.class);


    @GetMapping("/login")
    public String login(Model m) {
        m.addAttribute("loginform", new Benutzer("", "", "", false));
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute("loginform")Benutzer benutzerForm, 
                        BindingResult result,  Model m) {
        Benutzer neuerBenutzer = new Benutzer(benutzerForm.getLoginname(), benutzerForm.getPasswort(), benutzerForm.getVollername(), benutzerForm.isNutzungsbedingungenok());
        Boolean checkLogin = benutzerService.pruefeLogin(neuerBenutzer.getLoginname(), neuerBenutzer.getPasswort());
        if (checkLogin) {
            benutzerService.registriereBenutzer(neuerBenutzer);
            return "angebote/liste";
        } else {
            return "login";
        }
    }
}