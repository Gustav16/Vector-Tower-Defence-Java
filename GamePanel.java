import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;

public class GamePanel extends JPanel implements Runnable {

    static Graphics graphics;
    final static int FPS = 100;
    final static public long SKIP_TICKS = 1000000000 / FPS;

    static public int lives = 25;
    static public int money = 100;
    static public boolean roundStart = false;

    GamePanel() {
        new Thread(this).start();
        Square.makeGrid();
        Vectoid.makeVectoids();
    }
    

    public void paint(Graphics g) {
        Image image = createImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

    }

    public void draw(Graphics g) {
        Square.drawGrid(g);
        Vectoid.drawVectoids(g);

        Toolkit.getDefaultToolkit().sync();
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
