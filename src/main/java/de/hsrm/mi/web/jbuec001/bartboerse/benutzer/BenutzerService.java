package de.hsrm.mi.web.jbuec001.bartboerse.benutzer;

public interface BenutzerService {
    boolean pruefeLogin(String loginname, String passwort);
    String ermittlePasswort(String loginname);
    Benutzer registriereBenutzer(Benutzer neuerBenutzer);
    Benutzer findeBenutzer(String loginname);
}