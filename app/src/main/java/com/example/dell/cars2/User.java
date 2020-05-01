package com.example.dell.cars2;
public class User {

    public String username;
    public String email;
    public String password;
    public String car_no;
    public String type;
    public String intime;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email,String password,String car_no,String type,String intime) {
        this.username = username;
        this.email = email;
        this.car_no=car_no;
        this.password=password;
        this.intime=intime;
        this.type=type;
    }

}
