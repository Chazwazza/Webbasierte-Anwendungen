package de.hsrm.mi.web.jbuec001.bartboerse.bratservice;

import java.util.List;
import java.util.Optional;

import de.hsrm.mi.web.jbuec001.bartboerse.bratrepo.Braten;

public interface Bratenservice {
    List<Braten> alleBraten();
    Optional<Braten> sucheBratenMitId(int id);
    Braten editBraten(String loginname, Braten braten);
    void loescheBraten(int bratendatenid);
}