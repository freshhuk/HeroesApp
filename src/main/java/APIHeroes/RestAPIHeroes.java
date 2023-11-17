package APIHeroes;

import Domain.Entity.MarvelHero;
import Domain.Interface.IRepository;
import Repository.HeroRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RestAPIHeroes
{
    private final IRepository<MarvelHero> _repository;
    public RestAPIHeroes(IRepository<MarvelHero> repository)
    {
        _repository = repository;
    }
    
    @GetMapping("/Heroes")
    public String
}
