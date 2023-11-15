/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.dto;

/**
 *
 * @author aldo
 */
public class ItemAdjustementReportItem {

    /**
     * @return the actualTotal
     */
    public String getActualTotal() {
        return actualTotal;
    }

    /**
     * @param actualTotal the actualTotal to set
     */
    public void setActualTotal(String actualTotal) {
        this.actualTotal = actualTotal;
    }

    /**
     * @return the change
     */
    public String getChange() {
        return change;
    }

    /**
     * @param change the change to set
     */
    public void setChange(String change) {
        this.change = change;
    }

    /**
     * @return the no
     */
    public String getNo() {
        return no;
    }

    /**
     * @param no the no to set
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the unitName
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * @param unitName the unitName to set
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * @return the stock
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param stock the stock to set
     */
    public void setTotal(String stock) {
        this.total = stock;
    }
    private String no;
    private String code;
    private String name;
    
    private String unitName;
    
    private String total = String.valueOf(0);
    private String actualTotal;
    private String change;
}
