package Towers;

import Game.GamePanel;
import Game.Square;
import Game.Vectoid;
import java.awt.Color;

import java.awt.Graphics;
import Game.VectorTD;

public class Orange_Incinerator_Mk1 extends Tower {

    public static Orange_Incinerator_Mk1 towers[] = new Orange_Incinerator_Mk1[100];
    public static int price = 200;
    public static int count = 0;

    int timer = 0;
    int animationTime = 50;
    int attackspeed = 200;
    boolean shooting = false;
    int burnDamage = 50;

    public Orange_Incinerator_Mk1(int squareX, int squareY) {
        super(squareX, squareY);
        imagePath = "Images/Orange_Incinerator_Mk1.png";
        range = 100;
        damage = 100;

    }

    // shoot functionen
    public void shoot(Graphics g) {

        // Først tjekker vi om der er et mål som ikke er dødt inde for rækkevidden og
        // der er ladet et skud
        if (Vectoid.listOfVectoids[target].dead == false && inRange(target) == true && timer >= attackspeed
                && shooting == false) {
            shooting = true;
            timer = 0;

            // Ellers prøver vi et vælge et mål, hvis der er ladet et skud
        } else if (timer >= attackspeed && shooting == false) {
            pickTarget();

            // Ellers lader vi
        } else if (shooting == false) {
            timer++;
        }

        // Når skuddet er laddet skydder vi
        if (shooting == true) {
            timer++;

            // Udregner kordinaterne til den orange cirkel
            int radiusIncrease = range / animationTime;
            int tempX = centerX - (radiusIncrease) * timer;
            int tempY = centerY - (radiusIncrease) * timer;

            // Tegner den orange cirkel
            g.setColor(new Color(199, 106, 0));
            g.drawOval(tempX, tempY, radiusIncrease * 2 * timer, radiusIncrease * 2 * timer);

            // Hivs den orange cirkels radius er lig rækkevidden så giver vi skade til alle
            // Vectoids inde for rækkevidden
            if (timer == animationTime) {

                // Rammer alle mål inden for rækkevidden
                for (int j = 0; j < Vectoid.currentNumberOfVectoids; j++) {

                    if (Vectoid.listOfVectoids[j].dead == false && inRange(j) == true) {

                        if (Vectoid.listOfVectoids[j].type == "grass") {
                            Vectoid.listOfVectoids[j].takeDamage(damage * 1.5);
                        } else if (Vectoid.listOfVectoids[j].type == "ice") {
                            Vectoid.listOfVectoids[j].takeDamage(damage * 0.5);
                        } else {
                            Vectoid.listOfVectoids[j].takeDamage(damage);

                        }
                        // Brænder alle inden for rækkevidden

                        if (Vectoid.listOfVectoids[j].dead == false) {

                            if (Vectoid.listOfVectoids[j].type == "grass") {
                                Vectoid.listOfVectoids[j].burn(4, burnDamage * 1.5);
                            } else if (Vectoid.listOfVectoids[j].type == "ice") {
                                Vectoid.listOfVectoids[j].burn(4, burnDamage * 0.5);
                            } else {
                                Vectoid.listOfVectoids[j].burn(4, burnDamage);

                            }

                        }

                    }

                }

                // Når vi er færdig med at skyde, skal der lades et ny skyd

                timer = 0;
                shooting = false;
            }

        }

    }

    // Funktion der finder den først Vectoid som er inde for rækkevidden og som ikke
    // er død
    public void pickTarget() {
        for (int j = 0; j < Vectoid.currentNumberOfVectoids; j++) {
            if (Vectoid.listOfVectoids[j].dead == false && inRange(j) == true) {
                target = j;
                break;
            }

        }

    }

    // Køber et tårn hvis man har råd og det er inde for banen.
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