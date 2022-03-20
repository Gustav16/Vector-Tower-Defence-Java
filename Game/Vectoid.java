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
    int currentHealth;
    public static int maxHealth = 1000;

    
    public int x = Square.vectoidRoute[Math.round(distance)][0];
    public int y = Square.vectoidRoute[Math.round(distance)][1];
    public int degrees = 0;

    float speed = 1;
    Color bodyColor = new Color(255, 0, 0);
    public boolean dead = true;
    static long timer;

    int burnTimeRemaining = 0;
    int burnDamage = 0;
    int burnTicks = 0;


  




    public void draw(Graphics g) {

        if (burnTimeRemaining != 0){
            
            g.setColor(Color.orange);
            burnTicks++;
            if (burnTicks==100){
                burnTimeRemaining--;
            
                takeDamage(burnDamage);
                burnTicks=0;

            }
        } else {
            g.setColor(bodyColor);

        }
        
    
        
        
        
        
        x = Square.vectoidRoute[Math.round(distance)][0];
        y = Square.vectoidRoute[Math.round(distance)][1];
        //g2d.rotate(Math.toRadians(degrees));
        degrees++;
        
        //g.translate(x,y);
        int[] pentagonX = {10 + 2 + x, 4 + 2 + x, 20 + 2 + x, 36 + 2 + x, 30 + 2 + x};
        int[] pentagonY = {40 + y, 21 + y, 9 + y, 21 + y, 40 + y};
        
        
   
        //g.fillOval(x, y, radius, radius);
        g.fillPolygon( pentagonX, pentagonY, 5 );
        g.setColor(Color.black);
        g.drawPolygon( pentagonX, pentagonY, 5 );

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

        System.out.println(currentHealth);
        if (currentHealth <= 0 && dead==false) {
            dead = true;
            GamePanel.money += 10;
            countDead++;
            
            VectorTD.frame.moneyLabel.setText("Money: " + GamePanel.money + "$");
            if (countDead == maxNumberOfVectoids) {
                GamePanel.roundStart = false;

            }

        }
    }

    public void burn(int seconds, int dps){
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