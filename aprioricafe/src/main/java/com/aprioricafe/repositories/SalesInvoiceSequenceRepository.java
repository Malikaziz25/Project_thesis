/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.repositories;

import com.aprioricafe.entities.SalesInvoiceSequence;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aldo
 */
@Repository
public interface SalesInvoiceSequenceRepository extends JpaRepository<SalesInvoiceSequence,String>{
    @Query("""
        select pc
        from SalesInvoiceSequence pc
        where pc.xkey = :xkey
        """)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<SalesInvoiceSequence> findByXKey(String xkey);
}
