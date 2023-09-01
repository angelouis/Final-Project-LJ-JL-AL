package com.company.gamestore.repositories;

import com.company.gamestore.models.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {
    /**
     * finds all the consoles by manufacturer
     * @param manufacturer - not unique String
     * @return a list of consoles
     */
    List<Console> findByManufacturer(String manufacturer);
}
