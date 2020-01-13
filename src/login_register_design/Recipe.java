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

/**
 *
 * @author dcoriiu
 */
public class Recipe {
    
    private String recipeID;
    private Transaction transaction;
    private User user;

    public Recipe(Transaction transaction) {
        this.transaction = transaction;
        this.user = User.getInfoByUserID(transaction.getUserID());
    }
    
    
    public Recipe(String RecipeID, Transaction Transaction, String UserID) {
        this.recipeID = RecipeID;
        this.transaction = Transaction;
        this.user = User.getInfoByUserID(UserID);
    }
   
    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String RecipeID) {
        this.recipeID = RecipeID;
    }
    
     public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
    
    public void FinalizeRecipe() {
        
        PreparedStatement ps;
        ResultSet rs;
        String query = "INSERT INTO `recipes` (`transactionID`, `userID`) VALUES (?,?)";
        try {

            ps = My_CNX.getConnection().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Transaction.getInstance(user.getId()).getTransactionID()));
            ps.setInt(2, Integer.parseInt(Transaction.getInstance(user.getId()).getUserID()));

            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Recipe has been finalized!");
            }else{
                JOptionPane.showMessageDialog(null, "Error: Recipe FAIL");
            }

        } catch (SQLException ex) {
            System.out.print("Recipe Finalize Excetion: " + ex);
        }
    }

    public static Recipe getRecipeInfoByID(String RecipeID) {
        
       if (RecipeID == null)
            return null;
       
        Recipe newRecipe = null;
       
        PreparedStatement st;
        ResultSet rs;
        
        String query = "SELECT * FROM `recipes` WHERE `transactionID` = ?";
        
        try {
            st = My_CNX.getConnection().prepareStatement(query);

            st.setString(1, RecipeID);
            rs = st.executeQuery();

            if(rs.next())
            {
                String TransactionID = rs.getString(2);
                String userID = rs.getString(2);
                
                Transaction newTransaction = Transaction.getTransactionInfoByID(TransactionID);
                
                newRecipe = new Recipe(RecipeID, newTransaction, userID);
                
            }else{
                    // error message
//                    JOptionPane.showMessageDialog(null, "Error retrieving user data from UserID","Retrieve UserData from UserID Error",2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newRecipe;
    }
    
    public static List<String> getUserRecipeHistory(String UserID) {
        
       if (UserID == null)
            return null;
       
        List<String> RecipeIDList = new ArrayList<String>();
        
        PreparedStatement st;
        ResultSet rs;
        
        String query = "SELECT * FROM `recipes` WHERE `userID` = ?";
        
        try {
            st = My_CNX.getConnection().prepareStatement(query);

            st.setString(1, UserID);
            rs = st.executeQuery();

            while(rs.next())
            {
                String resultTransactionID =  rs.getString(1);
                RecipeIDList.add(resultTransactionID);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return RecipeIDList;
    }
    
    @Override
    public String toString() {
        return "\n-------RECIPE START-------\n" + "\tRecipeID=" + recipeID + user.toString() + transaction.toString() +  "\n-------RECIPE END-------\n\n"; 
    }
    
}