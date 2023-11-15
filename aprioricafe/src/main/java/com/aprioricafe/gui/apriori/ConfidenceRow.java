/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.gui.apriori;

import com.aprioricafe.entities.Menu;
import java.util.List;

/**
 *
 * @author aldo
 */
public class ConfidenceRow {

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the supportAntecedent
     */
    public double getSupportAntecedent() {
        return supportAntecedent;
    }

    /**
     * @param supportAntecedent the supportAntecedent to set
     */
    public void setSupportAntecedent(double supportAntecedent) {
        this.supportAntecedent = supportAntecedent;
    }

    /**
     * @return the supportItem
     */
    public double getSupportItem() {
        return supportItem;
    }

    /**
     * @param supportItem the supportItem to set
     */
    public void setSupportItem(double supportItem) {
        this.supportItem = supportItem;
    }

    /**
     * @return the confidence
     */
    public double getConfidence() {
        return confidence;
    }

    /**
     * @param confidence the confidence to set
     */
    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
    private Menu antecedent;
    private List<Menu> consequent;
    private double supportAntecedent;
    private double supportItem;
    private double confidence;
    private String level;

    /**
     * @return the antecedent
     */
    public Menu getAntecedent() {
        return antecedent;
    }

    /**
     * @param antecedent the antecedent to set
     */
    public void setAntecedent(Menu antecedent) {
        this.antecedent = antecedent;
    }

    /**
     * @return the consequent
     */
    public List<Menu> getConsequent() {
        return consequent;
    }

    /**
     * @param consequent the consequent to set
     */
    public void setConsequent(List<Menu> consequent) {
        this.consequent = consequent;
    }
}
