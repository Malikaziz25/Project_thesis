/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.services;

import com.aprioricafe.entities.CustomerPayment;
import com.aprioricafe.entities.PaymentMethod;
import com.aprioricafe.entities.SalesInvoice;
import com.aprioricafe.entities.SalesInvoiceSequence;
import com.aprioricafe.entities.SalesInvoiceStatus;
import com.aprioricafe.repositories.CustomerPaymentRepository;
import com.aprioricafe.repositories.SalesInvoiceRepository;
import com.aprioricafe.repositories.SalesInvoiceSequenceRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author aldo
 */
@Service
@Transactional
public class SalesInvoiceService {
    @Autowired
    SalesInvoiceSequenceRepository salesInvoiceSequenceRepository;
    
    @Autowired
    CustomerPaymentRepository customerPaymentRepository;
            
    @Autowired
    SalesInvoiceRepository salesInvoiceRepository;
    
    private long nextId;
    private long maxId;
    private int incrementBy;
    
    public SalesInvoiceService(){
        nextId = maxId = 0;
        incrementBy = 1;
    }
    public synchronized Long nextKey(){
	if(nextId==maxId)
	    reserveIds();
	return Long.valueOf(nextId++);
    }
    public void reserveIds(){
        
        long newNextId;
        
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        String monthyear = currentDate.format(formatter);
        //String format = String.format("%s/INV/09A02/%s", sequence, monthyear);
        var xseq = salesInvoiceSequenceRepository.findByXKey(monthyear);
        SalesInvoiceSequence seq;
        if(!xseq.isPresent()){
            newNextId = 1;
            seq = new SalesInvoiceSequence();
            seq.setXkey(monthyear);
        }else{
            seq = xseq.get();
            newNextId = seq.getNextID();
        }
        long newMaxId = newNextId + incrementBy;
        
        seq.setNextID(newMaxId);
        salesInvoiceSequenceRepository.saveAndFlush(seq);
        
        nextId = newNextId;
        maxId = newMaxId;

    }
    
    public String generateInvoiceNo(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        String monthyear = currentDate.format(formatter);
        
        String format = String.format("%03d/INV/09A02/%s", nextKey(), monthyear);
        return format;
    }
    
    private void setSalesInvoiceStatus(SalesInvoice salesInvoice){
        BigDecimal paidOnSales = salesInvoice.getTotalPaid();
        BigDecimal paidCredit = salesInvoice.getOtherPayment();
        BigDecimal totalPaid = paidOnSales.add(paidCredit);
        BigDecimal netDue = salesInvoice.getNetDue();
        if(netDue.compareTo(BigDecimal.ZERO)<=0){
            salesInvoice.setStatus(SalesInvoiceStatus.FULL_PAID.getValue());
        }else{
            if(totalPaid.compareTo(BigDecimal.ZERO)>0){
                salesInvoice.setStatus(SalesInvoiceStatus.PARTIALLY_PAID.getValue());    
            }else{
                salesInvoice.setStatus(SalesInvoiceStatus.UNPAID.getValue());    
            }
        }
        
    }
    
    public SalesInvoice calculatePayments(SalesInvoice salesInvoice){
        //find all payments for this invoice
        BigDecimal total = salesInvoice.getGrandTotal();
        BigDecimal paidOnSales = salesInvoice.getTotalPaid();
        BigDecimal otherPayments = BigDecimal.ZERO;
        
        List<CustomerPayment> payments = customerPaymentRepository.findAllByInvoiceId(salesInvoice.getId());
        for(CustomerPayment payment: payments ){
            otherPayments = otherPayments.add(payment.getTotalPaid());
        }
        salesInvoice.setOtherPayment(otherPayments);
        salesInvoice.setNetDue(total.subtract(paidOnSales).subtract(otherPayments));
        setSalesInvoiceStatus(salesInvoice);
        var saved = salesInvoiceRepository.save(salesInvoice);
        if(saved!=null){
            return saved;
        }
        return null;
    }
}
