package de.hsrm.mi.web.jbuec001.bartboerse.bratrepo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import de.hsrm.mi.web.jbuec001.bartboerse.benutzer.Benutzer;

@Entity
@Table(name="BRATEN")
public class Braten implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int bratenId;

    @ManyToOne
    private Benutzer anbieter;

    @Version
    private int version;

    @NotEmpty
    @Column(name="ABHOLORT")
    private String abholort;

    @DateTimeFormat(iso = ISO.DATE)
    @Future
    @NotNull
    @Column(name="HALTBARBIS")
    private LocalDate haltbarbis;

    @Size(min = 10, max = 80)
    @Column(name="BESCHREIBUNG")
    private String beschreibung;

    private int[] vgrad = { 0, 25, 50, 75, 100 };
    private int vgradAuswahl;

    public Braten(){}

    public Braten(String abholort, LocalDate haltbarbis, String beschreibung, int vgradAuswahl) {
        this.abholort = abholort;
        this.haltbarbis = haltbarbis;
        this.beschreibung = beschreibung;
        this.vgradAuswahl = vgradAuswahl;
    }

    public String toString() {
        return " { " + " Ort " + abholort + " MHD " + haltbarbis + " besch " + beschreibung + " }";
    }

    public Benutzer getAnbieter() {
        return this.anbieter;
    }

    public void setAnbieter(Benutzer anbieter) {
        this.anbieter = anbieter;
    }
    
    public int[] getVgrad() {
        return this.vgrad;
    }

    public void setVgrad(int[] vgrad) {
        this.vgrad = vgrad;
    }

    public int getVgradAuswahl() {
        return this.vgradAuswahl;
    }

    public void setVgradAuswahl(int vgradAuswahl) {
        this.vgradAuswahl = vgradAuswahl;
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