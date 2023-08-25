package com.company.gamestore.contollers;

import com.company.gamestore.models.Game;
import com.company.gamestore.repositories.GameRepository;
import com.company.gamestore.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    public void testCreateShouldReturn201() throws Exception{
        String gameJson = mapper.writeValueAsString(game);

        mockMvc.perform(post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gameJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }
    @Test
    public void testPutShouldReturnNoContent() throws Exception{
        String gameJson = mapper.writeValueAsString(game);

        mockMvc.perform(put("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gameJson))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    @Test
    public void testGetGameByIdShouldReturn200() throws Exception{

        mockMvc.perform(get("/games/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void testGetGameByEsrbRatingShouldReturn200() throws Exception{

        mockMvc.perform(get("/games/esrb/{esrbRating}","E"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void testGetGameByTitleShouldReturn200() throws Exception{

        mockMvc.perform(get("/games/title/{title}","Batman"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void testGetGameByStudioShouldReturn200() throws Exception{

        mockMvc.perform(get("/games/studio/{studio}","Warner Bros"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllGamesShouldReturn200() throws Exception{
        mockMvc.perform(get("/games"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void testDeleteGameShouldReturnNoContent() throws Exception{
        int id = game.getId();

        mockMvc.perform(delete("/games/{id}", id))
                .andDo(print())
                .andExpect(status().isNoContent());

    }
    @Test
    public void testGameShouldReturnNotFound() throws Exception {

        mockMvc.perform(get("/games/{id}", 999))
                .andExpect(status().isNotFound());
    }
}   

