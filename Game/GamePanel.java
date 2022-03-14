package Game;

import java.awt.*;

import javax.swing.*;

import Towers.Green_Laser_Mk1;
import Towers.Purple_Power_Mk1;
import Towers.Tower;
import java.awt.Graphics;

import java.awt.event.*;

public class GamePanel extends JPanel implements Runnable, MouseMotionListener, MouseListener {
   
   
    static Graphics graphics;
    final static int FPS = 100;
    final static public long SKIP_TICKS = 1000000000 / FPS;
    static public int lives = 25;
    static public int money = 10000;
    static public int round = 0;
    static public int interest = 3;
    static public String whatTower = "non";

    public static int mouseX, mouseY, mousePressedX, mousePressedY;

    static public boolean roundStart = false;

    
    static public boolean selectTower = false;
    static public String followMouseImage;

    

   

    GamePanel() {
        addMouseListener(this);  
        addMouseMotionListener(this);
        new Thread(this).start();
        Square.makeGrid();
        Vectoid.makeVectoids();
        System.out.println(Tower.class);
    }

    public void paint(Graphics g) {
        Image image = createImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

    }

    public void draw(Graphics g) {
        Vectoid.spawnVectoids();

        Square.drawGrid(g);
       
        
        Purple_Power_Mk1.drawAll(g);
        Purple_Power_Mk1.shootAll(g);
        Vectoid.drawVectoids(g);
        drawMouseTower(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawMouseTower(Graphics g) {
        if (followMouseImage != "non") {
            g.drawImage(Toolkit.getDefaultToolkit().getImage(followMouseImage), mouseX, mouseY, null);
        }

    }

    public void mouseClicked(MouseEvent e) {
        mousePressedX=mouseX;
        mousePressedY=mouseY;
        Purple_Power_Mk1.selectAll();
        Green_Laser_Mk1.selectAll();

        int x = (int) Math.floor(mouseX / Square.width);
        int y = (int) Math.floor(mouseY / Square.width);

        if (followMouseImage=="Images/Green_Laser_Mk1.png"){
            
            Green_Laser_Mk1.buy(x,y);
            

            
        } else if (followMouseImage=="Images/Purple_Power_Mk1.png"){
            Purple_Power_Mk1.buy(x, y);


        }

    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

    }



    public void mouseDragged(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {

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
