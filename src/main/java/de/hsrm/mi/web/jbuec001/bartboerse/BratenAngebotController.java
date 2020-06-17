package de.hsrm.mi.web.jbuec001.bartboerse;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@RequestMapping
@SessionAttributes(names = {"angebote"})
public class BratenAngebotController {
        
    ArrayList<BratenDaten> bratenlist;
    Logger logger = LoggerFactory.getLogger(BratenAngebotController.class);

    @ModelAttribute("angebote")
    public void initListe(Model m) {
        bratenlist = new ArrayList<BratenDaten>();
        BratenDaten a = new BratenDaten("Jöndhard", "Vollradsisrode", LocalDate.now(), "leckerer Grünkohlbraten");
        BratenDaten b = new BratenDaten("Friedfert", "Diedelingen", LocalDate.now(), "Gummibärchenbraten, frisch");
        bratenlist.add(a);
        bratenlist.add(b);
        m.addAttribute("angebote", bratenlist);
    }

    @PostMapping(value="/angebot/neu")
    public String angebot(  @ModelAttribute("angebote")ArrayList<BratenDaten> lst,
                            @Valid @ModelAttribute("angebotform")BratenDaten bratenDaten, 
                            BindingResult result, Model m) {
        BratenDaten b = new BratenDaten(bratenDaten.getName(), bratenDaten.getAbholort(), 
                                        bratenDaten.getHaltbarbis(), bratenDaten.getBeschreibung());
        logger.info("BindungResults = {}", result);
        logger.info("Die Bratendaten = {}", b.toString());
        if(result.hasErrors()) {
            logger.error("Results {}", result);
            return "angebote/liste";
        } else {
            lst.add(b);
            m.addAttribute("angebote", lst);
            return "angebote/liste";
        }
    }

    @GetMapping("/angebot/del/{n}")
    public String getDelete(Model m,
                            @ModelAttribute("angebote")ArrayList<BratenDaten> lst,
                            @ModelAttribute("angebotform")BratenDaten bratenDaten,
                            @PathVariable String n){
        int foo = Integer.parseInt(n);
        lst.remove(foo-1);
        m.addAttribute("hinweis", " " + (foo) + " gelöscht");
        return "angebote/liste";
    }

    @GetMapping("/angebot/neu/{n}")
    public String editEntry(Model m,
                            @PathVariable String n,
                            @ModelAttribute("angebote")ArrayList<BratenDaten> lst,
                            @ModelAttribute("angebotform")BratenDaten bratenDaten) {
        int foo = Integer.parseInt(n);
        BratenDaten bratan = lst.get(foo-1);
        m.addAttribute("name", bratan.getName());
        m.addAttribute("abholort", bratan.getAbholort());
        m.addAttribute("beschreibung", bratan.getBeschreibung());
        m.addAttribute("haltbarbis", bratan.getHaltbarbis());
        return "angebote/neu";
    }

    @GetMapping("/angebot/neu")
    public String createBraten(Model m) {
        m.addAttribute("angebotform", new BratenDaten(null, null, null, null));
        return "angebote/neu";
    }
    
    @GetMapping("/angebot")
    public String angebot(Model m) {
        return "angebote/liste";
    }
}