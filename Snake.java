
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
    private Random rand;
    
    // Array variables
    private int empty;
    private int walls;
    private int snake;
    private int food;
    
    
    public Snake()
    {
        rand = new Random();
        size = 20;
        moves = new ArrayList<int[]>();
        newLocation();
        createArr();
        newFoodLocation();
        length = 1;
        
        // Arr variables
        empty = 0;
        walls = 1;
        snake = 2;
        food = 3;
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
        int x = rand.nextInt(size - 2)+1;
        int y = rand.nextInt(size - 2)+1;
        
        moves.add(new int[] {y,x});
    }
    
    private void newFoodLocation()
    {
        food_x = rand.nextInt(size - 2)+1;
        food_y = rand.nextInt(size - 2)+1;
        
        while (arr[food_y][food_x] != empty)
        {
            food_x = rand.nextInt(size - 2)+1;
            food_y = rand.nextInt(size - 2)+1;
            
        }
        createArr();
    }
    
    private void createArr()
    {
        arr = new int[size][size]; 
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1)
                    arr[i][j] = walls;
                else if (inMoves(new int[] {i,j}))
                    arr[i][j] = snake;
                else if (i == food_y && j == food_x)
                    arr[i][j] = food;
                else
                    arr[i][j] = empty;
            }
        }
    }
    
    // Returns true if snake can move to location (x,y) - if location is 0.
    private boolean canMove(int new_y, int new_x)
    {
        if (arr[new_y][new_x] == empty || arr[new_y][new_x] == food)
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
        int x = moves.get(moves.size()-1)[1];
        int y = moves.get(moves.size()-1)[0];
        if (dir == 0 && canMove(y,x+1)) // right
            x = x + 1;
        else if (dir == 1 && canMove(y,x-1)) // left
            x = x - 1;
        else if (dir == 2 && canMove(y-1,x)) // up
            y = y - 1;
        else if (dir == 3 && canMove(y+1,x)) // down
            y = y + 1;
        
        if (x == food_x && y == food_y)
        {
            length = length + 5;
            newFoodLocation();
        }
        moves.add(new int[] {y,x});   
        checkLength(); 
        
        if (arr[y][x] != snake & (!canMove(y,x+1) || !canMove(y,x-1) || !canMove(y-1,x) || !canMove(y+1,x)))
        {
            
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
