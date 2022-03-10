package Game;

import java.awt.*;

import javax.swing.*;

import Towers.Green_Laser_Mk1;
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

        Square.drawGrid(g);
        Vectoid.drawVectoids(g);
        Vectoid.spawnVectoids();
        Green_Laser_Mk1.drawAll(g);
        drawMouseTower(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawMouseTower(Graphics g) {
        if (selectTower == true) {
            g.drawImage(Toolkit.getDefaultToolkit().getImage(followMouseImage), mouseX, mouseY, null);
        }

    }

    public void mouseClicked(MouseEvent e) {
        mousePressedX=mouseX;
        mousePressedY=mouseY;
       
        if (selectTower==true){
            

            int x = (int) Math.floor(mouseX / Square.width);
            int y = (int) Math.floor(mouseY / Square.width);
            System.out.println(x);
            System.out.println(y);
        
            if (x < 15 && y < 15 && x >= 0 && y >= 0 && Square.grid[x][y].isTowerPlacebel == true && money>=Green_Laser_Mk1.price) {
               
              money -= Green_Laser_Mk1.price;
              VectorTD.frame.moneyLabel.setText("Money: " + GamePanel.money + "$");
             Green_Laser_Mk1.towers[Green_Laser_Mk1.count] = new Green_Laser_Mk1(Square.grid[x][y].x+Square.strokeWeigth, Square.grid[x][y].y+Square.strokeWeigth, x, y);

              
              Square.grid[x][y].isTowerPlacebel=false;
              selectTower=false;
              Green_Laser_Mk1.count++;
        
            }

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
