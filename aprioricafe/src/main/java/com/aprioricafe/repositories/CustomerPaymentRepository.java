/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.repositories;

import com.aprioricafe.entities.CustomerPayment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aldo
 */
@Repository
public interface CustomerPaymentRepository extends JpaRepository<CustomerPayment,Long>{
    @Query("SELECT c FROM CustomerPayment c "
            + " INNER JOIN FETCH c.createdBy "
            + " LEFT JOIN FETCH c.updatedBy "
            + "where salesInvoice.id = :salesInvoiceId")
    List<CustomerPayment> findAllByInvoiceId(Long salesInvoiceId);
    
    @Query("SELECT c FROM CustomerPayment c "
            + " INNER JOIN FETCH c.createdBy "
            + " LEFT JOIN FETCH c.updatedBy "
            + " where salesInvoice.id = :salesInvoiceId and c.paymentMethod = :paymentMethod")
    List<CustomerPayment> findAllByInvoiceIdAndStatus(Long salesInvoiceId, String paymentMethod);
    
}
