
/**
 * Creates Applet for thread.
 * 
 * @author (Michael Gerovitch) 
 * @version (2018)
 */

import javax.swing.JFrame;
public class inputThread implements Runnable
{
    private KeyInput applet;
    
    public inputThread()
    {
        applet = new KeyInput();
    }
    
    public KeyInput getApp()
    {
        return applet;
    }
    
    public void run()
    {

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setVisible(true);
    }
}