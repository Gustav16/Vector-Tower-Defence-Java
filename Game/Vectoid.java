package Game;

import java.awt.*;
import java.awt.Color;


//Vectoids er dem som løber på banen
public class Vectoid {

    // Arrayliste over alle vectoids
    public static Vectoid listOfVectoids[] = new Vectoid[30];

    public static int radius = 50;
    public static int maxHealth = 550;

    // Forksellige typer af enemies
    public static String typeList[] = { "grass", "fire", "ice", "void", "netural" };
    static int waveType = -1;

    static int maxNumberOfVectoids = 30;
    public static int currentNumberOfVectoids = 0;
    static int countDead = 0;
    static long timer;
    static Color roundColor;

    // Hvor langt de er nået
    float distance = 0;

    int currentHealth;

    public String type;

    // Vectoids postion. Tager kordinaterne ud fra hvor de er på vectoidsRoute
    public int x = Square.vectoidRoute[Math.round(distance)][0];
    public int y = Square.vectoidRoute[Math.round(distance)][1];
    public int arraySpace;
    float speed = 1;
    Color bodyColor;
    public boolean dead = true;

    // Varibler der hjælper med at få Vectoiden til at brænde
    int burnTimeRemaining = 0;
    int burnDamage = 0;
    int burnTicks = 0;

    Vectoid(int arraySpac) {
        arraySpace = arraySpac;

    }

    // Tegner Vectoiden
    public void draw(Graphics g) {

        // Giver skade til Vectoiden og opdaterer hvor lang tid de skal brænde endnu
        if (burnTimeRemaining != 0) {

            burnTicks++;
            if (burnTicks == 100) {
                burnTimeRemaining--;
                takeDamage(burnDamage);
                burnTicks = 0;
            }
        }

        // Healthbar
        if (maxHealth != currentHealth) {

            g.setColor(Color.green);
            int healthBarWidth = Math.round((currentHealth * 40) / maxHealth);

            g.fillRect(2 + x, y, healthBarWidth, 5);

        }

        x = Square.vectoidRoute[Math.round(distance)][0];
        y = Square.vectoidRoute[Math.round(distance)][1];

        // Tegner Vectoiden
        int[] pentagonX = { 10 + 2 + x, 4 + 2 + x, 20 + 2 + x, 36 + 2 + x, 30 + 2 + x };
        int[] pentagonY = { 40 + y, 21 + y, 9 + y, 21 + y, 40 + y };
        g.setColor(bodyColor);
        // g.fillOval(x, y, radius, radius);
        g.fillPolygon(pentagonX, pentagonY, 5);
        g.setColor(Color.black);
        g.drawPolygon(pentagonX, pentagonY, 5);

    }

    // rykker Vectoiden
    public void move() {
        distance += speed;
        outOfMap();
    }

    // Tjekker om Vectoide er ud fra kortet og gør at man mister liv hvis den er.
    private void outOfMap() {
        if (distance >= Square.vectoidRoute.length) {

            GamePanel.lives -= 1;
            distance = 0;

            VectorTD.frame.livesLabel.setText("Lives: " + GamePanel.lives);

        }
    }

    // Funktion towers kalder når en Vectoid skal tage skade
    public void takeDamage(double d) {
        currentHealth -= d;

        // Tjekker om Vectoiden er død
        if (currentHealth <= 0 && dead == false && dead == false) {
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

    // Funktion der bliver kaldt når Vectoiden skal brænde
    public void burn(int seconds, double d) {
        burnTimeRemaining = seconds;
        burnDamage = (int) d;

    }

    public void overturn() {
        if (arraySpace != 0) {
            if (listOfVectoids[arraySpace].distance > listOfVectoids[arraySpace - 1].distance) {

                // Collections.swap(typeList, arraySpace, arraySpace-1);
                listOfVectoids[arraySpace - 1].arraySpace++;
                arraySpace--;

                overturn();

            }

        }

    }

    // Tegner alle Vectoids der ikke er døde og rykker dem
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

    // Fylder listOfVectoids op med Vectoids
    public static void makeVectoids() {
        for (int i = 0; i < maxNumberOfVectoids; i++) {
            listOfVectoids[i] = new Vectoid(i);
        }
    }

    // Spawner en Vectoids hvert 450000000 nano sekund
    public static void spawnVectoids() {
        if ((currentNumberOfVectoids < maxNumberOfVectoids) && (System.nanoTime() - timer) > 450000000
                && GamePanel.roundStart == true) {
            listOfVectoids[currentNumberOfVectoids].dead = false;
            listOfVectoids[currentNumberOfVectoids].currentHealth = maxHealth;
            listOfVectoids[currentNumberOfVectoids].distance = 0;
            listOfVectoids[currentNumberOfVectoids].type = typeList[waveType];
            listOfVectoids[currentNumberOfVectoids].bodyColor = roundColor;
            currentNumberOfVectoids++;
            timer = System.nanoTime();

        }

    }

    // Når man klikker på kanppen ny runde bliver denne her funktion kaldt
    public static void newRound() {

        // Funktion går kun i gang hvis runden ikke allerede er startet
        if (GamePanel.roundStart == false) {
            GamePanel.roundStart = true;
            maxHealth = (int) (550 * (Math.pow(1.2, GamePanel.round)));

            // Vectoid information
            VectorTD.frame.currentVectoidHp.setText("Hitpoints: " + maxHealth);
            VectorTD.frame.nextVectoidHp.setText("Hitpoints: " + Math.round(maxHealth * 1.2) );

            currentNumberOfVectoids = 0;
            countDead = 0;
            GamePanel.round++;

            // Opdatere hvilket type Vectoids det er nu
            if (waveType == 4) {
                GamePanel.interest++;
                VectorTD.frame.interestLabel.setText("Interest: " + GamePanel.interest + "%");
                waveType = 0;
            } else {
                waveType++;
            }

            // Sætter Vectoidens egenskaber ud fra hvilken type det er
            if (typeList[waveType] == "grass") {

                roundColor = new Color(0, 239, 31);

                VectorTD.frame.currentWeakness.setText("Weak agianst: fire");
                VectorTD.frame.currentStrength.setText("Strong agianst: void");

                VectorTD.frame.nextWeakness.setText("Weak agianst: ice");
                VectorTD.frame.nextStrength.setText("Strong agianst: grass");

            } else if (typeList[waveType] == "fire") {
                roundColor = new Color(255, 136, 0);

                VectorTD.frame.currentWeakness.setText("Weak agianst: ice");
                VectorTD.frame.currentStrength.setText("Strong agianst: grass");

                VectorTD.frame.nextWeakness.setText("Weak agianst: void");
                VectorTD.frame.nextStrength.setText("Strong agianst: fire");

            } else if (typeList[waveType] == "ice") {
                roundColor = new Color(49, 185, 230);

                VectorTD.frame.currentWeakness.setText("Weak agianst: void");
                VectorTD.frame.currentStrength.setText("Strong agianst: fire");

                VectorTD.frame.nextWeakness.setText("Weak agianst: grass");
                VectorTD.frame.nextStrength.setText("Strong agianst: ice");

            } else if (typeList[waveType] == "void") {
                roundColor = new Color(255, 0, 220);

                VectorTD.frame.currentWeakness.setText("Weak agianst: grass");
                VectorTD.frame.currentStrength.setText("Strong agianst: ice");

                VectorTD.frame.nextWeakness.setText("Weak agianst: nothing");
                VectorTD.frame.nextStrength.setText("Strong agianst: nothing");

            } else if (typeList[waveType] == "netural") {
                roundColor = new Color(155, 163, 162);

                VectorTD.frame.currentWeakness.setText("Weak agianst: nothing");
                VectorTD.frame.currentStrength.setText("Strong agianst: nothing");

                VectorTD.frame.nextWeakness.setText("Weak agianst: fire");
                VectorTD.frame.nextStrength.setText("Strong agianst: void");
            }

            // Opdatere runden
            VectorTD.frame.roundLabel.setText("Round: " + GamePanel.round);

            // Gør at man får flere penge hver runde
            GamePanel.money = GamePanel.money + GamePanel.money * GamePanel.interest / 100;
            VectorTD.frame.moneyLabel.setText("Money: " + GamePanel.money + "$");

            timer = System.nanoTime();
        }
    }
}