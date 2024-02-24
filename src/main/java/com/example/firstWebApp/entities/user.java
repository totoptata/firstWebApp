package com.example.firstWebApp.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
public class user implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String secondName;
    private String UserName;
    private String password;

    private String  email;
    private String address;
    private String phoneNumber;

    public user() {

    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public user(Long id, String firstName, String secondName, String email, String address, String phoneNumber,String UserName,String password) {
        this.id = id;
        this.UserName=UserName;
        this.password=password;

        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
