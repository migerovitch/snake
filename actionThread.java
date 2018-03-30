
/**
 * Write a description of class actionThread here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.concurrent.TimeUnit;
public class actionThread implements Runnable
{
    private KeyInput applet;
    
    public actionThread(KeyInput a)
    {
        applet = a;
    }
    
    public void run()
    {
       while (true)
       {
            try 
            {
                TimeUnit.MILLISECONDS.sleep(200);
                applet.repaint();
            } 
            catch(InterruptedException ex) {}
       }
    }
    
}