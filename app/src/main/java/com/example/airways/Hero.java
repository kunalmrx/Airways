package com.example.airways;


public class Hero {

    int image;
    String name, team,time;

    public Hero(int image, String name, String team, String time) {
        this.image = image;
        this.name = name;
        this.team = team;
        this.time=time;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getTime() {
        return time;
    }
}
