package de.hsrm.mi.web.jbuec001.login;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Loginbean {

    @NotNull
    @Min(2)
    private String username;
    
    @NotNull(message = "No password")
    @Min(2)
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