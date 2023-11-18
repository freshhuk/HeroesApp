package com.heroes.heroesapp.Repository;


import com.heroes.heroesapp.Domain.Entity.MarvelHero;
import com.heroes.heroesapp.Domain.Interface.IRepository;
import org.springframework.stereotype.Service;//for create service

import java.util.List;
import java.util.ArrayList;
@Service // create service
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
