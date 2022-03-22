package Towers;

import Game.GamePanel;
import Game.Square;
import Game.Vectoid;

import java.awt.Graphics;
import java.awt.*;
import Game.VectorTD;



public class Green_Laser_Mk2 extends Tower{

    public static Green_Laser_Mk2 towers[] = new Green_Laser_Mk2[100];
    public static int price = 10;
    public static int count = 0;

    public int bounceTarget;


    public Green_Laser_Mk2(int squareX, int squareY) {
        super(squareX, squareY);
        imagePath = "Images/Green_Laser_Mk2.png";
        range = 200;
        damage = 5;
        
    }


    public void shoot(Graphics g) {
        if (Vectoid.listOfVectoids[target].dead == false && inRange(target) == true) {

            g.setColor(Color.green);
            g.drawLine(centerX, centerY,
                    Vectoid.listOfVectoids[target].x + Vectoid.radius / 2,
                    Vectoid.listOfVectoids[target].y + Vectoid.radius / 2);

                    pickBounceTarget();

                    if (Vectoid.listOfVectoids[bounceTarget].dead == false && inRangeVectoid(bounceTarget) == true) {

            

                        g.setColor(Color.green);
                        g.drawLine(Vectoid.listOfVectoids[target].x + Vectoid.radius / 2, Vectoid.listOfVectoids[target].y + Vectoid.radius / 2,Vectoid.listOfVectoids[bounceTarget].x + Vectoid.radius / 2 , Vectoid.listOfVectoids[bounceTarget].y + Vectoid.radius / 2);
                        Vectoid.listOfVectoids[bounceTarget].takeDamage(damage);
                    }

            Vectoid.listOfVectoids[target].takeDamage(damage);


        } else {
            pickTarget();
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

    private void pickBounceTarget(){

        for (int j = 0; j < 10; j++) {
     
      
            if (Vectoid.listOfVectoids[j].dead == false && inRangeVectoid(j)==true && j!=target) {

                bounceTarget = j;

                
                break;
            }
        }
    }

    boolean inRangeVectoid(int secondTarget) {

        int a = Vectoid.listOfVectoids[secondTarget].x  - Vectoid.listOfVectoids[target].x ;
        var b = Vectoid.listOfVectoids[secondTarget].y  - Vectoid.listOfVectoids[target].y ;

        var c = Math.sqrt(a * a + b * b);

        

        if (c <= 600 && c!=0) {

            return (true);
        } else {
            return (false);
        }

    }


    public static void buy(int x, int y){

        if (x < 15 && y < 15 && x >= 0 && y >= 0 && Square.grid[x][y].isTowerPlacebel == true && GamePanel.money>=price) {
                
               
            GamePanel.money -= price;
            VectorTD.frame.moneyLabel.setText("Money: " + GamePanel.money + "$");
            towers[count] = new Green_Laser_Mk2( x, y);
            GamePanel.followMouseImage="non";
            Square.grid[x][y].isTowerPlacebel=false;
            GamePanel.selectTower=false;
            count++;
  
      
          }




    }

}
