/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.repositories;

import com.aprioricafe.entities.MenuItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author aldo
 */
public interface MenuItemRepository extends JpaRepository<MenuItem,Long>{
    @Query("SELECT c FROM MenuItem c "
            + " INNER JOIN FETCH c.createdBy "
            + " LEFT JOIN FETCH c.updatedBy "
            + "where menu.id = :menuId")
    public List<MenuItem> findAllByMenuId(Long menuId);
}
