/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.gui.salesinvoice;

import com.aldoapp.swingboot.helpers.RupiahHelper;
import com.aprioricafe.entities.SalesInvoiceItem;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author aldo
 */
public class SalesInvoiceItemTableModel extends AbstractTableModel{
    private List<SalesInvoiceItem> items;
    private final String[] columnNames = new String[]{"Kode Menu", "Nama Menu", "Kategori", "Harga", "Qty", "Sub Total"};

    public SalesInvoiceItemTableModel(List<SalesInvoiceItem> items){
        this.items = items;
    }
    
    @Override
    public int getRowCount() {
        return items.size();
    }
     @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        var item = items.get(row);
        var menu = item.getMenu();
        switch(col){
            case 0:
                return menu.getCode();
            case 1:
                return menu.getName();
            case 2:
                return menu.getCategory();
            case 3:
                return RupiahHelper.formatRupiah(item.getUnitPrice());
            case 4:
                return item.getQuantity();
            case 5:
                return RupiahHelper.formatRupiah(item.getNetAmount());
        }
        return "";
    }
    @Override
    public boolean isCellEditable(int row, int column){  
         return false;  
    }
}
