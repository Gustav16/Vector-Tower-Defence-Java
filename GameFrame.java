//pakke der styrer farver
import java.awt.Color;
import javax.swing.*;
//Pakke der hjælper med at lave vinduet
import javax.swing.JFrame;

import java.awt.*;

public class GameFrame extends JFrame {

    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 1000;

    Container pane = this.getContentPane();
    

    GameFrame() {
        pane.setLayout(new BorderLayout());

        //the bottun
        pane.add(new Menu(), BorderLayout.NORTH);

        /*
       I will later add this

        pane.add(new Menu(), BorderLayout.EAST);
        */
       
        //the game
        pane.add(new GamePanel(), BorderLayout.CENTER);
        

        pack();
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