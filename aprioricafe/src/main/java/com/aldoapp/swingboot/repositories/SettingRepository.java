/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldoapp.swingboot.repositories;

import com.aldoapp.swingboot.entities.Role;
import com.aldoapp.swingboot.entities.Setting;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aldo
 */
@Repository
public interface SettingRepository extends JpaRepository<Setting,Long>{
    Optional<Setting> findByKey(String key);
}
