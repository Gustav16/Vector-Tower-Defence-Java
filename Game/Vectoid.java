package Game;

import java.awt.*;
import java.awt.Color;

//Vectoids er dem som løber på banen
public class Vectoid {

    // Arrayliste over alle vectoids
    public static Vectoid listOfVectoids[] = new Vectoid[30];

    public static int radius = 50;
    public static int maxHealth = 550;
    int currentHealth;
    float distance = 0;
    public int moneyDrop;

    // Forksellige typer af enemies
    public static String typeList[] = { "Blue Spinner", "Big Purple Box", "Orange Shredder", "Green Flyer", "Hard Grey",
            "Yellow Sprinter" };
    public static int waveType = 0;
    static Color roundColor[] = { new Color(49, 185, 230), new Color(255, 0, 220), new Color(255, 136, 0),
            new Color(0, 239, 31), new Color(155, 163, 162), new Color(255, 255, 0) };
    public String type;
    public static int roundMoneyDrop = 4;

    static int maxNumberOfVectoids = 30;
    public static int currentNumberOfVectoids = 0;
    static int countDead = 0;
    static long timer;


    // Vectoids postion. Tager kordinaterne ud fra hvor de er på vectoidsRoute
    public int x = Square.vectoidRoute[Math.round(distance)][0];
    public int y = Square.vectoidRoute[Math.round(distance)][1];

    float speed = 1;
    Color bodyColor;
    public boolean dead = true;

    // Varibler der hjælper med at få Vectoiden til at brænde
    int burnTimeRemaining = 0;
    int burnDamage = 0;
    int burnTicks = 0;

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
        if (currentHealth <= 0 && dead == false) {
            dead = true;
            GamePanel.money += moneyDrop;
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
            listOfVectoids[i] = new Vectoid();
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
            listOfVectoids[currentNumberOfVectoids].bodyColor = roundColor[waveType];
            listOfVectoids[currentNumberOfVectoids].moneyDrop = roundMoneyDrop;
            if ( typeList[waveType] == "Yellow Sprinter" ){
                listOfVectoids[currentNumberOfVectoids].speed=2;

            }
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
            VectorTD.frame.currentType.setText(typeList[waveType]);
            VectorTD.frame.currentHealth.setText("Hitpoints: " + (int) maxHealth);
            VectorTD.frame.currentMoney.setText(roundMoneyDrop + "$");

            if (waveType == typeList.length) {
                VectorTD.frame.nextType.setText(typeList[0]);
            } else {
                VectorTD.frame.nextType.setText(typeList[waveType + 1]);
            }
            VectorTD.frame.nextHealth.setText("Hitpoints: " + (int) maxHealth * 1.2);
            VectorTD.frame.nextMoney.setText(roundMoneyDrop + 1 + "$");

            if (waveType == typeList.length) {
                GamePanel.interest += 3;
                VectorTD.frame.interestLabel.setText("Interest: " + GamePanel.interest + "%");
                waveType = 0;
            } else {
                waveType++;
            }

            // Opdatere runden
            GamePanel.round++;
            roundMoneyDrop++;
            VectorTD.frame.roundLabel.setText("Round: " + GamePanel.round);

            // Gør at man får flere penge hver runde
            GamePanel.money = GamePanel.money + GamePanel.money * GamePanel.interest / 100;
            VectorTD.frame.moneyLabel.setText("Money: " + GamePanel.money + "$");

            currentNumberOfVectoids = 0;
            countDead = 0;
            timer = System.nanoTime();
        }
    }
}