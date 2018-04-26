package snake;
 
/**
 * Description: Snake game.
 * 
 * Public Methods:
 * - Snake()
 * - getBoard()
 * - getX()
 * - getY()
 * - getLength()
 * - getSize()
 * - move(int direction)
 *      - 0: right
 *      - 1: left
 *      - 2: up
 *      - 3: down
 * - toString()
 * 
 * @author (Michael Gerovitch) 
 * @version (2018)
 */

import java.util.Random;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.util.concurrent.TimeUnit;

public class Snake
{

    private int[][] board;
    private int size;
    private int length;
    private int food_x, food_y;
    private ArrayList<int[]> moves;
    private Random rand;
    private static boolean gameEnd;
    private static int score;
    private int foodSize;
    
    private int x,y,new_x,new_y;
    
    // Array variables
    private int empty, walls, snake, food;
    
    
    public Snake()
    {
        rand = new Random();
        
        // Variables initation
        moves = new ArrayList<int[]>();
        size = 40;
        length = 1;
        score = 0;
        foodSize = 1;
        gameEnd = false;
        
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
    private ArrayList<int[]> getMoves()
    {
        return moves;
    }
    
    public int[][] getBoard() {
        return board;
    }

    public static boolean gameEnd()
    {
        return gameEnd;
    }
    
    public static int getScore()
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
        
        while (board[food_y][food_x] != empty)
        {
            food_x = rand.nextInt(size - 2)+1;
            food_y = rand.nextInt(size - 2)+1;
            
        }
        createArr();
    }
    
    // Generates an array
    private void createArr()
    {
        board = new int[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1)
                    board[i][j] = walls;
                else if (inMoves(new int[] {i,j}))
                    board[i][j] = snake;
                else if (i == food_y && j == food_x)
                    board[i][j] = food;
                else
                    board[i][j] = empty;
            }
        }
    }
    
    // Returns true if snake can move to location (x,y) - if location is 0.
    private boolean canMove(int new_y, int new_x)
    {
        return (board[new_y][new_x] == empty || board[new_y][new_x] == food);
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
    
    private int dirFlip(int dir)
    {
        if (dir == 0 || dir == 2)
            return dir + 1;
        else
            return dir - 1;
    }
    
    public void move(int dir)
    {
        gameEnd = false;
        
        // Obtains the snake's current location
        x = moves.get(moves.size()-1)[1];
        y = moves.get(moves.size()-1)[0];
        
        // Makes move
        
        int[] newMove = getNextMove(dir,new int[] {y,x});
        
        // Wall: game ends
        if (board[new_y][new_x] == walls)
        {
            gameEnd = true;
        }
        
        // Snake: cannot go backwards
        else if (board[new_y][new_x] == snake && !isSameMove(newMove,moves.get(moves.size()-1)))
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
        

        else if (board[new_y][new_x] == empty) moves.add(newMove);  
        
        else if (board[new_y][new_x] == food)
        {
            length = length + foodSize;
            newFoodLocation();
            moves.add(newMove);   
        }
        
        checkLength(); 
        createArr();
    }

    public String toString()
    {
        String output = "";
        
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
               output = output + board[i][j] + " ";
            }
            output = output + "\n";
        }
        return output;
    }
    
    public static int playerGame()
    {
        int speed = 50;
        
        KeyInput app = new KeyInput();
        JFrame frame = new JFrame();
        
        frame.setSize(800, 820);
        frame.getContentPane().add(app);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        while (!Snake.gameEnd())
        {
            try 
            {
                TimeUnit.MILLISECONDS.sleep(speed);
                app.repaint();
            } 
            catch(InterruptedException ex) {}
        }
        frame.setVisible(false);
        
        return Snake.getScore();
    }
}
