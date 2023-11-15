/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.entities;

import com.aldoapp.swingboot.entities.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author aldo
 */

@Entity
public class SalesInvoice {

    /**
     * @return the netDue
     */
    public BigDecimal getNetDue() {
        return netDue;
    }

    /**
     * @param netDue the netDue to set
     */
    public void setNetDue(BigDecimal netDue) {
        this.netDue = netDue;
    }

    /**
     * @return the otherPayment
     */
    public BigDecimal getOtherPayment() {
        return otherPayment;
    }

    /**
     * @param otherPayment the otherPayment to set
     */
    public void setOtherPayment(BigDecimal otherPayment) {
        this.otherPayment = otherPayment;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
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
    
    @Column(unique=true)
    private String invoiceNo;
    
    private LocalDate date;
    
    
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal grandTotal; // totalamount - discount amount
    
    
    private BigDecimal totalPaid; 
  
    private BigDecimal netDue; //totalamount -  discount amount - amountpaid - otherpayment
    private BigDecimal otherPayment;//bayar piutang
    private int status;//0: 
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesInvoiceItem> invoiceItems;
   
    public SalesInvoice(){
        otherPayment = BigDecimal.ZERO;
        totalAmount = BigDecimal.ZERO;
        totalPaid = BigDecimal.ZERO;
        netDue = BigDecimal.ZERO;
        discountAmount = BigDecimal.ZERO;
        grandTotal= BigDecimal.ZERO;
        
         
        
    }
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
     * @return the invoiceNo
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * @param invoiceNo the invoiceNo to set
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

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

    /**
     * @return the totalPaid
     */
    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    /**
     * @param amountPaid the totalPaid to set
     */
    public void setTotalPaid(BigDecimal amountPaid) {
        this.totalPaid = amountPaid;
    }

    /**
     * @return the invoiceItems
     */
    public List<SalesInvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    /**
     * @param invoiceItems the invoiceItems to set
     */
    public void setInvoiceItems(List<SalesInvoiceItem> invoiceItems) {
        for(SalesInvoiceItem item : invoiceItems){
            item.setInvoice(this);
        }
        this.invoiceItems = invoiceItems;
    }

  
}
