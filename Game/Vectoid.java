package Game;

import java.awt.*;
import java.awt.Color;

public class Vectoid {



    public static Vectoid listOfVectoids[] = new Vectoid[10];
    public static int radius = 50;

    static int maxNumberOfVectoids = 10;
    static int currentNumberOfVectoids = 0;
    static int countDead = 0;

    float distance = 0;
    int slowTime = 0;
    float slowSpeed = 0;
    int health = 1000;


    public int x = Square.vectoidRoute[Math.round(distance)][0];
    public int y = Square.vectoidRoute[Math.round(distance)][1];

    float speed = 1;
    Color bodyColor = new Color(255, 0, 0);
    public boolean dead = true;
    static long timer;

    int burnTimeRemaining = 0;
    int burnDamage;
    int burnTicks = 0;

  




    public void draw(Graphics g) {
        x = Square.vectoidRoute[Math.round(distance)][0];
        y = Square.vectoidRoute[Math.round(distance)][1];
        g.setColor(bodyColor);
        g.fillOval(x, y, radius, radius);

        if (burnTimeRemaining != 0){
            burnTicks++;
            if (burnTicks==100){
                burnTimeRemaining--;
                takeDamage(burnDamage);

            }
        }
    }

    public void move() {
        distance += speed;
        outOfMap();
    }

    private void outOfMap() {
        if (distance >= Square.vectoidRoute.length) {
            dead = true;
            GamePanel.lives -= 1;
            countDead++;
            VectorTD.frame.livesLabel.setText("Lives: " + GamePanel.lives);
            if (countDead == maxNumberOfVectoids) {
                GamePanel.roundStart = false;

            }

        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0 && dead==false) {
            dead = true;
            GamePanel.money += 10;
            countDead++;
            
            VectorTD.frame.moneyLabel.setText("Money: " + GamePanel.money + "$");
            if (countDead == maxNumberOfVectoids) {
                GamePanel.roundStart = false;

            }

        }
    }

    public void burn(int seconds, int damagePerTick){
        burnTimeRemaining = seconds;
        burnDamage = damagePerTick;




    }

    public static void drawVectoids(Graphics g) {

        if (GamePanel.roundStart == true) {
            for (int i = 0; i < Vectoid.currentNumberOfVectoids; i++) {
                if (listOfVectoids[i].dead == false) {
                    listOfVectoids[i].draw(g);
                    listOfVectoids[i].move();
                }
            }
        }

    }

    public static void makeVectoids() {
        for (int i = 0; i < maxNumberOfVectoids; i++) {
            listOfVectoids[i] = new Vectoid();
        }
    }

    public static void spawnVectoids() {
        if ((currentNumberOfVectoids < maxNumberOfVectoids) && (System.nanoTime() - timer) > 300000000
                && GamePanel.roundStart == true) {
            listOfVectoids[currentNumberOfVectoids].dead = false;
            listOfVectoids[currentNumberOfVectoids].health = 100;
            listOfVectoids[currentNumberOfVectoids].distance = 0;
            currentNumberOfVectoids++;
            timer = System.nanoTime();

        }

    }

    public static void newRound() {
        if (GamePanel.roundStart == false) {
            GamePanel.roundStart = true;
            currentNumberOfVectoids = 0;
            countDead = 0;
            GamePanel.round++;
            VectorTD.frame.roundLabel.setText("Round: " + GamePanel.round);

            timer = System.nanoTime();
        }
    }
}