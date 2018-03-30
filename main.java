
/**
 * Write a description of class main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.concurrent.TimeUnit;

public class main
{
    public static void main(String[] args)
    {
        inputThread inputT = new inputThread();
        
        Thread input = new Thread(inputT);
        Thread action = new Thread(new actionThread(inputT.getApp() ));
        
        input.start();
        action.start();
    }
}




