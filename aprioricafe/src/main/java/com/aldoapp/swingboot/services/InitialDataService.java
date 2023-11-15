/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldoapp.swingboot.services;

import com.aldoapp.swingboot.entities.Role;
import com.aldoapp.swingboot.repositories.RoleRepository;
import com.aldoapp.swingboot.repositories.UserRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author koplog
 */
@Service
public class InitialDataService {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    
    @Transactional
    public void setup(){
        //insert roles
        Role admin = new Role();
        admin.setId(Role.ADMIN);
        admin.setName("ADMIN");
        roleRepository.save(admin);
        
        Role operator = new Role();
        operator.setId(Role.OPERATOR);
        operator.setName("OPERATOR");
        roleRepository.save(operator);
        
       
    }
    
    public boolean isFirstRun(){
        var xrole = roleRepository.findById(Long.valueOf(1));
        if(xrole.isPresent()){
            return false;
        }
        setup();
        return true;
    }
    
    public String getDocPath(){
        String docPath = System.getProperty("user.home") + File.separator + "swingboot";
        
        return docPath;
    }
    
    public void checkFolders() throws IOException{
        //create year and month folder
        String docPath = getDocPath();
        LocalDate locDate = LocalDate.now();
        Files.createDirectories(Paths.get(docPath));
        String docYear = docPath + File.separator + locDate.getYear();
        Files.createDirectories(Paths.get(docYear));
        String docMonth = docPath + File.separator + locDate.getYear()+File.separator+locDate.getMonth();
        Files.createDirectories(Paths.get(docMonth));
    }
    
    public String getPDFPath(){
        String docPath = getDocPath();
        LocalDate locDate = LocalDate.now();
        String docMonth = docPath + File.separator + locDate.getYear()+File.separator+locDate.getMonth();
        return docMonth;
    }
}
