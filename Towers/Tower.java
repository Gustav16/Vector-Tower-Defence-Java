package Towers;

import java.awt.Graphics;

import java.awt.image.*;
import Game.GamePanel;

public class Tower {

    int x, y, timer, listOfTargets, price, NumberOftargets, animationTime;

    int damage, range, attackSpeed;
    String description;
    boolean showRange = false;
    static public BufferedImage img;

    Tower(int xPos, int yPos, String towerClass) {

        this.x = xPos;
        this.y = yPos;
    }






    void infobox(){


    }

      void buy() {
        if (GamePanel.selectTower == true) {
            

        }

    }

}
