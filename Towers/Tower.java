package Towers;

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import javax.imageio.*;
import java.io.IOException;
import java.awt.image.*;



public class Tower {

    int x, y, timer, listOfTargets, price, NumberOftargets, animationTime;

    int damage, range, attackSpeed;
    String description;
    boolean showRange = false;
    BufferedImage img;

    Tower(int xPos, int yPos) {
        try {
            img = ImageIO.read(getClass().getResource("/Images/Green_laser_Mk1.png/"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.x = xPos;
        this.y = yPos;
    }

    void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    void buy() {

    }

}
