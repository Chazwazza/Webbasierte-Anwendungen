package de.hsrm.mi.web.jbuec001.login;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Loginbean {

    @NotNull
    @Size(min = 3, max = 20, message = "Username sollte zwischen 3 und 20 Zeichen haben")
    private String username;
    
    @NotNull(message = "No password")
    @Min(3)
    private String password;
    
    public Loginbean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String toString() {
        return "Userdata {" + this.username + ": " + this.password + " }";
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}