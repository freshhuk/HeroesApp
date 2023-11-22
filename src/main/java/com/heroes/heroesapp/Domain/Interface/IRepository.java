package com.heroes.heroesapp.Domain.Interface;
import java.util.List;

public interface IRepository<T>
{
    List<T> All();
    T GetHero(int id);
    void Delete(int id);
    void Add(T entity);

}
