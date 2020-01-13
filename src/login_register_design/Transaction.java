/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_register_design;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author dcoriiu
 */
public class Transaction {
    
    private static Transaction transactionInstance;

    private String transactionID = Integer.toString(Integer.parseInt(getTransactionIDOfLastInsertedTransactionSQL()) + 1);
    private static String userID;
    private static List<String> itemIDList = new ArrayList<String>();
    private static String totalCost = "0";
    private String TimeStamp = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z").format(new Date(System.currentTimeMillis()));

    
    public static List<String> getItemIDList() {
        return itemIDList;
    }

    public static void setItemIDList(List<String> itemIDList) {
        Transaction.itemIDList = itemIDList;
    }
    
    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String TimeStamp) {
        this.TimeStamp = TimeStamp;
    }
    
    public Transaction() {
        transactionID = Integer.toString(Integer.parseInt(getTransactionIDOfLastInsertedTransactionSQL()) + 1);
        this.itemIDList = new ArrayList<String>();
        this.totalCost = "0";
    }
    
    public Transaction(String userID, List<String> itemIDList, String totalCost) {
        transactionID = Integer.toString(Integer.parseInt(getTransactionIDOfLastInsertedTransactionSQL()) + 1);
        this.userID = userID;
        this.itemIDList = itemIDList;
        this.totalCost = totalCost;
    }
    
    public Transaction(String userID) {
        System.out.println("TRANSACTION CONSTRUCTOR. USERID: " + userID);
        transactionID = Integer.toString(Integer.parseInt(getTransactionIDOfLastInsertedTransactionSQL()) + 1);
        this.userID = userID;
    }
    
    public Transaction(String userID, String itemIDList, String totalCost) {
        transactionID = Integer.toString(Integer.parseInt(getTransactionIDOfLastInsertedTransactionSQL()) + 1);
        this.userID = userID;
        this.itemIDList.add(itemIDList);
        this.totalCost = totalCost;
    }
    
    public Transaction(String TransactionID, String userID, List<String> itemIDList, String totalCost, String TimeStamp) {
        this.transactionID = TransactionID;
        this.userID = userID;
        this.itemIDList = itemIDList;
        this.totalCost = totalCost;
        this.TimeStamp = TimeStamp;
    }
    
    public static void setInstanceNull(){
        
        itemIDList.clear();
        totalCost="0";
        transactionInstance = null;
    }
    
    public static Transaction getInstance(String UserID, String itemIDList, String totalCost){
        if (transactionInstance == null){ //if there is no instance available... create new one
            transactionInstance = new Transaction(UserID, itemIDList, totalCost);
        } else {
            transactionInstance.itemIDList.add(itemIDList);
            transactionInstance.addItemPriceToTotalCost(totalCost);
        }

        return transactionInstance;
    }
    
    public static Transaction getInstance(String UserID){
        if (transactionInstance == null){ //if there is no instance available... create new one
            transactionInstance = new Transaction(UserID);
        } 

        return transactionInstance;
    }
    
    public static Transaction getInstance(){
        if (transactionInstance == null){ //if there is no instance available... create new one
            transactionInstance = new Transaction();
        } 

        return transactionInstance;
    }
    
    public static Transaction getTransactionInfoByID(String TransactionID) {
        if (TransactionID == null)
            return null;
        
        Transaction newTransaction = null;
        
        PreparedStatement ps;
        ResultSet rs;
        
        String query = "SELECT * FROM `transactions` WHERE `id` = ?";

        try {

            ps = My_CNX.getConnection().prepareStatement(query);
            ps.setString(1, TransactionID);
            
            rs = ps.executeQuery();
        
            if(rs.next())
            {
                String userID = rs.getString(1);
                String totalCost = rs.getString(2);
                String TimeStamp = rs.getString(3);
                
                List<String> newItemIDList = Item.getListOfItemsFromTrasaction(TransactionID);
                
                return new Transaction(TransactionID, userID, newItemIDList, totalCost, TimeStamp);
            }
            

        } catch (SQLException ex) {
            System.out.print("Transaction Finalize Excetion: " + ex);
        }
        
        return newTransaction;
    }
    
    public void updateItemsTransactionID() {
        for(String strID: getInstance(userID).getItemIDList()) {
            Item.setTransactionID(strID, getInstance(userID).getTransactionID());
        }
    }
    
    public static void removeItemFromTransaction(String itemID) {
        Item item = Item.getInfoByItemID(itemID);

        float totalPrice = Float.parseFloat(getTotalCost());
        float itemPrice = Float.parseFloat(item.getPrice());
        
        setTotalCost(String.valueOf(totalPrice - itemPrice));
        itemIDList.remove(itemID);
        Item.setTransactionID(itemID, "0");
    }
     
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String TransactionID) {
        this.transactionID = TransactionID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<String> getItem() {
        return itemIDList;
    }

    public void setItem(List<String> itemIDList) {
        this.itemIDList = itemIDList;
    }

    public static String getTotalCost() {
        return totalCost;
    }
    
    public static void setTotalCost(String newTotalConst) {
        totalCost = newTotalConst;
    }
    
    
    public void addItemPriceToTotalCost(String val) {
        float fTotalCost = Float.parseFloat(this.totalCost);
        
        fTotalCost += Float.parseFloat(val);
        
        setTotalCost(Float.toString(fTotalCost));
    }

    public void FinalizeTransaction(String argUserID) {
        
        PreparedStatement ps;
        ResultSet rs;
        String query = "INSERT INTO `transactions`(`userID`, `totalCost`, `timeStamp`) VALUES (?,?,?)";

        try {

            ps = My_CNX.getConnection().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(argUserID));
            getInstance(argUserID).setUserID(argUserID);
            ps.setString(2, getInstance(argUserID).getTotalCost());
            ps.setString(3, getInstance(argUserID).getTimeStamp());

            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Transaction has been finalized!");
            }else{
                JOptionPane.showMessageDialog(null, "Error: Transcation FAIL");
            }

        } catch (SQLException ex) {
            System.out.print("Transaction Finalize Excetion: " + ex);
        }
        
        this.setTransactionID(getTransactionIDOfLastInsertedTransactionSQL());
    }
    
    public static String getTransactionIDOfLastInsertedTransactionSQL() {
        
        PreparedStatement ps;
        ResultSet rs;
        
        int int_id = 0;
        String query = "SELECT `id` FROM `transactions` WHERE `userID` = ?";

        try {

            ps = My_CNX.getConnection().prepareStatement(query);
            ps.setString(1, String.valueOf(userID));
            
            rs = ps.executeQuery();
        
            while(rs.next())
            {
                int_id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.print("Transaction Finalize Excetion: " + ex);
        }
        return Integer.toString(int_id);
    }
    
    @Override
    public String toString() {
        return "\n\t\t------TRANSACTION START------\n\n" + "\t\t" + "Transaction ID: " + transactionID + ", itemIDList=" + itemIDList + ", totalCost=" + totalCost + ", TimeStamp=" + TimeStamp + '}' + allItemsToString() + "\n\n\t\t------------TRANSACTION END------\n\n";
    }

    public String allItemsToString() {
        String result = "\n\n\t\t\t------ITEMS START------\n";
//        for (Item newItem : getItemList()) {
        for (String itemID : itemIDList) {
                Item newItem = Item.getInfoByItemID(itemID);
                result += newItem.toString();
                result += "\n\t\t\t\t----\n";
        }
        result += "\n\t\t\t------ITEMS STOP------\n";
        
        return result;
    }
   
    
}
