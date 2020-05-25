package com.example.rewardme;

import java.io.Serializable;

public class Kids implements Serializable {
    private String name;
    private String starCount;

    public Kids(String name, String starCount) {
        this.name = name;
        this.starCount = starCount;
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
}
