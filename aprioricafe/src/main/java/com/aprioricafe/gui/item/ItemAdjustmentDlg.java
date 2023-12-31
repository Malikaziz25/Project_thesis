/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aprioricafe.gui.item;

import com.aldoapp.swingboot.AppSession;
import com.aldoapp.swingboot.BeanProvider;
import com.aprioricafe.dto.ItemAdjustementReportItem;
import com.aprioricafe.entities.Item;
import com.aprioricafe.repositories.ItemRepository;
import com.aprioricafe.services.PrintItemAdjustmentReportService;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author aldo
 */
public class ItemAdjustmentDlg extends javax.swing.JDialog {
    @Autowired
    AppSession appSession;
    
    @Autowired
    ItemRepository itemRepository;
    
    @Autowired
    PrintItemAdjustmentReportService printItemAdjustmentReportService;
    
    List<Item> idetails;
    Item selectedItem;
    int row ;
    
    /**
     * Creates new form ItemAdjustmentDlg
     */
    public ItemAdjustmentDlg() {
        BeanProvider.autowire(this); //use someRepository somewhere after this line.
        initComponents();
        setModal(true);
        tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // do some actions here, for example
                // print first column value from selected row
                row = tbl.getSelectedRow();
                if (!e.getValueIsAdjusting() && row != -1){
                    System.out.println(tbl.getValueAt(row, 0).toString());
                    selectedItem = idetails.get(row);
                    txtActual.setText(selectedItem.getStock().toString());
                }
            }
        });
        loadData();
        
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
        jLabel2 = new javax.swing.JLabel();
        txtActual = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Item Adjustment");

        jLabel1.setText("Daftar Barang");

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

        jLabel2.setText("Jumlah Aktual");

        txtActual.setText("0");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(37, 37, 37)
                .addComponent(txtActual, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addContainerGap(426, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrint)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(442, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSave))
                    .addComponent(jLabel2))
                .addGap(16, 16, 16)
                .addComponent(btnPrint)
                .addGap(30, 30, 30))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(100, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String tactual = txtActual.getText().trim();
        if(tactual.isBlank()){
            JOptionPane.showMessageDialog(null, "Masukkan jumlah aktual"); 
            return;
        }
        if(selectedItem==null){
            JOptionPane.showMessageDialog(null, "Pilih item"); 
            return;
        }
        Long newval = Long.parseLong(tactual);
        Long oldval = selectedItem.getStock();
        var change = newval - oldval;
        selectedItem.setStock(newval);
        var saved = itemRepository.save(selectedItem);
        if(saved==null){
            JOptionPane.showMessageDialog(null, "gagal mengupdate stok item"); 
        }else{
            var model = tbl.getModel();
            model.setValueAt(change, row, 5);
            model.setValueAt(newval, row, 3);
        }
                
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        List<ItemAdjustementReportItem> reportItems = new ArrayList<>();
        var model = tbl.getModel();
        var nrow = model.getRowCount();
        int no = 1;
        for(int i=0; i<nrow; i++){
            ItemAdjustementReportItem reportItem = new ItemAdjustementReportItem();
            reportItem.setCode(model.getValueAt(i, 1).toString());
            reportItem.setName(model.getValueAt(i, 2).toString());
            reportItem.setNo(String.valueOf(no));
            reportItem.setActualTotal(model.getValueAt(i, 3).toString());
            reportItem.setTotal(model.getValueAt(i, 4).toString());
            reportItem.setChange(model.getValueAt(i, 5).toString());
            reportItem.setUnitName(model.getValueAt(i, 6).toString());
            reportItems.add(reportItem);
            no++;
        }
        
        try{
            printItemAdjustmentReportService.generateAndPrint(appSession.getCurrentuser().getFullName(), reportItems);
        }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    public static void showModal(String[] args) {
        
        ItemAdjustmentDlg dialog = new ItemAdjustmentDlg();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.toFront();
        dialog.requestFocus();
        //System.exit(0);
    }
    private void loadData(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Kode Barang");
        tableModel.addColumn("Nama");
        tableModel.addColumn("Jumlah Aktual");
        tableModel.addColumn("Total ");
        tableModel.addColumn("Hasil (- atau +)");
        tableModel.addColumn("Unit");
        
        
        idetails = itemRepository.findAll();
        
        for (Item detail : idetails) {
            Vector row = new Vector();
            row.add(detail.getId());
            row.add(detail.getCode());
            row.add(detail.getName());
            row.add(detail.getStock());
            row.add(detail.getStock());
            row.add("");
            row.add(detail.getUnitName());
            tableModel.addRow(row);
        
        }
        
        
        tbl.setModel(tableModel);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtActual;
    // End of variables declaration//GEN-END:variables
}
