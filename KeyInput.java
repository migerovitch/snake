
/**
 * Collects user input.
 * 
 * @author (Michael Gerovitch) 
 * @version (2018)
 */

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class KeyInput extends Applet implements KeyListener
{    
    //private Rectangle rect;
    private int direction;
    private Snake newGame;
    
    public KeyInput()
    {
        newGame = new Snake();
        addKeyListener(this);
        direction = -1;
    }
    
    private void drawString(Graphics g, String text, int x, int y) 
    {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    public void paint(Graphics g)
    {
        setSize(5000, 5000);
        newGame.move(direction);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
        drawString(g,newGame.toString(),0,0);
        g.drawString("Score: "+newGame.getScore(),500,50);
    }
    
    public int getDir()
    {
        return direction;
    }
    
    public void keyPressed(KeyEvent e)
    {
        Snake.gameEnd();
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            direction = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            direction = 1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            direction = 2;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            direction = 3;
        }  
    }
    
    public void keyReleased(KeyEvent e)
    {
    }
    
    public void keyTyped(KeyEvent e)
    {
    
    }

}  