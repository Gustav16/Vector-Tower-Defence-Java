import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.Color;


public class VectorTD extends JPanel{

    static Graphics graphics;
    final static int FPS = 1;
    final static public long SKIP_TICKS = 1000000000 / FPS;
    Thread gameThread;
    Image image;
    Random random;

    public static void main(String[] args) {

        // Creates the display
        GameFrame frame = new GameFrame();

        setup();
        gameloop();
    }

    static void setup() {



    }

    static void draw(Graphics g) {

        System.out.println("tes");

        g.drawRect(100, 100, 100, 100);


        

    }


    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }


    // Gameloop
    static void gameloop() {
        // long then = System.nanoTime();
        // The gameloop runs until the program is closed
        while (true) {
            // Saves the current time
            long now = System.nanoTime();

            //update(graphics);
            repaint();

            // After the game has been updated, the program needs to wait until next
            // gameupdate. This waiting time is determined by the FPS variable.
            long sleeptime = SKIP_TICKS - (System.nanoTime() - now);
            if (sleeptime >= 0) {
                try {
                    Thread.sleep(sleeptime / 1000000);
                } catch (Exception e) {
                    System.out.println("Error: Can't sleep.");
                }
            }
            // If sleeptime < 0, the program is not updated fast enough
            else {
                System.out.println("We are running behind");
            }
        }
    }
}
