package com.heroes.heroesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HeroesAppApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(HeroesAppApplication.class, args);
    }
    @GetMapping("/hello")
    public String SayHello()
    {
        return "Hi, site is work";
    }

}
