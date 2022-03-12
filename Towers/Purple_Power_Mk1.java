package Towers;

import Game.GamePanel;
import Game.Square;
import Game.ThickLine;
import Game.Vectoid;
import java.awt.Graphics;
import java.awt.*;


import java.awt.Color;



public class Purple_Power_Mk1 {

    public static String imagePath = "Images/Puple_Power_Mk1.png";
    public static int price = 10;
    public static Purple_Power_Mk1 towers[] = new Purple_Power_Mk1[100];
    public static int count = 0;

    int target;
    int damage = 35;
    int range = 200;
    int timer = 0;
    int targets = 1;
    int x, y, squareX, squareY;
    boolean showRange = false;
    int centerX;
    int centerY;

    public Purple_Power_Mk1(int squareX, int squareY) {
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

    public void shoot(Graphics g) {

        if (Vectoid.listOfVectoids[target].dead) {
            timer++;

            int thickness = (int) Math.round(timer/10)+1;

            g.setColor(Color.pink);
            ThickLine.draw(g, centerX, centerY, Vectoid.listOfVectoids[target].x + Vectoid.radius / 2, Vectoid.listOfVectoids[target].x + Vectoid.radius / 2, thickness, Color.pink);

            if (timer == 100) {
                timer=0;
                Vectoid.listOfVectoids[target].takeDamage(damage);
                pickTarget();
            }

        } else {
            timer = 0;
            pickTarget();
        }

    }

    public void pickTarget() {
        for (int j = 0; j < 10; j++) {
            if (Vectoid.listOfVectoids[j].dead == false && inRange(j) == true) {
                target = j;
                break;
            }

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
            showRange = true;
        } else {

            Square.grid[squareX][squareY].strokeColor = Color.black;
            showRange = false;
        }

    }

}
