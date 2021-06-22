package com.example.passwordsafe;

public class PwdSafe {

    //variables for the PasswordSafe
    //Description, Username, Password
    private String description;
    private String username;
    private String password;

    //constructor
    public PwdSafe(String description, String username, String password) {
        this.description = description;
        this.username = username;
        this.password = password;
    }

    //getter and setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
