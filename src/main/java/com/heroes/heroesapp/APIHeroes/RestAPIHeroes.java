package com.heroes.heroesapp.APIHeroes;
import java.util.List;
import com.heroes.heroesapp.Domain.Entity.MarvelHero;
import com.heroes.heroesapp.Domain.Interface.IRepository;
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
    @Autowired
    public RestAPIHeroes(IRepository<MarvelHero> repository)
    {
        _repository = repository;
    }
    //Todo переписать крудик для работы с бд хибернейт последний дайканый видос глянуть 
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
                _repository.Delete(model.Id);
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
