/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aprioricafe.gui.menu;

import com.aldoapp.swingboot.AppSession;
import com.aldoapp.swingboot.BeanProvider;
import com.aldoapp.swingboot.gui.ImageFileSelector;
import com.aldoapp.swingboot.helpers.TimeHelper;
import com.aldoapp.swingboot.repositories.SettingRepository;
import com.aldoapp.swingboot.services.InitialDataService;
import com.aprioricafe.entities.Item;
import com.aprioricafe.entities.Menu;
import com.aprioricafe.entities.MenuItem;
import com.aprioricafe.repositories.ItemRepository;
import com.aprioricafe.repositories.MenuItemRepository;
import com.aprioricafe.repositories.MenuRepository;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author aldo
 */
public class AddMenuDlg extends javax.swing.JDialog {
    @Autowired
    SettingRepository settingRepository;
    
    @Autowired
    InitialDataService initialDataService;
    
    @Autowired
    AppSession appSession;
    
    @Autowired
    MenuRepository menuRepository;
    
    @Autowired
    MenuItemRepository menuItemRepository;
    
    @Autowired
    ItemRepository itemRepository;
            
    
    private Item item;
    private Menu entity;
    private List<Long> insertedItemIDs = new ArrayList<>();
    private List<MenuItem> items = new ArrayList<>();
    private String imgItem;
    
    private int row;
    
    /**
     * Creates new form AddMenuDlg
     */
    public AddMenuDlg() {
        BeanProvider.autowire(this); //use someRepository somewhere after this line.
        initComponents();
        setModal(true);
        Action action2 = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                var found = searchItem();
                if(!found){
                    JOptionPane.showMessageDialog(null, "Kode tidak ditemukan");
                    return;
                }
                int index = insertedItemIDs.indexOf(item.getId());
                if(index==-1){                
                    addMenuItem();
                    txtNewItem.setText("");
                    txtQty.setText("1");
                }else{
                    var prevQty = items.get(index).getQty();            
                    items.get(index).setQty(prevQty+1);
                    
                    txtNewItem.setText("");
                    txtQty.setText("1");
                    loadData();
                }
            }
        };
        txtNewItem.addActionListener(action2);
        
        tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // do some actions here, for example
                // print first column value from selected row
                row = tbl.getSelectedRow();
                if (!e.getValueIsAdjusting() && row != -1){
                    System.out.println(tbl.getValueAt(row, 0).toString());
                    var selectedItem = items.get(row);
                    txtQty.setText(selectedItem.getQty().toString());
                    txtNewItem.setText(selectedItem.getItem().getName());
                    item = selectedItem.getItem();
                }
            }
        });
        loadData();
    }
    
     void clearTextField(){
        txtName.setText("");
       
        txtPrice.setText("");        
        insertedItemIDs.clear();
        items.clear();
                
        item = null;
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
        
        /*if(item.getStock()-qty<0){
            JOptionPane.showMessageDialog(null, "Stok kurang");
            return;
        }*/       

        MenuItem newMenuItem = new MenuItem();
        newMenuItem.setCreatedAt(TimeHelper.getCurrentTimestamp());
        newMenuItem.setCreatedBy(appSession.getCurrentuser());

        newMenuItem.setItem(item);
        newMenuItem.setQty(qty);
        
        //save to entity items
        int index = insertedItemIDs.indexOf(item.getId());
        if(index==-1){
            items.add(newMenuItem);
            insertedItemIDs.add(item.getId());
        }else{
            items.set(index, newMenuItem);
        }
        txtQty.setText("");
        txtNewItem.setText("");
        item = null;
        loadData();
    }
    
    private boolean searchItem(){
        System.out.println("Searchxx");
        String code = txtNewItem.getText().trim();
        System.out.println("Searchxx"+code);
        if(code.isBlank()){
            System.out.println("blank");
            item = null;
            return false;
        }
        System.out.println("not blank");
        var xitem = itemRepository.findByCode(code);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbCategory = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtNewItem = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tambah Menu");

        jLabel1.setText("Nama Menu");

        jLabel2.setText("Kode Menu");

        jLabel3.setText("Kategori");

        cbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Breakfast", "Lunch", "Dinner", "Entrees", "Desserts", "Beverages", "Starters", "Sandwiches" }));

        jLabel4.setText("Harga");

        jLabel5.setText("Komposisi :");

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
        jScrollPane2.setViewportView(tbl);

        jLabel6.setText("Kode Barang");

        txtNewItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNewItemActionPerformed(evt);
            }
        });

        btnAdd.setText("Simpan");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel7.setText("Gambar");

        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageMouseClicked(evt);
            }
        });

        jLabel8.setText("Qty");

        txtQty.setText("1");

        jLabel9.setText("Rp.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(626, 626, 626)
                        .addComponent(btnSave))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnDelete)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtName)
                                    .addComponent(txtCode)
                                    .addComponent(cbCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPrice))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(40, 40, 40)
                                .addComponent(txtNewItem, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAdd)
                                .addGap(58, 58, 58)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel8)
                        .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNewItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdd)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNewItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewItemActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtNewItemActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = tbl.getSelectedRow();            
        if(row>=0){
            items.remove(row);           
            insertedItemIDs.remove(row);
            loadData();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
    @Transactional
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        save();
        
        int dialogResult = JOptionPane.showConfirmDialog (null, "Apakah anda mau buat menu lagi?","Confirmation",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            dispose();
            AddMenuDlg.showModal(null);
        }else{
            dispose();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked
        // TODO add your handling code here:
        try{
            File logoFile = ImageFileSelector.showDialog(null);
            if(logoFile==null){
                return;
            }
            String docPath = initialDataService.getDocPath();     
            var uid = java.util.UUID.randomUUID();
            File dest = new File(docPath+File.separator+uid+".png");
            imgItem = uid+".png";
            Files.copy(logoFile.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

            Image image = ImageIO.read(dest);
            if (image != null) {
                lblImage.setIcon(new ImageIcon(image));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
            ex.printStackTrace();
        }
    }//GEN-LAST:event_lblImageMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        addMenuItem();
    }//GEN-LAST:event_btnAddActionPerformed
    private boolean validateInputs(){
        boolean ret = true;        
        List<String> errors = new ArrayList();
        if(txtCode.getText().trim().length()==0){
            errors.add( "Masukkan kode menu");            
        }
        if(txtName.getText().trim().length()==0){
            errors.add( "Masukkan nama menu");            
        }
        if(txtPrice.getText().trim().length()==0){
            errors.add( "Masukkan harga menu");            
        }
        if(items.isEmpty()){
            errors.add( "Masukkan komposisi menu");            
        }
        if(!errors.isEmpty()){
            JOptionPane.showMessageDialog(null, String.join("\n", errors));
            return false;
        }
        
        return ret;
    }
    
    private void save(){
        
        if(!validateInputs()){
            return;
        }
        if(entity==null){
            entity = new Menu();
            entity.setCreatedAt(TimeHelper.getCurrentTimestamp());            
            entity.setCreatedBy(appSession.getCurrentuser());
        }else{
            entity.setUpdatedAt(TimeHelper.getCurrentTimestamp());
            entity.setUpdatedBy(appSession.getCurrentuser());
            if(items!=null){
                for(MenuItem detail : items){
                    detail.setUpdatedAt(TimeHelper.getCurrentTimestamp());                    
                }
            }
        }
        entity.setName(txtName.getText().trim());
        entity.setCode(txtCode.getText().trim());
        entity.setUnitPrice(new BigDecimal(txtPrice.getText()));
        entity.setCategory(cbCategory.getSelectedItem().toString());
        if(imgItem!=null && !imgItem.isBlank()){
            entity.setImage(imgItem);
        }
        entity.setMenuItems(items);
        entity.setIsCombination(false);
        var savedEntity = menuRepository.saveAndFlush(entity);
        if(savedEntity!=null){
            entity =  savedEntity;
            JOptionPane.showMessageDialog(null, "Data Tersimpan");             
        }else{
            JOptionPane.showMessageDialog(null, "Data Tidak Tersimpan");
        }
    }
    public void loadItems(Menu menu){
        this.items = menuItemRepository.findAllByMenuId(menu.getId());
        this.insertedItemIDs.clear();
        for(MenuItem si:this.items){
            this.insertedItemIDs.add(si.getItem().getId());
        }
        
    }
    public static void showModal(Menu currentItem) {
        
        AddMenuDlg dialog = new AddMenuDlg();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setTitle("Tambah Menu");
        if(currentItem!=null){
            dialog.setTitle("Edit Menu");
            dialog.setEntity(currentItem);               
            dialog.txtName.setText(currentItem.getName());
            dialog.txtCode.setText(currentItem.getCode());
            dialog.cbCategory.setSelectedItem(currentItem.getCategory());
            dialog.txtPrice.setText(currentItem.getUnitPrice().toString());
            dialog.imgItem = currentItem.getImage();
            dialog.loadItems(currentItem);
            dialog.loadData();
            
        }
        dialog.setVisible(true);
        dialog.toFront();
        dialog.requestFocus();
        //System.exit(0);
    }
    
    private void loadData(){
        DefaultTableModel tableModel = new DefaultTableModel();
        
        tableModel.addColumn("Kode Barang");
        tableModel.addColumn("Nama Barang");
        tableModel.addColumn("Jumlah");
        tableModel.addColumn("Satuan");
        for (MenuItem detail : items) {
            Vector row = new Vector();
            var item = detail.getItem();
            row.add(item.getCode());
            row.add(item.getName());                        
            row.add(detail.getQty());            
            row.add(item.getUnitName());            
            
            tableModel.addRow(row);
            
        }
        
        tbl.setModel(tableModel);
        if(imgItem !=null && !imgItem.isBlank()){
            try{
                String docPath = initialDataService.getDocPath();
                File dest = new File(docPath+File.separator+imgItem);
                System.out.println("img"+docPath+File.separator+imgItem);
                Image image = ImageIO.read(dest);
                if (image != null) {
                    lblImage.setIcon(new ImageIcon(image));
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbCategory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNewItem;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQty;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the entity
     */
    public Menu getEntity() {
        return entity;
    }

    /**
     * @param entity the entity to set
     */
    public void setEntity(Menu entity) {
        this.entity = entity;
    }
}
