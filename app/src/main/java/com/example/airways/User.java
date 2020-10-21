package com.example.airways;

public class User {
    public String name,last_name,email,password,gender,phone;
    public User()
    {

    }
    public User(String name, String last_name, String email, String password, String gender,String phone) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phone=phone;
    }
}

