//pakke der styrer farver
import java.awt.Color;

//Pakke der hjælper med at lave vinduet
import javax.swing.JFrame;


import javax.swing.JButton;
public class GameFrame extends JFrame {

    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 1000;
    

    GameFrame() {
        this.add(new Menu());
        this.add(new GamePanel());

       

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