package com.example.bioscoopapplicatie.domain;

public class User {
    private int id;
    private String username;
    private String emailAddress;
    private String firstname;
    private String lastName;
    private String city;
    private String registrationDate;

    public User(int id, String username, String emailAddress, String firstname, String lastName, String city, String registrationDate) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.firstname = firstname;
        this.lastName = lastName;
        this.city = city;
        this.registrationDate = registrationDate;
    }

    public User(String username, String emailAddress, String firstname, String lastName, String city) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.firstname = firstname;
        this.lastName = lastName;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
