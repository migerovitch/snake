
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
    private static boolean gameEnd = false;
    private int score;
    
    private int x,y,new_x,new_y;
    // Array variables
    private int empty;
    private int walls;
    private int snake;
    private int food;
    
    
    public Snake()
    {
        rand = new Random();
        
        // Variables initation
        moves = new ArrayList<int[]>();
        size = 20;
        length = 1;
        score = 0;
        // Game start
        newLocation();
        createArr();
        newFoodLocation();
        
        // Array symbols variables
        empty = 0;
        walls = 1;
        snake = 2;
        food = 3;
    }
    
    /*
     * Methods for returning variables stored in class. 
     */
    public ArrayList<int[]> getMoves()
    {
        return moves;
    }
    
    public static boolean gameEnd()
    {
        return gameEnd;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getLength()
    {
        return length;
    }
    
    public int getSize()
    {
        return size;
    }
    
    // Checks if location is in the snake's location.
    private boolean inMoves(int[] move)
    {
        for (int[] m:moves)
        {
            if (isSameMove(m,move))
                return true;
        }
        return false;
    }
    
    // Compares two moves
    private boolean isSameMove(int[] m1, int[] m2)
    {
        return m1[0] == m2[0] && m1[1] == m2[1];
    }
    
    // Sets random location for snake.
    private void newLocation()
    {
        int x = rand.nextInt(size - 2)+1;
        int y = rand.nextInt(size - 2)+1;
        
        moves.add(new int[] {y,x});
    }
    
    // Sets random location for food that is empty.
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
    
    // Generates an array
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
        return (arr[new_y][new_x] == empty || arr[new_y][new_x] == food);
    }
    
    // Makes sure that the snake makes only as many moves as it has to.
    private void checkLength()
    {
        if (moves.size() > length)
            moves.remove(0);
        score = length;
    }
    
    private int[] getNextMove(int dir,int[] loc)
    {      
        x = loc[1];
        y = loc[0];
        new_x = x;
        new_y = y;
        if (dir == 0) // right
            new_x = x + 1;
        else if (dir == 1) // left
            new_x = x - 1;
        else if (dir == 2) // up
            new_y = y - 1;
        else if (dir == 3)// down
            new_y = y + 1;
        return new int[] {new_y,new_x};
    }
    
    public int dirFlip(int dirn)
    {
        if (dirn == 0 || dirn == 2)
            return dirn + 1;
        else
            return dirn - 1;
    }
    
    public void move(int dir)
    {
        gameEnd = false;
        
        // Obtains the snake's current location
        x = moves.get(moves.size()-1)[1];
        y = moves.get(moves.size()-1)[0];
        
        // Makes move
        
        int[] newMove = getNextMove(dir,new int[] {y,x});
        
        // 
        if (arr[new_y][new_x] == walls)
        {
            gameEnd = true;
        }
        
        // 
        else if (arr[new_y][new_x] == snake && !isSameMove(newMove,moves.get(moves.size()-1)))
        {
            gameEnd = true;
            
            if (length > 1)
                {
                    if (!isSameMove(newMove,moves.get(moves.size()-2))) gameEnd = true;
                    
                    else
                    {
                        gameEnd = false;
                        dir = dirFlip(dir);
                        newMove = getNextMove(dir,new int[] {y,x});
                        moves.add(newMove);
                    }
                }
        }
        

        else if (arr[new_y][new_x] == empty)
        {
            moves.add(newMove);  
        }
        
        else if (arr[new_y][new_x] == food)
        {
            length = length + 1;
            newFoodLocation();
            moves.add(newMove);   
        }
        
        checkLength(); 
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
