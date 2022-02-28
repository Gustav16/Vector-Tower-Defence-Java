import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public class GamePanel extends JPanel implements Runnable {

    static Graphics graphics;
    final static int FPS = 100;
    final static public long SKIP_TICKS = 1000000000 / FPS;
    int v = 0;

    GamePanel() {
        //Square.makeGrid();
        //run();
    }

    public void paint(Graphics g) {
        // Square.drawGrid(g);
        g.fillRect(v, 10, 100, 100);
        v=v+10;
        System.out.println("x");
    }

    // Gameloop
     public void run() {
        // long then = System.nanoTime();
        // The gameloop runs until the program is closed
        while (true) {
            // Saves the current time
            long now = System.nanoTime();

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
