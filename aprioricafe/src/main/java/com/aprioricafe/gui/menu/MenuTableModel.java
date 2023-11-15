/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.gui.menu;

import com.aldoapp.swingboot.helpers.RupiahHelper;
import com.aldoapp.swingboot.services.InitialDataService;
import com.aprioricafe.entities.Menu;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author aldo
 */
public class MenuTableModel extends AbstractTableModel{
    
    String docPath ;
    private List<Menu> items;
    private final String[] columnNames = new String[]{"ID", "Kode Menu", "Nama", "Kategori", "Harga","Gambar" };
    
    public MenuTableModel(String docPath, List<Menu> items){
        this.items = items;
        this.docPath = docPath;
    }
    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public Class getColumnClass(int column)
    {
        return getValueAt(0, column).getClass();
    }
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    @Override
    public Object getValueAt(int row, int col) {
        var item = items.get(row);
        
        switch(col){
            case 0:
                return item.getId();
            case 1:
                return item.getCode();
            case 2:
                return item.getName();
            case 3:
                return item.getCategory();
            case 4:
                return RupiahHelper.formatRupiah(item.getUnitPrice());
            case 5:{
                try{
                    
                    File dest = new File(docPath+File.separator+item.getImage());
                    if(dest.exists() && !dest.isDirectory()) { 
                        Image image = ImageIO.read(dest);
                        ImageIcon img = new ImageIcon(image);
                        return img;
                    }else{
                        return "";
                    }
                    
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    ex.printStackTrace();
                }
            }
            
        }
        return "";
    }
    
    
    
}
