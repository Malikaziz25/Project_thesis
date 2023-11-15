/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aprioricafe.gui.salesinvoice;

import com.aldoapp.swingboot.AppSession;
import com.aldoapp.swingboot.BeanProvider;
import com.aldoapp.swingboot.helpers.RupiahHelper;
import com.aldoapp.swingboot.helpers.TimeHelper;
import com.aldoapp.swingboot.repositories.SettingRepository;
import com.aldoapp.swingboot.services.InitialDataService;
import com.aprioricafe.entities.Menu;
import com.aprioricafe.entities.MenuItem;
import com.aprioricafe.entities.SalesInvoice;
import com.aprioricafe.entities.SalesInvoiceItem;
import com.aprioricafe.entities.SalesInvoiceStatus;
import com.aprioricafe.repositories.CustomerPaymentRepository;
import com.aprioricafe.repositories.ItemRepository;
import com.aprioricafe.repositories.MenuItemRepository;
import com.aprioricafe.repositories.MenuRepository;
import com.aprioricafe.repositories.SalesInvoiceRepository;
import com.aprioricafe.services.SalesInvoiceService;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author aldo
 */
public class SalesInvoiceDlg extends javax.swing.JDialog {
    @Autowired
    SettingRepository settingRepository;
    
    @Autowired
    InitialDataService initialDataService;
    
    @Autowired
    AppSession appSession;
    
    @Autowired
    ItemRepository itemRepository;
    
    @Autowired
    SalesInvoiceRepository salesInvoiceRepository;
     
    @Autowired
    MenuRepository menuRepository;
    
    @Autowired
    MenuItemRepository menuItemRepository;
    
    @Autowired
    SalesInvoiceService salesInvoiceSvc;
    
    @Autowired
    CustomerPaymentRepository customerPaymentRepository;
    
    private Menu item;
    private SalesInvoice entity;
    private List<Long> insertedItemIDs = new ArrayList<>();
    private List<SalesInvoiceItem> items = new ArrayList<>();
    private String imgItem;
    private int row;
    BigDecimal total = new BigDecimal(0);
    
    /**
     * Creates new form SalesInvoiceDlg
     */
    void searchAndAdd(){
        var found = searchItem();
        if(!found){
            JOptionPane.showMessageDialog(null, "Kode tidak ditemukan");
            return;
        }
        int index = insertedItemIDs.indexOf(item.getId());
        if(index==-1){                
            addMenuItem();
            txtCode.setText("");     
            System.out.println("new");
        }else{
            
            System.out.println("existing");
            //update qty
            updateQtyItem();
            txtCode.setText("");                    
            loadData();
        }
    }
    public SalesInvoiceDlg() {
        BeanProvider.autowire(this); //use someRepository somewhere after this line.
        initComponents();
        setModal(true);
        Action action2 = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                searchAndAdd();
            }
        };
        txtCode.addActionListener(action2);
        tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // do some actions here, for example
                // print first column value from selected row
                row = tbl.getSelectedRow();
                if (!e.getValueIsAdjusting() && row != -1){
                    System.out.println(tbl.getValueAt(row, 0).toString());
                    var selectedItem = items.get(row);
                    txtQty.setText(selectedItem.getQuantity().toString());
                    txtCode.setText(selectedItem.getMenu().getCode());
                    item = selectedItem.getMenu();
                }
            }
        });
        loadData();
    }
    private boolean searchItem(){
        System.out.println("Searchxx");
        String code = txtCode.getText().trim();
        System.out.println("Searchxx"+code);
        if(code.isBlank()){
            System.out.println("blank");
            item = null;
            return false;
        }
        System.out.println("not blank");
        var xitem = menuRepository.findByCode(code);
        if(xitem.isPresent()){
            System.out.println("found");
            item = xitem.get();     
            //lblCommodityName.setText(product.getName());
            return true;
        }else{
            item = null;
        }
        return false;
    }
    DocumentListener calculateTotalFunc(){
        return new DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent de) {
                calculateTotal();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent de) {
                calculateTotal();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent de) {
                calculateTotal();
            }
          };
    }
    void calculateTotal(){
        total = new BigDecimal(0);
        for(SalesInvoiceItem item: items){
            total = total.add(item.getNetAmount());
        }
        lblTotal.setText("Total : "+RupiahHelper.formatRupiah(total));
    }
    void updateQtyItem(){
         if(item == null){
            return;
        }
        String tqty = txtQty.getText().trim();
        long qty = 0;
        if(tqty.isBlank()){
            qty = 1;
        }else{
            qty = Long.parseLong(tqty);
        }
        System.out.println("newqty");
        System.out.println(qty);
        int index = insertedItemIDs.indexOf(item.getId());
        items.get(index).setQuantity(qty);
    }
    void addMenuItem(){
        if(item == null){
            return;
        }
        String tqty = txtQty.getText().trim();
        long qty = 0;
        if(tqty.isBlank()){
            qty = 1;
        }else{
            qty = Long.parseLong(tqty);
        }
        System.out.println("newqty");
        System.out.println(qty);

        SalesInvoiceItem invoiceItem = new SalesInvoiceItem();
        invoiceItem.setCreatedAt(TimeHelper.getCurrentTimestamp());
        invoiceItem.setCreatedBy(appSession.getCurrentuser());
        invoiceItem.setUnitPrice(item.getUnitPrice());
        invoiceItem.setMenu(item);
        invoiceItem.setQuantity(qty);
        var totalAmount = item.getUnitPrice().multiply(BigDecimal.valueOf(qty));
        invoiceItem.setTotalAmount(totalAmount);
        
        var discountAmount = new BigDecimal(0);
        invoiceItem.setDiscountAmount(discountAmount);
        
        BigDecimal netAmount = totalAmount.subtract(discountAmount);
        invoiceItem.setNetAmount(netAmount);
        
        //save to entity items
        int index = insertedItemIDs.indexOf(item.getId());
        if(index==-1){
            items.add(invoiceItem);
            insertedItemIDs.add(item.getId());
        }else{
            items.set(index, invoiceItem);
        }
        
        clearTextField();
        loadData();
    }
    void clearTextField(){
        txtCode.setText("");
        txtQty.setText("1");
        txtCode.requestFocus();
                
        item = null;
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
        txtCode = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        btnPay = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Transaksi Penjualan");

        jLabel1.setText("Masukkan Kode Menu");

        btnAdd.setText("Save");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel2.setText("Daftar Menu");

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

        jLabel3.setText("Qty");

        txtQty.setText("1");

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 255));

        lblTotal.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 0));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("Rp. 0,00");
        lblTotal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnPay.setText("Bayar");
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        btnCancel.setText("Batal");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnCancel)
                                .addGap(18, 18, 18)
                                .addComponent(btnPay))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel1)
                                        .addGap(31, 31, 31)
                                        .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(113, 113, 113)
                                        .addComponent(btnAdd))
                                    .addComponent(btnDelete))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd)
                    .addComponent(jLabel3)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnPay))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        searchAndAdd();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = tbl.getSelectedRow();            
        if(row>=0){
            items.remove(row);           
            insertedItemIDs.remove(row);
            loadData();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
    @Transactional
    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        // TODO add your handling code here:
        if(items==null || (items!=null && items.size()==0)){
            JOptionPane.showMessageDialog(null, "Masukkan menu");
            return;
        }
        saveInvoice();
        
        int dialogResult = JOptionPane.showConfirmDialog (null, "Apakah anda mau buat invoice lagi?","Confirmation",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            dispose();
            SalesInvoiceDlg.showModal(null);
        }else{
            dispose();
        }

    }//GEN-LAST:event_btnPayActionPerformed
    void saveInvoice(){
        
        if(entity==null){
            entity = new SalesInvoice();
            entity.setCreatedAt(TimeHelper.getCurrentTimestamp());
            entity.setCreatedBy(appSession.getCurrentuser());
        }else{
            
            entity.setUpdatedAt(TimeHelper.getCurrentTimestamp());
            entity.setUpdatedBy(appSession.getCurrentuser());
            
            //check existing payment
            var existingPayments = customerPaymentRepository.findAllByInvoiceId(entity.getId());
            if(existingPayments != null && !existingPayments.isEmpty()){
                JOptionPane.showMessageDialog(null, "Data Tidak Bisa Disimpan karena sudah ada pembayaran piutang");
                return;
            }
        }
        var discountAmount = new BigDecimal(0);
        var amountPaid = new BigDecimal(0);
        
        entity.setStatus(SalesInvoiceStatus.UNPAID.getValue());
        entity.setDate(LocalDate.now());
        entity.setInvoiceItems(items);
        entity.setTotalAmount(total);
        
        var grandTotal = entity.getTotalAmount().subtract(discountAmount);        
        entity.setGrandTotal(grandTotal);
        
        entity.setTotalPaid(amountPaid);
        entity.setNetDue(grandTotal);       
        
        entity.setInvoiceNo(salesInvoiceSvc.generateInvoiceNo());
        var saved = salesInvoiceRepository.save(entity);
        if(saved==null){
            
            JOptionPane.showMessageDialog(null, "Data Tidak Tersimpan");
        }else{
            //show payment dlg
            //update stock
            System.out.println("xxx-----");
            
            for(SalesInvoiceItem salesInvoiceItem:items){
                var orderedQty = salesInvoiceItem.getQuantity();
                
                var xmenu = salesInvoiceItem.getMenu();    
                System.out.println("menuID "+xmenu.getId());
                System.out.println("menuName "+xmenu.getName());
                
                if(xmenu.isIsCombination()){
                    var combMenus = xmenu.getCombinations();
                    for(Menu combMenu: combMenus){
                        //var itemsInMenu = combMenu.getMenuItems();
                        var itemsInMenu = menuItemRepository.findAllByMenuId(combMenu.getId());
                        decreaseQtyInMenuItems(itemsInMenu, orderedQty);
                    }
                }else{
                    //1 menu consist of several items
                    //var itemsInMenu = xmenu.getMenuItems();
                    var itemsInMenu = menuItemRepository.findAllByMenuId(xmenu.getId());
                    decreaseQtyInMenuItems(itemsInMenu, orderedQty);
                }
                
                
            }
            PaymentDlg.showModal(saved);
            
            
        }
    }
    
    void decreaseQtyInMenuItems(List<MenuItem> itemsInMenu, Long orderedQty){
        for(MenuItem itemInMenu: itemsInMenu){
            var qtyInMenu = itemInMenu.getQty();
            var itemx = itemInMenu.getItem();

            System.out.println("itemID "+itemx.getId());
            System.out.println("itemName "+itemx.getName());

            var prevstock = itemx.getStock();
            System.out.println("prevstock");
            System.out.println(prevstock);

            var newstock = prevstock - (orderedQty * qtyInMenu);
            System.out.println("newstock");
            System.out.println(newstock);
            itemx.setStock(newstock);

            var savedItem = itemRepository.save(itemx);
            if(savedItem == null){
                JOptionPane.showMessageDialog(null, "Data Stock Item Tidak Tersimpan");            
            }
        }
    }
    
    public static void showModal(String[] args) {
        
        SalesInvoiceDlg dialog = new SalesInvoiceDlg();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.toFront();
        dialog.requestFocus();
        //System.exit(0);
    }
    private void loadData(){
        
        
        tbl.setModel(new SalesInvoiceItemTableModel(items));
        calculateTotal();
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtQty;
    // End of variables declaration//GEN-END:variables
}
