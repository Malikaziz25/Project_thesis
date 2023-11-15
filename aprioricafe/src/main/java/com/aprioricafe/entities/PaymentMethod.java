/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.entities;

import com.aldoapp.swingboot.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 *
 * @author aldo
 */

public  enum PaymentMethod{
    CASH("CASH"),
    CREDIT_CARD("CREDIT_CARD");


    private final String stringValue;
    private PaymentMethod(String stamp) {
        this.stringValue = stamp;
    }
    public String getStringValue(){
        return this.stringValue;
    }
}