package Domain.Entity;

import Domain.Interface.IHero;

public class MarvelHero implements IHero
{
    public String name;
    public int iq;
    public int power;
    public MarvelHero(String name, int iq, int power)
    {
        this.name = name;
        this.iq = iq;
        this.power = power;
    }
    @Override
    public String Info()
    {
        var result = "Hero info: Name - %s, IQ - %d, Power - %d", name, iq, power;
        return "Hero info: Name - %s, IQ - %d, Power - %d", name, iq, power;
    }
}
