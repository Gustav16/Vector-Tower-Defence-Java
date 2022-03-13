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

    static final int SCREEN_WIDTH = 1100;
    static final int SCREEN_HEIGHT = 800;

    public final JLabel moneyLabel = new JLabel("Money: " + GamePanel.money + "$", SwingConstants.CENTER);
    final JLabel livesLabel = new JLabel("Lives: " + GamePanel.lives, SwingConstants.CENTER);
    final JLabel roundLabel = new JLabel("Round: " + GamePanel.round, SwingConstants.CENTER);
    final JLabel interestLabel = new JLabel("Interest: " + GamePanel.interest + "%", SwingConstants.CENTER);

    JPanel topMenu;
    JPanel screen;
    JPanel towerMenu;

    JButton Green_Laser_Mk1;
    JButton Purple_Power_Mk1;
    JButton Orange_Incinerator_Mk1;
    JButton Blue_Rays_Mk1;
    JButton Green_Laser_Mk2;
    JButton Purple_Power_Mk2;
    JButton Orange_Incinerator_Mk2;
    JButton Blue_Rays_Mk2;

    GameFrame() {

        screen = new JPanel();
        screen.setLayout(new BorderLayout());

        // adding top menu
        addTopMenu();
        // adding game
        screen.add(new GamePanel(), BorderLayout.CENTER);
        // adding side menu
        addSideMenu();

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

    void addSideMenu() {
        // JPanel sideMenu = new JPanel();
        // sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        // sideMenu.CENTER_ALIGNMENT;
        // sideMenu.setBackground(Color.BLACK);

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
         * sideMenu.add(towerMenu);
         * sideMenu.add(towerInformation);
         */
        screen.add(towerMenu, BorderLayout.EAST);
        // screen.add(towerMenu, BorderLayout.EAST);
    }

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

        addLabel(moneyLabel);
        addLabel(livesLabel);
        topMenu.add(roundStart);
        addLabel(roundLabel);
        addLabel(interestLabel);
        topMenu.add(autoStart);

        screen.add(topMenu, BorderLayout.NORTH);
    }

    void addLabel(JLabel jLabel) {
        jLabel.setOpaque(true);
        jLabel.setForeground(Color.white);
        jLabel.setBackground(new Color(4, 23, 22));
        topMenu.add(jLabel);
    }

    void addtowerBottun(JButton name, String img) {

        name = new JButton(new ImageIcon(img));

        name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (GamePanel.followMouseImage != img || GamePanel.selectTower == false) {
                    GamePanel.selectTower = true;
                    GamePanel.followMouseImage = img;
                } else {
                    GamePanel.selectTower = false;
                }

            }
        });

        towerMenu.add(name);

    }
}