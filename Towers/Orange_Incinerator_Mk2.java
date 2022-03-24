package Towers;

import Game.GamePanel;
import Game.Square;
import Game.Vectoid;
import java.awt.Color;

import java.awt.Graphics;
import Game.VectorTD;

public class Orange_Incinerator_Mk2 extends Tower {

    public static Orange_Incinerator_Mk2 towers[] = new Orange_Incinerator_Mk2[100];
    public static int price = 275;
    public static int count = 0;

    int timer = 0;
    int animationTime = 50;
    int attackspeed = 150;
    boolean shooting = false;
    int burnDamage = 100;

    public Orange_Incinerator_Mk2(int squareX, int squareY) {
        super(squareX, squareY);
        imagePath = "Images/Orange_Incinerator_Mk2.png";
        range = 175;
        damage = 250;

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
            g.setColor(new Color(199, 106, 0));
            g.drawOval(tempX, tempY, radiusIncrease * 2 * timer, radiusIncrease * 2 * timer);

            if (timer == animationTime) {

                for (int j = 0; j < 10; j++) {

                    if (Vectoid.listOfVectoids[j].dead == false && inRange(j) == true) {

                        if (Vectoid.listOfVectoids[target].type == "grass") {
                            Vectoid.listOfVectoids[target].takeDamage(damage * 1.5);
                        } else if (Vectoid.listOfVectoids[target].type == "ice") {
                            Vectoid.listOfVectoids[target].takeDamage(damage * 0.5);
                        } else {
                            Vectoid.listOfVectoids[target].takeDamage(damage);

                        }
                        if (Vectoid.listOfVectoids[j].dead == false) {

                            if (Vectoid.listOfVectoids[target].type == "grass") {
                                Vectoid.listOfVectoids[j].burn(4, burnDamage * 1.5);
                            } else if (Vectoid.listOfVectoids[target].type == "ice") {
                                Vectoid.listOfVectoids[j].burn(4, burnDamage * 0.5);
                            } else {
                                Vectoid.listOfVectoids[j].burn(4, burnDamage);

                            }

                        }

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
            towers[count] = new Orange_Incinerator_Mk2(x, y);
            GamePanel.followMouseImage = "non";
            Square.grid[x][y].isTowerPlacebel = false;
            GamePanel.selectTower = false;
            count++;

        }

    }

}