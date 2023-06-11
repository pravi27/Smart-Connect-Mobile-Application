package com.example.login;

public class customers {
    String name;
    String nic;
    String contact;
    String vehicle;
    String address;
    String username;
    String password;

    public String getName() {
        return name;
    }

    public String getNic() {
        return nic;
    }

    public String getContact() {
        return contact;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public customers(String name, String nic, String contact, String vehicle, String address, String username, String password) {
        this.name = name;
        this.nic = nic;
        this.contact = contact;
        this.vehicle = vehicle;
        this.address = address;
        this.username = username;
        this.password = password;
    }
}
