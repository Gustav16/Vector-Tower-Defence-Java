package Towers;

import Game.GamePanel;
import Game.Square;
import Game.Vectoid;


import java.awt.Graphics;
import java.awt.*;

public class Tower {

    
    String imagePath;
    int target;
    int damage;
    int range;
    int x, y, squareX, squareY, centerX, centerY;
    boolean showRange = false;

    public Tower(int squareX, int squareY) {
        x = Square.grid[squareX][squareY].x + Square.strokeWeigth;
        y = Square.grid[squareX][squareY].y + Square.strokeWeigth;
        this.squareX = squareX;
        this.squareY = squareY;
        centerX = x + Square.width / 2 - Square.strokeWeigth / 2;
        centerY = y + Square.width / 2 - Square.strokeWeigth / 2;
    }

    public void draw(Graphics g) {

        g.drawImage(Toolkit.getDefaultToolkit().getImage(imagePath), x, y, null);
        if (showRange == true) {
            g.setColor(Color.white);
            g.drawOval(centerX - range, centerY - range, range * 2, range * 2);
        }
    }

    

    boolean inRange(int target) {

        int a = centerX - (Vectoid.listOfVectoids[target].x + Vectoid.radius / 2);
        var b = centerY - (Vectoid.listOfVectoids[target].y + Vectoid.radius / 2);

        var c = Math.sqrt(a * a + b * b);

        if (c <= this.range) {

            return (true);
        } else {
            return (false);
        }

    }


 



    public void select() {
        if (GamePanel.mousePressedX >= x && GamePanel.mousePressedX < x + Square.width && GamePanel.mousePressedY >= y
                && GamePanel.mousePressedY < this.y + Square.width) {
            Square.grid[squareX][squareY].strokeColor = Color.white;
            this.showRange = true;
        } else {

            Square.grid[squareX][squareY].strokeColor = Color.black;
            this.showRange = false;
        }

    }

    public static final void drawAllTowers(Graphics g) {

        for (int i = 0; i < Green_Laser_Mk1.count; i++) {
            Green_Laser_Mk1.towers[i].draw(g);
        }

        for (int i = 0; i < Purple_Power_Mk1.count; i++) {
            Purple_Power_Mk1.towers[i].draw(g);
        }

        for (int i = 0; i < Orange_Incinerator_Mk1.count; i++) {
            Orange_Incinerator_Mk1.towers[i].draw(g);
        }

        for (int i = 0; i < Green_Laser_Mk2.count; i++) {
            Green_Laser_Mk2.towers[i].draw(g);
        }

        for (int i = 0; i < Purple_Power_Mk2.count; i++) {
            Purple_Power_Mk2.towers[i].draw(g);
        }

        for (int i = 0; i < Orange_Incinerator_Mk2.count; i++) {
            Orange_Incinerator_Mk2.towers[i].draw(g);
        }

    }

    public static final void shootAllTowers(Graphics g) {

        for (int i = 0; i < Green_Laser_Mk1.count; i++) {
            Green_Laser_Mk1.towers[i].shoot(g);

        }

        for (int i = 0; i < Purple_Power_Mk1.count; i++) {
            Purple_Power_Mk1.towers[i].shoot(g);

        }

        for (int i = 0; i < Orange_Incinerator_Mk1.count; i++) {
            Orange_Incinerator_Mk1.towers[i].shoot(g);

        }
        
        for (int i = 0; i < Green_Laser_Mk2.count; i++) {
            Green_Laser_Mk2.towers[i].shoot(g);

        }
        for (int i = 0; i < Purple_Power_Mk2.count; i++) {
            Purple_Power_Mk2.towers[i].shoot(g);

        }

        for (int i = 0; i < Orange_Incinerator_Mk2.count; i++) {
            Orange_Incinerator_Mk2.towers[i].shoot(g);

        }

    }

    public static final void selectAllTowers() {

        for (int i = 0; i < Green_Laser_Mk1.count; i++) {
            Green_Laser_Mk1.towers[i].select();

        }

        for (int i = 0; i < Purple_Power_Mk1.count; i++) {
           Purple_Power_Mk1.towers[i].select();

        }

        for (int i = 0; i < Orange_Incinerator_Mk1.count; i++) {
            Orange_Incinerator_Mk1.towers[i].select();
 
         }

         for (int i = 0; i < Green_Laser_Mk2.count; i++) {
            Green_Laser_Mk2.towers[i].select();

        }
         for (int i = 0; i < Purple_Power_Mk2.count; i++) {
            Purple_Power_Mk2.towers[i].select();
 
         }

         for (int i = 0; i < Orange_Incinerator_Mk2.count; i++) {
            Orange_Incinerator_Mk2.towers[i].select();
 
         }

    }

}
