package de.hsrm.mi.web.jbuec001.bartboerse.benutzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BenutzerSerciveImpl implements BenutzerService {

    @Autowired 
    BenutzerRepository benutzerRepository;

    @Override
    public boolean pruefeLogin(String loginname, String passwort) {
        Benutzer b = findeBenutzer(loginname);
        if(b.getPasswort().equals(passwort)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String ermittlePasswort(String loginname) {
        Benutzer b = findeBenutzer(loginname);
        if(b == null)
            return null;
        return b.getLoginname();
    }

    @Override
    public Benutzer registriereBenutzer(Benutzer neuerBenutzer) {
        Benutzer b = benutzerRepository.save(neuerBenutzer);
        return b;
    }

    @Override
    public Benutzer findeBenutzer(String loginname) {
        for(Benutzer b : benutzerRepository.findAll()) {
            if(b.getLoginname().equalsIgnoreCase(loginname))
                return b;
        }
        return null;
    }
    
}