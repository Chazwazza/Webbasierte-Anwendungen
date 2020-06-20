package de.hsrm.mi.web.jbuec001.login;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @GetMapping("/login")
    public String login(Model m) {
        m.addAttribute("loginform", new Loginbean("", ""));
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginform")Loginbean loginform, 
                        BindingResult result,  Model m) {
        logger.info("Number of Errors = {}", result);
        if(result.hasErrors()) {
            logger.info("Login failed");
            return "login";
        }
        logger.info("passwort = {}", loginform.toString());
        m.addAttribute("user", loginform);
        return "angebote/liste";
        
        
        /** 
        if(username.isEmpty()) {
            m.addAttribute("hinweis", "Kein Username.");
            return "login";
        }
        if(password.equals(pw)) {
            m.addAttribute("user", username);
            return "hellopage";
        } else {
            m.addAttribute("hinweis", "Falsches Passwort");
            logger.error("Passwort ist {} und nicht {}", pw, password);
            return "login"; 
        }*/
    }
}