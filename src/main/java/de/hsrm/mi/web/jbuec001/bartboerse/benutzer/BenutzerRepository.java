package de.hsrm.mi.web.jbuec001.bartboerse.benutzer;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface BenutzerRepository extends Repository<Benutzer, Long> {
    List<Benutzer> findAllByLoginnameAsc();
    List<Benutzer> findAllByLoginnameDesc();
    Benutzer findByVollername(String loginname);
    Benutzer findByLoginname(String search);
}