package com.heroes.heroesapp.Repository;


import com.heroes.heroesapp.Domain.Entity.MarvelHero;
import com.heroes.heroesapp.Domain.Interface.IRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
@Repository
public class HeroRepository implements IRepository<MarvelHero>
{
    //Todo добавить везде ссесии для контекста моего бд
    private final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(MarvelHero.class)
            .buildSessionFactory();
    public List<MarvelHero> repos = new ArrayList<>();


    @Override
    public List<MarvelHero> All()
    {
        try (Session session = factory.openSession())
        {
            session.beginTransaction();

            var heroes = session.createQuery("from MarvelHero ").getResultList();

            session.getTransaction().commit();
            return heroes;
        }
    }

    @Override
    public MarvelHero GetHero(int id)
    {
        //Session session = factory.openSession();
        try(Session session = factory.openSession())
        {
                session.beginTransaction();
                MarvelHero hero = session.get(MarvelHero.class, id);
                session.getTransaction().commit();
                return hero;
        }
        finally
        {
            session.close();
        }
    }

    @Override
    public void Delete(int id)
    {
        repos.removeIf(hero -> hero.getId() == id);

    }

    //Метод который добавляет в бд сущность
    @Override
    public void Add(MarvelHero entity)
    {
        Session session = factory.openSession();
        try
        {
            if(entity != null)
            {
                session.beginTransaction();//Открываем транзакцию
                session.save(entity);//делаем запрос к бд
                session.getTransaction().commit();//закрываем транзакцию
            }
        }
        finally
        {
            session.close();
        }
    }
}
