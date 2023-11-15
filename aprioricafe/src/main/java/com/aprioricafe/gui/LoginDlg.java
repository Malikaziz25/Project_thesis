/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aprioricafe.gui;

import com.aldoapp.swingboot.AppSession;
import com.aldoapp.swingboot.BeanProvider;
import com.aldoapp.swingboot.entities.User;
import com.aldoapp.swingboot.repositories.SettingRepository;
import com.aldoapp.swingboot.repositories.UserRepository;
import com.aldoapp.swingboot.services.InitialDataService;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author koplog
 */
public class LoginDlg extends javax.swing.JDialog {
    @Autowired
    UserRepository userRespository;
    
    @Autowired
    AppSession appSession;
    
    @Autowired
    SettingRepository settingRepository;
    
    @Autowired
    InitialDataService initialDataService;
    
    /**
     * Creates new form LoginDlg
     */
    public LoginDlg() {
        BeanProvider.autowire(this); //use someRepository somewhere after this line.
        initComponents();
        setModal(true);
        Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                doLogin();
            }
        };

        txtUsername.addActionListener(action);
        txtPassword.addActionListener( action );
        
        try{
            
            //String docPath = initialDataService.getDocPath();
            //File dest = new File(docPath+File.separator+"logo.png");
            File dest = new File("images"+File.separator+"login-logo.png");
            if(dest.exists() && !dest.isDirectory()) { 
                BufferedImage img = null;
                img = ImageIO.read(dest);
                Image dimg = img.getScaledInstance(imgLogo.getWidth(), imgLogo.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(dimg);
                imgLogo.setIcon(imageIcon);
            }
            
            
            File dest2 = new File("images"+File.separator+"login-footer.png");
            if(dest2.exists() && !dest2.isDirectory()) { 
                BufferedImage img2 = null;
                img2 = ImageIO.read(dest2);
                Image dimg2 = img2.getScaledInstance(bgBottom.getWidth(), bgBottom.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon imageIcon2 = new ImageIcon(dimg2);
                bgBottom.setIcon(imageIcon2);
            }
            

        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
            ex.printStackTrace();
        }

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
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        imgLogo = new javax.swing.JLabel();
        bgBottom = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        jLabel1.setText("Username");

        jLabel2.setText("Password");

        txtUsername.setText("admin");

        txtPassword.setText("willamette");

        btnLogin.setText("Masuk");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        imgLogo.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Selamat Datang");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(bgBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(imgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)))
                        .addGap(162, 162, 162))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(imgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bgBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    public static void showModal(String[] args) {
        
        LoginDlg dialog = new LoginDlg();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        //dialog.setResizable(false);
        //dialog.setUndecorated(true);
        dialog.setVisible(true);
        dialog.toFront();
        dialog.requestFocus();
        //System.exit(0);
        
    }
    
    private void doLogin(){
        // TODO add your handling code here:
        if(!validateInputs()){
            return;
        }
        var xuser = userRespository.findByUsername(txtUsername.getText());
        if(xuser.isPresent()){
            String password = new String(txtPassword.getPassword());
            User user = xuser.get();
            boolean s = BCrypt.checkpw(password, user.getPassword());
            if(s){
                appSession.setCurrentuser(user);
                dispose();
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Username / password salah");
    }
    
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        doLogin();

    }//GEN-LAST:event_btnLoginActionPerformed
    private boolean validateInputs(){
        boolean res = true;
        
        if(txtUsername.getText().trim().length()==0){
            JOptionPane.showMessageDialog(null, "Masukkan username");
            res = false;
        }
        String password = new String(txtPassword.getPassword());
        if(password.trim().length()==0){
            JOptionPane.showMessageDialog(null, "Masukkan password");
            res = false;
        }
        return res;
    }
    
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgBottom;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel imgLogo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
