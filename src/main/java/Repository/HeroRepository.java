package Repository;


import Domain.Entity.MarvelHero;
import Domain.Interface.IRepository;

import java.util.List;
import java.util.ArrayList;

public class HeroRepository implements IRepository<MarvelHero>
{
    public List<MarvelHero> repos = new ArrayList<>();
    @Override
    public List<MarvelHero> All()
    {
        return repos;
    }

    @Override
    public MarvelHero Get(int id)
    {
        for (MarvelHero hero : repos)
        {
            if(hero.Id == id)
            {
                return  hero;
            }
        }
        return null;
    }

    @Override
    public void Delete(int id)
    {
        repos.removeIf(hero -> hero.Id == id);

    }

    @Override
    public void Add(MarvelHero entity)
    {
        if(entity != null)
        {
            repos.add(entity);
        }
    }
}
