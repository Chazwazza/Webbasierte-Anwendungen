package de.hsrm.mi.web.jbuec001.bartboerse;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes(names = {"angebote"})
public class BratenAngebotController {
        
    ArrayList<BratenDaten> bratenlist;
    Logger logger = LoggerFactory.getLogger(BratenAngebotController.class);

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
    public String angebot(  @Valid @ModelAttribute("angebotform") BratenDaten bratenDaten, 
                            BindingResult result, Model m,
                            @ModelAttribute("angebote")ArrayList<BratenDaten> lst) {
        logger.info("BindungResults = {}", result);
        logger.info("Die Bratendaten Name = {}", result.getFieldValue("name"));
        if(result.hasErrors()) {
            logger.error("Results {}", result);
            return "angebote/liste";
        }
        BratenDaten b = new BratenDaten(bratenDaten.getName(), bratenDaten.getAbholort(), 
                                        bratenDaten.getHaltbarbis(), bratenDaten.getBeschreibung(), bratenDaten.getVgradAuswahl());
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
    public String createBraten(Model m) {
        m.addAttribute("angebotform", new BratenDaten());
        return "angebote/neu";
    }
    
    @GetMapping("/angebot")
    public String angebot(Model m) {
        return "angebote/liste";
    }
}