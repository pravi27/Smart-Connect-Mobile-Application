package com.example.login;

public class suppliers {
    String name;
    String no;
    String contact;
    String email;
    String location;




    public String getNo() {
        return no;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getLocation() {
        return location;
    }
    public String getContact() {
        return contact;
    }

    public suppliers(String no, String name, String email, String location, String contact) {

        this.no = no;
        this.name = name;
        this.email = email;
        this.location = location;
        this.contact = contact;

    }
}
