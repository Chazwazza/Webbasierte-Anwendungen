package de.hsrm.mi.web.jbuec001.login;

import javax.validation.Valid;

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

import de.hsrm.mi.web.jbuec001.bartboerse.benutzer.BenutzerService;

@Controller
@RequestMapping
public class LoginController {

    @Autowired BenutzerService benutzerService;
    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @GetMapping("/login")
    public String login(Model m) {
        m.addAttribute("loginform", new Loginbean("", ""));
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute("loginform")Loginbean loginform, 
                        BindingResult result,  Model m) {
        Boolean succLogin = benutzerService.pruefeLogin(loginform.getUsername(), loginform.getPassword());
        if (succLogin) {
            return "angebote/liste";
        } else {
            return "login";
        }
    }
}