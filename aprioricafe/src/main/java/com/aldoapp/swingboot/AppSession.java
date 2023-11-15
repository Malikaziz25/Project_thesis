/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldoapp.swingboot;

import com.aldoapp.swingboot.entities.Role;
import com.aldoapp.swingboot.entities.RoleAccess;
import com.aldoapp.swingboot.entities.User;
import com.aldoapp.swingboot.repositories.RoleAccessRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author koplog
 */
public class AppSession {
    
    @Autowired
    RoleAccessRepository roleAccessRepository;
    
    private User currentuser;
    private boolean admin;
    private List<RoleAccess> existingRoles = new ArrayList<RoleAccess>();
    /**
     * @return the currentuser
     */
    public User getCurrentuser() {
        return currentuser;
    }

    /**
     * @param currentuser the currentuser to set
     */
    public void setCurrentuser(User currentuser) {
        this.currentuser = currentuser;
        admin  = false;
        for (Role s : currentuser.getRoles()) {
            if(s.getId().equals(Role.ADMIN)){
                admin  = true;
            }
            var roleAcccess = roleAccessRepository.findAllByRoleId(s.getId());
            if(roleAcccess!= null && !roleAcccess.isEmpty()){
                existingRoles.addAll(roleAcccess);
            }
        }        
    }
    
    public boolean isAdmin(){
        return admin;
    }
    
    public boolean hasAccess(String menu){
        if(admin){
            return true;
        }
        for(RoleAccess ra: existingRoles){
            if(ra.getMenu().equals(menu) && ra.isHasAccess()){
                return true;
            }
        }
        return false;
    }
    
}
