package Game;
import java.awt.*;
import java.awt.Color;

public class Vectoid {

    static int maxNumberOfVectoids = 10;
    static int currentNumberOfVectoids = 0;
    public static Vectoid listOfVectoids[] = new Vectoid[10];
    static int countDead = 0;

    int radius = 50;
    float distance = 0;
    public int x = Square.vectoidRoute[Math.round(distance)][0];
    public int y = Square.vectoidRoute[Math.round(distance)][1];
    float speed = 3;
    int slowTime = 0;
    float slowSpeed = 0;
    int health = 100;
    Color bodyColor = new Color(255, 0, 0);
    public boolean dead = false;
    static long timer;

    public void draw(Graphics g) {
        x = Square.vectoidRoute[Math.round(distance)][0];
        y = Square.vectoidRoute[Math.round(distance)][1];
        g.setColor(bodyColor);
        g.fillOval(x, y, radius, radius);
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

        if (health <= 0) {
            dead = true;

            GamePanel.money += 10;
        }
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
        if ((currentNumberOfVectoids < maxNumberOfVectoids) && (System.nanoTime() - timer) > 300000000) {
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
            timer = System.nanoTime();
        }
    }
}