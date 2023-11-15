/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author aldo
 */
@Entity
public class SalesInvoiceSequence {

    /**
     * @return the xkey
     */
    public String getXkey() {
        return xkey;
    }

    /**
     * @param xkey the xkey to set
     */
    public void setXkey(String xkey) {
        this.xkey = xkey;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "xkey")
    private String xkey;
    
    
    private Long nextID;
    

    /**
     * @return the nextID
     */
    public Long getNextID() {
        return nextID;
    }

    /**
     * @param nextID the nextID to set
     */
    public void setNextID(Long nextID) {
        this.nextID = nextID;
    }

    
}
