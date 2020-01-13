/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_register_design.MarketBtns;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Vlade
 */
public class KidsBtn {
    
    private final String[] imagePath = {"KidsMarket", "KidsMarket1", "KidsMarket2", "KidsMarket3"};
    private final String[] footwearLabels = {"Footwear Kids", "Kids Trainers", "Kids School Shoes", "Kids Football Boots", "Kids Boys Footwear", "Kids Girls Footwear", "Kids Baby Shoes"};
    private final String[] footwearLabelsImageNames = {"KidsTrainders", "KidsSchoolShoes", "KidsFootballBoots", "KidsBoysFootwear", "KidsGirlsFootwear", "KidsBabyShoes"};
    private final String[] clothingLabels = {"Clothing Kids", "Kids Tracksuits", "Kids T-Shirts", "Kids Character Clothing", "Kids Jackets & Coats", "Kids Baselayers", "Kids Hoodies"};
    private final String[] clothingLabelsImageNames = {"KidsT-Shirts", "KidsTracksuits", "KidsCharacterClothing", "KidsJackets&Coats", "KidsBaselayers", "KidsHoodies"};
    
    public KidsBtn() {
    }

    public void setIcon(JLabel jLabel_Market1, JLabel jLabel_Market2) {
        int index = Math.abs(new Random().nextInt()) % 3;
        
        Image image = new ImageIcon(System.getProperty("user.dir") + "\\src\\images\\" + imagePath[index] + ".png").getImage().getScaledInstance(jLabel_Market1.getWidth(), jLabel_Market1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImage = new ImageIcon(image);
        jLabel_Market1.setIcon(newImage);
        
        image = new ImageIcon(System.getProperty("user.dir") + "\\src\\images\\" + imagePath[index + 1] + ".png").getImage().getScaledInstance(jLabel_Market2.getWidth(), jLabel_Market2.getHeight(), Image.SCALE_SMOOTH);
        newImage = new ImageIcon(image);
        jLabel_Market2.setIcon(newImage);
    }
    
    public void setFootwearLabels(JPanel jPanel_MarketLabels) {
        
        new Market().setLabels(jPanel_MarketLabels, footwearLabels, footwearLabelsImageNames);
    }
    
    public void setClothingLabels(JPanel jPanel_MarketLabels) {
        
        new Market().setLabels(jPanel_MarketLabels, clothingLabels, clothingLabelsImageNames);
    }
}
