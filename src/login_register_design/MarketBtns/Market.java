/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_register_design.MarketBtns;

import java.awt.Component;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import login_register_design.DbUtils;
import login_register_design.My_CNX;
import login_register_design.Register_Form;

/**
 *
 * @author Vlade
 */
public class Market {
    
    public void setLabels(JPanel jPanel_MarketLabels, String[] footwearLabels, String[] footwearLabelsImageNames) {
        int countText = 0;
        int countImage = 0;
        
        for (Component jc : jPanel_MarketLabels.getComponents()) {
            if (jc instanceof JLabel) {
                if(((JLabel) jc).getIcon() == null) {
                    ((JLabel) jc).setText(footwearLabels[countText]);
                    countText++;
                } else {
                    String imgPath = System.getProperty("user.dir") + "\\src\\images\\Labels\\" + footwearLabelsImageNames[countImage] + ".jpg";
                    Image image = new ImageIcon(imgPath).getImage().getScaledInstance(((JLabel) jc).getWidth(), ((JLabel) jc).getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon newImage = new ImageIcon(image);
                    ((JLabel) jc).setIcon(newImage);
                    countImage++;
                }
            }
        }
    }
    
    public void searchAfterGenreAndType (JTable jTable_ItemsList, String genre, String type) {
        PreparedStatement st;
        ResultSet rs;

        String query = "SELECT * FROM `items` WHERE `genre` = ? AND `type` = ? ";

        try {
            st = My_CNX.getConnection().prepareStatement(query);
            st.setString(1, genre);
            st.setString(2, type);
            rs = st.executeQuery();
            jTable_ItemsList.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
