/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.repositories;

import com.aprioricafe.entities.Item;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author aldo
 */
public interface ItemRepository extends JpaRepository<Item,Long>{
    Optional<Item> findByCode(String code);
    Optional<Item> findByName(String name);
}
