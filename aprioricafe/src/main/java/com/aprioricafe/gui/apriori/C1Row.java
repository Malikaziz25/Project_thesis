/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.gui.apriori;

import com.aprioricafe.entities.Menu;

/**
 *
 * @author aldo
 */
public class C1Row {
    private Menu menu;
    private double support;
    private int numberOfTransactions;

    /**
     * @return the menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    /**
     * @return the support
     */
    public double getSupport() {
        return support;
    }

    /**
     * @param support the support to set
     */
    public void setSupport(double support) {
        this.support = support;
    }

    /**
     * @return the numberOfTransactions
     */
    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    /**
     * @param numberOfTransactions the numberOfTransactions to set
     */
    public void setNumberOfTransactions(int numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }
}
