import java.awt.*;
import java.awt.Color;

public class Vectoid {


    int radius = 50;
    float distance = 0;
    int x = Square.vectoidRoute[Math.round(distance)][0];
    int y = Square.vectoidRoute[Math.round(distance)][1];
    float speed = 3;
    int timer = 0;
    int slowTime = 0;
    float slowSpeed = 0;
    int health = 100;
    static int maxNumberOfVectoids = 10;
    static int currentNumberOfVectoids = 0;
    Color bodyColor = new Color(255, 0, 0);

    public static Vectoid listOfVectoids[] = new Vectoid[10];

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

    public static void newRound() {
       

        if (currentNumberOfVectoids < maxNumberOfVectoids) {

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override

                        public void run() {
                            listOfVectoids[currentNumberOfVectoids] = new Vectoid();
                            currentNumberOfVectoids++;
                            newRound();
                        }
                    },
                    500);
        }
        

    }

    private void outOfMap() {
        if (distance >= Square.vectoidRoute.length) {

        }
    }
}