/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.repositories;

import com.aprioricafe.entities.Menu;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author aldo
 */
public interface MenuRepository extends JpaRepository<Menu,Long>{
    Optional<Menu> findByCode(String code);
}
