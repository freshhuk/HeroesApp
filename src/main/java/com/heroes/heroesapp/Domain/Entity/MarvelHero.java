package com.heroes.heroesapp.Domain.Entity;


public class MarvelHero
{
    public int Id;
    public String name;
    public int iq;
    public int power;
    public MarvelHero(int id,String name, int iq, int power)
    {
        this.Id = id;
        this.name = name;
        this.iq = iq;
        this.power = power;
    }

}
