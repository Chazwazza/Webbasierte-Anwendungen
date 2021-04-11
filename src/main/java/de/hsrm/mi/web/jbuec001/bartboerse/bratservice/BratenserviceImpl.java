package de.hsrm.mi.web.jbuec001.bartboerse.bratservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.jbuec001.bartboerse.benutzer.Benutzer;
import de.hsrm.mi.web.jbuec001.bartboerse.benutzer.BenutzerRepository;
import de.hsrm.mi.web.jbuec001.bartboerse.bratrepo.Braten;
import de.hsrm.mi.web.jbuec001.bartboerse.bratrepo.BratenRepository;

@Service
public class BratenserviceImpl implements Bratenservice {

    @Autowired
    private BratenRepository bratenrepo;

    @Autowired
    private BenutzerRepository benutzerrepo;

    @Override
    public List<Braten> alleBraten() {
        List<Braten> allebraten = new ArrayList<Braten>();
        allebraten.addAll(bratenrepo.findAll());
        return allebraten;
    }

    @Override
    public Optional<Braten> sucheBratenMitId(int id) {
        Optional<Braten> b = bratenrepo.findById(id);
        return b;
    }

    @Override
    @Transactional
    public Braten editBraten(String loginname, Braten braten) {
        Benutzer user = benutzerrepo.findByLoginname(loginname);
        braten.setAnbieter(user);
        try {
            for(Braten b : bratenrepo.findAll()) {
                if(braten.getBeschreibung().equals(b.getBeschreibung())) {
                    bratenrepo.save(b);
                    return b;
                }
            }
            bratenrepo.save(braten);
            return braten;
        } catch(OptimisticLockException e) {
            return braten;
        }
    }

    @Override
    public void loescheBraten(int bratendatenid) {
        bratenrepo.deleteById(bratendatenid);
    }

    
}