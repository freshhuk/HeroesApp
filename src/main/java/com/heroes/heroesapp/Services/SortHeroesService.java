package com.heroes.heroesapp.Services;

import com.heroes.heroesapp.Domain.Entity.MarvelHero;
import com.heroes.heroesapp.Domain.Interface.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Comparator;

@Service
public class SortHeroesService
{
    private final IRepository<MarvelHero> _repository;

    @Autowired
    public SortHeroesService(IRepository<MarvelHero> repository)
    {
        _repository = repository;
    }
    public List<MarvelHero> Sort_GetAllHeroes(String sort_type)
    {
        switch (sort_type)
        {
            case "NoSort":
                return NoSort();

            case "SortByMax":
                return SortByMax();

            case "SortByMin":
                return SortByMin();

            default:
                return null;
        }
    }
//SortMethod
    private List<MarvelHero> NoSort()
    {
        return _repository.All();
    }
    private List<MarvelHero> SortByMax()
    {
        var models = _repository.All();
        models.sort(Comparator.comparing(MarvelHero::getId));
        return models;
    }
    private List<MarvelHero> SortByMin()
    {
        var models = _repository.All();
        models.sort(Comparator.comparing(MarvelHero::getId).reversed());
        return models;
    }
}
