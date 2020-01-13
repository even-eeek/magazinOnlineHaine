/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_register_design;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dcoriiu
 */
public class User {

    
    private String id;
    private String full_name;
    private String username;
    private String password;
    private String phone;
    private String gender;
    private Object picture;
    private List<Integer> TransactionHistory = new ArrayList<Integer>();

    public User(String id, String full_name, String username, String password, String phone, String gender, Object picture) {
        this.id = id;
        this.full_name = full_name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.picture = picture;
    }
    
     public User(String id, String full_name, String username, String phone, String gender) {
        this.id = id;
        this.full_name = full_name;
        this.username = username;
        this.password = null;
        this.phone = phone;
        this.gender = gender;
        this.picture = null;
    }
    
    public List<Integer> getTransactionHistory() {
        return TransactionHistory;
    }

    public void setTransactionHistory(List<Integer> TransactionHistory) {
        this.TransactionHistory = TransactionHistory;
    }
    
    public void addTransactionID(int TransactionID) {
        this.TransactionHistory.add(TransactionID);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Object getPicture() {
        return picture;
    }

    public void setPicture(Object picture) {
        this.picture = picture;
    }
    
    public static User getInfoByUserID(String UserID) {
        
       if (UserID == null)
            return null;
       
       User newUser = null;
       
        PreparedStatement st;
        ResultSet rs;
        
        String query = "SELECT * FROM `users` WHERE `id` = ?";
        
        try {
            st = My_CNX.getConnection().prepareStatement(query);

            st.setString(1, UserID);
            rs = st.executeQuery();

            if(rs.next())
            {
                String full_name = rs.getString(2);
                String username = rs.getString(3);
                String phone = rs.getString(4);
                String gender = rs.getString(5);
                
                newUser = new User(UserID, full_name, username, phone, gender);
            }else{
                // error message
                JOptionPane.showMessageDialog(null, "Error retrieving user data from UserID","Retrieve UserData from UserID Error",2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newUser;
    }
    
    @Override
    public String toString() {
        return "\n\n\t------USER START------\n\n" + "\tUser{" + "id=" + id + ", full_name=" + full_name + ", username=" + username + ", phone=" + phone + ", gender=" + gender + '}' + "\n\n\t------USER END------\n";
    }
    
}
