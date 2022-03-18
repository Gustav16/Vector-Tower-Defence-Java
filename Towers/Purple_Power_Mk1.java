package Towers;

import Game.GamePanel;
import Game.Square;

import Game.Vectoid;
import Game.VectorTD;

import java.awt.Graphics;
import java.awt.*;

import java.awt.Color;

public class Purple_Power_Mk1 extends Tower{


    public static Purple_Power_Mk1 towers[] = new Purple_Power_Mk1[100];

    int timer = 0;
    int attackspeed = 100;
    boolean shooting = false;


    public Purple_Power_Mk1(int squareX, int squareY) {
        super(squareX, squareY);
        imagePath = "Images/Purple_Power_Mk1.png";
        range = 250;
        price = 40;
        damage = 35;
  

    }



    public void shoot(Graphics g) {

        if (shooting == true) {
            if (Vectoid.listOfVectoids[target].dead == false) {
                timer++;
                g.setColor(new Color(255, 0, 220));
                g.drawLine(centerX, centerY, Vectoid.listOfVectoids[target].x + Vectoid.radius / 2,
                        Vectoid.listOfVectoids[target].y + Vectoid.radius / 2);

                if (timer == 10) {
                    timer = 0;
                    Vectoid.listOfVectoids[target].takeDamage(damage);
                    shooting = false;
                }
            } else {
                shooting = false;
                timer = attackspeed;
            }
        }

        if (timer >= attackspeed && shooting == false) {
            pickTarget();

            if (inRange(target) == true) {
                shooting = true;
                timer = 0;
            }

        } else if (shooting == false) {
            timer++;
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
            towers[count] = new Purple_Power_Mk1(x, y);
            GamePanel.followMouseImage = "non";

            Square.grid[x][y].isTowerPlacebel = false;
            GamePanel.selectTower = false;
            Purple_Power_Mk1.count++;

        }

    }



}
