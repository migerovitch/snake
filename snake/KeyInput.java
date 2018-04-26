package snake; 
 
/**
 * Collects user input.
 * 
 * @author (Michael Gerovitch) 
 * @version (2018)
 */

import java.applet.Applet;
import java.awt.*;
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
//        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
//        drawString(g,newGame.toString(),0,0);
//        g.drawString("Score: "+newGame.getScore(),500,50);
        drawBoard(g);
    }

    public void drawBoard(Graphics g)
    {
        int temp;
        for (int i = 0; i < newGame.getBoard().length; i++)
        {
            for (int j = 0; j < newGame.getBoard()[i].length; j++)
            {
                temp = newGame.getBoard()[i][j];
                drawRect(temp,j,i,g);
            }
        }
    }
    
    public void drawRect(int colorInt, int x, int y, Graphics g)
    {
        Color color = null;
        if (colorInt == 0)
            color = new Color(132,178,103);
        else if (colorInt == 1)
            color = new Color(94,75,39);
        else if (colorInt == 2)
            color = new Color(255,255,0);
        else if (colorInt == 3)
            color = new Color(255,0,0);
        g.setColor(color);
        g.fillRect(x*20,y*20,20,20);
    }
    
    public int getDir()
    {
        return direction;
    }
    
    public void keyPressed(KeyEvent e)
    {   
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