/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aprioricafe.gui.apriori;

import com.aldoapp.swingboot.BeanProvider;
import com.aprioricafe.dto.AssociationResultReportItem;
import com.aprioricafe.entities.Menu;
import com.aprioricafe.services.PrintAprioriResultService;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author aldo
 */
public class AssociationResultDlg extends javax.swing.JDialog {
    @Autowired
    PrintAprioriResultService printAprioriResultService;
            
    private double minimumConfidence = 0.0;
    List<ConfidenceRow> confidences = new ArrayList<>();
    List<AssociationResultReportItem> reportItems = new ArrayList<>();
    /**
     * Creates new form AssociationResultDlg
     */
    public AssociationResultDlg() {
        BeanProvider.autowire(this); //use someRepository somewhere after this line.
        initComponents();
        setModal(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        btnPrint = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Hasil Kombinasi 2 Produk");

        jLabel1.setText("Kombinasi 2 Produk");

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl);

        btnPrint.setText("Buat Laporan");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnClose.setText("Kembali");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPrint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1044, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrint)
                    .addComponent(btnClose))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        try{  
            printAprioriResultService.generateAndPrint(reportItems);
        }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    public static void showModal(double minimumConfidence, List<ConfidenceRow> confidences) {
        
        AssociationResultDlg dialog = new AssociationResultDlg();
        dialog.pack();
        dialog.minimumConfidence = minimumConfidence;
        dialog.confidences = confidences;
        dialog.setLocationRelativeTo(null);
        dialog.loadData();
        dialog.setVisible(true);
        dialog.toFront();
        dialog.requestFocus();
        //System.exit(0);
    }
    
    private void loadData(){
        DefaultTableModel tableModel = new DefaultTableModel();
        
        tableModel.addColumn("No");
        tableModel.addColumn("Itemset {A U B}");
        tableModel.addColumn("Support {A U B}");
        tableModel.addColumn("Support {A}");
        tableModel.addColumn("Confidence");
        
        int no = 1;
        reportItems.clear();
                
        for(ConfidenceRow confrow: confidences){
            Vector row = new Vector();
            if(confrow.getConfidence()>=minimumConfidence){
                
                row.add(no);
                String itemset = "";
                itemset += confrow.getAntecedent().getName() + "=>";
                
                List<String> menus = new ArrayList<>();
                for(Menu menu : confrow.getConsequent()){
                    menus.add(menu.getName());
                }
                itemset += String.join(", ", menus);
                row.add(itemset);
                
                row.add(String.format( "%.2f %%",confrow.getSupportAntecedent()));
                row.add(String.format( "%.2f %%",confrow.getSupportItem()));
                row.add(String.format( "%.2f %%",confrow.getConfidence()));
                tableModel.addRow(row);
                
                //for report
                AssociationResultReportItem item = new AssociationResultReportItem();
                item.setNo(String.valueOf(no));
                
                
                String itemset2 = "JIKA MEMBELI ";
                itemset2 += confrow.getAntecedent().getName() + " MAKA MEMBELI ";
                
                List<String> menus2 = new ArrayList<>();
                for(Menu menu : confrow.getConsequent()){
                    menus2.add(menu.getName());
                }
                itemset2 += String.join(", ", menus2);                
                item.setItemsetAUB(itemset2);
                item.setLevel(confrow.getLevel());
                item.setConfidence(String.format( "%.2f %%",confrow.getConfidence()));
                item.setSupportA(String.format( "%.2f %%",confrow.getSupportItem()));
                item.setSupportAUB(String.format( "%.2f %%",confrow.getSupportAntecedent()));
                reportItems.add(item);
                
                no++;
            }
            
        }
        tbl.setModel(tableModel);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnPrint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables
}
