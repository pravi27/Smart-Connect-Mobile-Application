package com.example.login;

public class employees {
    String name;
    String nic;
    String contact;
    String EmpID;
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

    public String getEmpID() {
        return EmpID;
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

    public employees(String name, String nic, String contact, String EmpID, String address, String username, String password) {
        this.name = name;
        this.nic = nic;
        this.contact = contact;
        this.EmpID = EmpID;
        this.address = address;
        this.username = username;
        this.password = password;
    }
}
