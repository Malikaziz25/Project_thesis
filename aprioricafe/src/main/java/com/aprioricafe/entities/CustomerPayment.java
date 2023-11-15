/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.entities;

import com.aldoapp.swingboot.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity
public class CustomerPayment {

    /**
     * @return the totalAmount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return the discountAmount
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * @param discountAmount the discountAmount to set
     */
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * @return the grandTotal
     */
    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    /**
     * @param grandTotal the grandTotal to set
     */
    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;
    
    @ManyToOne(targetEntity = User.class)
    private User createdBy;
    
    @ManyToOne
    @JoinColumn(name="updated_by_user_id ", nullable = true)    
    private User updatedBy;
    
    private String paymentMethod;//from PaymentType
    
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal grandTotal; // totalamount - discount amount        
    
    private BigDecimal totalPaid;
    
    private BigDecimal realTotalPaid;
    private BigDecimal changeAmount;
    
    
    @ManyToOne(targetEntity = SalesInvoice.class)
    private SalesInvoice salesInvoice;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the createdAt
     */
    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    public java.sql.Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(java.sql.Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return the createdBy
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the updatedBy
     */
    public User getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy the updatedBy to set
     */
    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return the paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @param paymentMethod the paymentMethod to set
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * @return the totalPaid
     */
    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    /**
     * @param totalPaid the totalPaid to set
     */
    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    /**
     * @return the changeAmount
     */
    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    /**
     * @param changeAmount the changeAmount to set
     */
    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    /**
     * @return the salesInvoice
     */
    public SalesInvoice getSalesInvoice() {
        return salesInvoice;
    }

    /**
     * @param salesInvoice the salesInvoice to set
     */
    public void setSalesInvoice(SalesInvoice salesInvoice) {
        this.salesInvoice = salesInvoice;
    }

    /**
     * @return the realTotalPaid
     */
    public BigDecimal getRealTotalPaid() {
        return realTotalPaid;
    }

    /**
     * @param realTotalPaid the realTotalPaid to set
     */
    public void setRealTotalPaid(BigDecimal realTotalPaid) {
        this.realTotalPaid = realTotalPaid;
    }
}
