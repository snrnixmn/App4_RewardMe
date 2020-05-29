package com.example.rewardme;

import java.io.Serializable;

public class Kids implements Serializable {
    private String name;
    private String starCount;
    private String choresCount;
    private String chores;

    public Kids(String name, String starCount, String choresCount, String chores) {
        this.name = name;
        this.starCount = starCount;
        this.choresCount = choresCount;
        this.chores = chores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarCount() {
        return starCount;
    }

    public void setStarCount(String starCount) {
        this.starCount = starCount;
    }

    public String getChoresCount() {
        return choresCount;
    }

    public void setChoresCount(String choresCount) {
        this.choresCount = choresCount;
    }

    public String getChores() {
        return chores;
    }

    public void setChores(String chores) {
        this.chores = chores;
    }
}
