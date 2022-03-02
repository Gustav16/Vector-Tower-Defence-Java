import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import javax.swing.JButton;

public class Menu extends JPanel{



    Menu() {
        JButton b=new JButton("Click Here");
        b.setBounds(500,100,95,300);  
        b.setPreferredSize(new Dimension(200, 100));
        this.add(b);  

    }

     void makeTopMenu() {

        JButton b=new JButton("Click Here");
        b.setBounds(500,100,95,30);  
        b.setPreferredSize(new Dimension(200, 100));
        this.add(b);  

  

    }

}
