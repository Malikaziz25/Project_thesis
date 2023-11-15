/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.gui.apriori;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aldo
 */
public class CombinationGenerator<T> {
    public List<List<T>> generateCombinations(List<T> items, int k) {
        List<List<T>> result = new ArrayList<>();
        generateCombinationsHelper(items, k, new ArrayList<>(), result, 0);
        return result;
    }

    private void generateCombinationsHelper(List<T> items, int k, List<T> currentCombo, List<List<T>> result, int startIndex) {
        if (currentCombo.size() == k) {
            result.add(new ArrayList<>(currentCombo));
            return;
        }

        for (int i = startIndex; i < items.size(); i++) {
            currentCombo.add(items.get(i));
            generateCombinationsHelper(items, k, currentCombo, result, i + 1);
            currentCombo.remove(currentCombo.size() - 1);
        }
    }
    /*
    public static void main(String[] args) {
        List<Integer> items = new ArrayList<>();
        items.add(1);
        items.add(2);
        items.add(3);
        items.add(4);
        items.add(5);

        CombinationGenerator<Integer> generator = new CombinationGenerator<>();
        List<List<Integer>> combinations = generator.generateCombinations(items);

        for (List<Integer> combo : combinations) {
            System.out.println(combo);
        }
    }
    */
}
