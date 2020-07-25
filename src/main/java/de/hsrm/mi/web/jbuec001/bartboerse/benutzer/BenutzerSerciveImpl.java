package de.hsrm.mi.web.jbuec001.bartboerse.benutzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BenutzerSerciveImpl implements BenutzerService {

    @Autowired 
    BenutzerRepository benutzerRepository;

    @Override
    public boolean pruefeLogin(String loginname, String passwort) {
        if (passwort.equals(loginname + "!")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String ermittlePasswort(String loginname) {
        String pw = "";
        return pw;
    }

    @Override
    public Benutzer registriereBenutzer(Benutzer neuerBenutzer) {
        
        return null;
    }

    @Override
    public Benutzer findeBenutzer(String loginname) {

        return null;
    }
    
}