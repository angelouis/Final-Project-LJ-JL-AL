package com.company.gamestore.repositories;

import com.company.gamestore.models.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

//import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    private BigDecimal bigDecimal = new BigDecimal("2.22");

    private Game game;

    @BeforeEach
    public void setUp(){
        gameRepository.deleteAll();

    }
    @Test
    public void shouldGetAll(){

        game = new Game();

        game.setQuantity(1);
        game.setStudio("Warner Bros");
        game.setEsrbRating("E");
        game.setPrice(bigDecimal);
        game.setTitle("Batman");
        game.setDescription("The newest in the batman series relive your roots as batman");

        game = gameRepository.save(game);

        List<Game> gameList = gameRepository.findAll();
        assertEquals(gameList.size(),1); // asserts that the returned list is the correct amount of all the games in the repo
    }
    @Test
    public void shouldCreateGame(){

        game = new Game();

        game.setQuantity(1);
        game.setStudio("Warner Bros");
        game.setEsrbRating("E");
        game.setPrice(bigDecimal);
        game.setTitle("Batman");
        game.setDescription("The newest in the batman series relive your roots as batman");

        assertEquals(gameRepository.findAll().size(),0); //  makes sures that the game is created by knowing the before
        game = gameRepository.save(game);

        Optional<Game> game1 = gameRepository.findById(game.getId());
        assertEquals(gameRepository.findAll().size(),1); //  makes sures that the game is created by knowing the after the creation of the game
    }
    @Test
    public void shouldUpdateGame(){
        game = new Game();

        game.setQuantity(1);
        game.setStudio("Warner Bros");
        game.setEsrbRating("E");
        game.setPrice(bigDecimal);
        game.setTitle("Batman");
        game.setDescription("The newest in the batman series relive your roots as batman");

        game = gameRepository.save(game);

        game.setEsrbRating("T");
        game = gameRepository.save(game);

        Optional<Game> game1 = gameRepository.findById(game.getId());
        assertEquals(game1.get(), game); // compares the updated information with the one it gets from the id
    }
    @Test
    public void shouldDeleteGame(){
        game = new Game();

        game.setQuantity(1);
        game.setStudio("Warner Bros");
        game.setEsrbRating("E");
        game.setPrice(bigDecimal);
        game.setTitle("Batman");
        game.setDescription("The newest in the batman series relive your roots as batman");

        game = gameRepository.save(game);

        gameRepository.deleteById(game.getId());

        Optional<Game> game1 = gameRepository.findById(game.getId());
        assertFalse(game1.isPresent()); // makes sures that the deleted game is no longer in the repo with the assertfalse
    }
    @Test
    public void shouldGetGameById(){
        game = new Game();

        game.setQuantity(1);
        game.setStudio("Warner Bros");
        game.setEsrbRating("E");
        game.setPrice(bigDecimal);
        game.setTitle("Batman");
        game.setDescription("The newest in the batman series relive your roots as batman");

        game = gameRepository.save(game);

        Optional<Game> game1 = gameRepository.findById(game.getId());
        assertEquals(game1.get(), game); // makes sures that the game can be grabbed by id
    }
    @Test
    public void shouldGetGameByEsrbRating(){
        game = new Game();

        game.setQuantity(1);
        game.setStudio("Warner Bros");
        game.setEsrbRating("E");
        game.setPrice(bigDecimal);
        game.setTitle("Batman");
        game.setDescription("The newest in the batman series relive your roots as batman");

        game = gameRepository.save(game);

        List<Game> game1 = gameRepository.findByEsrbRating(game.getEsrbRating());
        assertEquals(game1.get(0), game);// makes sures the game can be found by rating
    }
    @Test
    public void shouldGetGameByTitle(){
        game = new Game();

        game.setQuantity(1);
        game.setStudio("Warner Bros");
        game.setEsrbRating("E");
        game.setPrice(bigDecimal);
        game.setTitle("Batman");
        game.setDescription("The newest in the batman series relive your roots as batman");

        game = gameRepository.save(game);

        List<Game> game1 = gameRepository.findByTitle(game.getTitle());
        assertEquals(game1.get(0), game);
    }
    @Test
    public void shouldGetGameByStudio(){
        game = new Game();

        game.setQuantity(1);
        game.setStudio("Warner Bros");
        game.setEsrbRating("E");
        game.setPrice(bigDecimal);
        game.setTitle("Batman");
        game.setDescription("The newest in the batman series relive your roots as batman");

        game = gameRepository.save(game);

        List<Game> game1 = gameRepository.findByStudio(game.getStudio());
        assertEquals(game1.get(0), game);
    }

}
