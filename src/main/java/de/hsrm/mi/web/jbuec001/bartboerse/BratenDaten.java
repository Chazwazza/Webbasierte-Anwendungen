package de.hsrm.mi.web.jbuec001.bartboerse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.Valid;
import javax.validation.constraints.Future;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import de.hsrm.mi.web.jbuec001.bartboerse.validator.GuteAdresse;

public class BratenDaten {

    @Size(min = 3, message = "Name ist zu kurz")
    @NotNull(message = "Feld ist leer.")
    private String name;

    @GuteAdresse
    private String abholort;

    @DateTimeFormat(iso = ISO.DATE)
    @Future(message = "iiieh - der ist schon abgelaufen")
    @NotNull(message = "Feld ist leer.")
    private LocalDate haltbarbis;

    @Size(min = 10, max = 80, message = "bitte aussagekräftige Beschreibung eingeben")
    @NotNull(message = "Is zu wenig")
    private String beschreibung;
    
    public BratenDaten(@Valid String name, String abholort, LocalDate haltbarbis, String beschreibung) {
        this.name = name;
        this.abholort = abholort;
        this.haltbarbis = haltbarbis;
        this.beschreibung = beschreibung;
    }

    public String toString() {
        return " { Name " + name + " Ort " + abholort + " MHD " + haltbarbis + " besch " + beschreibung + " }";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbholort() {
        return this.abholort;
    }

    public void setAbholort(String abholort) {
        this.abholort = abholort;
    }

    public LocalDate getHaltbarbis() {
        return this.haltbarbis;
    }

    public void setHaltbarbis(LocalDate haltbarbis) {
        this.haltbarbis = haltbarbis;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}