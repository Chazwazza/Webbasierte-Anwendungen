package de.hsrm.mi.web.jbuec001.bartboerse.bratui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import de.hsrm.mi.web.jbuec001.bartboerse.bratrepo.Braten;
import de.hsrm.mi.web.jbuec001.bartboerse.bratservice.Bratenservice;



@Controller
@Validated
public class BratenWebController {
        
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    @Autowired private Validator validator = factory.getValidator();
    @Autowired private Bratenservice bratenservice;
    private final Logger logger = LoggerFactory.getLogger(BratenWebController.class);
    private ArrayList<Braten> bratenlist;


    @ModelAttribute("braten/angebote")
    public void initListe(Model m) {
        bratenlist = new ArrayList<Braten>();
        Braten a = new Braten("Vollradsisrode", LocalDate.now(), "leckerer Grünkohlbraten", 25);
        Braten b = new Braten("Diedelingen", LocalDate.now(), "Gummibärchenbraten, frisch", 75);
        bratenlist.add(a);
        bratenlist.add(b);
        m.addAttribute("angebote", bratenlist);
    }


    @PostMapping(value = "braten/angebot/neu")
    public String angebot(@ModelAttribute("angebotform")Braten braten, @ModelAttribute("loggedinusername") String user,
                          BindingResult result, Model m) {
        logger.info("Jetzt gibts neue Braten von: " + user);
        List<Braten> lst = bratenservice.alleBraten();
        Set<ConstraintViolation<Braten>> violations = validator.validate(braten);
        Braten b = new Braten(braten.getAbholort(), braten.getHaltbarbis(), braten.getBeschreibung(), braten.getVgradAuswahl());
        logger.info("Bratandatan: {}", b.toString());
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Braten> violation : violations) {
                logger.error("ERROR " +  violation.getMessage() + " bei " + violation.getPropertyPath());
                String attribute = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                ObjectError error = new ObjectError(attribute, message);
                result.addError(error);
            }
            logger.info("NEW BINDINGRESULTS = {}", result);
            if(result.hasErrors()) {
                logger.info("getFieldError", result.getFieldError("name"));
                //m.addAttribute("angebotform", result);
                return "redirect:/braten/neu";
            }
        }
        
        Braten b2 = bratenservice.editBraten(user, b);
        lst.add(b2);
        m.addAttribute("angebote", lst);
        return "braten/liste";
    }

    //Zum Deleten
    @GetMapping("braten/angebot/del/{n}")
    public String getDelete(Model m, @PathVariable String n){
        logger.info("Ich lösche jetzt was!");
        int foo = Integer.parseInt(n);
        bratenservice.loescheBraten(foo);
        m.addAttribute("hinweis", " " + (foo) + " gelöscht");
        return "redirect:/braten/liste";
    }

    //Editieren
    @GetMapping("braten/angebot/neu/{n}")
    public String editEntry(Model m, @PathVariable String n) {
        int foo = Integer.parseInt(n);
        List<Braten> lst = bratenservice.alleBraten();
        Braten bratan = lst.get(foo);
        bratenservice.loescheBraten(foo);
        m.addAttribute("angebotform", bratan);
        return "braten/neu";
    }

    @GetMapping("braten/angebot/neu")
    public String createBraten(Model m, Locale locale) {
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("angebotform", new Braten());
        return "braten/neu";
    }

    //Erstes Aufrufen von Path /angebot
    @GetMapping("braten/angebot")
    public String angebot(Model m, Locale locale) {
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("angebote", bratenservice.alleBraten());
        return "braten/liste";
    }
}