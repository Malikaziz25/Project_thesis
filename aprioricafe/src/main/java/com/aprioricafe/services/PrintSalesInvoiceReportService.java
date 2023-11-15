/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.services;

import com.aldoapp.swingboot.entities.Setting;
import com.aldoapp.swingboot.repositories.SettingRepository;
import com.aldoapp.swingboot.services.InitialDataService;
import com.aprioricafe.dto.SalesInvoiceDetailsReportItem;
import java.awt.Desktop;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aldo
 */
@Service
public class PrintSalesInvoiceReportService {
    @Autowired
    InitialDataService initialDataSvc;
    
    @Autowired
    SettingRepository settingRepository;
    
    
    private Map<String, Object> getParameters(String invoiceDate, String invoiceNo, String totalAmount){
        String compName = "";
        String compAddress = "";
        String compPhone = "";
        String compLogo = "";
        
        var xComp = settingRepository.findByKey(Setting.COMPANY_NAME);
        if(xComp.isPresent()){
            compName = xComp.get().getValue();
        }
        var xCompAdd = settingRepository.findByKey(Setting.COMPANY_ADDRESS);
        if(xCompAdd.isPresent()){
            compAddress = xCompAdd.get().getValue();
        }
        var xCompPhone = settingRepository.findByKey(Setting.COMPANY_PHONE);
        if(xCompPhone.isPresent()){
            compPhone = xCompPhone.get().getValue();
        }
        String docPath = initialDataSvc.getDocPath();
        compLogo = docPath+File.separator+"logo.png";
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("companyName", compName);
        parameters.put("companyAddress", compAddress);
        parameters.put("companyPhone", compPhone);
        parameters.put("companyLogoPath", compLogo);
        parameters.put("invoiceDate", invoiceDate);
        parameters.put("invoiceNo", invoiceNo);
        parameters.put("totalAmount", totalAmount);
        
        return parameters;
    }
    private JasperReport getJasperReport() throws FileNotFoundException, JRException {
        //File template = ResourceUtils.getFile("classpath:reports/invoice.jrxml");
        //return JasperCompileManager.compileReport(template.getAbsolutePath());
        //InputStream jasperStream = getClass().getResourceAsStream("classpath:reports/invoice.jasper");
        //JasperReport report = (JasperReport) JRLoader.loadObject(jasperStream);
        String reportFile = "reports/sales-invoice.jrxml";
        JasperReport Jasp = JasperCompileManager.compileReport(reportFile);
        return Jasp;
    }
    private JRDataSource getDataSource(List<SalesInvoiceDetailsReportItem> result){
        
        
        return new JRBeanCollectionDataSource(result);
    }
    
    public void generateAndPrint(String invoiceDate, String invoiceNo, String totalAmount, 
            List<SalesInvoiceDetailsReportItem> result)throws JRException, FileNotFoundException, FontFormatException, IOException{
        
        String pdfFileName = java.util.UUID.randomUUID().toString();
        String destFileName = initialDataSvc.getPDFPath()+ File.separator + pdfFileName +".pdf";
        // 1. compile template ".jrxml" file
        JasperReport jasperReport = getJasperReport();

        JRDataSource dataSource = getDataSource(result);
        Map<String, Object> parameters = getParameters(invoiceDate,invoiceNo,totalAmount );
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, 
            parameters, 
            dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
        if (Desktop.isDesktopSupported()) {
            try {
                
                File myFile = new File(destFileName);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }
}
