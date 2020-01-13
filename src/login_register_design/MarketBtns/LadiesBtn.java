/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_register_design.MarketBtns;

import java.awt.Component;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Vlade
 */
public class LadiesBtn {
    
    private final String[] imagePath = {"LadiesMarket", "LadiesMarket1", "LadiesMarket2", "LadiesMarket3"};
    private final String[] footwearLabels = {"Footwear Ladies", "Ladies Walking Boots & Shoes", "Ladies Boots", "Ladies Trainers", "Ladies Sandals", "Ladies Shoes", "Ladies Running Shoes"};
    private final String[] footwearLabelsImageNames = {"LadiesWalkingBoots&Shoes", "LadiesSandals", "LadiesShoes", "LadiesRunningShoes", "LadiesBoots", "LadiesTrainers"};
    private final String[] clothingLabels = {"Clothing Ladies", "Ladies Gym Wear", "Ladies T-Shirts", "Ladies Swimwear", "Ladies Jackets & Coats", "Ladies Sports Bras", "Ladies Hoodies"};
    private final String[] clothingLabelsImageNames = {"LadiesGymWear", "LadiesJackets&Coats", "LadiesSportsBras", "LadieHoodies", "LadiesT-Shirts", "LadieSwimwear"};
    
    public LadiesBtn() {
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
