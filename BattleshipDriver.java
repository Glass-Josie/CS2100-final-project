/**
Driver Class
finished!
*/
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BattleshipDriver
{  
   public static final String RESET = "\u001B[0m";
   public static final String RED = "\u001B[31m";
   public static final String GREEN = "\u001B[32m";
   public static final String BLUE = "\033[0;34m";
   
   public static void main(String [] args) throws IOException
   {
      
      System.out.println(BLUE + "Welcome to Battleship!" + RESET);
          
      //creates variables 
      Game game = new Game();
      Scanner keyboard = new Scanner(System.in);
      String first;
      
      //display starting boards
      System.out.println(game.toString());
      
      //flips a coin
      Random r = new Random();
      if (r.nextInt(2) == 0)
      {
         //player goes first
         System.out.println("You won the coin toss and get to go first.");
         first = "player";   
      }
      else
      {
         //computer goes first
         System.out.println("The computer won the coin toss and gets to go first.");
         first = "computer"; 
      }
      
      //while computer’s entire fleet isn’t sunk and user’s fleet isn’t completely sunk... 
      while (game.computerDefeated() == false && game.userDefeated() == false)
      {  
         //when player's turn is first
         if (first == "player")
         {  
            //local variables
            String strMove;
            Move move;
            
            System.out.print("Your turn: ");
            //Exception handling for mismatch cases
            strMove = keyboard.nextLine().toUpperCase();
            
            move = new Move(strMove);
            String result =  game.makePlayerMove(strMove);
            if (result != null)
            {
                System.out.printf("The computer says: \"%s\"\n", result);
            }
            
            
            //display boards
            System.out.println("");
            System.out.println(game.toString());
            
            System.out.printf("Computer's turn. Press enter to continue.\n");
            keyboard.nextLine();
            //computer makes a move
            String[] s = game.makeComputerMove();
            System.out.printf("Computer Chose : %s\n", s[0]);
            
            //display boards
            System.out.println("");
            System.out.println(game.toString());
          }   
         //when computer's turn is first
         else
         {
            System.out.printf("Computer's turn. Press enter to continue.\n");
            keyboard.nextLine();
            //computer makes a move
            String[] s = game.makeComputerMove();
            System.out.printf("Computer Chose : %s\n", s[0]);
            
            
            //display boards
            System.out.println("");
            System.out.println(game.toString());
            
            //local variables
            String strMove;
            Move move;
            
            System.out.print("Your turn: ");
            //Exception handling for mismatch cases
            strMove = keyboard.nextLine().toUpperCase();
            
            move = new Move(strMove);
            String result =  game.makePlayerMove(strMove);
            if (result != null)
            {
                System.out.printf("The computer says: \"%s\"\n", result);
            }

            //display boards
            System.out.println("");
            System.out.println(game.toString());
            
         }
           
      }
      
      //reporting who won
      if (game.computerDefeated() == true)
         System.out.printf(GREEN + "Game over, you won :)" + RESET);
      else
         System.out.printf(RED + "Game over, the computer won :(" + RESET);
       
   }
}
