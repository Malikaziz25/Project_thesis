/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldoapp.swingboot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author koplog
 */
@Component
public class BeanProvider {
    private static ApplicationContext applicationContext;

    /**
     * Autowires the specified object in the spring context
     * 
     * @param object
     */
    public static void autowire(Object object) {
        applicationContext.getAutowireCapableBeanFactory().autowireBean(object);
    }

    @Autowired
    private void setApplicationContext(ApplicationContext applicationContext) {
        BeanProvider.applicationContext = applicationContext;
    }
}
