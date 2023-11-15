/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.repositories;

import com.aprioricafe.entities.SalesInvoice;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aldo
 */
@Repository
public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice,Long>{
    @Query("SELECT c FROM SalesInvoice c "
            + " INNER JOIN FETCH c.createdBy "
            + " LEFT JOIN FETCH c.updatedBy "
            + "where c.date BETWEEN :startDate AND :endDate")
    List<SalesInvoice> findAllByInvoiceDateBetween(LocalDate startDate, LocalDate endDate);
    
   
}
