package de.hsrm.mi.web.jbuec001.bartboerse.benutzer;

public class BenutzernameSchonVergebenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BenutzernameSchonVergebenException(String msg) {
        super(msg);
    }
}