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
        assertEquals(gameList.size(),1);
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

        assertEquals(gameRepository.findAll().size(),0);
        game = gameRepository.save(game);

        Optional<Game> game1 = gameRepository.findById(game.getId());
        assertEquals(gameRepository.findAll().size(),1);
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
        assertEquals(game1.get(), game);
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
        assertFalse(game1.isPresent());
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
        assertEquals(game1.get(), game);
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
        assertEquals(game1.get(0), game);
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
