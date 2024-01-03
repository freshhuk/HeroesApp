package com.heroes.heroesapp.APIHeroes;
import com.heroes.heroesapp.Domain.Entity.MarvelHero;
import com.heroes.heroesapp.Domain.Models.HeroUpdateDTO;
import com.heroes.heroesapp.Services.SortHeroesService;
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
    SortHeroesService mocksortservice;


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

        Mockito.when(mocksortservice.Sort_GetAllHeroes("NoSort")).thenReturn(List.of(entity_1, entity_2));

        List<MarvelHero> result = controller.GetAll("NoSort").getBody();

        Assertions.assertEquals(expectedList, result);


    }

    @Test
    void UpdateEntityTest()
    {
        HeroUpdateDTO entity = new HeroUpdateDTO(1, "Test", 20, 3);
        var result = controller.UpdateEntity(entity).getBody();
        String expectedmassage = "Successful update";

        Assertions.assertEquals(expectedmassage, result);
    }

    @Test
    void DeleteEntityTest()
    {
        MarvelHero entity_1 = new MarvelHero("Test", 20,20);
        var result = controller.DeleteEntity(entity_1).getBody();
        String expectedmassage = "Successful delete";

        Assertions.assertEquals(expectedmassage, result);

    }

    @Test
    void AddNullEntityTest()
    {
        var result = controller.AddEntity(null).getBody();
        String expectedmassage = "Entity is null";
        Assertions.assertEquals(expectedmassage, result);
    }

    @Test
    void UpdateNullEntityTest()
    {
        var result = controller.UpdateEntity(null).getBody();
        String expectedmassage = "ModelUpdate is null";

        Assertions.assertEquals(expectedmassage, result);
    }

    @Test
    void DeleteNullEntityTest()
    {

        var result = controller.DeleteEntity(null).getBody();
        String expectedmassage = "model is null";

        Assertions.assertEquals(expectedmassage, result);

    }

}
