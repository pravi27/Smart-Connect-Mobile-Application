package com.example.login;

public class records {
    String Time;

    String Date;

    String Price;

    String Units;


    public String getTime() {
        return Time;
    }

    public String getDate() {
        return Date;
    }

    public String getPrice() {
        return Price;
    }

    public String getUnits() {
        return Units;
    }


    public records (String Time, String Date, String Price, String Units) {
        this.Time = Time;
        this.Date = Date;
        this.Price = Price;
        this.Units = Units;

    }
}