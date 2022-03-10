package Towers;

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import javax.imageio.*;
import java.io.IOException;
import java.awt.image.*;
import Game.GamePanel;

public class Tower {

    int x, y, timer, listOfTargets, price, NumberOftargets, animationTime;

    int damage, range, attackSpeed;
    String description;
    boolean showRange = false;
    static public BufferedImage img;

    Tower(int xPos, int yPos) {
        try {
            img = ImageIO.read(getClass().getResource("/Images/Green_laser_Mk1.png/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = xPos;
        this.y = yPos;
    }

    public void draw(Graphics g) {
        //g.drawImage(img, x, y, null);
        g.setColor(Color.red);
        g.fillRect(GamePanel.mouseX - 25, GamePanel.mouseY - 25, 50, 50);
    }

    void infobox() {

    }

    void buy() {
        if (GamePanel.selectTower == false) {

        }

    }

    public final static void drawTowers(Graphics g) {
 

    }

    public final static void drawMouseTower(Graphics g) {
        if (GamePanel.selectTower == true) {
            g.setColor(Color.red);
            g.fillRect(GamePanel.mouseX - 25, GamePanel.mouseY - 25, 50, 50);
        }
    }

}
