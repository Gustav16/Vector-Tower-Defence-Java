package Towers;

import Game.GamePanel;
import Game.Square;
import Game.Vectoid;

//Importere pakker der burges til at tegne med
import java.awt.Graphics;
import java.awt.*;


import Game.VectorTD;



public class Green_Laser_Mk1 extends Tower{

    public static Green_Laser_Mk1 towers[] = new Green_Laser_Mk1[100];
    public static int price = 250;
    public static int count = 0;


    public Green_Laser_Mk1(int squareX, int squareY) {
        super(squareX, squareY);
        imagePath = "Images/Green_Laser_Mk1.png";
        range = 200;
        damage = 1;
        
    }

    //Funktionen som skyder Vectoids
    public void shoot(Graphics g) {

        //Først tjekker vi om vores target faktisk er inde for rækkevidden og den ikke er død
        if (Vectoid.listOfVectoids[target].dead == false && inRange(target) == true) {

            //Vi tegner en grøn streg mellem tårnet om Vectoiden
            g.setColor(Color.green);
            g.drawLine(centerX, centerY,
                    Vectoid.listOfVectoids[target].x + Vectoid.radius / 2,
                    Vectoid.listOfVectoids[target].y + Vectoid.radius / 2);

            //Vectoiden tager skade
            Vectoid.listOfVectoids[target].takeDamage(damage);

            //Hvis Vectoiden enten dør eller kommer ud af rækkevidde vælger vi et nyt
        } else {
            pickTarget();
        }

    }

    //Function der finder den først Vectoid som er inde for rækkevidden og som ikke er død
    public void pickTarget() {
        //Her har vi en for-løkke som kører arraylisten med Vectoids igennem.
        for (int j = 0; j < 10; j++) {
            //Hvis Vectoiden ikke er død og den er inde for rækkevidden har en et validt mål
            if (Vectoid.listOfVectoids[j].dead == false && inRange(j) == true) {
                //Når vi har fundet et validt mål sætter vi target til målet og breaker herefter
                target = j;
                break;
            }
        }
    }

    public static void buy(int x, int y){

        if (x < 15 && y < 15 && x >= 0 && y >= 0 && Square.grid[x][y].isTowerPlacebel == true && GamePanel.money>=price) {
                
               
            GamePanel.money -= price;
            VectorTD.frame.moneyLabel.setText("Money: " + GamePanel.money + "$");
            towers[count] = new Green_Laser_Mk1( x, y);
            GamePanel.followMouseImage="non";
            Square.grid[x][y].isTowerPlacebel=false;
            GamePanel.selectTower=false;
            count++;
  
      
          }




    }

}
