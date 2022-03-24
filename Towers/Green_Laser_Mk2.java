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

    //Vi har et ekstra mål
    public int bounceTarget;


    public Green_Laser_Mk2(int squareX, int squareY) {
        super(squareX, squareY);
        imagePath = "Images/Green_Laser_Mk2.png";
        range = 200;
        damage = 5;
        
    }

    //Shoot fungere på samme måde som Green_Laser_Mk1 untagen at en ekstra skal blive skudt
    public void shoot(Graphics g) {
        if (Vectoid.listOfVectoids[target].dead == false && inRange(target) == true) {

            //tegner en streg mellem tårnet og Vectoiden
            g.setColor(Color.green);
            g.drawLine(centerX, centerY,
                    Vectoid.listOfVectoids[target].x + Vectoid.radius / 2,
                    Vectoid.listOfVectoids[target].y + Vectoid.radius / 2);

                    //Efter vi har valgt et validt mål prøver vi at vælge et bounceTarget
                    pickBounceTarget();

                    //hvis vi finder et validt bounceTarget tager den også skade
                    if (Vectoid.listOfVectoids[bounceTarget].dead == false && inRangeVectoid(bounceTarget) == true) {

                        //tegner en streg mellem target og bounceTarget
                        g.setColor(Color.green);
                        g.drawLine(Vectoid.listOfVectoids[target].x + Vectoid.radius / 2, Vectoid.listOfVectoids[target].y + Vectoid.radius / 2,Vectoid.listOfVectoids[bounceTarget].x + Vectoid.radius / 2 , Vectoid.listOfVectoids[bounceTarget].y + Vectoid.radius / 2);
                        
                        //Bouncetarget tager skade
                        if (Vectoid.listOfVectoids[target].type == "fire") {
                            Vectoid.listOfVectoids[target].takeDamage(damage * 1.5);
                        } else if (Vectoid.listOfVectoids[target].type == "void") {
                            Vectoid.listOfVectoids[target].takeDamage(damage * 0.5);
                        } else {
                            Vectoid.listOfVectoids[target].takeDamage(damage);
            
                        }
                    }

                    //target tager skade
                    if (Vectoid.listOfVectoids[target].type == "fire") {
                        Vectoid.listOfVectoids[target].takeDamage(damage * 1.5);
                    } else if (Vectoid.listOfVectoids[target].type == "void") {
                        Vectoid.listOfVectoids[target].takeDamage(damage * 0.5);
                    } else {
                        Vectoid.listOfVectoids[target].takeDamage(damage);
        
                    }

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


    //finder et bounceTarget
    private void pickBounceTarget(){

        for (int j = 0; j < 10; j++) {
            if (Vectoid.listOfVectoids[j].dead == false && inRangeVectoid(j)==true && j!=target) {

                bounceTarget = j;

                break;
            }
        }
    }

    //tjekker om bounceTarget ligger 50 eller midnre pixels vækg fra target
    boolean inRangeVectoid(int secondTarget) {

        int a = Vectoid.listOfVectoids[secondTarget].x  - Vectoid.listOfVectoids[target].x ;
        var b = Vectoid.listOfVectoids[secondTarget].y  - Vectoid.listOfVectoids[target].y ;

        var c = Math.sqrt(a * a + b * b);

        

        if (c <= 50 && c!=0) {

            return (true);
        } else {
            return (false);
        }

    }


    //funger på samme måde som forrige tårn
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
