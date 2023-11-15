/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.gui.apriori;

import com.aprioricafe.entities.Menu;
import java.util.List;

/**
 *
 * @author aldo
 */
public class MenuInSalesInvoice {

    /**
     * @return the menuIDs
     */
    public List<Long> getMenuIDs() {
        return menuIDs;
    }

    /**
     * @param menuIDs the menuIDs to set
     */
    public void setMenuIDs(List<Long> menuIDs) {
        this.menuIDs = menuIDs;
    }
    private Long salesInvoiceId;
    private List<Long> menuIDs;

    /**
     * @return the salesInvoiceId
     */
    public Long getSalesInvoiceId() {
        return salesInvoiceId;
    }

    /**
     * @param salesInvoiceId the salesInvoiceId to set
     */
    public void setSalesInvoiceId(Long salesInvoiceId) {
        this.salesInvoiceId = salesInvoiceId;
    }

   
}
