package Towers;

import Game.GamePanel;
import Game.Square;
import Game.Vectoid;

import java.awt.Graphics;
import java.awt.*;

public class Tower {

    public static String imagePath ;
    public static int price;
    public static Green_Laser_Mk1 towers[] = new Green_Laser_Mk1[100];
    public static int count = 0;

    int target;
    int damage = 1;
    int range = 200;
    int targets = 1;
    int x, y, squareX, squareY;
    boolean showRange = false;
    int centerX;
    int centerY;

    public Tower(int squareX, int squareY) {
        x = Square.grid[squareX][squareY].x + Square.strokeWeigth;
        y = Square.grid[squareX][squareY].y + Square.strokeWeigth;
        this.squareX = squareX;
        this.squareY = squareY;
        centerX = x + Square.width / 2 - Square.strokeWeigth / 2;
        centerY = y + Square.width / 2 - Square.strokeWeigth / 2;
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


    public static void drawAll(Graphics g) {
        for (int i = 0; i < count; i++) {
            towers[i].draw(g);
        }
    }

    public static void shootAll(Graphics g) {

        for (int i = 0; i < count; i++) {
            towers[i].shoot(g);

        }

    }

    public static void selectAll() {

        for (int i = 0; i < Green_Laser_Mk1.count; i++) {
            Green_Laser_Mk1.towers[i].select();

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
        Purple_Power_Mk1.shootAll(g);
        Green_Laser_Mk1.shootAll(g);
    }

    public static final void shootAllTowers(Graphics g) {
        Purple_Power_Mk1.drawAll(g);
        Green_Laser_Mk1.drawAll(g);

    }

    public static final void selectAllTowers() {
        Purple_Power_Mk1.selectAll();
        Green_Laser_Mk1.selectAll();

    }

}
