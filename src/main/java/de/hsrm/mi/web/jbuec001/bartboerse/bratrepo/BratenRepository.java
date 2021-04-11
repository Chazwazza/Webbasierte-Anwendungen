package de.hsrm.mi.web.jbuec001.bartboerse.bratrepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.hsrm.mi.web.jbuec001.bartboerse.benutzer.Benutzer;

public interface BratenRepository extends JpaRepository<Braten, Integer> {
    List<Braten> findByBeschreibungContainsIgnoringCase(String beschreibung);
    List<Braten> findByAnbieter(Benutzer anbieter);
    
}