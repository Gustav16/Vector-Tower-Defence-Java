package Towers;

import Game.GamePanel;
import Game.Square;
import Game.Vectoid;

import java.awt.Graphics;
import java.awt.*;

public class Green_Laser_Mk1 {

    static public String imagePath = "Images/Green_Laser_Mk1.png";
    public static int targetList[] = new int[10];
    static public Green_Laser_Mk1 towers[] = new Green_Laser_Mk1[100];

    int damage = 1;
    public static int price = 10;
    int range = 100;
    int timer = 0;
    int targets = 1;
    int x, y, squareX, squareY;
    public static int count = 0;

    public Green_Laser_Mk1(int xPos, int yPos, int squareX, int squareY) {
        x = xPos;
        y = yPos;
        this.squareX = squareX;
        this.squareY = squareY;
    }

    public void draw(Graphics g) {
        g.drawImage(Toolkit.getDefaultToolkit().getImage(imagePath), x, y, null);

    }

    public void shoot(Graphics g) {

        for (int j = 0; j < 10; j++) {
            if (Vectoid.listOfVectoids[targetList[j]].dead == false && inRange(targetList[j]) == true) {
                g.setColor(Color.green);
                g.drawLine(x, y, Vectoid.listOfVectoids[targetList[j]].x, Vectoid.listOfVectoids[targetList[j]].y);
                Vectoid.listOfVectoids[targetList[j]].takeDamage(damage);
                break;
            }
        }

    }

    public void pickTarget() {
        for (int j = 0; j < 10; j++) {
            targetList[j] = j;

        }

    }

    boolean inRange(int target) {

        int a = Vectoid.listOfVectoids[target].x;
        var b = Vectoid.listOfVectoids[target].y;

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
            towers[i].select();
        }
    }

    public void select() {

        if (GamePanel.mousePressedX >= x && GamePanel.mousePressedX < x + Square.width && GamePanel.mousePressedY >= y
                && GamePanel.mousePressedY < this.y + Square.width) {
             Square.grid[squareX][squareY].strokeColor = Color.white;
            //System.out.println("test");
        } else {

            Square.grid[squareX][squareY].strokeColor = Color.black;
        }

    }

}
