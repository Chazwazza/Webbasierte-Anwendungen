package de.hsrm.mi.web.jbuec001.bartboerse.benutzer;

import org.springframework.stereotype.Service;

@Service
public class BenutzerSerciveImpl implements BenutzerService {


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
        
        return null;
    }
    
}