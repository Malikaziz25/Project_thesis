/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprioricafe.services;

import com.aldoapp.swingboot.AppSession;
import com.aldoapp.swingboot.helpers.TimeHelper;
import com.aprioricafe.entities.Item;
import com.aprioricafe.entities.Menu;
import com.aprioricafe.entities.MenuItem;
import com.aprioricafe.repositories.ItemRepository;
import com.aprioricafe.repositories.MenuRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author aldo
 */
@Service
public class InitialMenuDataService {
    @Autowired
    AppSession appSession;
     
    @Autowired
    ItemRepository itemRepository;
    
    @Autowired
    MenuRepository menuRepository;
    
    @Transactional
    public void initData(){
        addItems();
        addEntreesMenus();
        addBeveragesMenus();
    }
    
    void addItem(String name,int stock, String unitName ){
        Item itemx = new Item();
        itemx.setName(name);
        itemx.setCode(name.replaceAll("\\s+",""));
        itemx.setStock(Long.valueOf(stock));
        itemx.setUnitName(unitName);
        itemx.setCreatedBy(appSession.getCurrentuser());
        itemx.setCreatedAt(TimeHelper.getCurrentTimestamp());
        itemRepository.save(itemx);
    }
    //initial data
    void addItems(){
        addItem("aqua", 61, "pcs");
        addItem("susu", 3200, "ml");
        addItem("bubuk coklat", 850, "gr");
        addItem("hazelnut", 720, "gr");
        addItem("oreo", 660, "gr");
        addItem("caramel", 820, "ml");
        addItem("sosis", 121 , "pcs");
        addItem("bubuk kopi robusta", 700, "gr");
        addItem("kentang", 4200, "gr");
        addItem("chili sachet", 234, "pcs");
        addItem("bubuk cappucino", 1200, "gr");
        addItem("ekspreso", 678, "gr");
        addItem("ice cream", 5200, "gr");
        addItem("fanta", 6200, "ml");
        addItem("sprite", 5800, "ml");
        addItem("indomie goreng", 51, "pcs");
        addItem("indomie rebus", 64, "pcs");
        addItem("bubuk thai tea", 420, "gr");
        addItem("spaghety", 4400, "gr");
        addItem("keju", 200, "gr");
        addItem("bawang putih", 32, "pcs");
        addItem("bawang merah", 55, "pcs");
        addItem("garam", 121, "gr");
        addItem("merica", 221, "gr");
        addItem("bubuk strawberry", 789, "gr");
        addItem("beras", 4400, "gr");
        addItem("telor", 67, "pcs");
        addItem("tea", 61, "pcs");
        addItem("gula", 200, "gr");        
    }
    
    void addMenu(String name, String cat, List<MenuItem> menuItems){
        StringBuilder initials = new StringBuilder();
        for (String s : name.split(" ")) {
          initials.append(s.charAt(0));
        }
        
        Menu menu = new Menu();
        menu.setCategory(cat);
        menu.setCode(initials.toString());
        menu.setCreatedBy(appSession.getCurrentuser());
        menu.setCreatedAt(TimeHelper.getCurrentTimestamp());
        menu.setUnitPrice(new BigDecimal(10000));
        menu.setMenuItems(menuItems);
        menu.setName(name);
        
        menuRepository.save(menu);
    }
    Item getItemByName(String name){
        var itx = itemRepository.findByName(name.trim());
        if(itx.isPresent()){
            return itx.get();
        }else{
            JOptionPane.showMessageDialog(null, "Kode Barang tidak ditemukan: "+name); 
        }
        return null;
    }
    MenuItem createMenuItem(String name, int qty){
        MenuItem mi = new MenuItem();
        mi.setItem(getItemByName(name));
        mi.setQty(Long.valueOf(qty));
        mi.setCreatedBy(appSession.getCurrentuser());
        mi.setCreatedAt(TimeHelper.getCurrentTimestamp());
        return mi;
    }
    void addEntreesMenus(){
        String cat1 = "Entrees";
        
        List<MenuItem> items1 = new ArrayList<>();        
        items1.add(createMenuItem("sosis", 5));        
        addMenu("sosis goreng", cat1, items1);
                
        List<MenuItem> items2 = new ArrayList<>();        
        items2.add(createMenuItem("kentang", 120));        
        items2.add(createMenuItem("chili sachet", 2));        
        addMenu("kentang goreng", cat1, items2);
                
        List<MenuItem> items3 = new ArrayList<>();        
        items3.add(createMenuItem("indomie goreng", 1));        
        items3.add(createMenuItem("telor", 1));        
        addMenu("indomie telur goreng", cat1, items3);
        
        List<MenuItem> items4 = new ArrayList<>();        
        items4.add(createMenuItem("indomie rebus", 1));        
        items4.add(createMenuItem("telor", 1));        
        addMenu("indomie telur rebus", cat1, items4);
                
        List<MenuItem> items5 = new ArrayList<>();        
        items5.add(createMenuItem("spaghety", 250));        
        items5.add(createMenuItem("keju", 15));        
        items5.add(createMenuItem("bawang putih", 5));        
        items5.add(createMenuItem("garam", 2));        
        items5.add(createMenuItem("merica", 2));        
        addMenu("aglio - olio -green-mussel-spaghety", cat1, items5);
        
        List<MenuItem> items6 = new ArrayList<>();        
        items6.add(createMenuItem("beras", 250));        
        items6.add(createMenuItem("bawang putih", 1));        
        items6.add(createMenuItem("telor", 1));        
        items6.add(createMenuItem("merica", 1));        
        items6.add(createMenuItem("garam", 2));        
        items6.add(createMenuItem("bawang merah", 2));        
        addMenu("Nasi goreng kampung", cat1, items6);
        
        List<MenuItem> items7 = new ArrayList<>();        
        items7.add(createMenuItem("beras", 250));        
        items7.add(createMenuItem("bawang putih", 1));        
        items7.add(createMenuItem("telor", 1));        
        items7.add(createMenuItem("merica", 7));        
        items7.add(createMenuItem("garam", 8));        
        items7.add(createMenuItem("bawang merah", 2));        
        items7.add(createMenuItem("sosis", 3));        
        addMenu("Nasi goreng jambal", cat1, items7);
        
    }
    void addBeveragesMenus(){
        String cat1 = "Beverages";
        
        List<MenuItem> items1 = new ArrayList<>();        
        items1.add(createMenuItem("aqua", 1));        
        addMenu("Air Mineral", cat1, items1);
        
        List<MenuItem> items2 = new ArrayList<>();        
        items2.add(createMenuItem("susu", 100)); 
        items2.add(createMenuItem("bubuk coklat", 50)); 
        items2.add(createMenuItem("hazelnut", 20));          
        addMenu("Milk shake choco hazelnut", cat1, items2);
        
        List<MenuItem> items3 = new ArrayList<>();        
        items3.add(createMenuItem("susu", 100)); 
        items3.add(createMenuItem("bubuk coklat", 50)); 
        items3.add(createMenuItem("oreo", 20));          
        addMenu("Milkshake choco oreo", cat1, items3);
        
        List<MenuItem> items4 = new ArrayList<>();        
        items4.add(createMenuItem("susu", 100)); 
        items4.add(createMenuItem("bubuk coklat", 50)); 
        items4.add(createMenuItem("caramel", 20));          
        addMenu("Milkshake choco caramel", cat1, items4);
        
        List<MenuItem> items5 = new ArrayList<>();        
        items5.add(createMenuItem("susu", 100)); 
        items5.add(createMenuItem("bubuk kopi robusta", 50));                
        addMenu("Vietnam dripp milk", cat1, items5);
        
        List<MenuItem> items6 = new ArrayList<>();        
        items6.add(createMenuItem("susu", 50)); 
        items6.add(createMenuItem("bubuk cappucino", 50));              
        addMenu("cappucino", cat1, items6);
        
        List<MenuItem> items7 = new ArrayList<>();        
        items7.add(createMenuItem("ekspreso", 50)); 
        items7.add(createMenuItem("ice cream", 25));          
        addMenu("affogato", cat1, items7);
        
        List<MenuItem> items8 = new ArrayList<>();        
        items8.add(createMenuItem("fanta ", 200)); 
        items8.add(createMenuItem("ice cream", 25));         
        addMenu("fanta float", cat1, items8);
        
        List<MenuItem> items9 = new ArrayList<>();        
        items9.add(createMenuItem("susu ", 50)); 
        items9.add(createMenuItem("bubuk thai tea", 25));         
        addMenu("ice shake thai tea latte", cat1, items9);
        
        List<MenuItem> items10 = new ArrayList<>();        
        items10.add(createMenuItem("susu", 100)); 
        items10.add(createMenuItem("bubuk strawberry", 50));              
        addMenu("Milk shake strawbery", cat1, items10);
        
        List<MenuItem> items11 = new ArrayList<>();        
        items11.add(createMenuItem("tea", 1)); 
        items11.add(createMenuItem("gula", 1));              
        addMenu("teh manis", cat1, items11);
        
        List<MenuItem> items12 = new ArrayList<>();        
        items12.add(createMenuItem("sprite", 200)); 
        items12.add(createMenuItem("ice cream", 25));              
        addMenu("sprite float", cat1, items12);
        
    }
}
