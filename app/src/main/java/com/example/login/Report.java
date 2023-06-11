package com.example.login;

public class Report {
    String Time;

    String Date;

    String Income;

    String Expense;

    public String getTime() {
        return Time;
    }

    public String getDate() {
        return Date;
    }

    public String getIncome() { return Income;}

    public String getExpense() { return Expense;}



    public Report(String editTextTime, String editTextDate, String editTextIncome, String editTextExpense) {
        this.Time = editTextTime;
        this.Date = editTextDate;
        this.Income = editTextIncome;
        this.Expense = editTextExpense;
    }
}

