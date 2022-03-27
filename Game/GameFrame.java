package Game;

//pakke der styrer farver
import java.awt.Color;
import javax.swing.*;
//Pakke der hjælper med at lave vinduet
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    //CENTER regions størrelse
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 800;

    //Jlabels i top menuen
    public final JLabel moneyLabel = new JLabel("Money: " + GamePanel.money + "$", SwingConstants.CENTER);
    final JLabel livesLabel = new JLabel("Lives: " + GamePanel.lives, SwingConstants.CENTER);
    final JLabel roundLabel = new JLabel("Round: " + GamePanel.round, SwingConstants.CENTER);
    final JLabel interestLabel = new JLabel("Interest: " + GamePanel.interest + "%", SwingConstants.CENTER);


    //De forskellige JPanels
    JPanel topMenu;
    JPanel screen;
    JPanel towerMenu;
    JPanel buttonMenu;

    //De forskellige knapper
    JButton Green_Laser_Mk1;
    JButton Purple_Power_Mk1;
    JButton Orange_Incinerator_Mk1;
    JButton Blue_Rays_Mk1;
    JButton Green_Laser_Mk2;
    JButton Purple_Power_Mk2;
    JButton Orange_Incinerator_Mk2;
    JButton Blue_Rays_Mk2;

    //Alle de forskellige jLabels i bund menuen
    final JLabel currentVectoid = new JLabel("Current Vectoid: ", SwingConstants.CENTER);
    final JLabel currentType = new JLabel(Vectoid.typeList[Vectoid.waveType], SwingConstants.CENTER);
    final JLabel currentHealth = new JLabel("550 Hitpoints", SwingConstants.CENTER);
    final JLabel currentMoney = new JLabel("4$", SwingConstants.CENTER);

    final JLabel nextVectoid = new JLabel("Next Vectoid: ", SwingConstants.CENTER);
    final JLabel nextType = new JLabel(Vectoid.typeList[Vectoid.waveType+1], SwingConstants.CENTER);
    final JLabel nextHealth = new JLabel("660 HitPoints", SwingConstants.CENTER);
    final JLabel nextMoney = new JLabel("5$", SwingConstants.CENTER);

    GameFrame() {

        screen = new JPanel();
        screen.setLayout(new BorderLayout());

        // adding top menu
         addTopMenu();
        // adding game
        screen.add(new GamePanel(), BorderLayout.CENTER);
        // adding side menu
        addSideMenu();
        
        addButtonMenu();

        this.setContentPane(screen);

        // Stopper programmet når vinduet lukkes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Vinduets størrelse
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        // Vinduets baggrundsfarve
        this.setBackground(Color.BLACK);

        // Viser vinduet
        setVisible(true);

        // Gør at man ikke kan ændre størrelse på vinduet
        setResizable(false);

    }

    //Indeling af kode. Den del af koden som laver menuen i bunden.
    void addButtonMenu() {

        buttonMenu = new JPanel();
        buttonMenu.setLayout(new GridLayout(2, 4, 10, 10));
        buttonMenu.setBackground(Color.BLACK);


        addbottunLabel(currentVectoid);
        addbottunLabel(currentType);
        addbottunLabel(currentHealth);
        addbottunLabel(currentMoney);

        addbottunLabel(nextVectoid);
        addbottunLabel(nextType);
        addbottunLabel(nextHealth);
        addbottunLabel(nextMoney);

        screen.add(buttonMenu, BorderLayout.SOUTH);
   

    }

    //Indeling af kode. Den del af koden som laver top menuen.
    void addTopMenu() {
        topMenu = new JPanel();
        topMenu.setLayout(new GridLayout(2, 3, 10, 10));
        topMenu.setBackground(Color.BLACK);

        JButton roundStart = new JButton("Next round");
        roundStart.setBackground(new Color(245, 28, 92));
        roundStart.setFocusPainted(false);

        roundStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Vectoid.newRound();

            }
        });

        JButton autoStart = new JButton("Auto start: " + GamePanel.roundStart);
        autoStart.setBackground(new Color(245, 28, 92));
        autoStart.setFocusPainted(false);

        //Vi adder forskellige ting til top menuen
        addLabel(moneyLabel);
        addLabel(livesLabel);
        topMenu.add(roundStart);
        addLabel(roundLabel);
        addLabel(interestLabel);
        topMenu.add(autoStart);

        screen.add(topMenu, BorderLayout.NORTH);
    }




 //Indeling af kode. Den del af koden som laver siden menuen hvor man køber tårne fra
    void addSideMenu() {
        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));

        sideMenu.setBackground(Color.BLACK);

        towerMenu = new JPanel();
        towerMenu.setLayout(new GridLayout(2, 4, 10, 10));
        towerMenu.setBackground(Color.BLACK);

        addtowerBottun(Green_Laser_Mk1, "Images/Green_Laser_Mk1.png");
        addtowerBottun(Purple_Power_Mk1, "Images/Purple_Power_Mk1.png");
        addtowerBottun(Orange_Incinerator_Mk1, "Images/Orange_Incinerator_Mk1.png");
        addtowerBottun(Blue_Rays_Mk1, "Images/Blue_Rays_Mk1.png");

        addtowerBottun(Green_Laser_Mk2, "Images/Green_Laser_Mk2.png");
        addtowerBottun(Purple_Power_Mk2, "Images/Purple_Power_Mk2.png");
        addtowerBottun(Orange_Incinerator_Mk2, "Images/Orange_Incinerator_Mk2.png");
        addtowerBottun(Blue_Rays_Mk2, "Images/Blue_Rays_Mk2.png");

        /*
         * final JLabel towerMenuName = new JLabel("Towers");
         * towerMenuName.setForeground(Color.white);
         * towerMenuName.setBackground(new Color(4, 23, 22));
         * towerMenuName.setFont(new Font("Verdana", Font.PLAIN, 25));
         * 
         * final JLabel towerInformation = new JLabel("Towers");
         * towerInformation.setForeground(Color.white);
         * towerInformation.setBackground(new Color(4, 23, 22));
         * towerInformation.setFont(new Font("Verdana", Font.PLAIN, 25));
         * 
         * sideMenu.add(towerMenuName);
         * 
         * sideMenu.add(towerInformation);
         * 
         * 
         */

        sideMenu.add(towerMenu);

        JLabel test = new JLabel("this is test", SwingConstants.CENTER);
        sideMenu.add(test);

        screen.add(sideMenu, BorderLayout.EAST);

        // screen.add(towerMenu, BorderLayout.EAST);
    }

    //Funktion som der laver en jLabel og sætter den i topMenu
    void addLabel(JLabel jLabel) {
        jLabel.setOpaque(true);
        jLabel.setForeground(Color.white);
        jLabel.setBackground(new Color(4, 23, 22));
        topMenu.add(jLabel);
    }


    //Funktion som der laver en jLabel og sætter den i buttonMenu
    void addbottunLabel(JLabel jLabel){
        jLabel.setOpaque(true);
        jLabel.setForeground(Color.white);
        jLabel.setBackground(new Color(4, 23, 22));
        buttonMenu.add(jLabel);

    }


 //Funktion som der  laver en knap med et billede på
    void addtowerBottun(JButton name, String img) {

        name = new JButton(new ImageIcon(img));

        name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (GamePanel.followMouseImage != img || GamePanel.selectTower == false) {
                    GamePanel.selectTower = true;
                    GamePanel.followMouseImage = img;
                } else {
                    GamePanel.selectTower = false;
                    GamePanel.followMouseImage = "non";
                }

            }
        });

        towerMenu.add(name);

    }
}