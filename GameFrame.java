//pakke der styrer farver
import java.awt.Color;

//Pakke der hjælper med at lave vinduet
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    

    GameFrame() {

        this.add(new GamePanel());

        // Stopper programmet når vinduet lukkes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Vinduets størrelse
        setSize(1000, 1000);

        // Vinduets baggrundsfarve
        this.setBackground(Color.BLACK);

        // Viser vinduet
        setVisible(true);

        // Gør at man ikke kan ændre størrelse på vinduet
        setResizable(false);

        

    }

}