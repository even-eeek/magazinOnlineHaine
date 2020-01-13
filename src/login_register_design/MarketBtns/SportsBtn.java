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
public class SportsBtn {
    
    private final String[] imagePath = {"SportMarket", "SportMarket1", "SportMarket2", "SportMarket3"};
    private final String[] runningLabels = {"Running", "Mens Running Shoes", "Ladies Running Shoes", "Kids Running Shoes", "Mens Running Clothing", "Ladies Running Clothing", "Road Running"};
    private final String[] runningLabelsImageNames = {"MensRunningShoes", "LadiesRunningShoes", "KidsRunningShoes", "MensRunningClothing", "LadiesRunningClothing", "RoadRunning"};
    private final String[] outdoorLabels = {"Outdoor", "Mens Outdoor Clothing", "Ladies Outdoor Clothing", "Kids Outdoor Clothing", "Mens Outdoor Footwear", "Ladies Outdoor Footwear", "Kids Outdoor Footwear"};
    private final String[] outdoorLabelsImageNames = {"MensOutdoorClothing", "LadiesOutdoorClothing", "KidsOutdoorClothing", "MensOutdoorFootwear", "LadiesOutdoorFootwear", "KidsOutdoorFootwear"};
    private final String[] fitnessAndTrainingLabels = {"Fitness And Training", "Mens Gym Clothes", "Ladies Gym Clothes", "Weights & Dumbells", "Mens Gym Trainers", "Ladies Gym Trainers", "Gym Mats"};
    private final String[] fitnessAndTrainingLabelsImageNames = {"MensGymClothes", "LadiesGymClothes", "Weights&Dumbells", "MensGymTrainers", "LadiesGymTrainers", "GymMats"};
    private final String[] skiingLabels = {"Skiing", "Mens Ski Wear", "Ladies Ski Wear", "Kids Ski Wear", "Ski Boots", "Ski Equipment", "Snow Boots"};
    private final String[] skiingLabelsImageNames = {"MensSkiWear", "LadiesSkiWear", "KidsSkiWear", "SkiBoots", "SkiEquipment", "SnowBoots"};
    
    public SportsBtn() {
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
    
    public void setRunningLabels(JPanel jPanel_MarketLabels) {
        
        new Market().setLabels(jPanel_MarketLabels, runningLabels, runningLabelsImageNames);
    }
    
    public void setOutdoorLabels(JPanel jPanel_MarketLabels) {
        
        new Market().setLabels(jPanel_MarketLabels, outdoorLabels, outdoorLabelsImageNames);
    }
    
    public void setFitnessAndTrainingLabels(JPanel jPanel_MarketLabels) {
        
        new Market().setLabels(jPanel_MarketLabels, fitnessAndTrainingLabels, fitnessAndTrainingLabelsImageNames);
    }
    
    public void setSkiingLabels(JPanel jPanel_MarketLabels) {
        
        new Market().setLabels(jPanel_MarketLabels, skiingLabels, skiingLabelsImageNames);
    }
}
