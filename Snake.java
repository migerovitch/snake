
/**
 * Description: Snake game.
 * 
 * Public Methods:
 * - Snake()
 * - getX()
 * - getY()
 * - getLength()
 * - getSize()
 * - move(int direction)
 * - toString()
 * 
 * @author (Michael Gerovitch) 
 * @version (2018)
 */

import java.util.Random;
import java.util.concurrent.TimeUnit;
public class Snake
{
    private int[][] arr;
    private int x;
    private int y;
    private int size;
    private int length;
    
    public Snake()
    {
        size = 10;
        arr = new int[size][size];
        newLocation();
        createArr();
        length = 1;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getLength()
    {
        return length;
    }
    
    public int getSize()
    {
        return size;
    }
    // Sets random location for snake.
    private void newLocation()
    {
        Random rand = new Random();
        x = rand.nextInt(size - 2)+1;
        y = rand.nextInt(size - 2)+1;
    }
    
    private void createArr()
    {
        arr = new int[size][size];
        
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1)
                    arr[i][j] = 1;
                else if (i == y && j == x)
                    arr[i][j] = 8;
                else
                    arr[i][j] = 0;
            }
        }
    }
    
    // Returns true if snake can move to location (x,y) - if location is 0.
    private boolean canMove(int new_x, int new_y)
    {
        if (arr[new_x][new_y] == 0)
            return true;
        
        return false;
    }
    
    public void move(int dir)
    {
        if (dir == 0 && canMove(x+1,y)) // right
            x = x + 1;
        else if (dir == 1 && canMove(x-1,y)) // left
            x = x - 1;
        else if (dir == 2 && canMove(x-1,y)) // up
            y = y - 1;
        else if (dir == 3 && canMove(x,y+1)) // down
            y = y + 1;
            
        if (!canMove(x+1,y) || !canMove(x-1,y) || !canMove(x-1,y) || !canMove(x,y+1))
        {
            // ADD END GAME CONDITION.
        }
        createArr();
    }
    
    public String toString()
    {
        String output = "";
        
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++)
            {
               output = output + arr[i][j] + " ";
            }
            output = output + "\n";
        }
        return output;
    }
}
