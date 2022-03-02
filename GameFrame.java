
//pakke der styrer farver
import java.awt.Color;
import javax.swing.*;
//Pakke der hjælper med at lave vinduet
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {

    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 1000;

    GameFrame() {

        JPanel screen = new JPanel();
        screen.setLayout(new BorderLayout());

        JPanel topMenu = new JPanel();
        topMenu.setLayout(new GridLayout(2, 3, 10, 10));
        topMenu.setBackground(Color.BLACK);

        JLabel moneyLabel = new JLabel("Money: " + GamePanel.money + "$", SwingConstants.CENTER);
        JLabel livesLabel = new JLabel("Lives: " + GamePanel.lives, SwingConstants.CENTER);
        JLabel roundLabel = new JLabel("Round: " + GamePanel.round, SwingConstants.CENTER);
        JLabel interestLabel = new JLabel("Interest: " + GamePanel.interest + "%", SwingConstants.CENTER);

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

        screen.add(new GamePanel(), BorderLayout.CENTER);
        screen.add(topMenu, BorderLayout.NORTH);

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

}