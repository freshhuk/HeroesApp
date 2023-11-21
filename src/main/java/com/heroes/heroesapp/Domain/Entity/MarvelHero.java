package com.heroes.heroesapp.Domain.Entity;

import jakarta.persistence.*;

@Entity//указываем что это модель для бд
@Table(name = "heroes")//связываем с таблицой
public class MarvelHero
{

    @Id //@id - primaery key
    @Column(name = "id") // Указываем столбец из таблицы его имя
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "iq")
    private int iq;
    @Column(name = "power")
    private int power;
    public MarvelHero(){}

    public MarvelHero(String name, int iq, int power)
    {
        this.name = name;
        this.iq = iq;
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
}
