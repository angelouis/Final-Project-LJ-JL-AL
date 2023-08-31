package com.company.gamestore.controllers;

import com.company.gamestore.models.Console;
import com.company.gamestore.models.Game;
import com.company.gamestore.repositories.GameRepository;
import com.company.gamestore.services.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private GameRepository gameRepository;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    private BigDecimal bigDecimal = new BigDecimal("2.22");
    
    private Game game;

    @BeforeEach
    public void setUp(){
        gameRepository.deleteAll();

        game = new Game();
        game.setId(1);
        game.setQuantity(1);
        game.setStudio("Warner Bros");
        game.setEsrbRating("E");
        game.setPrice(bigDecimal);
        game.setTitle("Batman");
        game.setDescription("The newest in the batman series relive your roots as batman");

    }

    @Test
    public void testCreateGameShouldReturn201() throws Exception{
        String gameJson = mapper.writeValueAsString(game);

        Game game1 = new Game();
        game1.setQuantity(1);
        game1.setStudio("Warner Bros");
        game1.setEsrbRating("E");
        game1.setPrice(bigDecimal);
        game1.setTitle("Batman");
        game1.setDescription("The newest in the batman series relive your roots as batman");

        when(serviceLayer.saveGame(game)).thenReturn(game1);

        mockMvc.perform(post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gameJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }
    @Test
    public void testUpdateShouldReturnNoContent() throws Exception{
        String gameJson = mapper.writeValueAsString(game);

        serviceLayer.saveGame(game);

        doNothing().when(serviceLayer).updateGame(game);

        mockMvc.perform(put("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gameJson))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    @Test
    public void testGetGameByIdShouldReturn200() throws Exception{

        when(serviceLayer.findGame(game.getId())).thenReturn(game);

        mockMvc.perform(get("/games/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void testGetGameByEsrbRatingShouldReturn200() throws Exception{


        when(serviceLayer.getGameByEsrbRating(game.getEsrbRating())).thenReturn(Optional.ofNullable(game));

        mockMvc.perform(get("/games/esrb/{esrbRating}","E"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void testGetGameByTitleShouldReturn200() throws Exception{

        when(serviceLayer.getGameByTitle(game.getTitle())).thenReturn(Optional.ofNullable(game));

        mockMvc.perform(get("/games/title/{title}","Batman"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void testGetGameByStudioShouldReturn200() throws Exception{

        when(serviceLayer.getGameByStudio(game.getStudio())).thenReturn(Optional.ofNullable(game));

        mockMvc.perform(get("/games/studio/{studio}","Warner Bros"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllGamesShouldReturn200() throws Exception{

        Game game1 = new Game();
        game1.setQuantity(1);
        game1.setStudio("Warner Bros");
        game1.setEsrbRating("E");
        game1.setPrice(bigDecimal);
        game1.setTitle("Batman");
        game1.setDescription("The newest in the batman series relive your roots as batman");

        List<Game> gameList = new ArrayList<>();
        gameList.add(game);
        gameList.add(game1);

        when(serviceLayer.findAllGames())
                .thenReturn(gameList);

        mockMvc.perform(get("/games"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void testDeleteGameShouldReturnNoContent() throws Exception{
        int id = game.getId();

        serviceLayer.saveGame(game);

        doNothing().when(serviceLayer).removeGame(id);

        mockMvc.perform(delete("/games/{id}", id))
                .andDo(print())
                .andExpect(status().isNoContent());

    }
    @Test
    public void shouldReturn422WhenPostingAnEmptyEsrb() throws Exception {
        Game game1 = new Game();
        game1.setQuantity(1);
        game1.setStudio("Warner Bros");
        game1.setPrice(bigDecimal);
        game1.setTitle("Batman");
        game1.setDescription("The newest in the batman series relive your roots as batman");

        mockMvc.perform(post("/games")               // Perform the POST request
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(game1))
                )
                .andDo(print())                         // Print results to console
                .andExpect(status().isUnprocessableEntity());
    }
}   

