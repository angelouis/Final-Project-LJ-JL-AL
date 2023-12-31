package com.company.gamestore.repositories;

import com.company.gamestore.models.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer> {
    /**
     * finds tax by state
     * @param state - the state is unqiue string
     * @return the tax
     */
    Optional<Tax> findTaxByState(String state);
}
