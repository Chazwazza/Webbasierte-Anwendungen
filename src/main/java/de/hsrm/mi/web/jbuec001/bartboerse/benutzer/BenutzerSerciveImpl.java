package de.hsrm.mi.web.jbuec001.bartboerse.benutzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BenutzerSerciveImpl implements BenutzerService {

    @Autowired BenutzerRepository benutzerRepository;
    private Logger logger = LoggerFactory.getLogger(BenutzerSerciveImpl.class);

    @Override
    public boolean pruefeLogin(String loginname, String passwort) {
        Benutzer b = findeBenutzer(loginname);
        if(b == null) {
            logger.info("user ist leer");
        }
        if(!(b == null)) {
            String userPw = b.getPasswort();
            logger.info("PASSWORDs: " + userPw + " in DB, " + passwort + " getting this shit");
            if(userPw.equals(passwort)) {
                return true;
            } else {
                return false;
            }
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
        Benutzer erg = findeBenutzer(neuerBenutzer.getLoginname());
        if(erg == null) {
            return benutzerRepository.save(neuerBenutzer);
        }
        return null;
    }

    @Override
    public Benutzer findeBenutzer(String loginname) {
        Benutzer benutzer = benutzerRepository.findByLoginname(loginname);
        if(benutzer == null) {
            return benutzer;
        }
        return null;
    }
    
}