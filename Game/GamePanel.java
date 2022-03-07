package Game;

import java.awt.*;
import javax.swing.*;
import Towers.Tower;
import java.awt.Graphics;
import java.applet.*;
import java.util.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements Runnable, MouseMotionListener {

    static Graphics graphics;
    final static int FPS = 100;
    final static public long SKIP_TICKS = 1000000000 / FPS;

    static public int lives = 25;
    static public int money = 100;
    static public int round = 0;
    static public int interest = 3;
    static public boolean selectTower = false;
    static public Image followMouseImage;

    static int mouseX, mouseY;

    static public boolean roundStart = false;

    GamePanel() {
        addMouseMotionListener(this);
        new Thread(this).start();
        Square.makeGrid();
        Vectoid.makeVectoids();
        System.out.println(Tower.class);

    }
    /*
     * Toolkit t=Toolkit.getDefaultToolkit();
     * Image i=t.getImage("p3.gif");
     * g.drawImage(i, 120,100,this);
     */

    public void paint(Graphics g) {
        Image image = createImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

    }

    public void draw(Graphics g) {

        Square.drawGrid(g);
        Vectoid.drawVectoids(g);
        Vectoid.spawnVectoids();
        drawMouseTower(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawMouseTower(Graphics g) {
        if (selectTower == true) {

            g.setColor(Color.red);
            g.fillRect(mouseX, mouseY, 50, 50);
        }

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

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
