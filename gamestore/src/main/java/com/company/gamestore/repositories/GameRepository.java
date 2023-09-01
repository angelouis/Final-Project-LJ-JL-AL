package com.company.gamestore.repositories;

import com.company.gamestore.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    Optional<Game> findByEsrbRating(String esrbRating);

    Optional<Game> findByTitle(String title);

    Optional<Game> findByStudio(String studio);

}
