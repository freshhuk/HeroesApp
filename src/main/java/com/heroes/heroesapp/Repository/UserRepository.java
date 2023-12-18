package com.heroes.heroesapp.Repository;

import com.heroes.heroesapp.Domain.Entity.User;
import com.heroes.heroesapp.Domain.Entity.MarvelHero;
import com.heroes.heroesapp.Domain.Interface.IRepository;
import com.heroes.heroesapp.Domain.Models.HeroUpdateDTO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IRepository<User>
{
    private  final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(MarvelHero.class)
            .buildSessionFactory();

    @Override
    public List<User> All()
    {
        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            var model = session.createQuery("from User", User.class).getResultList();//get all heroes
            session.getTransaction().commit();
            return model;
        }
    }

    @Override
    public User GetEntity(int id)
    {
        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            var model = session.get(User.class,id);
            session.getTransaction().commit();
            return model;
        }
    }

    public User GetUserByLogin(String user_login)
    {
        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            var model = session.get(User.class, user_login);
            session.getTransaction().commit();
            return model;
        }
    }

    @Override
    public void Delete(int id)
    {
        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            var model = session.get(User.class,id);
            session.remove(model);
            session.getTransaction().commit();
        }
    }

    @Override
    public void Add(User entity)
    {
        try(Session session = factory.openSession())
        {
            session.getTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        }
    }

    //Todo костыль
    @Override
    public void Update(HeroUpdateDTO entity) {

    }
}