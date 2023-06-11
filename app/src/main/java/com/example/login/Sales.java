package com.example.login;

public class Sales {
    String editTextTime;

    String editTextDate;

    String petrol92;

    String petrol95;

    String dieselA;

    String dieselS;

    public String getTime() {
        return editTextTime;
    }

    public String getDate() {
        return editTextDate;
    }

    public String getPetrol92() {
        return petrol92;
    }

    public String getPetrol95() {
        return petrol95;
    }

    public String getdieselA() {
        return dieselA;
    }

    public String getdieselS() {
        return dieselS;
    }


    public Sales(String editTextTime,String editTextDate,String petrol92,String petrol95,String dieselA,String dieselS) {
        this.editTextTime = editTextTime;
        this.editTextDate = editTextDate;
        this.petrol92 = petrol92;
        this.petrol95 = petrol95;
        this.dieselA = dieselA;
        this.dieselS = dieselS;

    }
}

