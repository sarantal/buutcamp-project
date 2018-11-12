package com.buutcamp.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int userId;

    @NotNull(message="is required")
    @Size(min=1, message="is required")
    @Column(name = "username")
    private String userName;

    @NotNull(message="is required")
    @Size(min=1, message="is required")
    @Column(name = "email")
    private String userEmail;

    @NotNull(message="is required")
    @Size(min=1,message="is required")
    @Column(name = "password")
    private String password;

    private String role;


    public MyUser() {}

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() { return userEmail; }

    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}


//To use regex patterns for validation, use the pattern below
//@Pattern(regexp="(?=.*?[0-9])(?=.*?[A-Za-z])(?=.*[^0-9A-Za-z]).+")