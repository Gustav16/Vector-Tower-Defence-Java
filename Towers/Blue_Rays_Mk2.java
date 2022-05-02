package Towers;

import Game.GamePanel;
import Game.Square;
import Game.Vectoid;

//Importere pakker der burges til at tegne med
import java.awt.Graphics;
import java.awt.*;

import Game.VectorTD;

public class Blue_Rays_Mk2 extends Tower {

    // arraliste over alle tårne af denne type
    public static Blue_Rays_Mk2 towers[] = new Blue_Rays_Mk2[100];

    // hvor mange der er
    public static int count = 0;
    static int price = 1000;
    static int maxTargets = 5;
    int timer = 0;
    int attackspeed = 75;
    boolean shooting = false;

    public int[] targets = new int[maxTargets];

    public Blue_Rays_Mk2(int squareX, int squareY) {
        super(squareX, squareY);
        imagePath = "Images/Blue_Rays_Mk2.png";
        range = 300;
        damage = 1000;

    }

    // Funktionen som skyder Vectoids
    public void shoot(Graphics g) {

        //

        // Først tjekker vi om vi er i gang med at skyde
        if (shooting == true) {
            // Nu tjekker vi om det mål vi er igang med at skyder ikke er dødt

            timer++;
            for (int whichTarget = 0; whichTarget < target; whichTarget++) {
                // System.out.println(targets[whichTarget]);

                if (Vectoid.listOfVectoids[targets[whichTarget]].dead == false) {

                    // Tegne streg mellem målet og tårn
                    g.setColor(new Color(49, 185, 230));
                    g.drawLine(centerX, centerY, Vectoid.listOfVectoids[targets[whichTarget]].x + Vectoid.radius / 2,
                            Vectoid.listOfVectoids[targets[whichTarget]].y + Vectoid.radius / 2);

                    // Giver skade til målet
                    if (timer == 10) {

                        if (Vectoid.listOfVectoids[targets[whichTarget]].type == "fire") {
                            Vectoid.listOfVectoids[targets[whichTarget]].takeDamage(damage * 1.5);
                        } else if (Vectoid.listOfVectoids[targets[whichTarget]].type == "void") {
                            Vectoid.listOfVectoids[targets[whichTarget]].takeDamage(damage * 0.5);
                        } else {
                            Vectoid.listOfVectoids[targets[whichTarget]].takeDamage(damage);

                        }

                    }

                }
            }
            // Gør så der skal lades igen
            if (timer == 10) {

                timer = 0;
                shooting = false;
            }
        }

        // Hvis skuddet er ladet og vi ikke er i gang med at skyde vælger vi et mål
        if (timer >= attackspeed && shooting == false) {
            pickTarget();

            // Hvis vi finder et mål inde for rækkevidden går vi i gang med at skyde det
            if (inRange(targets[0]) == true) {
                shooting = true;
                timer = 0;
            }
            // Ellers lader vi
        } else if (shooting == false) {
            timer++;
        }

    }

    // Funktion der finder den først Vectoid som er inde for rækkevidden og som ikke
    // er død
    public void pickTarget() {

        target = 0;
        // Her har vi en for-løkke som kører arraylisten med Vectoids igennem.
        for (int j = 0; j < Vectoid.currentNumberOfVectoids; j++) {
            // Hvis Vectoiden ikke er død og den er inde for rækkevidden har en et validt
            // mål
            if (Vectoid.listOfVectoids[j].dead == false && inRange(j) == true) {
                // Når vi har fundet et validt mål sætter vi target til målet og breaker
                // herefter

                targets[target] = j;
                target++;
                if (target == maxTargets) {
                    break;
                }
            }
        }
    }

    // Køber et tårn hvis man har råd og det er inde for banen.
    public static void buy(int x, int y) {

        if (x < 15 && y < 15 && x >= 0 && y >= 0 && Square.grid[x][y].isTowerPlacebel == true
                && GamePanel.money >= price) {

            GamePanel.money -= price;
            VectorTD.frame.moneyLabel.setText("Money: " + GamePanel.money + "$");
            towers[count] = new Blue_Rays_Mk2(x, y);
            GamePanel.followMouseImage = "non";
            Square.grid[x][y].isTowerPlacebel = false;
            GamePanel.selectTower = false;
            count++;

        }

    }

}
