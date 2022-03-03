
//pakke der styrer farver
import java.awt.Color;
import javax.swing.*;
//Pakke der hjælper med at lave vinduet
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;

public class GameFrame extends JFrame {

    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 1000;
    final JLabel moneyLabel;

    GameFrame() {

        JPanel screen = new JPanel();
        screen.setLayout(new BorderLayout());

        JPanel topMenu = new JPanel();
        topMenu.setLayout(new GridLayout(2, 3, 10, 10));
        topMenu.setBackground(Color.BLACK);

        moneyLabel = new JLabel("Money: " + GamePanel.money + "$", SwingConstants.CENTER);
        final JLabel livesLabel = new JLabel("Lives: " + GamePanel.lives, SwingConstants.CENTER);
        final JLabel roundLabel = new JLabel("Round: " + GamePanel.round, SwingConstants.CENTER);
        final JLabel interestLabel = new JLabel("Interest: " + GamePanel.interest + "%", SwingConstants.CENTER);

        moneyLabel.setOpaque(true);
        livesLabel.setOpaque(true);
        roundLabel.setOpaque(true);
        interestLabel.setOpaque(true);

        moneyLabel.setForeground(Color.white);
        livesLabel.setForeground(Color.white);
        roundLabel.setForeground(Color.white);
        interestLabel.setForeground(Color.white);

        moneyLabel.setBackground(new Color(4, 23, 22));
        livesLabel.setBackground(new Color(4, 23, 22));
        roundLabel.setBackground(new Color(4, 23, 22));
        interestLabel.setBackground(new Color(4, 23, 22));

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

        topMenu.add(moneyLabel);
        topMenu.add(livesLabel);
        topMenu.add(roundStart);
        topMenu.add(roundLabel);
        topMenu.add(interestLabel);
        topMenu.add(autoStart);

        //JPanel sideMenu = new JPanel();
        //sideMenu.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //sideMenu.setBackground(Color.BLACK);

        JPanel towerMenu = new JPanel();
        towerMenu.setLayout(new GridLayout(6, 4, 10, 10));
        towerMenu.setBackground(Color.BLACK);

        JLabel label1 = new JLabel();
        label1.setIcon(new ImageIcon("Images/Green_Laser_Mk1.png"));
        towerMenu.add(label1);
        
        JLabel label2 = new JLabel();
        label2.setIcon(new ImageIcon("Images/Purple_Power_Mk1.png"));
        towerMenu.add(label2);

        JLabel label3 = new JLabel();
        label3.setIcon(new ImageIcon("Images/Orange_Incinerator_Mk1.png"));
        towerMenu.add(label3);

        JLabel label4 = new JLabel();
        label4.setIcon(new ImageIcon("Images/Blue_Rays_Mk1.png"));
        towerMenu.add(label4);

        JLabel label5 = new JLabel();
        label1.setIcon(new ImageIcon("Images/Green_Laser_Mk2.png"));
        towerMenu.add(label5);
        
        JLabel label6 = new JLabel();
        label2.setIcon(new ImageIcon("Images/Purple_Power_Mk2.png"));
        towerMenu.add(label6);

        JLabel label7 = new JLabel();
        label3.setIcon(new ImageIcon("Images/Orange_Incinerator_Mk2.png"));
        towerMenu.add(label7);

        JLabel label8 = new JLabel();
        label4.setIcon(new ImageIcon("Images/Blue_Rays_Mk2.png"));
        towerMenu.add(label8);        
        
        
        



        screen.add(new GamePanel(), BorderLayout.CENTER);
        screen.add(topMenu, BorderLayout.NORTH);
        screen.add(towerMenu, BorderLayout.EAST);

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

    void update() {

        
    }

}