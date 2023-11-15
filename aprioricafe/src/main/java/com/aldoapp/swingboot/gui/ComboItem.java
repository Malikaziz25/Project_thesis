/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldoapp.swingboot.gui;

/**
 *
 * @author aldo
 */
public class ComboItem {
    private String key;
    private String value;

    private Object entity;

    public ComboItem(String key, String value, Object entity) {
        this.key = key;
        this.value = value;
        this.entity = entity;
    }

    public Object getEntity() {
        return this.entity;
    }


    @Override
    public String toString() {
        return key;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
