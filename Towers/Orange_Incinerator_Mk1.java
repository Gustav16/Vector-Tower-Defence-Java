package Towers;

import Game.GamePanel;
import Game.Square;
import Game.Vectoid;
import java.awt.Color;

import java.awt.Graphics;
import Game.VectorTD;

public class Orange_Incinerator_Mk1 extends Tower {

    public static Orange_Incinerator_Mk1 towers[] = new Orange_Incinerator_Mk1[100];
    public static int price = 10;
    public static int count = 0;

    int timer = 0;
    int animationTime = 50;
    int attackspeed = 200;
    boolean shooting = false;

    public Orange_Incinerator_Mk1(int squareX, int squareY) {
        super(squareX, squareY);
        imagePath = "Images/Orange_Incinerator_Mk1.png";
        range = 100;
        damage = 50;

    }

    public void shoot(Graphics g) {

        if (Vectoid.listOfVectoids[target].dead == false && inRange(target) == true && timer >= attackspeed
                && shooting == false) {
            shooting = true;
            timer = 0;
        } else if (timer >= attackspeed && shooting == false) {
            pickTarget();

        } else if (shooting == false) {
            timer++;
        }

        if (shooting == true) {
            timer++;

            int radiusIncrease = range / animationTime;

            int tempX = centerX - (radiusIncrease) * timer;
            int tempY = centerY - (radiusIncrease) * timer;
            g.setColor(new Color(199,106,0));
            g.drawOval(tempX, tempY, radiusIncrease * 2 * timer, radiusIncrease * 2 * timer);

            if (timer == animationTime) {

                
                  for (int j = 0; j < 10; j++) {
                  
                  if (Vectoid.listOfVectoids[j].dead == false && inRange(j) == true)
                  {
                  
                  
                  Vectoid.listOfVectoids[j].takeDamage(damage);
                  Vectoid.listOfVectoids[j].burn(4, 25);
                  
                  }
                  
                  }
                 
                timer = 0;
                shooting = false;
            }

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

    public static void buy(int x, int y) {

        if (x < 15 && y < 15 && x >= 0 && y >= 0 && Square.grid[x][y].isTowerPlacebel == true
                && GamePanel.money >= price) {

            GamePanel.money -= price;
            VectorTD.frame.moneyLabel.setText("Money: " + GamePanel.money + "$");
            towers[count] = new Orange_Incinerator_Mk1(x, y);
            GamePanel.followMouseImage = "non";
            Square.grid[x][y].isTowerPlacebel = false;
            GamePanel.selectTower = false;
            count++;

        }

    }

}