package com.heroes.heroesapp.Domain.Entity;

import com.heroes.heroesapp.Domain.Interface.IHero;

public class MarvelHero implements IHero
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
    @Override
    public String Info()
    {
        return "Hero info: Name - " + name + ", IQ - " + iq + ", Power - " + power;
    }
}
