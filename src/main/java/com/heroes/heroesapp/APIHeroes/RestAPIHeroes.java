package com.heroes.heroesapp.APIHeroes;
import java.util.List;
import com.heroes.heroesapp.Domain.Entity.MarvelHero;
import com.heroes.heroesapp.Domain.Interface.IRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.style.ToStringCreator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@Service
@RestController
@RequestMapping("/api")
public class RestAPIHeroes
{
    private final IRepository<MarvelHero> _repository;
    //Подключаем нашу фабрику сесий, которая будет брать конфиг нашей бд и на нее делать запросы
    private final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(MarvelHero.class)
            .buildSessionFactory();
    private final Session session = factory.getCurrentSession();
    @Autowired
    public RestAPIHeroes(IRepository<MarvelHero> repository)
    {
        _repository = repository;
    }
//Todo решить проблему с методом добовление пользователя. при зауске получаю исключение.
// Для теста весь код про ссесии запихнуть в мейн
    @GetMapping("/test")
    public ResponseEntity<String> TestAdd()
    {
        var model = new MarvelHero("Betman", 20, 200);

        session.beginTransaction();//Открываем транзакцию
        session.save(model);//делаем запрос к бд
        session.getTransaction().commit();//закрываем транзакцию
        return ResponseEntity.ok().body("Successful");
    }




    @GetMapping("/heroes")
    public ResponseEntity<List<MarvelHero>> GetAll()
    {
        var heroes = _repository.All();
        return ResponseEntity.ok(heroes);
    }
    @PostMapping ("/add")
    public ResponseEntity<String> AddEntity(@RequestBody MarvelHero model)
    {
        try
        {
            if(model != null)
            {
                _repository.Add(model);
                return ResponseEntity.ok().body("Entity added successful");
            }
            return ResponseEntity.badRequest().body("Entity is null");
        }
        catch(Exception  exception)
        {
            return ResponseEntity.badRequest().body("Error added");
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> DeleteEntity(@RequestBody MarvelHero model)
    {
        try
        {
            if(model != null)
            {
                _repository.Delete(model.getId());
                return ResponseEntity.ok().body("Successful delete");
            }
            return ResponseEntity.badRequest().body("model is null");
        }
        catch (Exception ex)
        {
            return ResponseEntity.badRequest().body("Error in delete");
        }
    }
}
