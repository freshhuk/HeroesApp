package com.heroes.heroesapp.Repository;


import com.heroes.heroesapp.Domain.Entity.MarvelHero;
import com.heroes.heroesapp.Domain.Interface.IRepository;
import com.heroes.heroesapp.Domain.Models.HeroUpdateDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class HeroRepository implements IRepository<MarvelHero>
{
    private final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(MarvelHero.class)
            .buildSessionFactory();


    @Override
    public List<MarvelHero> All()
    {
        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            var heroes = session.createQuery("from MarvelHero", MarvelHero.class).getResultList();//get all heroes
            session.getTransaction().commit();
            return heroes;
        }

    }

    @Override
    public MarvelHero GetHero(int id)
    {

        try(Session session = factory.openSession())
        {
                session.beginTransaction();
                MarvelHero hero = session.get(MarvelHero.class, id);
                session.getTransaction().commit();
                return hero;
        }
    }

    @Override
    public void Delete(int id)
    {

        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            var hero = session.get(MarvelHero.class, id);
            session.remove(hero);//delete hero in database
            session.getTransaction().commit();
        }
    }

    @Override
    public void Add(MarvelHero entity)
    {
        try(Session session = factory.openSession())
        {
            if(entity != null)
            {
                session.beginTransaction();//Открываем транзакцию
                session.persist(entity);//делаем sql insert запрос к бд
                session.getTransaction().commit();//закрываем транзакцию
            }
        }
    }
    @Override
    public  void Update(HeroUpdateDTO model)
    {
        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            var hero = session.get(MarvelHero.class ,model.getId());
            hero.setName(model.getName());//change field hero
            hero.setIq(model.getIq());//change field hero
            hero.setPower(model.getPower());//change field hero
            session.getTransaction().commit();
        }
    }
}
