/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.entities;

/**
 *
 * @author aldo
 */
public enum SalesInvoiceStatus{
    FULL_PAID(1), 
    UNPAID(2), 
    PARTIALLY_PAID(3),
    NOT_FULL_PAID(5)// OTHER THAN FULL_PAID
    ;

    private final int value;
    private SalesInvoiceStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}