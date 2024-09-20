package com.example.madfinalproject;

public class Restaurant {
    private String name;
    private String density;
    private String distance;

    public Restaurant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Restaurant(String name, String density, String distance) {
        this.name = name;
        this.density = density;
        this.distance = distance;
    }
}
