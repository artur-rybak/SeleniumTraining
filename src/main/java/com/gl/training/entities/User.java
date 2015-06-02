package com.gl.training.entities;


public class User {

    private String name;
    private String password;
    private String fullName;
    private String email;

    public User(String name, String password, String fullName, String email){
        this.name = name;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
