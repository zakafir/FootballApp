package com.st00.afir.footballapp;

/**
 * Created by zakaria_afir on 06/08/2017.
 */

public class Team {

    private String name;
    private String logoUrl;

    public Team(String name, String logoUrl) {
        this.name = name;
        this.logoUrl = logoUrl;
    }

    public String getName() {
        return name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }
}
