/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aprioricafe.gui;


import com.aldoapp.swingboot.AppSession;
import com.aldoapp.swingboot.BeanProvider;

import com.aldoapp.swingboot.entities.User;
import com.aldoapp.swingboot.gui.CloseableTabbedPaneLayerUI;
import com.aldoapp.swingboot.gui.ImagePanel;
import com.aldoapp.swingboot.gui.setting.CompanyDlg;
import com.aldoapp.swingboot.gui.setting.SettingsDlg;
import com.aldoapp.swingboot.gui.user.UserManagement;

import com.aldoapp.swingboot.services.InitialDataService;
import com.aprioricafe.gui.apriori.PreprocessDataDlg;
import com.aprioricafe.gui.item.AddItemQtyDlg;
import com.aprioricafe.gui.item.ItemAdjustmentDlg;
import com.aprioricafe.gui.item.ItemList;
import com.aprioricafe.gui.menu.AddCombinationMenuDlg;
import com.aprioricafe.gui.menu.AddMenuDlg;
import com.aprioricafe.gui.menu.MenuList;
import com.aprioricafe.gui.report.SalesReportDlg;
import com.aprioricafe.gui.salesinvoice.SalesInvoiceDlg;
import com.aprioricafe.gui.salesinvoice.SalesInvoiceList;
import com.aprioricafe.gui.staff.InputStaffDlg;
import com.aprioricafe.gui.staff.StaffList;
import com.aprioricafe.services.InitialMenuDataService;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.io.File;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author aldo
 */

public class MainFrame extends javax.swing.JFrame {
    @Autowired
    AppSession appSession;
    
    @Autowired
    InitialDataService initialDataSvc;
    
    @Autowired
    InitialMenuDataService initialMenuDataService;
    
    
    private User user;
    private boolean isAdmin = false;
    private JTabbedPane tabbedPane;
    private JLabel lblUser;
    private HashMap<String,JPanel> openTabs = new HashMap<>();
    

    /**
     * Creates new form MainFrame
     */
    public JComponent makeUI() {
        UIManager.put("TabbedPane.tabInsets", new Insets(2, 2, 2, 50));
        //JPanel p = new JPanel(new BorderLayout());
        //ImagePanel panel = new ImagePanel(new ImageIcon("images"+File.separator+"background.png").getImage());
        DashboardPanel p = new DashboardPanel();
        tabbedPane = new JTabbedPane();
        tabbedPane.addContainerListener(new ContainerListener() {
            @Override
            public void componentAdded(ContainerEvent e) {
                // Do nothing when a component is added to the tabbed pane
                //p.remove(panel);
                p.hideImage();
            }

            @Override
            public void componentRemoved(ContainerEvent e) {
                // Get the removed component (tab) from the event
                Component removedComponent = e.getChild();
                System.out.println("Tab removed: " + removedComponent);
                openTabs.values().remove((JPanel)removedComponent);
                // Do your processing here with the removed tab.
                if(openTabs.isEmpty()){
                    //p.add(panel);
                    p.showImage();
                }
            }
        });
        

        
        //p.add(panel);
        p.add(new JLayer<JTabbedPane>(tabbedPane, new CloseableTabbedPaneLayerUI()));
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        p.add(statusPanel, BorderLayout.SOUTH);
        
        lblUser = new JLabel("status");
        lblUser.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(lblUser);
        
        //return p;
        return p;
      }
    void addTab(String title, JPanel panel){
        if(openTabs.containsKey(title)){
            var pnl = openTabs.get(title);
            tabbedPane.setSelectedComponent(pnl);
        }else{
            tabbedPane.addTab( title, panel);    
            tabbedPane.setSelectedComponent(panel);
            
            openTabs.put(title, panel);
        }
        
        
    }
    /**
     * Creates new form MainFrame
     */
    public MainFrame()  {
        BeanProvider.autowire(this); //use someRepository somewhere after this line.
        initComponents();
        setContentPane(makeUI());
        
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu6 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        rootPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        mnFile = new javax.swing.JMenu();
        mnCompanySetting = new javax.swing.JMenuItem();
        mnInitialData = new javax.swing.JMenuItem();
        mnUsers = new javax.swing.JMenu();
        mnAddStaff = new javax.swing.JMenuItem();
        mnStaffList = new javax.swing.JMenuItem();
        mnUser = new javax.swing.JMenuItem();
        mnTransaction = new javax.swing.JMenu();
        mnSalesInvoice = new javax.swing.JMenuItem();
        mnListTransaction = new javax.swing.JMenuItem();
        mnInventory = new javax.swing.JMenu();
        mnAddItem = new javax.swing.JMenuItem();
        mnItems = new javax.swing.JMenuItem();
        mnStockAdjustment = new javax.swing.JMenuItem();
        mnMenus = new javax.swing.JMenu();
        mnAddMenu = new javax.swing.JMenuItem();
        mnAddCombinationMenu = new javax.swing.JMenuItem();
        mnListMenu = new javax.swing.JMenuItem();
        mnAnalize = new javax.swing.JMenu();
        mnAnalizeAssociation = new javax.swing.JMenuItem();
        mnReport = new javax.swing.JMenu();
        mnSalesReport = new javax.swing.JMenuItem();

        jMenu6.setText("jMenu6");

        jMenuItem10.setText("jMenuItem10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Cafe");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        rootPanel.setName("rootPanel"); // NOI18N

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 834, Short.MAX_VALUE)
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );

        mnFile.setText("File");

        mnCompanySetting.setText("Company");
        mnCompanySetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCompanySettingActionPerformed(evt);
            }
        });
        mnFile.add(mnCompanySetting);

        mnInitialData.setText("Install Initial Data");
        mnInitialData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnInitialDataActionPerformed(evt);
            }
        });
        mnFile.add(mnInitialData);

        menuBar.add(mnFile);

        mnUsers.setText("User");

        mnAddStaff.setText("Input Karyawan");
        mnAddStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAddStaffActionPerformed(evt);
            }
        });
        mnUsers.add(mnAddStaff);

        mnStaffList.setText("Data Karyawan");
        mnStaffList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnStaffListActionPerformed(evt);
            }
        });
        mnUsers.add(mnStaffList);

        mnUser.setText("User");
        mnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnUserActionPerformed(evt);
            }
        });
        mnUsers.add(mnUser);

        menuBar.add(mnUsers);

        mnTransaction.setText("Transaksi");

        mnSalesInvoice.setText("Transaksi Penjualan");
        mnSalesInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSalesInvoiceActionPerformed(evt);
            }
        });
        mnTransaction.add(mnSalesInvoice);

        mnListTransaction.setText("Daftar Transaksi");
        mnListTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnListTransactionActionPerformed(evt);
            }
        });
        mnTransaction.add(mnListTransaction);

        menuBar.add(mnTransaction);

        mnInventory.setText("Inventory");

        mnAddItem.setText("Pengadaan Barang");
        mnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAddItemActionPerformed(evt);
            }
        });
        mnInventory.add(mnAddItem);

        mnItems.setText("Data Barang");
        mnItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItemsActionPerformed(evt);
            }
        });
        mnInventory.add(mnItems);

        mnStockAdjustment.setText("Adjustment");
        mnStockAdjustment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnStockAdjustmentActionPerformed(evt);
            }
        });
        mnInventory.add(mnStockAdjustment);

        menuBar.add(mnInventory);

        mnMenus.setText("Menu");

        mnAddMenu.setText("Tambah Menu");
        mnAddMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAddMenuActionPerformed(evt);
            }
        });
        mnMenus.add(mnAddMenu);

        mnAddCombinationMenu.setText("Tambah Menu Kombinasi");
        mnAddCombinationMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAddCombinationMenuActionPerformed(evt);
            }
        });
        mnMenus.add(mnAddCombinationMenu);

        mnListMenu.setText("Daftar Menu");
        mnListMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnListMenuActionPerformed(evt);
            }
        });
        mnMenus.add(mnListMenu);

        menuBar.add(mnMenus);

        mnAnalize.setText("Analisis");

        mnAnalizeAssociation.setText("Analisis Asosiasi Apriori");
        mnAnalizeAssociation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAnalizeAssociationActionPerformed(evt);
            }
        });
        mnAnalize.add(mnAnalizeAssociation);

        menuBar.add(mnAnalize);

        mnReport.setText("Laporan");

        mnSalesReport.setText("Laporan Transaksi Penjualan");
        mnSalesReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSalesReportActionPerformed(evt);
            }
        });
        mnReport.add(mnSalesReport);

        menuBar.add(mnReport);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(rootPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rootPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try{
            initialDataSvc.checkFolders();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        if(initialDataSvc.isFirstRun()){
            SettingsDlg.showModal(null);
        }else{
            LoginDlg.showModal(null);
            user = appSession.getCurrentuser();
            if(user==null){
                dispose();
            }else{
               isAdmin = appSession.isAdmin();
               lblUser.setText("User: "+user.getFullName());
               if(!isAdmin){
                   onlyShowTransactionsMenus();
               }
                       
            }
        }
    }//GEN-LAST:event_formWindowOpened
    void onlyShowTransactionsMenus(){
        menuBar.remove(mnFile);
        menuBar.remove(mnUsers);
        menuBar.remove(mnInventory);
        menuBar.remove(mnMenus);
        menuBar.remove(mnAnalize);
        menuBar.remove(mnReport);
        
        menuBar.revalidate();
        menuBar.repaint();
        
    }
    private void mnCompanySettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCompanySettingActionPerformed
        // TODO add your handling code here:       
        CompanyDlg.showModal(null);
    }//GEN-LAST:event_mnCompanySettingActionPerformed

    private void mnSalesInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSalesInvoiceActionPerformed
        SalesInvoiceDlg.showModal(null);
        
    }//GEN-LAST:event_mnSalesInvoiceActionPerformed

    private void mnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAddItemActionPerformed
        
        AddItemQtyDlg.showModal(null);
        
    }//GEN-LAST:event_mnAddItemActionPerformed

    private void mnAddStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAddStaffActionPerformed
       
        InputStaffDlg.showModal(null);
    }//GEN-LAST:event_mnAddStaffActionPerformed

    private void mnStaffListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnStaffListActionPerformed
        
        addTab("Daftar Karyawan", new StaffList());
        
    }//GEN-LAST:event_mnStaffListActionPerformed

    private void mnItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItemsActionPerformed
        
        addTab("Data Barang", new ItemList());
        
    }//GEN-LAST:event_mnItemsActionPerformed

    private void mnAddMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAddMenuActionPerformed
        
        AddMenuDlg.showModal(null);
        
    }//GEN-LAST:event_mnAddMenuActionPerformed

    private void mnListMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnListMenuActionPerformed
        addTab("Data Menu", new MenuList());
        
    }//GEN-LAST:event_mnListMenuActionPerformed

    private void mnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnUserActionPerformed
        
        UserManagement.showModal(null);
        
    }//GEN-LAST:event_mnUserActionPerformed

    private void mnListTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnListTransactionActionPerformed
        addTab("Data Transaksi Penjualan", new SalesInvoiceList());
        
    }//GEN-LAST:event_mnListTransactionActionPerformed

    private void mnStockAdjustmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnStockAdjustmentActionPerformed
    
        ItemAdjustmentDlg.showModal(null);
        
    }//GEN-LAST:event_mnStockAdjustmentActionPerformed

    private void mnAnalizeAssociationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAnalizeAssociationActionPerformed
        
        PreprocessDataDlg.showModal(null);
        
    }//GEN-LAST:event_mnAnalizeAssociationActionPerformed

    private void mnAddCombinationMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAddCombinationMenuActionPerformed
        AddCombinationMenuDlg.showModal(null);
        
    }//GEN-LAST:event_mnAddCombinationMenuActionPerformed

    private void mnInitialDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnInitialDataActionPerformed
        
        initialMenuDataService.initData();
        JOptionPane.showMessageDialog(null, "Data Terinstall!");
    }//GEN-LAST:event_mnInitialDataActionPerformed

    private void mnSalesReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSalesReportActionPerformed
        SalesReportDlg.showModal(null);
    }//GEN-LAST:event_mnSalesReportActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mnAddCombinationMenu;
    private javax.swing.JMenuItem mnAddItem;
    private javax.swing.JMenuItem mnAddMenu;
    private javax.swing.JMenuItem mnAddStaff;
    private javax.swing.JMenu mnAnalize;
    private javax.swing.JMenuItem mnAnalizeAssociation;
    private javax.swing.JMenuItem mnCompanySetting;
    private javax.swing.JMenu mnFile;
    private javax.swing.JMenuItem mnInitialData;
    private javax.swing.JMenu mnInventory;
    private javax.swing.JMenuItem mnItems;
    private javax.swing.JMenuItem mnListMenu;
    private javax.swing.JMenuItem mnListTransaction;
    private javax.swing.JMenu mnMenus;
    private javax.swing.JMenu mnReport;
    private javax.swing.JMenuItem mnSalesInvoice;
    private javax.swing.JMenuItem mnSalesReport;
    private javax.swing.JMenuItem mnStaffList;
    private javax.swing.JMenuItem mnStockAdjustment;
    private javax.swing.JMenu mnTransaction;
    private javax.swing.JMenuItem mnUser;
    private javax.swing.JMenu mnUsers;
    public javax.swing.JPanel rootPanel;
    // End of variables declaration//GEN-END:variables
}
