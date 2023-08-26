package com.company.gamestore.repositories;

import com.company.gamestore.models.TShirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TShirtRepository extends JpaRepository<TShirt, Integer> {
    /**
     * Provides a list of t-shirts that is for a specific color
     * @param color - needs the color input
     * @return Returns a list of t-shirts in the color
     */
    public List<TShirt> findByColor(String color);

    /**
     * Provides a list of t-shirts that is for a specific size
     * @param size - needs the size input
     * @return Returns a list of t-shirts in the size
     */
    public List<TShirt> findBySize(String size);
}
