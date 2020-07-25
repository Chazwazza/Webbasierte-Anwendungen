package de.hsrm.mi.web.jbuec001.bartboerse;

import java.time.LocalDate;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.SessionAttributes;



@Controller
@Validated
@SessionAttributes(names = {"angebote"})
public class BratenAngebotController {
        
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    @Autowired private Validator validator = factory.getValidator();
    private final Logger logger = LoggerFactory.getLogger(BratenAngebotController.class);
    private ArrayList<BratenDaten> bratenlist;


    @ModelAttribute("angebote")
    public void initListe(Model m) {
        bratenlist = new ArrayList<BratenDaten>();
        BratenDaten a = new BratenDaten("Jöndhard", "Vollradsisrode", LocalDate.now(), "leckerer Grünkohlbraten", 25);
        BratenDaten b = new BratenDaten("Friedfert", "Diedelingen", LocalDate.now(), "Gummibärchenbraten, frisch", 75);
        bratenlist.add(a);
        bratenlist.add(b);
        m.addAttribute("angebote", bratenlist);
    }


    @PostMapping(value = "/angebot/neu")
    public String angebot(  @ModelAttribute("angebotform") BratenDaten bratenDaten, 
                            BindingResult result, Model m,
                            @ModelAttribute("angebote")ArrayList<BratenDaten> lst) {
        Set<ConstraintViolation<BratenDaten>> violations = validator.validate(bratenDaten);
        BratenDaten b = new BratenDaten(bratenDaten.getName(), bratenDaten.getAbholort(), 
                                        bratenDaten.getHaltbarbis(), bratenDaten.getBeschreibung(), bratenDaten.getVgradAuswahl());
        
        if (!violations.isEmpty()) {
            for (ConstraintViolation<BratenDaten> violation : violations) {
                logger.error("ERROR " +  violation.getMessage() + " bei " + violation.getPropertyPath());
                String attribute = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                ObjectError error = new ObjectError(attribute, message);
                result.addError(error);
            }
            logger.info("NEW BINDINGRESULTS = {}", result);
            if(result.hasErrors()) {
                logger.error("Validierungsfehler lelel");
                logger.info("getFieldError", result.getFieldError("name"));
                //m.addAttribute("angebotform", result);
                return "angebote/neu";
            }
        }
        lst.add(b);
        m.addAttribute("angebote", lst);
        return "angebote/liste";
    }

    //Zum Deleten
    @GetMapping("/angebot/del/{n}")
    public String getDelete(Model m,
                            @ModelAttribute("angebote")ArrayList<BratenDaten> lst,
                            @ModelAttribute("angebotform")BratenDaten bratenDaten,
                            @PathVariable String n){
        logger.info("Ich lösche jetzt was!");
        int foo = Integer.parseInt(n);
        lst.remove(foo-1);
        m.addAttribute("hinweis", " " + (foo) + " gelöscht");
        return "angebote/liste";
    }

    //Editieren
    @GetMapping("/angebot/neu/{n}")
    public String editEntry(Model m,
                            @PathVariable String n,
                            @ModelAttribute("angebote")ArrayList<BratenDaten> lst,
                            @ModelAttribute("angebotform")BratenDaten bratenDaten) {
        int foo = Integer.parseInt(n);
        BratenDaten bratan = lst.get(foo-1);
        lst.remove(foo-1);
        m.addAttribute("angebotform", bratan);
        return "angebote/neu";
    }

    @GetMapping("/angebot/neu")
    public String createBraten(Model m, Locale locale) {
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("angebotform", new BratenDaten());
        return "angebote/neu";
    }

    //Erstes Aufrufen von Path /angebot
    @GetMapping("/angebot")
    public String angebot(Model m, Locale locale) {
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("angebotform", new BratenDaten(null, null, null, null, 0));
        return "angebote/liste";
    }
}