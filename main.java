package snake;
/**
 * Write a description of class main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.JFrame;
import java.util.concurrent.TimeUnit;
public class main
{
    
    
    public static void main(String[] args)
    {
        
        // Variables
        int speed = 150;
        

        
        
        //System.out.println("Pregame: " + input.isAlive());
        KeyInput app = new KeyInput();
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.getContentPane().add(app);
        frame.setVisible(true);
        while (!Snake.gameEnd())
        {
            try 
            {
                TimeUnit.MILLISECONDS.sleep(speed);
                app.repaint();
            } 
            catch(InterruptedException ex) {}
        }
        frame.setVisible(false);
        System.out.println("Score: " + Snake.getScore());
    }
}




