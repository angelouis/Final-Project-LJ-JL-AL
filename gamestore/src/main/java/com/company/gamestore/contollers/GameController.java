package com.company.gamestore.contollers;

import com.company.gamestore.models.Game;
import com.company.gamestore.repositories.GameRepository;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodels.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
public class GameController implements Serializable {

    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public GameViewModel addGame(@RequestBody GameViewModel gameViewModel){
        return serviceLayer.saveGame(gameViewModel);
    }

    @PutMapping("/games")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody GameViewModel gameViewModel){
        serviceLayer.updateGame(gameViewModel);
    }

    @GetMapping("/games/{id}")
    public GameViewModel getGameById(@PathVariable int id){
      return serviceLayer.findGame(id);
    }

    @GetMapping("/games/esrb/{esrbRating}")
    public Optional<Game> getGameByEsrbRating(@PathVariable String esrbRating) {

        return serviceLayer.getGameByEsrbRating(esrbRating);
    }
    
    @GetMapping("/games/title/{title}")
    public Optional<Game> getGameByTitle(@PathVariable String title) {

        return serviceLayer.getGameByTitle(title);
    }
    
    @GetMapping("/games/studio/{studio}")
    public Optional<Game> findByStudio(@PathVariable String studio){

        return serviceLayer.getGameByStudio(studio);
    }
    
    @GetMapping("/games")
    public List<GameViewModel> getAllGames(){return serviceLayer.findAllGames();}

    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id){
        serviceLayer.removeGame(id);
    }

}
