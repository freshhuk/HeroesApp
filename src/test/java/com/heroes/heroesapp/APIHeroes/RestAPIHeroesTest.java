package com.heroes.heroesapp.APIHeroes;
import com.heroes.heroesapp.Domain.Entity.MarvelHero;
import com.heroes.heroesapp.Repository.HeroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class RestAPIHeroesTest
{
    @InjectMocks
    RestAPIHeroes controller;
    @Mock
    HeroRepository mockrepository;



    @Test
    void AddEntityTest()
    {
        MarvelHero entity_1 = new MarvelHero("Test", 20,20);
        var result = controller.AddEntity(entity_1).getBody();
        String expectedmassage = "Entity added successful";
        Assertions.assertEquals(expectedmassage, result);
    }
    @Test
    void GetAllTest()
    {
        MarvelHero entity_1 = new MarvelHero("Test", 20,20);
        MarvelHero entity_2 = new MarvelHero("Test2", 20,20);
        List<MarvelHero> expectedList = List.of(entity_1, entity_2);

        Mockito.when(mockrepository.All()).thenReturn(List.of(entity_1, entity_2));

        List<MarvelHero> result = controller.GetAll().getBody();

        Assertions.assertEquals(expectedList, result);


    }
}
