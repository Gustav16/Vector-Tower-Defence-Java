package Game;

import java.awt.*;
import java.awt.Color;

public class Vectoid {

    public static Vectoid listOfVectoids[] = new Vectoid[10];
    public static int radius = 50;
    public static int maxHealth = 550;

    public static String typeList[] = { "grass", "fire", "ice", "void", "netural" };
    static int waveType = -1;

    static int maxNumberOfVectoids = 10;
    static int currentNumberOfVectoids = 0;
    static int countDead = 0;
    static long timer;

    float distance = 0;
    int slowTime = 0;
    float slowSpeed = 0;
    int currentHealth;
    String type;

    public int x = Square.vectoidRoute[Math.round(distance)][0];
    public int y = Square.vectoidRoute[Math.round(distance)][1];

    float speed = 1;
    Color bodyColor = new Color(255, 0, 0);
    public boolean dead = true;

    int burnTimeRemaining = 0;
    int burnDamage = 0;
    int burnTicks = 0;

    public void draw(Graphics g) {

        if (burnTimeRemaining != 0) {

            burnTicks++;
            if (burnTicks == 100) {
                burnTimeRemaining--;
                takeDamage(burnDamage);
                burnTicks = 0;
            }

        }

        if (maxHealth != currentHealth) {

            g.setColor(Color.green);
            int healthBarWidth = Math.round((currentHealth * 40) / maxHealth);

            g.fillRect(2 + x, y, healthBarWidth, 5);

        }

        x = Square.vectoidRoute[Math.round(distance)][0];
        y = Square.vectoidRoute[Math.round(distance)][1];

        int[] pentagonX = { 10 + 2 + x, 4 + 2 + x, 20 + 2 + x, 36 + 2 + x, 30 + 2 + x };
        int[] pentagonY = { 40 + y, 21 + y, 9 + y, 21 + y, 40 + y };
        g.setColor(bodyColor);
        // g.fillOval(x, y, radius, radius);
        g.fillPolygon(pentagonX, pentagonY, 5);
        g.setColor(Color.black);
        g.drawPolygon(pentagonX, pentagonY, 5);

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
        currentHealth -= damage;

        if (currentHealth <= 0 && dead == false) {
            dead = true;
            GamePanel.money += 10;
            countDead++;
            burnTimeRemaining = 0;
            burnTicks = 0;

            VectorTD.frame.moneyLabel.setText("Money: " + GamePanel.money + "$");
            if (countDead == maxNumberOfVectoids) {
                GamePanel.roundStart = false;

            }

        }
    }

    public void burn(int seconds, int dps) {
        burnTimeRemaining = seconds;
        burnDamage = dps;

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
        if ((currentNumberOfVectoids < maxNumberOfVectoids) && (System.nanoTime() - timer) > 450000000
                && GamePanel.roundStart == true) {
            listOfVectoids[currentNumberOfVectoids].dead = false;
            listOfVectoids[currentNumberOfVectoids].currentHealth = maxHealth;
            listOfVectoids[currentNumberOfVectoids].distance = 0;
            listOfVectoids[currentNumberOfVectoids].type = typeList[waveType];

            if (listOfVectoids[currentNumberOfVectoids].type == "grass") {
                listOfVectoids[currentNumberOfVectoids].bodyColor = new Color(0, 255, 33);

            } else if (listOfVectoids[currentNumberOfVectoids].type == "fire") {
                listOfVectoids[currentNumberOfVectoids].bodyColor = new Color(255, 136, 0);

            } else if (listOfVectoids[currentNumberOfVectoids].type == "ice") {
                listOfVectoids[currentNumberOfVectoids].bodyColor = new Color(49, 185, 230);

            } else if (listOfVectoids[currentNumberOfVectoids].type == "void") {
                listOfVectoids[currentNumberOfVectoids].bodyColor = new Color(255, 0, 220);

            } else if (listOfVectoids[currentNumberOfVectoids].type == "netural") {
                listOfVectoids[currentNumberOfVectoids].bodyColor = new Color(155, 163, 162);
                VectorTD.frame.currentVectoidHp.setText("Hitpoints: " + maxHealth);
                VectorTD.frame.currentWeakness.setText("Hitpoints: " + maxHealth);
                VectorTD.frame.currentStrength.setText("Hitpoints: " + maxHealth);

                VectorTD.frame.nextVectoidHp.setText("Hitpoints: " + maxHealth*1.2);
                VectorTD.frame.nextWeakness.setText("Hitpoints: " + maxHealth);
                VectorTD.frame.nextStrength.setText("Hitpoints: " + maxHealth);
            }

            currentNumberOfVectoids++;
            timer = System.nanoTime();

        }

    }

    public static void newRound() {
        if (GamePanel.roundStart == false) {
            GamePanel.roundStart = true;
            maxHealth = (int) (550 * (Math.pow(1.2, GamePanel.round)));
            currentNumberOfVectoids = 0;
            countDead = 0;
            GamePanel.round++;
            if (waveType == 4) {
                GamePanel.interest++;
                VectorTD.frame.interestLabel.setText("Interest: " + GamePanel.interest + "%");
                waveType = 0;
            } else {
                waveType++;
            }

            VectorTD.frame.roundLabel.setText("Round: " + GamePanel.round);
            GamePanel.money = GamePanel.money + GamePanel.money * GamePanel.interest / 100;
            VectorTD.frame.moneyLabel.setText("Money: " + GamePanel.money + "$");

            timer = System.nanoTime();
        }
    }
}