package Game;

import java.awt.*;

import javax.swing.*;

//Importe alle tårnene
import Towers.Green_Laser_Mk1;
import Towers.Green_Laser_Mk2;
import Towers.Purple_Power_Mk1;
import Towers.Purple_Power_Mk2;
import Towers.Orange_Incinerator_Mk1;
import Towers.Orange_Incinerator_Mk2;
import Towers.Tower;

//Importe graphic
import java.awt.Graphics;

//Pakke der lave mouse movement
import java.awt.event.*;

//Den klasse som tegner alle elemnter i CENTER region ved hjælp af Graphics pakke
public class GamePanel extends JPanel implements Runnable, MouseMotionListener, MouseListener {

    static Graphics graphics;
    //Varible som som bruges til at lave en bestem framrate
    final static int FPS = 100;
    final static public long SKIP_TICKS = 1000000000 / FPS;

    static public int lives = 25;
    static public int money = 10000;
    static public int round = 0;
    static public int interest = 3;

    
 
    //varible som beksriver musen position, og musen position hver gang man trykker
    public static int mouseX, mouseY, mousePressedX, mousePressedY;

    static public boolean roundStart = false;

    //Varible som holder styr køb af tårne
    static public boolean selectTower = false;
    static public String followMouseImage;

    //constructer i GamePanel som intalizere forskellige elementer
    GamePanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        new Thread(this).start();
        Square.makeGrid();
        Vectoid.makeVectoids();
    }
   //Function man skal før man kan tegne, gør så de gamle frames bliver slettet.
    public void paint(Graphics g) {
        Image image = createImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

    }
//den function som bliver opdateret hver frame. Alle grafiske elemnter er inde i denen function
    public void draw(Graphics g) {

        //Tjekker om der skal spawnes Vectoids
        Vectoid.spawnVectoids();

        //tegner selveste banen
        Square.drawGrid(g);
        
        //Tegner alle tårne
        Tower.drawAllTowers(g);

        //Tegner alle Vectoids
        Vectoid.drawVectoids(g);

        //Gør så alle tårne skyder
        Tower.shootAllTowers(g);
        
        //Gør så det tårn man har valgt følger med musen
        drawMouseTower(g);


        //Gør animationerne mere smooth
        Toolkit.getDefaultToolkit().sync();
    }

    //functionen som tegner det tower som følger musen
    private void drawMouseTower(Graphics g) {
        if (followMouseImage != "non") {
            g.drawImage(Toolkit.getDefaultToolkit().getImage(followMouseImage), mouseX, mouseY, null);
        }

    }

    // function som bliver kaldt hver gang man trykker på musen
    public void mouseClicked(MouseEvent e) {

        //updatere de varible som holder styr på musen position efter man har trykket
        mousePressedX = e.getX();
        mousePressedY = e.getY();

        //Function der gør at man kan klikke på tårnene
        Tower.selectAllTowers();


        //Finder den firkant der passer til musen kordinater
        int x = (int) Math.floor(mousePressedX / Square.width);
        int y = (int) Math.floor(mousePressedY / Square.width);


        //tjekke hvilket tårn der skal købes
        if (followMouseImage == "Images/Green_Laser_Mk1.png") {

            Green_Laser_Mk1.buy(x, y);

        } else if (followMouseImage == "Images/Purple_Power_Mk1.png") {

            Purple_Power_Mk1.buy(x, y);

        } else if (followMouseImage == "Images/Orange_Incinerator_Mk1.png") {

            Orange_Incinerator_Mk1.buy(x, y);

        } else if (followMouseImage == "Images/Green_Laser_Mk2.png") {
            Green_Laser_Mk2.buy(x, y);

        } else if (followMouseImage == "Images/Purple_Power_Mk2.png") {

            Purple_Power_Mk2.buy(x, y);

        } else if (followMouseImage == "Images/Orange_Incinerator_Mk2.png") {

            Orange_Incinerator_Mk2.buy(x, y);
        }
    }

    //Opdatere de varible som holder styr på musen nuværende position
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

    }
   
    //En masse functioner man skal have med, men som jeg ikke bruger til noget
    public void mouseDragged(MouseEvent e) {    }
    public void mousePressed(MouseEvent e) {    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseReleased(MouseEvent e) {    }
    public void mouseExited(MouseEvent e) {    }

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
