package com.company.gamestore.service;

import com.company.gamestore.models.Game;
import com.company.gamestore.repositories.GameRepository;
import com.company.gamestore.viewmodels.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {
    private GameRepository gameRepository;

    @Autowired
    public ServiceLayer(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    @Transactional
    public GameViewModel saveGame(GameViewModel gameViewModel){
        Game game = new Game();
        game.setStudio((gameViewModel.getStudio()));
        game.setDescription(gameViewModel.getDescription());
        game.setQuantity(gameViewModel.getQuantity());
        game.setEsrbRating(gameViewModel.getEsrbRating());
        game.setTitle(gameViewModel.getTitle());
        game.setPrice(gameViewModel.getPrice());
        game = gameRepository.save(game);

        gameViewModel.setId(game.getId());

        return gameViewModel;
    }


    public GameViewModel findGame(int id){
        Optional<Game> game = gameRepository.findById(id);

        if (game.isPresent()) {
            return buildGameViewModel(game.get());
        }
        else {
            throw new NotFoundException("Game not found");
        }
    }

    private GameViewModel buildGameViewModel(Game game){

        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setStudio((game.getStudio()));
        gameViewModel.setDescription(game.getDescription());
        gameViewModel.setQuantity(game.getQuantity());
        gameViewModel.setEsrbRating(game.getEsrbRating());
        gameViewModel.setTitle(game.getTitle());
        gameViewModel.setPrice(game.getPrice());

        return gameViewModel;

    }

    public List<GameViewModel> findAllGames(){

        List<Game> gameList = gameRepository.findAll();

        List<GameViewModel> gameViewModelList = new ArrayList<>();

        for(Game game:gameList){
            GameViewModel  gameViewModel = buildGameViewModel(game);
        }
        return gameViewModelList;
    }

    @Transactional
    public void updateGame(GameViewModel gameViewModel){
        Game game = new Game();
        game.setStudio((gameViewModel.getStudio()));
        game.setDescription(gameViewModel.getDescription());
        game.setQuantity(gameViewModel.getQuantity());
        game.setEsrbRating(gameViewModel.getEsrbRating());
        game.setTitle(gameViewModel.getTitle());
        game.setPrice(gameViewModel.getPrice());
        gameRepository.save(game);
    }

    @Transactional
    public void removeGame(int id){
        gameRepository.deleteById(id);
    }

    public Optional<Game> getGameByEsrbRating(String esrbRating) {

        return gameRepository.findByEsrbRating(esrbRating);
    }

    public Optional<Game> getGameByTitle(String title) {

        return gameRepository.findByTitle(title);
    }

    public Optional<Game> getGameByStudio(String studio){

        return gameRepository.findByStudio(studio);
    }


}

