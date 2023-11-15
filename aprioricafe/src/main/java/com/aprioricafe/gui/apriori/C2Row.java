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
public class C2Row {
    private Menu menu1;
    private Menu menu2;
    private double support;
    private int numberOfTransactions;

    /**
     * @return the menu1
     */
    public Menu getMenu1() {
        return menu1;
    }

    /**
     * @param menu1 the menu1 to set
     */
    public void setMenu1(Menu menu1) {
        this.menu1 = menu1;
    }

    /**
     * @return the menu2
     */
    public Menu getMenu2() {
        return menu2;
    }

    /**
     * @param menu2 the menu2 to set
     */
    public void setMenu2(Menu menu2) {
        this.menu2 = menu2;
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
