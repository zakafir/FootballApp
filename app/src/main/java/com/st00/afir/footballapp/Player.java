package com.st00.afir.footballapp;

/**
 * Created by zakaria_afir on 30/07/2017.
 */

public class Player {

    private int jerseyNumber;
    private String name;
    private String nationality;
    private String DateOfBirth;

    public Player(int jerseyNumber, String name, String nationality, String dateOfBirth) {
        this.jerseyNumber = jerseyNumber;
        this.name = name;
        this.nationality = nationality;
        DateOfBirth = dateOfBirth;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }
}
