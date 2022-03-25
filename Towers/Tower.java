package Towers;

//Her importer vi de foskellige klasser jeg har lavet så vi kan tage brug af varibler og function 
import Game.GamePanel;
import Game.Square;
import Game.Vectoid;

//Importer Graphics og awt til at lave graphic
import java.awt.Graphics;
import java.awt.*;

//klassen som alle andre tårne nedavre fra
public class Tower {
    String imagePath;
    int target;
    int damage;
    int range;
   
    int x, y, squareX, squareY, centerX, centerY;
    boolean showRange = false;

    //udrenger forskellige kordinater
    public Tower(int squareX, int squareY) {
        x = Square.grid[squareX][squareY].x + Square.strokeWeigth;
        y = Square.grid[squareX][squareY].y + Square.strokeWeigth;
        this.squareX = squareX;
        this.squareY = squareY;
        centerX = x + Square.width / 2 - Square.strokeWeigth / 2;
        centerY = y + Square.width / 2 - Square.strokeWeigth / 2;
    }

    //generalt funktion som tegner
    public void draw(Graphics g) {
        g.drawImage(Toolkit.getDefaultToolkit().getImage(imagePath), x, y, null);
        if (showRange == true) {
            //Tegner den hvide cirkel hvis man klikke på et tårn
            g.setColor(Color.white);
            g.drawOval(centerX - range, centerY - range, range * 2, range * 2);
        }
    }

    //Vi bruger pythagoras til at finde afstanden mellem centrum af tårnet og centrum af Vectoiden
    boolean inRange(int target) {
        //Først udregner vi a og b
        int a = centerX - (Vectoid.listOfVectoids[target].x + Vectoid.radius / 2);
        var b = centerY - (Vectoid.listOfVectoids[target].y + Vectoid.radius / 2);

        //Nu udregner vi c som er distancen mellem tårnet og Vectoiden
        var c = Math.sqrt(a * a + b * b);

        //Hvis c er mindre eller det samme som rækkeviden af tårnet må Vectoiden være inde for rækkeviden
        if (c <= this.range) {

            //Hvis Vectoiden er inde for rækkeviden returnere vi sandt ellers retunere vi falsk
            return (true);
        } else {
            return (false);
        }
    }

    //Gør at man kan klikke på tårne
    public void select() {
        if (GamePanel.mousePressedX >= x && GamePanel.mousePressedX < x + Square.width && GamePanel.mousePressedY >= y
                && GamePanel.mousePressedY < this.y + Square.width) {
            Square.grid[squareX][squareY].strokeColor = Color.white;
            this.showRange = true;
        } else {

            Square.grid[squareX][squareY].strokeColor = Color.black;
            this.showRange = false;
        }
    }

    //En funktion som tegner ALLE tårne
    public static final void drawAllTowers(Graphics g) {

        for (int i = 0; i < Green_Laser_Mk1.count; i++) {
            Green_Laser_Mk1.towers[i].draw(g);
        }

        for (int i = 0; i < Purple_Power_Mk1.count; i++) {
            Purple_Power_Mk1.towers[i].draw(g);
        }

        for (int i = 0; i < Orange_Incinerator_Mk1.count; i++) {
            Orange_Incinerator_Mk1.towers[i].draw(g);
        }

        for (int i = 0; i < Green_Laser_Mk2.count; i++) {
            Green_Laser_Mk2.towers[i].draw(g);
        }

        for (int i = 0; i < Purple_Power_Mk2.count; i++) {
            Purple_Power_Mk2.towers[i].draw(g);
        }

        for (int i = 0; i < Orange_Incinerator_Mk2.count; i++) {
            Orange_Incinerator_Mk2.towers[i].draw(g);
        }

    }

    //En funktion som gør at ALLE tårne skyder
    public static final void shootAllTowers(Graphics g) {

        for (int i = 0; i < Green_Laser_Mk1.count; i++) {
            Green_Laser_Mk1.towers[i].shoot(g);

        }

        for (int i = 0; i < Purple_Power_Mk1.count; i++) {
            Purple_Power_Mk1.towers[i].shoot(g);

        }

        for (int i = 0; i < Orange_Incinerator_Mk1.count; i++) {
            Orange_Incinerator_Mk1.towers[i].shoot(g);

        }
        
        for (int i = 0; i < Green_Laser_Mk2.count; i++) {
            Green_Laser_Mk2.towers[i].shoot(g);

        }
        for (int i = 0; i < Purple_Power_Mk2.count; i++) {
            Purple_Power_Mk2.towers[i].shoot(g);

        }

        for (int i = 0; i < Orange_Incinerator_Mk2.count; i++) {
            Orange_Incinerator_Mk2.towers[i].shoot(g);

        }

    }

    //en funktion som gør at man kan klikke på alle tårne
    public static final void selectAllTowers() {

        for (int i = 0; i < Green_Laser_Mk1.count; i++) {
            Green_Laser_Mk1.towers[i].select();

        }

        for (int i = 0; i < Purple_Power_Mk1.count; i++) {
           Purple_Power_Mk1.towers[i].select();

        }

        for (int i = 0; i < Orange_Incinerator_Mk1.count; i++) {
            Orange_Incinerator_Mk1.towers[i].select();
 
         }

         for (int i = 0; i < Green_Laser_Mk2.count; i++) {
            Green_Laser_Mk2.towers[i].select();

        }
         for (int i = 0; i < Purple_Power_Mk2.count; i++) {
            Purple_Power_Mk2.towers[i].select();
 
         }

         for (int i = 0; i < Orange_Incinerator_Mk2.count; i++) {
            Orange_Incinerator_Mk2.towers[i].select();
 
         }

    }

}
