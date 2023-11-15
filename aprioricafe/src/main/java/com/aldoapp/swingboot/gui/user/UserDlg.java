/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aldoapp.swingboot.gui.user;

import com.aldoapp.swingboot.BeanProvider;
import com.aldoapp.swingboot.entities.Role;
import com.aldoapp.swingboot.entities.User;
import com.aldoapp.swingboot.gui.ComboItem;
import com.aldoapp.swingboot.helpers.TimeHelper;
import com.aldoapp.swingboot.repositories.RoleRepository;
import com.aldoapp.swingboot.repositories.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author aldo
 */
public class UserDlg extends javax.swing.JDialog {

    /**
     * @return the entity
     */
    public User getEntity() {
        return entity;
    }

    /**
     * @param entity the entity to set
     */
    public void setEntity(User entity) {
        this.entity = entity;
    }
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    private long selectedID;
    
    private User entity;
    
    /**
     * Creates new form UserDialog
     */
    public UserDlg() {
        BeanProvider.autowire(this); //use someRepository somewhere after this line.
        initComponents();
        setModal(true);
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
        txtPassword = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbRole = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtFullname = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("User Management");

        jLabel1.setText("Username");

        txtPassword.setName("txtPassword"); // NOI18N

        jLabel2.setText("Password");

        txtUsername.setName("txtUsername"); // NOI18N

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel3.setText("Role");

        jLabel5.setText("Nama Lengkap");

        txtFullname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFullnameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(95, 95, 95)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                    .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPassword)
                                    .addComponent(btnSave)
                                    .addComponent(txtFullname))))
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleName("Username (tanpa spasi)");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if(!validateInputs()){
            return;
        }
        //https://stackoverflow.com/a/50515537/1225672
        try{
            String hash = BCrypt.hashpw(txtPassword.getText(),BCrypt.gensalt());
            if(entity==null){
                entity = new User();
                entity.setCreatedAt(TimeHelper.getCurrentTimestamp());
            }else {
                entity.setId(selectedID);
                entity.setUpdatedAt(TimeHelper.getCurrentTimestamp());
            }
            entity.setFullName(txtFullname.getText());
            entity.setUsername(txtUsername.getText());
            entity.setPassword(hash);
            HashSet<Role> roles = new HashSet<Role>();
            if(cbRole.getSelectedIndex()==0){
                var xrole = roleRepository.findById(Role.ADMIN);
                roles.add(xrole.get());
            }else if(cbRole.getSelectedIndex()==1){
                var xrole = roleRepository.findById(Role.OPERATOR);
                roles.add(xrole.get());
            }

            entity.setRoles(roles);
            
            User saved = userRepository.save(entity);
            if(saved!=null){            
                JOptionPane.showMessageDialog(null, "Data Tersimpan");   
                clearFields();
                dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Data Tidak Tersimpan");
            }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Data Tidak Tersimpan. "+ex.getMessage());
        }
        
    }//GEN-LAST:event_btnSaveActionPerformed
    
    private void loadData(){
        Iterable<Role> entites = roleRepository.findAll();
        cbRole.removeAllItems();
        for (Role entity : entites) {
            cbRole.addItem(new ComboItem(entity.getName(), entity.getName(), entity));
        }
    }
    
    private void txtFullnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFullnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFullnameActionPerformed
    private boolean validateInputs(){
        boolean res = true;        
        
        List<String> errors = new ArrayList();
        if(txtUsername.getText().trim().length()==0){
            errors.add("Masukkan username");            
        }
        if(txtFullname.getText().trim().length()==0){
            errors.add( "Masukkan nama lengkap");            
        }
        if(txtPassword.getText().trim().length()==0){
            errors.add( "Masukkan password");            
        }
        if(!errors.isEmpty()){
            JOptionPane.showMessageDialog(null, String.join("\n", errors));
            return false;
        }
        return res;
    }
    private void clearFields() {
        
        txtUsername.setText("");
        txtFullname.setText("");
        txtPassword.setText("");

    }
    
    public static void showModal(User currentUser) {
        UserDlg dialog = new UserDlg();
        dialog.pack();
        dialog.setLocationRelativeTo(null);    
        if(currentUser!=null){
            dialog.setEntity(currentUser);
            dialog.txtFullname.setText(currentUser.getFullName());
            dialog.txtUsername.setText(currentUser.getUsername());
            dialog.selectedID = currentUser.getId();
            
            var srole = currentUser.getRoles();
            for (Iterator<Role> it = srole.iterator(); it.hasNext(); ) {
                Role role = it.next();
                dialog.cbRole.getModel().setSelectedItem(new ComboItem(role.getName(), role.getName(), role));
            }
            
        }
        dialog.setVisible(true);
        
        //System.exit(0);
    }
    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cbRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtFullname;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}