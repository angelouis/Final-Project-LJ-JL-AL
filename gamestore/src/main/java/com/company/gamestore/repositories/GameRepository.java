package com.company.gamestore.repositories;

import com.company.gamestore.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    /**
     *
     * @param esrbRating
     * @return a game based on input string
     */
    List<Game> findByEsrbRating(String esrbRating);

    List<Game> findByTitle(String title);

    List<Game> findByStudio(String studio);

}
