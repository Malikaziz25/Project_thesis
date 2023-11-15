/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.repositories;

import com.aprioricafe.entities.SalesInvoiceItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aldo
 */
@Repository
public interface SalesInvoiceItemRepository extends JpaRepository<SalesInvoiceItem,Long> {
    @Query("SELECT c FROM SalesInvoiceItem c "
            + " INNER JOIN FETCH c.createdBy "
            + " LEFT JOIN FETCH c.updatedBy "
            + "where invoice.id = :salesInvoiceId")
    List<SalesInvoiceItem> findAllByInvoiceId(Long salesInvoiceId);
}
