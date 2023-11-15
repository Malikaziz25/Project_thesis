/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldoapp.swingboot.repositories;


import com.aldoapp.swingboot.entities.RoleAccess;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author koplog
 */
@Repository
public interface RoleAccessRepository extends JpaRepository<RoleAccess, Long>{
    
    
    @Query("SELECT c FROM RoleAccess c "          
            + "where role.id = :roleId")
    List<RoleAccess> findAllByRoleId(Long roleId);
}
