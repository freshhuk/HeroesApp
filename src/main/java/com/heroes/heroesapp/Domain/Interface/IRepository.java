package com.heroes.heroesapp.Domain.Interface;
import com.heroes.heroesapp.Domain.Models.HeroUpdateDTO;

import java.util.List;

public interface IRepository<T>
{
    List<T> All();
    T GetEntity(int id);
    void Delete(int id);
    void Add(T entity);
    void Update(HeroUpdateDTO entity);
}
