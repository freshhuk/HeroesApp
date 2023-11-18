package com.heroes.heroesapp.APIHeroes;
import java.util.List;
import com.heroes.heroesapp.Domain.Entity.MarvelHero;
import com.heroes.heroesapp.Domain.Interface.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@Service
@RestController
@RequestMapping("/api")
public class RestAPIHeroes
{
    private final IRepository<MarvelHero> _repository;
    @Autowired
    public RestAPIHeroes(IRepository<MarvelHero> repository)
    {
        _repository = repository;
    }

    @GetMapping("/heroes")
    public ResponseEntity<List<MarvelHero>> GetAll()
    {

        var heroes = _repository.All();
        return ResponseEntity.ok(heroes);
    }
    @PostMapping ("/add")
    public ResponseEntity<Void> AddEntity(@RequestBody MarvelHero model)
    {
        if(model != null)
        {
            _repository.Add(model);

        }
        return ResponseEntity.noContent().build();

    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> DeleteEntity(@RequestBody MarvelHero model)
    {
        if(model != null)
        {
            _repository.Delete(model.Id);

        }
        return ResponseEntity.noContent().build();

    }
    @GetMapping("/hello")
    public String SayHello()
    {
        return "Hi, site is work";
    }
}
