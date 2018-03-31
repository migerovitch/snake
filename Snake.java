
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
import java.util.ArrayList;
import java.util.Arrays;
public class Snake
{
    private int[][] arr;
    private int size;
    private int length;
    private int food_x;
    private int food_y;
    private ArrayList<int[]> moves;
    public Snake()
    {
        size = 10;
        moves = new ArrayList<int[]>();
        newLocation();
        createArr();
        length = 3;
    }
    
    public ArrayList<int[]> getMoves()
    {
        return moves;
    }
    
    public int getLength()
    {
        return length;
    }
    
    public int getSize()
    {
        return size;
    }
    
    private boolean inMoves(int[] move)
    {
        for (int[] m:moves)
        {
            if (m[0] == move[0] && m[1] == move[1])
                return true;
        }
        return false;
    }
    
    // Sets random location for snake.
    private void newLocation()
    {
        Random rand = new Random();
        int x = rand.nextInt(size - 2)+1;
        int y = rand.nextInt(size - 2)+1;
        
        moves.add(new int[] {x,y});
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
                else if (inMoves(new int[] {j,i}))
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
    
    private void checkLength()
    {
        if (moves.size() > length)
            moves.remove(0);
    }
    
    public void move(int dir)
    {
        int x = moves.get(moves.size()-1)[0];
        int y = moves.get(moves.size()-1)[1];
        if (dir == 0 && canMove(x+1,y)) // right
            x = x + 1;
        else if (dir == 1 && canMove(x-1,y)) // left
            x = x - 1;
        else if (dir == 2 && canMove(x,y-1)) // up
            y = y - 1;
        else if (dir == 3 && canMove(x,y+1)) // down
            y = y + 1;
        
        moves.add(new int[] {x,y});   
        checkLength();    
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
