import java.awt.*;
import java.awt.Color;

public class Vectoid {

    static int maxNumberOfVectoids = 10;
    static int currentNumberOfVectoids = 0;
    public static Vectoid listOfVectoids[] = new Vectoid[10];

    int radius = 50;
    float distance = 0;
    int x = Square.vectoidRoute[Math.round(distance)][0];
    int y = Square.vectoidRoute[Math.round(distance)][1];
    float speed = 3;
    int timer = 0;
    int slowTime = 0;
    float slowSpeed = 0;
    int health = 100;
    Color bodyColor = new Color(255, 0, 0);
    boolean dead = false;

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

        }
    }

    public void takeDamage(int damage) {
        health -= damage;

        if (health <= 0) {
            dead = true;
            GamePanel.money += 10;
        }
    }

    public static void makeVectoids() {
        for (int i = 0; i < maxNumberOfVectoids; i++) {
            listOfVectoids[i] = new Vectoid();
        }
    }

    public static void newRound() {
        GamePanel.roundStart = true;

        for (int i = 0; i < maxNumberOfVectoids; i++) {
            listOfVectoids[i].dead = false;
            listOfVectoids[i].health = 100;
            currentNumberOfVectoids++;

        }

    }

}