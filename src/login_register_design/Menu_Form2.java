/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_register_design;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import login_register_design.MarketBtns.KidsBtn;
import login_register_design.MarketBtns.LadiesBtn;
import login_register_design.MarketBtns.Market;
import login_register_design.MarketBtns.MenBtn;
import login_register_design.MarketBtns.ProductInformation;
import login_register_design.MarketBtns.SportsBtn;
import login_register_design.MarketBtns.Cart;

/**
 *
 * @author Vlade
 */
public class Menu_Form2 extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     * @param username
     * @param img
     */
    
    private static String userID = null;
    private String username = null;
    private String new_item_image_path = null;
    
    enum MarketState { 
        MEN, LADIES, KIDS, SPORTS; 
    }
    private MarketState runningMarketType;
        
    private boolean runningStateHome = true;
    private boolean runningStateProfile = false;
    private boolean runningStateMarket = false;
    private boolean runningStateSettings = true;
    
    private static final MyClock myClock = new MyClock();
    
    ProductInformation pi = new ProductInformation();
    
    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String newUserID) {
        userID = newUserID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public boolean getRunningStateHome() {
        return runningStateHome;
    }
 
    public boolean getRunningStateProfile() {
        return runningStateProfile;
    }
    
    public boolean getRunningStateMarket() {
        return runningStateMarket;
    }
 
    public boolean getRunningStateSettings() {
        return runningStateSettings;
    }
    
    public void setRunningStateHome(boolean newValue) {
        runningStateHome = newValue;
        if (runningStateHome){
            setRunningStateProfile(false);
            setRunningStateMarket(false);
            setRunningStateSettings(false);
        }
        
        setVisibleCanvas();
    }
 
    public void setRunningStateProfile(boolean newValue) {
        runningStateProfile = newValue;
        if (runningStateProfile){
            setRunningStateHome(false);
            setRunningStateMarket(false);
            setRunningStateSettings(false);
        }
        
        setVisibleCanvas();
    }
    
    public void setRunningStateMarket(boolean newValue) {
        runningStateMarket = newValue;
        if (runningStateMarket){
            setRunningStateHome(false);
            setRunningStateProfile(false);
            setRunningStateSettings(false);
        }
        
        setVisibleCanvas();
    }
 
    public void setRunningStateSettings(boolean newValue) {
        runningStateSettings = newValue;
        if (runningStateSettings){
            setRunningStateHome(false);
            setRunningStateProfile(false);
            setRunningStateMarket(false);
        }
        
        setVisibleCanvas();
    }
    
    public void setVisibleCanvas(){     
        
        jPanel3.setVisible(runningStateProfile);
        jPanel6.setVisible(runningStateMarket);
        jPanel8.setVisible(runningStateHome);
        jPanel_AddItem.setVisible(false);
        
        if (runningStateSettings){
            return;
        }
    }
    
    public void changeLabel(MarketState marketType) {
  
        if(myClock.getElapsedTimeSecs() % 3000 == 0 && marketType == MarketState.MEN)
            new MenBtn().setIcon(jLabel_PageMarket1, jLabel_PageMarket2);
        else if(myClock.getElapsedTimeSecs() % 5000 == 0 && marketType == MarketState.LADIES)
            new LadiesBtn().setIcon(jLabel_PageMarket1, jLabel_PageMarket2);
        else if(myClock.getElapsedTimeSecs() % 5000 == 0 && marketType == MarketState.KIDS)
            new KidsBtn().setIcon(jLabel_PageMarket1, jLabel_PageMarket2);
        else if(myClock.getElapsedTimeSecs() % 5000 == 0 && marketType == MarketState.SPORTS)
            new SportsBtn().setIcon(jLabel_PageMarket1, jLabel_PageMarket2);
    }
    
    public String returnUserIdFromUsername() {
        
        if (this.username == null)
            return null;
        
        PreparedStatement st;
        ResultSet rs;
        
        String id = null;
        String query = "SELECT `id` FROM `users` WHERE `username` = ?";
        
        
        try {
            st = My_CNX.getConnection().prepareStatement(query);

            st.setString(1, username);
            rs = st.executeQuery();

            if(rs.next())
            {
                id = Integer.toString(rs.getInt(1));
            }else{
                // error message
                JOptionPane.showMessageDialog(null, "Error retrieving UserID","Retrieve UserID Error",2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
       
    public Menu_Form2(String username, byte[] img) {
        initComponents();
        
        this.username = username;
        setUserID(returnUserIdFromUsername());
        
        jLabel_UserName.setText(username);
        
        Image avatar;
        if (img == null){
            String curDir = System.getProperty("user.dir");
            avatar = new ImageIcon(curDir + "\\src\\images\\User-blue-icon.png").getImage().getScaledInstance(jLabel_Avatar.getWidth(), jLabel_Avatar.getHeight(), Image.SCALE_SMOOTH);
        } else {
            avatar = new ImageIcon(img).getImage().getScaledInstance(jLabel_Avatar.getWidth(), jLabel_Avatar.getHeight(), Image.SCALE_SMOOTH);
        }
        ImageIcon newImage = new ImageIcon(avatar);
        jLabel_Avatar.setIcon(newImage);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPopupMenuMen = new javax.swing.JPopupMenu();
        FootwearMen = new javax.swing.JMenuItem();
        ClothingMen = new javax.swing.JMenuItem();
        Top_Categories5 = new javax.swing.JMenu();
        jPopupMenuLadies = new javax.swing.JPopupMenu();
        FootwearLadies = new javax.swing.JMenuItem();
        ClothingLadies = new javax.swing.JMenuItem();
        Top_Categories = new javax.swing.JMenu();
        jPopupMenuKids = new javax.swing.JPopupMenu();
        FootwearKids = new javax.swing.JMenuItem();
        ClothingKids = new javax.swing.JMenuItem();
        Top_Categories1 = new javax.swing.JMenu();
        jPopupMenuSports = new javax.swing.JPopupMenu();
        Running = new javax.swing.JMenuItem();
        Outdoor = new javax.swing.JMenuItem();
        FitnessAndTraining = new javax.swing.JMenuItem();
        Skiing = new javax.swing.JMenuItem();
        Top_Categories2 = new javax.swing.JMenu();
        jPopupMenuOutdoor = new javax.swing.JPopupMenu();
        Top_Categories3 = new javax.swing.JMenu();
        jPopupMenuBrands = new javax.swing.JPopupMenu();
        Top_Categories4 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel_Home_Btn = new javax.swing.JPanel();
        jPanel_Indicator_Home = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel_Profile_Btn = new javax.swing.JPanel();
        jPanel_Indicator_Profile = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel_Market_Men = new javax.swing.JPanel();
        jPanel_Indicator_Men = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel_Settings_Btn = new javax.swing.JPanel();
        jPanel_Indicator_Settings = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel_Market_Ladies = new javax.swing.JPanel();
        jPanel_Indicator_Ladies = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel_Market_Kids = new javax.swing.JPanel();
        jPanel_Indicator_Kids = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel_Market_Sports = new javax.swing.JPanel();
        jPanel_Indicator_Sports = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel_Market_Other = new javax.swing.JPanel();
        jPanel_Indicator_Outdoor = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel_Market_Brands = new javax.swing.JPanel();
        jPanel_Indicator_Brands = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel_Market_Sale = new javax.swing.JPanel();
        jPanel_Indicator_Market7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabelCart = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel_AddItemBtn = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel_Logout = new javax.swing.JLabel();
        jLabel_Avatar = new javax.swing.JLabel();
        jLabel_UserName = new javax.swing.JLabel();
        jPanel_AddItem = new javax.swing.JPanel();
        jLabel_ItemNameText = new javax.swing.JLabel();
        jLabel_ItemPriceText = new javax.swing.JLabel();
        jLabel_ItemSizeText = new javax.swing.JLabel();
        jLabel_ItemDescriptionText = new javax.swing.JLabel();
        jLabel_ItemGenreText = new javax.swing.JLabel();
        jLabel_ItemTypeText = new javax.swing.JLabel();
        jLabel_ItemImageText = new javax.swing.JLabel();
        jButton_SelectItemImage = new javax.swing.JButton();
        jLabel_imgItempath = new javax.swing.JLabel();
        jTextField_ItemName = new javax.swing.JTextField();
        jTextField_ItemPrice = new javax.swing.JTextField();
        jTextField_ItemSize = new javax.swing.JTextField();
        jTextField_ItemDescription = new javax.swing.JTextField();
        jComboBox_ItemGenre = new javax.swing.JComboBox<>();
        jComboBox_ItemType = new javax.swing.JComboBox<>();
        jButton_AddItem = new javax.swing.JButton();
        jLabel_ItemGenreText1 = new javax.swing.JLabel();
        jComboBox_ItemCategory = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_ItemsList = new javax.swing.JTable();
        jLabel_PageMarket1 = new javax.swing.JLabel();
        jPanel_MarketLabels = new javax.swing.JPanel();
        jLabel_TitleMarket4 = new javax.swing.JLabel();
        jLabel_TitleMarket2 = new javax.swing.JLabel();
        jLabel_TitleMarket5 = new javax.swing.JLabel();
        jLabel_TitleMarket6 = new javax.swing.JLabel();
        jLabel_Market1 = new javax.swing.JLabel();
        jLabel_Market2 = new javax.swing.JLabel();
        jLabel_Market3 = new javax.swing.JLabel();
        jLabel_Market4 = new javax.swing.JLabel();
        jLabel_Market5 = new javax.swing.JLabel();
        jLabel_Market6 = new javax.swing.JLabel();
        jLabel_TitleMarket1 = new javax.swing.JLabel();
        jLabel_TitleMarket3 = new javax.swing.JLabel();
        jLabel_TitleMarket7 = new javax.swing.JLabel();
        jLabel_Image = new javax.swing.JLabel();
        jLabel_PageMarket2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel_Image1 = new javax.swing.JLabel();

        FootwearMen.setText("Footwear");
        FootwearMen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FootwearMenActionPerformed(evt);
            }
        });
        jPopupMenuMen.add(FootwearMen);

        ClothingMen.setText("Clothing");
        ClothingMen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClothingMenActionPerformed(evt);
            }
        });
        jPopupMenuMen.add(ClothingMen);

        Top_Categories5.setText("Top Categories");
        jPopupMenuMen.add(Top_Categories5);

        FootwearLadies.setText("Footwear");
        FootwearLadies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FootwearLadiesActionPerformed(evt);
            }
        });
        jPopupMenuLadies.add(FootwearLadies);

        ClothingLadies.setText("Clothing");
        ClothingLadies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClothingLadiesActionPerformed(evt);
            }
        });
        jPopupMenuLadies.add(ClothingLadies);

        Top_Categories.setText("Top Categories");
        jPopupMenuLadies.add(Top_Categories);

        FootwearKids.setText("Footwear");
        FootwearKids.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FootwearKidsActionPerformed(evt);
            }
        });
        jPopupMenuKids.add(FootwearKids);

        ClothingKids.setText("Clothing");
        ClothingKids.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClothingKidsActionPerformed(evt);
            }
        });
        jPopupMenuKids.add(ClothingKids);

        Top_Categories1.setText("Top Categories");
        jPopupMenuKids.add(Top_Categories1);

        Running.setText("Running");
        Running.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunningActionPerformed(evt);
            }
        });
        jPopupMenuSports.add(Running);

        Outdoor.setText("Outdoor");
        Outdoor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OutdoorActionPerformed(evt);
            }
        });
        jPopupMenuSports.add(Outdoor);

        FitnessAndTraining.setText("Fitness & Training");
        FitnessAndTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FitnessAndTrainingActionPerformed(evt);
            }
        });
        jPopupMenuSports.add(FitnessAndTraining);

        Skiing.setText("Skiing");
        Skiing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkiingActionPerformed(evt);
            }
        });
        jPopupMenuSports.add(Skiing);

        Top_Categories2.setText("Top Categories");
        jPopupMenuSports.add(Top_Categories2);

        Top_Categories3.setText("Top Categories");
        jPopupMenuOutdoor.add(Top_Categories3);

        Top_Categories4.setText("Top Categories");
        jPopupMenuBrands.add(Top_Categories4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(23, 35, 51));

        jPanel_Home_Btn.setBackground(new java.awt.Color(41, 57, 80));
        jPanel_Home_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_Home_BtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Indicator_HomeLayout = new javax.swing.GroupLayout(jPanel_Indicator_Home);
        jPanel_Indicator_Home.setLayout(jPanel_Indicator_HomeLayout);
        jPanel_Indicator_HomeLayout.setHorizontalGroup(
            jPanel_Indicator_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel_Indicator_HomeLayout.setVerticalGroup(
            jPanel_Indicator_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Home");

        javax.swing.GroupLayout jPanel_Home_BtnLayout = new javax.swing.GroupLayout(jPanel_Home_Btn);
        jPanel_Home_Btn.setLayout(jPanel_Home_BtnLayout);
        jPanel_Home_BtnLayout.setHorizontalGroup(
            jPanel_Home_BtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Home_BtnLayout.createSequentialGroup()
                .addComponent(jPanel_Indicator_Home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Home_BtnLayout.setVerticalGroup(
            jPanel_Home_BtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Indicator_Home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_Home_BtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Profile_Btn.setBackground(new java.awt.Color(23, 35, 51));
        jPanel_Profile_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_Profile_BtnMousePressed(evt);
            }
        });

        jPanel_Indicator_Profile.setOpaque(false);

        javax.swing.GroupLayout jPanel_Indicator_ProfileLayout = new javax.swing.GroupLayout(jPanel_Indicator_Profile);
        jPanel_Indicator_Profile.setLayout(jPanel_Indicator_ProfileLayout);
        jPanel_Indicator_ProfileLayout.setHorizontalGroup(
            jPanel_Indicator_ProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel_Indicator_ProfileLayout.setVerticalGroup(
            jPanel_Indicator_ProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Profile");

        javax.swing.GroupLayout jPanel_Profile_BtnLayout = new javax.swing.GroupLayout(jPanel_Profile_Btn);
        jPanel_Profile_Btn.setLayout(jPanel_Profile_BtnLayout);
        jPanel_Profile_BtnLayout.setHorizontalGroup(
            jPanel_Profile_BtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Profile_BtnLayout.createSequentialGroup()
                .addComponent(jPanel_Indicator_Profile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel9)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel_Profile_BtnLayout.setVerticalGroup(
            jPanel_Profile_BtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Indicator_Profile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_Profile_BtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Market_Men.setBackground(new java.awt.Color(23, 35, 51));
        jPanel_Market_Men.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_Market_MenMousePressed(evt);
            }
        });

        jPanel_Indicator_Men.setOpaque(false);

        javax.swing.GroupLayout jPanel_Indicator_MenLayout = new javax.swing.GroupLayout(jPanel_Indicator_Men);
        jPanel_Indicator_Men.setLayout(jPanel_Indicator_MenLayout);
        jPanel_Indicator_MenLayout.setHorizontalGroup(
            jPanel_Indicator_MenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel_Indicator_MenLayout.setVerticalGroup(
            jPanel_Indicator_MenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Men");

        javax.swing.GroupLayout jPanel_Market_MenLayout = new javax.swing.GroupLayout(jPanel_Market_Men);
        jPanel_Market_Men.setLayout(jPanel_Market_MenLayout);
        jPanel_Market_MenLayout.setHorizontalGroup(
            jPanel_Market_MenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Market_MenLayout.createSequentialGroup()
                .addComponent(jPanel_Indicator_Men, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel10)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel_Market_MenLayout.setVerticalGroup(
            jPanel_Market_MenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Indicator_Men, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_Market_MenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Settings_Btn.setBackground(new java.awt.Color(23, 35, 51));
        jPanel_Settings_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_Settings_BtnMousePressed(evt);
            }
        });

        jPanel_Indicator_Settings.setOpaque(false);

        javax.swing.GroupLayout jPanel_Indicator_SettingsLayout = new javax.swing.GroupLayout(jPanel_Indicator_Settings);
        jPanel_Indicator_Settings.setLayout(jPanel_Indicator_SettingsLayout);
        jPanel_Indicator_SettingsLayout.setHorizontalGroup(
            jPanel_Indicator_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel_Indicator_SettingsLayout.setVerticalGroup(
            jPanel_Indicator_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Settings");

        javax.swing.GroupLayout jPanel_Settings_BtnLayout = new javax.swing.GroupLayout(jPanel_Settings_Btn);
        jPanel_Settings_Btn.setLayout(jPanel_Settings_BtnLayout);
        jPanel_Settings_BtnLayout.setHorizontalGroup(
            jPanel_Settings_BtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Settings_BtnLayout.createSequentialGroup()
                .addComponent(jPanel_Indicator_Settings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel11)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel_Settings_BtnLayout.setVerticalGroup(
            jPanel_Settings_BtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Indicator_Settings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_Settings_BtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Market_Ladies.setBackground(new java.awt.Color(23, 35, 51));
        jPanel_Market_Ladies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_Market_LadiesMousePressed(evt);
            }
        });

        jPanel_Indicator_Ladies.setOpaque(false);

        javax.swing.GroupLayout jPanel_Indicator_LadiesLayout = new javax.swing.GroupLayout(jPanel_Indicator_Ladies);
        jPanel_Indicator_Ladies.setLayout(jPanel_Indicator_LadiesLayout);
        jPanel_Indicator_LadiesLayout.setHorizontalGroup(
            jPanel_Indicator_LadiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel_Indicator_LadiesLayout.setVerticalGroup(
            jPanel_Indicator_LadiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Ladies");

        javax.swing.GroupLayout jPanel_Market_LadiesLayout = new javax.swing.GroupLayout(jPanel_Market_Ladies);
        jPanel_Market_Ladies.setLayout(jPanel_Market_LadiesLayout);
        jPanel_Market_LadiesLayout.setHorizontalGroup(
            jPanel_Market_LadiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Market_LadiesLayout.createSequentialGroup()
                .addComponent(jPanel_Indicator_Ladies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel12)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel_Market_LadiesLayout.setVerticalGroup(
            jPanel_Market_LadiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Indicator_Ladies, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_Market_LadiesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Market_Kids.setBackground(new java.awt.Color(23, 35, 51));
        jPanel_Market_Kids.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_Market_KidsMousePressed(evt);
            }
        });

        jPanel_Indicator_Kids.setOpaque(false);

        javax.swing.GroupLayout jPanel_Indicator_KidsLayout = new javax.swing.GroupLayout(jPanel_Indicator_Kids);
        jPanel_Indicator_Kids.setLayout(jPanel_Indicator_KidsLayout);
        jPanel_Indicator_KidsLayout.setHorizontalGroup(
            jPanel_Indicator_KidsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel_Indicator_KidsLayout.setVerticalGroup(
            jPanel_Indicator_KidsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Kids");

        javax.swing.GroupLayout jPanel_Market_KidsLayout = new javax.swing.GroupLayout(jPanel_Market_Kids);
        jPanel_Market_Kids.setLayout(jPanel_Market_KidsLayout);
        jPanel_Market_KidsLayout.setHorizontalGroup(
            jPanel_Market_KidsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Market_KidsLayout.createSequentialGroup()
                .addComponent(jPanel_Indicator_Kids, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel13)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel_Market_KidsLayout.setVerticalGroup(
            jPanel_Market_KidsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Indicator_Kids, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_Market_KidsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Market_Sports.setBackground(new java.awt.Color(23, 35, 51));
        jPanel_Market_Sports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_Market_SportsMousePressed(evt);
            }
        });

        jPanel_Indicator_Sports.setOpaque(false);

        javax.swing.GroupLayout jPanel_Indicator_SportsLayout = new javax.swing.GroupLayout(jPanel_Indicator_Sports);
        jPanel_Indicator_Sports.setLayout(jPanel_Indicator_SportsLayout);
        jPanel_Indicator_SportsLayout.setHorizontalGroup(
            jPanel_Indicator_SportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel_Indicator_SportsLayout.setVerticalGroup(
            jPanel_Indicator_SportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Sports");

        javax.swing.GroupLayout jPanel_Market_SportsLayout = new javax.swing.GroupLayout(jPanel_Market_Sports);
        jPanel_Market_Sports.setLayout(jPanel_Market_SportsLayout);
        jPanel_Market_SportsLayout.setHorizontalGroup(
            jPanel_Market_SportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Market_SportsLayout.createSequentialGroup()
                .addComponent(jPanel_Indicator_Sports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel14)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel_Market_SportsLayout.setVerticalGroup(
            jPanel_Market_SportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Indicator_Sports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_Market_SportsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Market_Other.setBackground(new java.awt.Color(23, 35, 51));
        jPanel_Market_Other.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_Market_OtherMousePressed(evt);
            }
        });

        jPanel_Indicator_Outdoor.setOpaque(false);

        javax.swing.GroupLayout jPanel_Indicator_OutdoorLayout = new javax.swing.GroupLayout(jPanel_Indicator_Outdoor);
        jPanel_Indicator_Outdoor.setLayout(jPanel_Indicator_OutdoorLayout);
        jPanel_Indicator_OutdoorLayout.setHorizontalGroup(
            jPanel_Indicator_OutdoorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel_Indicator_OutdoorLayout.setVerticalGroup(
            jPanel_Indicator_OutdoorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Other");

        javax.swing.GroupLayout jPanel_Market_OtherLayout = new javax.swing.GroupLayout(jPanel_Market_Other);
        jPanel_Market_Other.setLayout(jPanel_Market_OtherLayout);
        jPanel_Market_OtherLayout.setHorizontalGroup(
            jPanel_Market_OtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Market_OtherLayout.createSequentialGroup()
                .addComponent(jPanel_Indicator_Outdoor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel15)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel_Market_OtherLayout.setVerticalGroup(
            jPanel_Market_OtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Indicator_Outdoor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_Market_OtherLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Market_Brands.setBackground(new java.awt.Color(23, 35, 51));
        jPanel_Market_Brands.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_Market_BrandsMousePressed(evt);
            }
        });

        jPanel_Indicator_Brands.setOpaque(false);

        javax.swing.GroupLayout jPanel_Indicator_BrandsLayout = new javax.swing.GroupLayout(jPanel_Indicator_Brands);
        jPanel_Indicator_Brands.setLayout(jPanel_Indicator_BrandsLayout);
        jPanel_Indicator_BrandsLayout.setHorizontalGroup(
            jPanel_Indicator_BrandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel_Indicator_BrandsLayout.setVerticalGroup(
            jPanel_Indicator_BrandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Brands");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Market_BrandsLayout = new javax.swing.GroupLayout(jPanel_Market_Brands);
        jPanel_Market_Brands.setLayout(jPanel_Market_BrandsLayout);
        jPanel_Market_BrandsLayout.setHorizontalGroup(
            jPanel_Market_BrandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Market_BrandsLayout.createSequentialGroup()
                .addComponent(jPanel_Indicator_Brands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel16)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel_Market_BrandsLayout.setVerticalGroup(
            jPanel_Market_BrandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Indicator_Brands, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_Market_BrandsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Market_Sale.setBackground(new java.awt.Color(204, 0, 0));
        jPanel_Market_Sale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_Market_SaleMousePressed(evt);
            }
        });

        jPanel_Indicator_Market7.setOpaque(false);

        javax.swing.GroupLayout jPanel_Indicator_Market7Layout = new javax.swing.GroupLayout(jPanel_Indicator_Market7);
        jPanel_Indicator_Market7.setLayout(jPanel_Indicator_Market7Layout);
        jPanel_Indicator_Market7Layout.setHorizontalGroup(
            jPanel_Indicator_Market7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel_Indicator_Market7Layout.setVerticalGroup(
            jPanel_Indicator_Market7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("SALE");

        javax.swing.GroupLayout jPanel_Market_SaleLayout = new javax.swing.GroupLayout(jPanel_Market_Sale);
        jPanel_Market_Sale.setLayout(jPanel_Market_SaleLayout);
        jPanel_Market_SaleLayout.setHorizontalGroup(
            jPanel_Market_SaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Market_SaleLayout.createSequentialGroup()
                .addComponent(jPanel_Indicator_Market7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel17)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel_Market_SaleLayout.setVerticalGroup(
            jPanel_Market_SaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Indicator_Market7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_Market_SaleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Home_Btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_Profile_Btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_Market_Men, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_Settings_Btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_Market_Ladies, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_Market_Kids, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_Market_Sports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_Market_Other, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_Market_Brands, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_Market_Sale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel_Home_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_Profile_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Market_Men, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Market_Ladies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Market_Kids, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Market_Sports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Market_Other, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Market_Brands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Market_Sale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel_Settings_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(72, 120, 197));

        jTextField1.setBackground(new java.awt.Color(123, 156, 225));
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(198, 198, 198)));
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextField1.setPreferredSize(new java.awt.Dimension(2, 20));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search24.png"))); // NOI18N

        jLabelCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_shopping_cart_24px.png"))); // NOI18N
        jLabelCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCartMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCart)
                .addGap(5, 5, 5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(71, 120, 197));

        jPanel4.setBackground(new java.awt.Color(120, 168, 252));

        jPanel5.setBackground(new java.awt.Color(84, 127, 206));

        jLabel_AddItemBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AddItem.png"))); // NOI18N
        jLabel_AddItemBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_AddItemBtnMouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shop48.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shop48.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shop48.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel_AddItemBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel3)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel4)))
                .addContainerGap(1400, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_AddItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        jLabel_Logout.setBackground(new java.awt.Color(120, 160, 252));
        jLabel_Logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout32.png"))); // NOI18N
        jLabel_Logout.setOpaque(true);
        jLabel_Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_LogoutMousePressed(evt);
            }
        });

        jLabel_Avatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shop48.png"))); // NOI18N

        jLabel_UserName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel_UserName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_UserName.setText("admin");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_Logout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel_Logout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel_ItemNameText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_ItemNameText.setText("Item Name:");

        jLabel_ItemPriceText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_ItemPriceText.setText("        Price:");

        jLabel_ItemSizeText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_ItemSizeText.setText("         Size:");

        jLabel_ItemDescriptionText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_ItemDescriptionText.setText("Description:");

        jLabel_ItemGenreText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_ItemGenreText.setText("       Genre:");

        jLabel_ItemTypeText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_ItemTypeText.setText("        Type:");

        jLabel_ItemImageText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_ItemImageText.setText("      Image:");

        jButton_SelectItemImage.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_SelectItemImage.setText("select image");
        jButton_SelectItemImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SelectItemImageActionPerformed(evt);
            }
        });

        jLabel_imgItempath.setFont(new java.awt.Font("Sylfaen", 0, 11)); // NOI18N
        jLabel_imgItempath.setText("image path");

        jTextField_ItemName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField_ItemPrice.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField_ItemSize.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField_ItemDescription.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jComboBox_ItemGenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_ItemGenreActionPerformed(evt);
            }
        });

        jButton_AddItem.setBackground(new java.awt.Color(235, 47, 6));
        jButton_AddItem.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 24)); // NOI18N
        jButton_AddItem.setForeground(new java.awt.Color(255, 255, 255));
        jButton_AddItem.setText("Add Item");
        jButton_AddItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_AddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddItemActionPerformed(evt);
            }
        });

        jLabel_ItemGenreText1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_ItemGenreText1.setText("  Category:");

        jComboBox_ItemCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_ItemCategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_AddItemLayout = new javax.swing.GroupLayout(jPanel_AddItem);
        jPanel_AddItem.setLayout(jPanel_AddItemLayout);
        jPanel_AddItemLayout.setHorizontalGroup(
            jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AddItemLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_AddItemLayout.createSequentialGroup()
                        .addGroup(jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_AddItemLayout.createSequentialGroup()
                                .addComponent(jLabel_ItemSizeText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_ItemSize, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_AddItemLayout.createSequentialGroup()
                                .addComponent(jLabel_ItemPriceText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_ItemPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_AddItemLayout.createSequentialGroup()
                                .addComponent(jLabel_ItemNameText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_ItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel_AddItemLayout.createSequentialGroup()
                                    .addComponent(jLabel_ItemTypeText)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox_ItemType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_AddItemLayout.createSequentialGroup()
                                    .addComponent(jLabel_ItemDescriptionText)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField_ItemDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_AddItemLayout.createSequentialGroup()
                                    .addGroup(jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_ItemGenreText)
                                        .addComponent(jLabel_ItemGenreText1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBox_ItemCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox_ItemGenre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel_AddItemLayout.createSequentialGroup()
                        .addComponent(jLabel_ItemImageText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_AddItemLayout.createSequentialGroup()
                                .addComponent(jButton_AddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 129, Short.MAX_VALUE))
                            .addGroup(jPanel_AddItemLayout.createSequentialGroup()
                                .addComponent(jButton_SelectItemImage, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_imgItempath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel_AddItemLayout.setVerticalGroup(
            jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AddItemLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_ItemNameText)
                    .addComponent(jTextField_ItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_ItemPriceText)
                    .addComponent(jTextField_ItemPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_ItemSizeText)
                    .addComponent(jTextField_ItemSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_ItemDescriptionText)
                    .addComponent(jTextField_ItemDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_AddItemLayout.createSequentialGroup()
                        .addGroup(jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_ItemGenreText)
                            .addComponent(jComboBox_ItemGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_ItemGenreText1)
                            .addComponent(jComboBox_ItemCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_ItemTypeText)
                            .addComponent(jComboBox_ItemType, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_ItemImageText))
                    .addGroup(jPanel_AddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_SelectItemImage)
                        .addComponent(jLabel_imgItempath)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_AddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel_AddItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_AddItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(660, 520));
        setVisibleCanvas();

        jTable_ItemsList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Item Name", "Image", "Owner", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_ItemsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ItemsListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_ItemsList);

        jLabel_PageMarket1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PageMarket1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shop48.png"))); // NOI18N

        jLabel_TitleMarket4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_TitleMarket4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_TitleMarket4.setText("Label1");
        jLabel_TitleMarket4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel_TitleMarket2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_TitleMarket2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_TitleMarket2.setText("Label1");
        jLabel_TitleMarket2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_TitleMarket2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket2MouseExited(evt);
            }
        });

        jLabel_TitleMarket5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_TitleMarket5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_TitleMarket5.setText("Label1");
        jLabel_TitleMarket5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_TitleMarket5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket5MouseExited(evt);
            }
        });

        jLabel_TitleMarket6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_TitleMarket6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_TitleMarket6.setText("Label1");
        jLabel_TitleMarket6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_TitleMarket6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket6MouseExited(evt);
            }
        });

        jLabel_Market1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Market1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shop48.png"))); // NOI18N

        jLabel_Market2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Market2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shop48.png"))); // NOI18N

        jLabel_Market3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Market3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shop48.png"))); // NOI18N

        jLabel_Market4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Market4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shop48.png"))); // NOI18N

        jLabel_Market5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Market5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shop48.png"))); // NOI18N

        jLabel_Market6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Market6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shop48.png"))); // NOI18N

        jLabel_TitleMarket1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_TitleMarket1.setText("jLabel");
        jLabel_TitleMarket1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_TitleMarket1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket1MouseExited(evt);
            }
        });

        jLabel_TitleMarket3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_TitleMarket3.setText("jLabel");
        jLabel_TitleMarket3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_TitleMarket3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket3MouseExited(evt);
            }
        });

        jLabel_TitleMarket7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_TitleMarket7.setText("jLabel");
        jLabel_TitleMarket7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_TitleMarket7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_TitleMarket7MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel_MarketLabelsLayout = new javax.swing.GroupLayout(jPanel_MarketLabels);
        jPanel_MarketLabels.setLayout(jPanel_MarketLabelsLayout);
        jPanel_MarketLabelsLayout.setHorizontalGroup(
            jPanel_MarketLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MarketLabelsLayout.createSequentialGroup()
                .addGroup(jPanel_MarketLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_MarketLabelsLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_TitleMarket2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_TitleMarket5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_TitleMarket6))
                    .addGroup(jPanel_MarketLabelsLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_TitleMarket1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_TitleMarket3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_TitleMarket7)))
                .addGap(240, 240, 240))
            .addGroup(jPanel_MarketLabelsLayout.createSequentialGroup()
                .addGroup(jPanel_MarketLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_MarketLabelsLayout.createSequentialGroup()
                        .addGap(578, 578, 578)
                        .addComponent(jLabel_TitleMarket4))
                    .addGroup(jPanel_MarketLabelsLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addGroup(jPanel_MarketLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_Market4, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_MarketLabelsLayout.createSequentialGroup()
                                .addGap(320, 320, 320)
                                .addComponent(jLabel_Market5, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(jLabel_Market6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_MarketLabelsLayout.createSequentialGroup()
                                .addComponent(jLabel_Market1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(jLabel_Market2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(jLabel_Market3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel_MarketLabelsLayout.setVerticalGroup(
            jPanel_MarketLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MarketLabelsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_TitleMarket4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_MarketLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Market3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Market2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Market1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_MarketLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_TitleMarket1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_TitleMarket3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_TitleMarket7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_MarketLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Market4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Market5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Market6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_MarketLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_TitleMarket6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_MarketLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_TitleMarket5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_TitleMarket2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel_Image.setBackground(new java.awt.Color(245, 243, 243));
        jLabel_Image.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel_PageMarket2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PageMarket2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shop48.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_PageMarket2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel_PageMarket1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel_MarketLabels, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel_PageMarket1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_PageMarket2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel_MarketLabels, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(57, 57, 57)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(660, 520));

        jPanel9.setBackground(new java.awt.Color(245, 243, 243));

        jLabel_Image1.setBackground(new java.awt.Color(245, 243, 243));
        jLabel_Image1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(jLabel_Image1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Image1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1561, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 1564, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)))
        );

        setVisibleCanvas();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public static void setjScrollPane1(JScrollPane jScrollPane1) {
        jScrollPane1 = jScrollPane1;
    }

    private void jPanel_Profile_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_Profile_BtnMousePressed
        
        if(this.username == null ){
            // show a new form
            Login_Form form = new Login_Form();
            form.setVisible(true);
            form.pack();
            form.setLocationRelativeTo(null);
            // close the current form(login form)
            this.dispose();
        } else {
            setRunningStateProfile(true);
        
            setColor(jPanel_Profile_Btn);
            jPanel_Indicator_Profile.setOpaque(true);
            resetColor(new JPanel[]{jPanel_Home_Btn, jPanel_Market_Men, jPanel_Settings_Btn, jPanel_Market_Ladies, jPanel_Market_Kids, jPanel_Market_Sports, jPanel_Market_Other, jPanel_Market_Brands}, 
                   new JPanel[]{jPanel_Indicator_Home, jPanel_Indicator_Men, jPanel_Indicator_Settings, jPanel_Indicator_Ladies, jPanel_Indicator_Kids, jPanel_Indicator_Sports, jPanel_Indicator_Outdoor, jPanel_Indicator_Brands});
        }
    }//GEN-LAST:event_jPanel_Profile_BtnMousePressed

    private void jPanel_Home_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_Home_BtnMousePressed
        
        setRunningStateHome(true);
        
        setColor(jPanel_Home_Btn);
        jPanel_Indicator_Home.setOpaque(true);
        resetColor(new JPanel[]{jPanel_Profile_Btn, jPanel_Market_Men, jPanel_Settings_Btn, jPanel_Market_Ladies, jPanel_Market_Kids, jPanel_Market_Sports, jPanel_Market_Other, jPanel_Market_Brands},
                   new JPanel[]{jPanel_Indicator_Profile, jPanel_Indicator_Men, jPanel_Indicator_Settings, jPanel_Indicator_Ladies, jPanel_Indicator_Kids, jPanel_Indicator_Sports, jPanel_Indicator_Outdoor, jPanel_Indicator_Brands});
    }//GEN-LAST:event_jPanel_Home_BtnMousePressed

    private void jPanel_Market_MenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_Market_MenMousePressed
               
        setColor(jPanel_Market_Men);
        jPanel_Indicator_Men.setOpaque(true);
        resetColor(new JPanel[]{jPanel_Home_Btn, jPanel_Profile_Btn, jPanel_Settings_Btn, jPanel_Market_Ladies, jPanel_Market_Kids, jPanel_Market_Sports, jPanel_Market_Other, jPanel_Market_Brands},
                   new JPanel[]{jPanel_Indicator_Home, jPanel_Indicator_Profile, jPanel_Indicator_Settings, jPanel_Indicator_Ladies, jPanel_Indicator_Kids, jPanel_Indicator_Sports, jPanel_Indicator_Outdoor, jPanel_Indicator_Brands});       
      
        jPopupMenuMen.show(this, jPanel_Market_Men.getX() + 135, jPanel_Market_Men.getY() + 30);

    }//GEN-LAST:event_jPanel_Market_MenMousePressed

    private void jPanel_Settings_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_Settings_BtnMousePressed
        
        setColor(jPanel_Settings_Btn);
        jPanel_Indicator_Settings.setOpaque(true);
        resetColor(new JPanel[]{jPanel_Home_Btn, jPanel_Market_Men, jPanel_Profile_Btn, jPanel_Market_Ladies, jPanel_Market_Kids, jPanel_Market_Sports, jPanel_Market_Other, jPanel_Market_Brands},
                   new JPanel[]{jPanel_Indicator_Home, jPanel_Indicator_Men, jPanel_Indicator_Profile, jPanel_Indicator_Ladies, jPanel_Indicator_Kids, jPanel_Indicator_Sports, jPanel_Indicator_Outdoor, jPanel_Indicator_Brands});
    }//GEN-LAST:event_jPanel_Settings_BtnMousePressed

    private void jLabel_LogoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_LogoutMousePressed
        
        Login_Form lf = new Login_Form();
        lf.setVisible(true);
        lf.pack();
        lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel_LogoutMousePressed
    
    private void jTable_ItemsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ItemsListMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTable_ItemsList.getModel();
        int selectedRowIndex = jTable_ItemsList.getSelectedRow();
        String id = model.getValueAt(selectedRowIndex, 0).toString();
        
        PreparedStatement st;
        ResultSet rs;

        String query = "SELECT * FROM `items` WHERE `id` = ?";

        try {
            st = My_CNX.getConnection().prepareStatement(query);
            st.setString(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                String itemname = rs.getString("itemname");
                String owner = rs.getString("owner");
                String price = rs.getString("price");
                String dimension = rs.getString("dimension");
                String description = rs.getString("description");
                
                byte[] img = rs.getBytes("image");
                Image image = new ImageIcon(img).getImage().getScaledInstance(250, 270, Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(image);
                
                pi.setVisible(true);
                pi.pack();
        
                pi.setItemID(id);
                pi.setUserID(getUserID());
                pi.productInfoBrandName.setText(owner);
                pi.productInfoModel.setText(itemname);
                pi.productInfoPrice.setText(price);
                pi.productInfoDimension.setText(dimension);
                pi.productInfoFeature.setText(description);
                pi.productPhoto.setIcon(newImage);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable_ItemsListMouseClicked

    private void jPanel_Market_LadiesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_Market_LadiesMousePressed
        
        setColor(jPanel_Market_Ladies);
        jPanel_Indicator_Ladies.setOpaque(true);
        resetColor(new JPanel[]{jPanel_Home_Btn, jPanel_Profile_Btn, jPanel_Settings_Btn, jPanel_Market_Men, jPanel_Market_Kids, jPanel_Market_Sports, jPanel_Market_Other, jPanel_Market_Brands},
                   new JPanel[]{jPanel_Indicator_Home, jPanel_Indicator_Profile, jPanel_Indicator_Settings, jPanel_Indicator_Men, jPanel_Indicator_Kids, jPanel_Indicator_Sports, jPanel_Indicator_Outdoor, jPanel_Indicator_Brands});
        
        jPopupMenuLadies.show(this, jPanel_Market_Ladies.getX() + 135, jPanel_Market_Ladies.getY() + 30);
    }//GEN-LAST:event_jPanel_Market_LadiesMousePressed

    private void jPanel_Market_KidsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_Market_KidsMousePressed
        
        setColor(jPanel_Market_Kids);
        jPanel_Indicator_Kids.setOpaque(true);
        resetColor(new JPanel[]{jPanel_Home_Btn, jPanel_Profile_Btn, jPanel_Settings_Btn, jPanel_Market_Men, jPanel_Market_Ladies, jPanel_Market_Sports, jPanel_Market_Other, jPanel_Market_Brands},
                   new JPanel[]{jPanel_Indicator_Home, jPanel_Indicator_Profile, jPanel_Indicator_Settings, jPanel_Indicator_Men, jPanel_Indicator_Ladies, jPanel_Indicator_Sports, jPanel_Indicator_Outdoor, jPanel_Indicator_Brands});
        
        jPopupMenuKids.show(this, jPanel_Market_Kids.getX() + 135, jPanel_Market_Kids.getY() + 30);
    }//GEN-LAST:event_jPanel_Market_KidsMousePressed

    private void jPanel_Market_SportsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_Market_SportsMousePressed
        
        setColor(jPanel_Market_Sports);
        jPanel_Indicator_Sports.setOpaque(true);
        resetColor(new JPanel[]{jPanel_Home_Btn, jPanel_Profile_Btn, jPanel_Settings_Btn, jPanel_Market_Men, jPanel_Market_Ladies, jPanel_Market_Kids, jPanel_Market_Other, jPanel_Market_Brands},
                   new JPanel[]{jPanel_Indicator_Home, jPanel_Indicator_Profile, jPanel_Indicator_Settings, jPanel_Indicator_Men, jPanel_Indicator_Ladies, jPanel_Indicator_Kids, jPanel_Indicator_Outdoor, jPanel_Indicator_Brands});
        
        jPopupMenuSports.show(this, jPanel_Market_Sports.getX() + 135, jPanel_Market_Sports.getY() + 30);
    }//GEN-LAST:event_jPanel_Market_SportsMousePressed

    private void jPanel_Market_OtherMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_Market_OtherMousePressed
        
        setColor(jPanel_Market_Other);
        jPanel_Indicator_Outdoor.setOpaque(true);
        resetColor(new JPanel[]{jPanel_Home_Btn, jPanel_Profile_Btn, jPanel_Settings_Btn, jPanel_Market_Men, jPanel_Market_Ladies, jPanel_Market_Kids, jPanel_Market_Sports, jPanel_Market_Brands},
                   new JPanel[]{jPanel_Indicator_Home, jPanel_Indicator_Profile, jPanel_Indicator_Settings, jPanel_Indicator_Men, jPanel_Indicator_Ladies, jPanel_Indicator_Kids, jPanel_Indicator_Sports, jPanel_Indicator_Brands});
        
        jPopupMenuOutdoor.show(this, jPanel_Market_Other.getX() + 135, jPanel_Market_Other.getY() + 30);
    }//GEN-LAST:event_jPanel_Market_OtherMousePressed

    private void jPanel_Market_BrandsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_Market_BrandsMousePressed
        
        setColor(jPanel_Market_Brands);
        jPanel_Indicator_Brands.setOpaque(true);
        resetColor(new JPanel[]{jPanel_Home_Btn, jPanel_Profile_Btn, jPanel_Settings_Btn, jPanel_Market_Men, jPanel_Market_Ladies, jPanel_Market_Kids, jPanel_Market_Sports, jPanel_Market_Other},
                   new JPanel[]{jPanel_Indicator_Home, jPanel_Indicator_Profile, jPanel_Indicator_Settings, jPanel_Indicator_Men, jPanel_Indicator_Ladies, jPanel_Indicator_Kids, jPanel_Indicator_Sports, jPanel_Indicator_Outdoor});
        
        
        jPopupMenuBrands.show(this, jPanel_Market_Brands.getX() + 135, jPanel_Market_Brands.getY() + 30);
    }//GEN-LAST:event_jPanel_Market_BrandsMousePressed

    private void jPanel_Market_SaleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_Market_SaleMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel_Market_SaleMousePressed

    
    
    //MEN AND LADIES MENU BUTTONS
    private void FootwearMenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FootwearMenActionPerformed
        
        runningMarketType = MarketState.MEN;
        setRunningStateMarket(true);
        
        MenBtn menButton = new MenBtn();
        menButton.setIcon(jLabel_PageMarket1, jLabel_PageMarket2);
        menButton.setFootwearLabels(jPanel_MarketLabels);
    }//GEN-LAST:event_FootwearMenActionPerformed

    private void ClothingMenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClothingMenActionPerformed
        
        runningMarketType = MarketState.MEN;
        setRunningStateMarket(true);
        
        MenBtn menButton = new MenBtn();
        menButton.setIcon(jLabel_PageMarket1, jLabel_PageMarket2);
        menButton.setClothingLabels(jPanel_MarketLabels);
    }//GEN-LAST:event_ClothingMenActionPerformed

    private void FootwearLadiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FootwearLadiesActionPerformed
        
        runningMarketType = MarketState.LADIES;
        setRunningStateMarket(true);
        
        LadiesBtn ladiesButton = new LadiesBtn();
        ladiesButton.setIcon(jLabel_PageMarket1, jLabel_PageMarket2);
        ladiesButton.setFootwearLabels(jPanel_MarketLabels);
    }//GEN-LAST:event_FootwearLadiesActionPerformed

    private void ClothingLadiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClothingLadiesActionPerformed
        
        runningMarketType = MarketState.LADIES;
        setRunningStateMarket(true);
        
        LadiesBtn ladiesButton = new LadiesBtn();
        ladiesButton.setIcon(jLabel_PageMarket1, jLabel_PageMarket2);
        ladiesButton.setClothingLabels(jPanel_MarketLabels);
    }//GEN-LAST:event_ClothingLadiesActionPerformed

    
    
    // MARKET LABELS MOUSE CLICK
    private void jLabel_TitleMarket1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket1MouseEntered
        jLabel_TitleMarket1.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_jLabel_TitleMarket1MouseEntered

    private void jLabel_TitleMarket1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket1MouseExited
        jLabel_TitleMarket1.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_jLabel_TitleMarket1MouseExited

    private void jLabel_TitleMarket1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket1MouseClicked
        String labelTitle = jLabel_TitleMarket1.getText();
        String genre = labelTitle.substring(0, labelTitle.indexOf(' '));
        String type = labelTitle.substring(labelTitle.indexOf(' ') + 1);
        
        new Market().searchAfterGenreAndType(jTable_ItemsList, genre, type);       
    }//GEN-LAST:event_jLabel_TitleMarket1MouseClicked

    public static JLabel getjLabel_TitleMarket1() {
        return jLabel_TitleMarket1;
    }

    public static void setjLabel_TitleMarket1(JLabel jLabel_TitleMarket1) {
        Menu_Form2.jLabel_TitleMarket1 = jLabel_TitleMarket1;
    }

    private void jLabel_TitleMarket3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket3MouseClicked
        String labelTitle = jLabel_TitleMarket3.getText();
        String genre = labelTitle.substring(0, labelTitle.indexOf(' '));
        String type = labelTitle.substring(labelTitle.indexOf(' ') + 1);
        
        new Market().searchAfterGenreAndType(jTable_ItemsList, genre, type);
    }//GEN-LAST:event_jLabel_TitleMarket3MouseClicked

    private void jLabel_TitleMarket3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket3MouseEntered
        jLabel_TitleMarket3.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_jLabel_TitleMarket3MouseEntered

    private void jLabel_TitleMarket3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket3MouseExited
        jLabel_TitleMarket3.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_jLabel_TitleMarket3MouseExited

    private void jLabel_TitleMarket7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket7MouseEntered
        jLabel_TitleMarket7.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_jLabel_TitleMarket7MouseEntered

    private void jLabel_TitleMarket7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket7MouseExited
        jLabel_TitleMarket7.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_jLabel_TitleMarket7MouseExited

    private void jLabel_TitleMarket7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket7MouseClicked
        String labelTitle = jLabel_TitleMarket7.getText();
        String genre = labelTitle.substring(0, labelTitle.indexOf(' '));
        String type = labelTitle.substring(labelTitle.indexOf(' ') + 1);
        
        new Market().searchAfterGenreAndType(jTable_ItemsList, genre, type);
    }//GEN-LAST:event_jLabel_TitleMarket7MouseClicked

    private void jLabel_TitleMarket2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket2MouseEntered
        jLabel_TitleMarket2.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_jLabel_TitleMarket2MouseEntered

    private void jLabel_TitleMarket2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket2MouseExited
        jLabel_TitleMarket2.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_jLabel_TitleMarket2MouseExited

    private void jLabel_TitleMarket2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket2MouseClicked
        String labelTitle = jLabel_TitleMarket7.getText();
        String genre = labelTitle.substring(0, labelTitle.indexOf(' '));
        String type = labelTitle.substring(labelTitle.indexOf(' ') + 1);
        
        new Market().searchAfterGenreAndType(jTable_ItemsList, genre, type);
    }//GEN-LAST:event_jLabel_TitleMarket2MouseClicked

    private void jLabel_TitleMarket5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket5MouseEntered
        jLabel_TitleMarket5.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_jLabel_TitleMarket5MouseEntered

    private void jLabel_TitleMarket5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket5MouseExited
        jLabel_TitleMarket5.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_jLabel_TitleMarket5MouseExited

    private void jLabel_TitleMarket5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket5MouseClicked
        String labelTitle = jLabel_TitleMarket5.getText();
        String genre = labelTitle.substring(0, labelTitle.indexOf(' '));
        String type = labelTitle.substring(labelTitle.indexOf(' ') + 1);
        
        new Market().searchAfterGenreAndType(jTable_ItemsList, genre, type);
    }//GEN-LAST:event_jLabel_TitleMarket5MouseClicked

    private void jLabel_TitleMarket6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket6MouseEntered
        jLabel_TitleMarket6.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_jLabel_TitleMarket6MouseEntered

    private void jLabel_TitleMarket6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket6MouseExited
        jLabel_TitleMarket6.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_jLabel_TitleMarket6MouseExited

    private void jLabel_TitleMarket6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_TitleMarket6MouseClicked
        String labelTitle = jLabel_TitleMarket6.getText();
        String genre = labelTitle.substring(0, labelTitle.indexOf(' '));
        String type = labelTitle.substring(labelTitle.indexOf(' ') + 1);

        new Market().searchAfterGenreAndType(jTable_ItemsList, genre, type);
    }//GEN-LAST:event_jLabel_TitleMarket6MouseClicked

    

    //KIDS MENU BUTTONS
    private void FootwearKidsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FootwearKidsActionPerformed
        runningMarketType = MarketState.KIDS;
        setRunningStateMarket(true);
        
        KidsBtn kidsButton = new KidsBtn();
        kidsButton.setIcon(jLabel_PageMarket1, jLabel_PageMarket2);
        kidsButton.setFootwearLabels(jPanel_MarketLabels);
    }//GEN-LAST:event_FootwearKidsActionPerformed

    private void ClothingKidsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClothingKidsActionPerformed
        runningMarketType = MarketState.KIDS;
        setRunningStateMarket(true);
        
        KidsBtn kidsButton = new KidsBtn();
        kidsButton.setIcon(jLabel_PageMarket1, jLabel_PageMarket2);
        kidsButton.setClothingLabels(jPanel_MarketLabels);
    }//GEN-LAST:event_ClothingKidsActionPerformed

    
    //SPORTS MENU BUTTONS
    private void SkiingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkiingActionPerformed
        runningMarketType = MarketState.SPORTS;
        setRunningStateMarket(true);
        
        SportsBtn sportButton = new SportsBtn();
        sportButton.setIcon(jLabel_PageMarket1, jLabel_PageMarket2);
        sportButton.setSkiingLabels(jPanel_MarketLabels);
    }//GEN-LAST:event_SkiingActionPerformed

    private void RunningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RunningActionPerformed
        runningMarketType = MarketState.SPORTS;
        setRunningStateMarket(true);
        
        SportsBtn sportButton = new SportsBtn();
        sportButton.setIcon(jLabel_PageMarket1, jLabel_PageMarket2);
        sportButton.setRunningLabels(jPanel_MarketLabels);
    }//GEN-LAST:event_RunningActionPerformed

    private void OutdoorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OutdoorActionPerformed
        runningMarketType = MarketState.SPORTS;
        setRunningStateMarket(true);
        
        SportsBtn sportButton = new SportsBtn();
        sportButton.setIcon(jLabel_PageMarket1, jLabel_PageMarket2);
        sportButton.setOutdoorLabels(jPanel_MarketLabels);
    }//GEN-LAST:event_OutdoorActionPerformed

    private void FitnessAndTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FitnessAndTrainingActionPerformed
        runningMarketType = MarketState.SPORTS;
        setRunningStateMarket(true);
        
        SportsBtn sportButton = new SportsBtn();
        sportButton.setIcon(jLabel_PageMarket1, jLabel_PageMarket2);
        sportButton.setFitnessAndTrainingLabels(jPanel_MarketLabels);
    }//GEN-LAST:event_FitnessAndTrainingActionPerformed

    
    
    //ADD ITEM BUTTON + PANEL
    private void jLabel_AddItemBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AddItemBtnMouseClicked
            
            jPanel_AddItem.setVisible(true);
            jComboBox_ItemGenre.addItem("Men");
            jComboBox_ItemGenre.addItem("Ladies");
            jComboBox_ItemGenre.addItem("Kids");
            jComboBox_ItemGenre.addItem("Sports");
    }//GEN-LAST:event_jLabel_AddItemBtnMouseClicked

    
    
    //ITEM INFORMATION
    private void jButton_SelectItemImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SelectItemImageActionPerformed
        // select an image and set the image path into a jlabel
        String path = null;

        JFileChooser chooser = new JFileChooser();

        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        // file extension
        FileNameExtensionFilter extension = new FileNameExtensionFilter("*.Images","jpg","png","jpeg");
        chooser.addChoosableFileFilter(extension);

        int filestate = chooser.showSaveDialog(null);

        // check if the user select an image
        if(filestate == JFileChooser.APPROVE_OPTION){

            File selectedImage = chooser.getSelectedFile();
            path = selectedImage.getAbsolutePath();
            jLabel_imgItempath.setText(path);

            new_item_image_path = path;
        }
    }//GEN-LAST:event_jButton_SelectItemImageActionPerformed

    
    //ADD ITEM BUTTON
    private void jButton_AddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddItemActionPerformed
        
        String itemname = jTextField_ItemName.getText();
        String price = jTextField_ItemPrice.getText();
        String size = jTextField_ItemSize.getText();
        String description = jTextField_ItemDescription.getText();
        String genre = jComboBox_ItemGenre.getSelectedItem().toString();
        String type = jComboBox_ItemType.getSelectedItem().toString();
        String category = jComboBox_ItemCategory.getSelectedItem().toString();
        String dimension = "10";
        String owner="SportMagazine HQ Official";
        String transactionID = "0";
        
        
        if(true)//verifyFields()#####################################################################################
        {
            PreparedStatement ps;
//            String registerUserQuery = "INSERT INTO `items`(`itemname`, `image`, `owner`, `price`, `dimension`, `description`, `genre`, `category`, `type`, 'transactionID') VALUES (?,?,?,?,?,?,?,?,?,?)";
                 
            String sql = "INSERT INTO `items`(`itemname`, `image`, `owner`, `price`, `dimension`, `description`, `genre`, `category`, `type`, `transactionID`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            try {
                ps = My_CNX.getConnection().prepareStatement(sql);
                ps.setString(1, itemname);
                ps.setString(3, owner);
                ps.setString(4, price);
                ps.setString(5, dimension);
                ps.setString(6, description);
                ps.setString(7, genre);
                ps.setString(8, category);
                ps.setString(9, type);
                ps.setString(10, transactionID);
                     
                try {
                    // save the image as blob in the database
                    if(new_item_image_path != null){
                             InputStream image = new FileInputStream(new File(new_item_image_path));
                             ps.setBlob(2, image);           
                         }else{
                             ps.setNull(2, java.sql.Types.NULL);
                         }
                         
                         if(ps.executeUpdate() != 0){
                            JOptionPane.showMessageDialog(null, "Your Item Has Been Added");
                             
                         }else{
                             JOptionPane.showMessageDialog(null, "Error: Check Your Informations");
                         }
                         
                     } catch (FileNotFoundException ex) {
                         Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     
                 } catch (SQLException ex) {
                     Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
                 }
        }
        
        
    }//GEN-LAST:event_jButton_AddItemActionPerformed

    public static void INSERT_ITEMS_TEST(){
     
        int count = 0;
        while(count++ < 20) {
            TEST_ADD_ITEMS(count);
        }
    }
    
    public static void TEST_ADD_ITEMS(int count) {
        
        String path_to_image = "";
        String itemname = "";
        if(count < 10){
            itemname = "Role" + count;
            path_to_image = System.getProperty("user.dir") + "\\src\\images\\Items\\role.jpg";
        } else {
            itemname = "Bicicleta" + count;
            path_to_image = System.getProperty("user.dir") + "\\src\\images\\Items\\bicicleta.jpg";
        }
        String price = "10";
        String size = "10";
        String description = "Item_" + count;
        String genre = "Men";
        String type = "Football Boots";
        String category = "Footwear";
        String dimension = "10";
        String owner="SportMagazine HQ Official";
        String transactionID = "0";
        
        
        
        
        
        if(true)//verifyFields()#####################################################################################
        {
            PreparedStatement ps;
//            String registerUserQuery = "INSERT INTO `items`(`itemname`, `image`, `owner`, `price`, `dimension`, `description`, `genre`, `category`, `type`, 'transactionID') VALUES (?,?,?,?,?,?,?,?,?,?)";
                 
            String sql = "INSERT INTO `items`(`itemname`, `image`, `owner`, `price`, `dimension`, `description`, `genre`, `category`, `type`, `transactionID`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            try {
                ps = My_CNX.getConnection().prepareStatement(sql);
                ps.setString(1, itemname);
                ps.setString(3, owner);
                ps.setString(4, price);
                ps.setString(5, dimension);
                ps.setString(6, description);
                ps.setString(7, genre);
                ps.setString(8, category);
                ps.setString(9, type);
                ps.setString(10, transactionID);
                     
                try {
                    // save the image as blob in the database
                    if(path_to_image != null){
                             InputStream image = new FileInputStream(new File(path_to_image));
                             ps.setBlob(2, image);           
                         }else{
                             ps.setNull(2, java.sql.Types.NULL);
                         }
                         
                         if(ps.executeUpdate() != 0){
                            JOptionPane.showMessageDialog(null, "Your Item: " + itemname + " Has Been Added");
                             
                         }else{
                             JOptionPane.showMessageDialog(null, "Error: Check Your Informations");
                         }
                         
                     } catch (FileNotFoundException ex) {
                         Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     
                 } catch (SQLException ex) {
                     Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
                 }
        }
    }
    
    private void jComboBox_ItemGenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_ItemGenreActionPerformed
        jComboBox_ItemCategory.removeAllItems();
        if (jComboBox_ItemGenre.getSelectedItem().toString().equals("Men")){
            jComboBox_ItemCategory.addItem("Footwear");
            jComboBox_ItemCategory.addItem("Clothing");
        } else if (jComboBox_ItemGenre.getSelectedItem().toString().equals("Ladies")){
            jComboBox_ItemCategory.addItem("Footwear");
            jComboBox_ItemCategory.addItem("Clothing");
        } else if (jComboBox_ItemGenre.getSelectedItem().toString().equals("Kids")){
            jComboBox_ItemCategory.addItem("Footwear");
            jComboBox_ItemCategory.addItem("Clothing");
        } else if (jComboBox_ItemGenre.getSelectedItem().toString().equals("Sports")){
            jComboBox_ItemCategory.addItem("Running");
            jComboBox_ItemCategory.addItem("Outdoor");
            jComboBox_ItemCategory.addItem("Fitness And Training");
            jComboBox_ItemCategory.addItem("Skiing");
        }
    }//GEN-LAST:event_jComboBox_ItemGenreActionPerformed

    private void jComboBox_ItemCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_ItemCategoryActionPerformed
        jComboBox_ItemType.removeAllItems();
        if (jComboBox_ItemGenre.getSelectedItem() != null && jComboBox_ItemCategory.getSelectedItem() != null)
            if (jComboBox_ItemGenre.getSelectedItem().toString().equals("Men")
                    && jComboBox_ItemCategory.getSelectedItem().toString().equals("Footwear")){
                jComboBox_ItemType.addItem("Football Boots");
                jComboBox_ItemType.addItem("Walking Boots & Shoes");
                jComboBox_ItemType.addItem("Boots");
                jComboBox_ItemType.addItem("Trainers");
                jComboBox_ItemType.addItem("Astro Trainers");
                jComboBox_ItemType.addItem("Running Shoes");
            } else if (jComboBox_ItemGenre.getSelectedItem().toString().equals("Ladies")
                    && jComboBox_ItemCategory.getSelectedItem().toString().equals("Footwear")){
                jComboBox_ItemType.addItem("Sandals");
                jComboBox_ItemType.addItem("Walking Boots & Shoes");
                jComboBox_ItemType.addItem("Boots");
                jComboBox_ItemType.addItem("Trainers");
                jComboBox_ItemType.addItem("Shoes");
                jComboBox_ItemType.addItem("Running Shoes");
            } else if (jComboBox_ItemGenre.getSelectedItem().toString().equals("Kids")
                    && jComboBox_ItemCategory.getSelectedItem().toString().equals("Footwear")){
                jComboBox_ItemType.addItem("Football Boots");
                jComboBox_ItemType.addItem("School Shoes");
                jComboBox_ItemType.addItem("Baby Shoes");
                jComboBox_ItemType.addItem("Trainers");
                jComboBox_ItemType.addItem("Boys Footwear");
                jComboBox_ItemType.addItem("Girls Footwear");
            } else if (jComboBox_ItemGenre.getSelectedItem().toString().equals("Sports")){
            }
    }//GEN-LAST:event_jComboBox_ItemCategoryActionPerformed

    private void jLabelCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCartMouseClicked
        Cart.getInstance(this.getUserID()).PrepareCartItems();
    }//GEN-LAST:event_jLabelCartMouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
//        SwingWorkerRealTime swingWorkerRealTime = new SwingWorkerRealTime();
//        swingWorkerRealTime.go();
    }//GEN-LAST:event_jLabel16MouseClicked

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_Form2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Form2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Form2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Form2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        Menu_Form2 menu = new Menu_Form2(null, null);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                menu.setVisible(true);
            }
        });
        
        myClock.start();
        while(true){
            if(menu.runningMarketType == MarketState.MEN)
                menu.changeLabel(MarketState.MEN);
            else if(menu.runningMarketType == MarketState.LADIES)
                menu.changeLabel(MarketState.LADIES);
            else if(menu.runningMarketType == MarketState.KIDS)
                menu.changeLabel(MarketState.KIDS);
            else
                menu.changeLabel(MarketState.SPORTS);
        }
    }
    
    
    
    private void setColor(JPanel pane) {
        
        pane.setBackground(new Color(41, 57, 80));
    }
    
    private void resetColor(JPanel[] pane, JPanel[] indicators) {
        for (int i = 0; i < pane.length; i++)
            pane[i].setBackground(new Color(23, 35, 51));
        
        for (int i = 0; i < pane.length; i++)
            indicators[i].setOpaque(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ClothingKids;
    private javax.swing.JMenuItem ClothingLadies;
    private javax.swing.JMenuItem ClothingMen;
    private javax.swing.JMenuItem FitnessAndTraining;
    private javax.swing.JMenuItem FootwearKids;
    private javax.swing.JMenuItem FootwearLadies;
    private javax.swing.JMenuItem FootwearMen;
    private javax.swing.JMenuItem Outdoor;
    private javax.swing.JMenuItem Running;
    private javax.swing.JMenuItem Skiing;
    private javax.swing.JMenu Top_Categories;
    private javax.swing.JMenu Top_Categories1;
    private javax.swing.JMenu Top_Categories2;
    private javax.swing.JMenu Top_Categories3;
    private javax.swing.JMenu Top_Categories4;
    private javax.swing.JMenu Top_Categories5;
    private javax.swing.JButton jButton_AddItem;
    private javax.swing.JButton jButton_SelectItemImage;
    private javax.swing.JComboBox<String> jComboBox_ItemCategory;
    private javax.swing.JComboBox<String> jComboBox_ItemGenre;
    private javax.swing.JComboBox<String> jComboBox_ItemType;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCart;
    private javax.swing.JLabel jLabel_AddItemBtn;
    private javax.swing.JLabel jLabel_Avatar;
    private javax.swing.JLabel jLabel_Image;
    private javax.swing.JLabel jLabel_Image1;
    private javax.swing.JLabel jLabel_ItemDescriptionText;
    private javax.swing.JLabel jLabel_ItemGenreText;
    private javax.swing.JLabel jLabel_ItemGenreText1;
    private javax.swing.JLabel jLabel_ItemImageText;
    private javax.swing.JLabel jLabel_ItemNameText;
    private javax.swing.JLabel jLabel_ItemPriceText;
    private javax.swing.JLabel jLabel_ItemSizeText;
    private javax.swing.JLabel jLabel_ItemTypeText;
    private javax.swing.JLabel jLabel_Logout;
    private javax.swing.JLabel jLabel_Market1;
    private javax.swing.JLabel jLabel_Market2;
    private javax.swing.JLabel jLabel_Market3;
    private javax.swing.JLabel jLabel_Market4;
    private javax.swing.JLabel jLabel_Market5;
    private javax.swing.JLabel jLabel_Market6;
    private javax.swing.JLabel jLabel_PageMarket1;
    private javax.swing.JLabel jLabel_PageMarket2;
    private static javax.swing.JLabel jLabel_TitleMarket1;
    private javax.swing.JLabel jLabel_TitleMarket2;
    private javax.swing.JLabel jLabel_TitleMarket3;
    private javax.swing.JLabel jLabel_TitleMarket4;
    private javax.swing.JLabel jLabel_TitleMarket5;
    private javax.swing.JLabel jLabel_TitleMarket6;
    private javax.swing.JLabel jLabel_TitleMarket7;
    private javax.swing.JLabel jLabel_UserName;
    private javax.swing.JLabel jLabel_imgItempath;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_AddItem;
    private javax.swing.JPanel jPanel_Home_Btn;
    private javax.swing.JPanel jPanel_Indicator_Brands;
    private javax.swing.JPanel jPanel_Indicator_Home;
    private javax.swing.JPanel jPanel_Indicator_Kids;
    private javax.swing.JPanel jPanel_Indicator_Ladies;
    private javax.swing.JPanel jPanel_Indicator_Market7;
    private javax.swing.JPanel jPanel_Indicator_Men;
    private javax.swing.JPanel jPanel_Indicator_Outdoor;
    private javax.swing.JPanel jPanel_Indicator_Profile;
    private javax.swing.JPanel jPanel_Indicator_Settings;
    private javax.swing.JPanel jPanel_Indicator_Sports;
    private javax.swing.JPanel jPanel_MarketLabels;
    private javax.swing.JPanel jPanel_Market_Brands;
    private javax.swing.JPanel jPanel_Market_Kids;
    private javax.swing.JPanel jPanel_Market_Ladies;
    private javax.swing.JPanel jPanel_Market_Men;
    private javax.swing.JPanel jPanel_Market_Other;
    private javax.swing.JPanel jPanel_Market_Sale;
    private javax.swing.JPanel jPanel_Market_Sports;
    private javax.swing.JPanel jPanel_Profile_Btn;
    private javax.swing.JPanel jPanel_Settings_Btn;
    private javax.swing.JPopupMenu jPopupMenuBrands;
    private javax.swing.JPopupMenu jPopupMenuKids;
    private javax.swing.JPopupMenu jPopupMenuLadies;
    private javax.swing.JPopupMenu jPopupMenuMen;
    private javax.swing.JPopupMenu jPopupMenuOutdoor;
    private javax.swing.JPopupMenu jPopupMenuSports;
    private static javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private static javax.swing.JTable jTable_ItemsList;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField_ItemDescription;
    private javax.swing.JTextField jTextField_ItemName;
    private javax.swing.JTextField jTextField_ItemPrice;
    private javax.swing.JTextField jTextField_ItemSize;
    // End of variables declaration//GEN-END:variables

    public static JTable getjTable_ItemsList() {
        return jTable_ItemsList;
    }

    public static void setjTable_ItemsList(JTable jTable_ItemsList) {
        jTable_ItemsList = jTable_ItemsList;
    }
}
