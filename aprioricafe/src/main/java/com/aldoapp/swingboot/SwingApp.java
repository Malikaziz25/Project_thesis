/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldoapp.swingboot;
//TODO: change to your mainframe
import com.aprioricafe.gui.MainFrame;
import com.formdev.flatlaf.FlatLightLaf;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan({"com.aldoapp.swingboot.entities","com.aprioricafe.entities"})
@EnableJpaRepositories({"com.aldoapp.swingboot.repositories","com.aprioricafe.repositories"})
@ComponentScan({"com.aldoapp.swingboot","com.aldoapp.swingboot.services","com.aprioricafe.services"})
public class SwingApp implements CommandLineRunner {
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean
    public AppSession getAppSession(){
        return new AppSession();
    }

    public static void main(String[] args) {
        try{
            new SpringApplicationBuilder(SwingApp.class)
                .headless(false).bannerMode(Banner.Mode.OFF).run(args);
        }catch (Exception ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void run(String... args) throws Exception {
        UIManager.setLookAndFeel( new FlatLightLaf() );
        SwingUtilities.invokeLater(() -> {
            //set theme
            try {
                
                //show main frame
                MainFrame frame = new MainFrame();
                //frame.setContentPane(new MainFrame().makeUI());
                //frame.setContentPane(frame.rootPanel);
                frame.setSize(900,600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);

                frame.setVisible(true);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }catch (Exception ex) {
                java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            


        });
    }
}
