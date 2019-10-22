package Inlämningsuppgift2;

import java.time.LocalDate;

public class Customer {

    protected String name;
    protected String personNr;
    protected LocalDate gymFee;

    public Customer (){}

    public Customer (String name, String personNr, LocalDate gymFee) {
        this.name = name;
        this.personNr = personNr;
        this.gymFee = gymFee;
    }

    protected String getName() {
        return name;
    }

    protected String getPersonNr() {
        return personNr;
    }

    protected LocalDate getGymFee() {
        return gymFee;
    }
}
