package com.company.gamestore.repositories;

import com.company.gamestore.models.Invoice;
import com.company.gamestore.models.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer> {

    Optional<Tax> findTaxByState(String state);
}
