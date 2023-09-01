package com.company.gamestore.controllers;

import com.company.gamestore.models.Game;
import com.company.gamestore.services.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
public class GameController implements Serializable {

    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody @Valid Game game){
        return serviceLayer.saveGame(game);
    }

    @PutMapping("/games")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody Game game){
        serviceLayer.updateGame(game);
    }

    @GetMapping("/games/{id}")
    public Game getGameById(@PathVariable int id){
      return serviceLayer.findGame(id);
    }

    @GetMapping("/games/esrb/{esrbRating}")
    public List<Game> getGameByEsrbRating(@PathVariable String esrbRating) {

        return serviceLayer.getGameByEsrbRating(esrbRating);
    }
    
    @GetMapping("/games/title/{title}")
    public List<Game> getGameByTitle(@PathVariable String title) {

        return serviceLayer.getGameByTitle(title);
    }
    
    @GetMapping("/games/studio/{studio}")
    public List<Game> findByStudio(@PathVariable @Valid String studio){

        return serviceLayer.getGameByStudio(studio);
    }
    
    @GetMapping("/games")
    public List<Game> getAllGames(){return serviceLayer.findAllGames();}

    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id){
        serviceLayer.removeGame(id);
    }

}
