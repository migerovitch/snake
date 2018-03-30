
/**
 * Write a description of class main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.concurrent.TimeUnit;

public class test
{
    public static void main(String[] args)
    {
        while(true)
        {
            try 
            {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("PRINT");
            } 
            catch(InterruptedException ex) 
            {
            }
        }
    }
}