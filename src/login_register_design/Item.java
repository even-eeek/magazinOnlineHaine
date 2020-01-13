/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_register_design;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Item {
    
    public String id;
    public String itemName;
    public byte[] image;
    public String owner;
    public String price;
    public String dimension;
    public String description;
    public String genre;
    public String category;
    public String type;

    public Item(String id, String itemName, byte[] image, String owner, String price, String dimension, String genre, String category, String type, String transactionID) {
        this.id = id;
        this.itemName = itemName;
        this.image = image;
        this.owner = owner;
        this.price = price;
        this.dimension = dimension;
        this.genre = genre;
        this.category = category;
        this.type = type;
        this.transactionID = transactionID;
    }

    public Item(String id, String itemName, byte[] image, String owner, String price, String dimension, String description, String genre, String category, String type, String transactionID) {
        this.id = id;
        this.itemName = itemName;
        this.image = image;
        this.owner = owner;
        this.price = price;
        this.dimension = dimension;
        this.description = description;
        this.genre = genre;
        this.category = category;
        this.type = type;
        this.transactionID = transactionID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public static String transactionID;

    public Item(String id, String itemName, byte[] image, String owner, String price, String dimension, String genre) {
        this.id = id;
        this.itemName = itemName;
        this.image = image;
        this.owner = owner;
        this.price = price;
        this.dimension = dimension;
        this.genre = genre;
        
    }
    
    public Item(String id, String itemName, byte[] image, String owner){
        this.id = id;
        this.itemName = itemName;
        this.image = image;
        this.owner = owner;
    }
    
    public static List<String> getListOfItemsFromTrasaction(String TransactionID){
         
        if (TransactionID == null)
            return null;
        
        List<String> newList = new ArrayList<String>();
        
        PreparedStatement st;
        ResultSet rs;
        
        String sql = "SELECT * FROM `items` WHERE `transactionID` = ?";
       
        try {
            st = My_CNX.getConnection().prepareStatement(sql);
            st.setString(1, TransactionID);
            
            rs = st.executeQuery();
            while(rs.next())
            {
                newList.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newList;
    }
    
    public static List<String> getAllItemID() {
        List<String> newList = new ArrayList<>();
        
        PreparedStatement st;
        ResultSet rs;
        
        String sql = "SELECT * FROM `items`";
        
        try {
            st = My_CNX.getConnection().prepareStatement(sql);
            
            rs = st.executeQuery();
            while(rs.next())
            {
                newList.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newList;
    }
    
    public static void RemoveItemsFromCurrnetTransaction(){
        List<String> itemIDList = getAllItemID();
        
        if(itemIDList == null)
            return;
        
        for (String itemID: itemIDList) {
            SetTransactionIDDefault(itemID);
        }
    }
    public static void SetTransactionIDDefault(String itemID) {
        if (itemID == null)
            return;
        
        String LastTransactionIDFromDB = Integer.toString(Integer.parseInt(Transaction.getTransactionIDOfLastInsertedTransactionSQL()) + 1);
        
        Item item = Item.getInfoByItemID(itemID);
        if(!item.getTransactionID().equals(LastTransactionIDFromDB)) {
            return;
        }
        
        PreparedStatement st;
        
        String sql="UPDATE `items` SET `transactionID`=0 WHERE `id`=?";
        
        try {
            st = My_CNX.getConnection().prepareStatement(sql);
            st.setString(1, itemID);
            
            int result = st.executeUpdate();
            if(result > 0)
            {
                
            } else{
                // error message
                JOptionPane.showMessageDialog(null, "Error setting transactionID to item","Retrieve UserData from UserID Error",2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void setTransactionID(String itemID, String transactionID) {
        
        if (itemID == null)
            return;
        
        if (transactionID == null)
            return ;
        
        String LastTransactionIDFromDB = Integer.toString(Integer.parseInt(Transaction.getTransactionIDOfLastInsertedTransactionSQL()) + 1);
        
        Item item = Item.getInfoByItemID(itemID);
        if(!item.getTransactionID().equals("0") && !item.getTransactionID().equals(LastTransactionIDFromDB)) {
            return;
        }
       
        PreparedStatement st;
        
        String sql="UPDATE `items` SET `transactionID`=? WHERE `id`=?";
        
        try {
            st = My_CNX.getConnection().prepareStatement(sql);
            st.setString(1, transactionID);
            st.setString(2, itemID);
            
            int result = st.executeUpdate();
            if(result > 0)
            {
                
            } else{
                // error message
                JOptionPane.showMessageDialog(null, "Error setting transactionID to item","Retrieve UserData from UserID Error",2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form2.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public static Item getInfoByTransactionID(String TransactionID) {
        
       if (TransactionID == null)
            return null;
       
        Item newItem = null;
       
        PreparedStatement st;
        ResultSet rs;
        
        String query = "SELECT * FROM `items` WHERE `transactionID` = ?";
        
        try {
            st = My_CNX.getConnection().prepareStatement(query);

            st.setString(1, TransactionID);
            rs = st.executeQuery();

            if(rs.next())
            {
                String ItemID = rs.getString(1);
                String ItemName = rs.getString(11);
                byte[] image = rs.getBytes(3);
                String owner = rs.getString(4);
                String price = rs.getString(5);
                String dimension = rs.getString(6);
                String description = rs.getString(7);
                String genre = rs.getString(8);
                String category = rs.getString(9);
                String type = rs.getString(10);
                
                newItem = new Item(ItemID, ItemName, image, owner, price, dimension, description, genre, type, category, TransactionID);
            }else{
                // error message
                JOptionPane.showMessageDialog(null, "Error retrieving user data from UserID","Retrieve UserData from UserID Error",2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newItem;
    }
    
    public static Item getInfoByItemName(String ItemName) {
        
       if (ItemName == null)
            return null;
       
       Item newItem = null;
       
        PreparedStatement st;
        ResultSet rs;
        
        String query = "SELECT * FROM `items` WHERE `itemname` = ?";
        
        try {
            st = My_CNX.getConnection().prepareStatement(query);

            st.setString(1, ItemName);
            rs = st.executeQuery();

            if(rs.next())
            {
                String ItemID = rs.getString(1);
                byte[] image = rs.getBytes(3);
                String owner = rs.getString(4);
                String price = rs.getString(5);
                String dimension = rs.getString(6);
                String description = rs.getString(7);
                String genre = rs.getString(8);
                String category = rs.getString(9);
                String type = rs.getString(10);
                String transactionID = rs.getString(11);
                
                newItem = new Item(ItemID, ItemName, image, owner, price, dimension, description, genre, type, category, transactionID);
            }else{
                // error message
                JOptionPane.showMessageDialog(null, "Error retrieving user data from UserID","Retrieve UserData from UserID Error",2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newItem;
    }
    
    public static Item getInfoByItemID(String ItemID) {
        
       if (ItemID == null)
            return null;
       
       Item newItem = null;
       
        PreparedStatement st;
        ResultSet rs;
        
        String query = "SELECT * FROM `items` WHERE `id` = ?";
        
        try {
            st = My_CNX.getConnection().prepareStatement(query);

            st.setString(1, ItemID);
            rs = st.executeQuery();

            if(rs.next())
            {
                String ItemName = rs.getString(2);
                byte[] image = rs.getBytes(3);
                String owner = rs.getString(4);
                String price = rs.getString(5);
                String dimension = rs.getString(6);
                String genre = rs.getString(8);
                String category = rs.getString(9);
                String type = rs.getString(10);
                String transactionID = rs.getString(11);
                
                newItem = new Item(ItemID, ItemName, image, owner, price, dimension, genre, type, category, transactionID);
            }else{
                // error message
                JOptionPane.showMessageDialog(null, "Error retrieving user data from UserID","Retrieve UserData from UserID Error",2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newItem;
    }
    
    
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    @Override
    public String toString() {
        return "\n\t\t\t\tItem{" + "id=" + id + ", itemName=" + itemName + ", image=" + image + ", owner=" + owner + ", price=" + price + ", dimension=" + dimension + ", genre=" + genre + '}';
    }
    
}
