package com.company.gamestore.contollers;

import com.company.gamestore.models.Game;
import com.company.gamestore.repositories.GameRepository;
import com.company.gamestore.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {

    @Autowired
    GameRepository gameRepository;


    @QueryMapping
    public List<Game> findAllGames(@Argument Integer id){
        return gameRepository.findAll();
    }

    @QueryMapping
    public Game findGameById(@Argument Integer id){
        Optional<Game> returnVal = gameRepository.findById(id);
        if(returnVal.isPresent()){
            return returnVal.get();
        } else{
            return null;
        }
    }
    @QueryMapping
    public Optional<Game> getGameByEsrbRating(@PathVariable String esrbRating) {

        return gameRepository.findByEsrbRating(esrbRating);
    }

    @QueryMapping
    public Optional<Game> getGameByTitle(@PathVariable String title) {

        return gameRepository.findByTitle(title);
    }

    @QueryMapping
    public Optional<Game> findByStudio(@PathVariable String studio){

        return gameRepository.findByStudio(studio);
    }
}
