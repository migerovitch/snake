package snake;
/**
 * Write a description of class main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Scanner;

public class main
{
    private static int score;
    private static Scanner scan;
    
    public static void main(String[] args)
    {
        int bestScore = 0;
        boolean playAgain = true;
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 1; i++)
        {
            score = Snake.playerGame();
            System.out.println("Your score for game " + (i+1) + " is " + score);
            if (score > bestScore) bestScore = score;
        }
        System.out.println("Your best score was " + bestScore);
    }

}




