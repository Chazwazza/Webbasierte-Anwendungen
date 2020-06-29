package de.hsrm.mi.web.jbuec001.bartboerse.benutzer;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="benutzer")
public class Benutzer implements Serializable {
    private static final long serialVersionUID = -6400758504290942030L;

    @Id
    @GeneratedValue
    private long userId;

    @Version
    private long version;

    @NotNull(message = "Darf nicht leer sein")
    @Column(name="Loginname", unique=true)
    private String loginname;

    @NotNull(message = "Darf nicht leer sein")
    @Min(3)
    @Column(name="passwort")
    private String passwort;

    @NotNull(message = "Darf nicht Leer sein")
    @Column(name="vollername")
    private String vollname;

    private boolean nutzungsbedingungenok;

    public Benutzer(String loginname, String passwort, String vollname, boolean nutzungsbedingungenok) {
        this.loginname = loginname;
        this.passwort = passwort;
        this.vollname = vollname;
        this.nutzungsbedingungenok = nutzungsbedingungenok;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getVollname() {
        return vollname;
    }

    public void setVollname(String vollname) {
        this.vollname = vollname;
    }

    public boolean isNutzungsbedingungenok() {
        return nutzungsbedingungenok;
    }

    public void setNutzungsbedingungenok(boolean nutzungsbedingungenok) {
        this.nutzungsbedingungenok = nutzungsbedingungenok;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((loginname == null) ? 0 : loginname.hashCode());
        result = prime * result + (nutzungsbedingungenok ? 1231 : 1237);
        result = prime * result + ((passwort == null) ? 0 : passwort.hashCode());
        result = prime * result + ((vollname == null) ? 0 : vollname.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        benutzer other = (benutzer) obj;
        if (loginname == null) {
            if (other.loginname != null)
                return false;
        } else if (!loginname.equals(other.loginname))
            return false;
        if (nutzungsbedingungenok != other.nutzungsbedingungenok)
            return false;
        if (passwort == null) {
            if (other.passwort != null)
                return false;
        } else if (!passwort.equals(other.passwort))
            return false;
        if (vollname == null) {
            if (other.vollname != null)
                return false;
        } else if (!vollname.equals(other.vollname))
            return false;
        return true;
    }
}