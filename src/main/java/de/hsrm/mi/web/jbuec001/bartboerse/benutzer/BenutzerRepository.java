package de.hsrm.mi.web.jbuec001.bartboerse.benutzer;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {
    //Wichtig: Richtig schreiben, sonst kann das Bean nicht erstellt werden
    LinkedList<Benutzer> findAllByOrderByLoginnameAsc();
    LinkedList<Benutzer> findAllByOrderByLoginnameDesc();
    Benutzer findByVollername(String loginname);
    Benutzer findByLoginname(String search);

    //Zum loeschen
    void delete(Benutzer benutzer);
    void deleteAll();
}