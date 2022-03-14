package Towers;

import Game.GamePanel;
import Game.Square;
import Game.Vectoid;

import java.awt.Graphics;
import java.awt.*;
import Game.VectorTD;
public class Green_Laser_Mk1 {

    public static String imagePath = "Images/Green_Laser_Mk1.png";
    public static int price = 10;
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

    public Green_Laser_Mk1(int squareX, int squareY) {
        x = Square.grid[squareX][squareY].x+Square.strokeWeigth;
        y = Square.grid[squareX][squareY].y+Square.strokeWeigth;
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
        if (Vectoid.listOfVectoids[target].dead == false && inRange(target) == true) {

            g.setColor(Color.green);
            g.drawLine(centerX, centerY,
                    Vectoid.listOfVectoids[target].x + Vectoid.radius / 2,
                    Vectoid.listOfVectoids[target].y + Vectoid.radius / 2);
            Vectoid.listOfVectoids[target].takeDamage(damage);

        } else {
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

    public static void buy(int x, int y){

        if (x < 15 && y < 15 && x >= 0 && y >= 0 && Square.grid[x][y].isTowerPlacebel == true && GamePanel.money>=price) {
                
               
            GamePanel.money -= price;
            VectorTD.frame.moneyLabel.setText("Money: " + GamePanel.money + "$");
            towers[count] = new Green_Laser_Mk1( x, y);
            GamePanel.followMouseImage="non";

            
            Square.grid[x][y].isTowerPlacebel=false;
            GamePanel.selectTower=false;
            Green_Laser_Mk1.count++;
      
          }




    }

}
