package de.hsrm.mi.web.jbuec001.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;   

@Controller
@RequestMapping
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @GetMapping("/login")
    public String login() {
        System.out.println("GetLogin");
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam("username")String username, 
                        @RequestParam("passwort")String passwort, Model m) {
        System.out.println("PostLogin");
        logger.info("passwort = {}", passwort);
        String laenge = Integer.toString(username.length());
        String pw = username + laenge;
        if(username.isEmpty()) {
            m.addAttribute("hinweis", "Kein Username.");
            return "login";
        }
        if(passwort.equals(pw)) {
            m.addAttribute("user", username);
            return "hellopage";
        } else {
            m.addAttribute("hinweis", "Falsches Passwort");
            logger.error("Passwort ist {} und nicht {}", pw, passwort);
            return "login";
        }
    }
}