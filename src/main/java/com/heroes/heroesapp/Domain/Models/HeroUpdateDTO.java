package com.heroes.heroesapp.Domain.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class HeroUpdateDTO
{

    private int id;//id update entity
    private String name;
    private int iq;
    private int power;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getIq() {
        return iq;
    }

    public int getPower() {
        return power;
    }
}
