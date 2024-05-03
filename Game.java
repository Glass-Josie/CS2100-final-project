/**
Game class
finished!
*/
import java.util.Scanner;
import java.io.*;
import java.util.*;
public class Game
{
   //variables
   private  ComputerBoard computer;
   private UserBoard player;
   Scanner keyboard = new Scanner(System.in);
   
   //constructor
   /**
   Creates the two boards (file names are fixed “compFleet.txt” and “userFleet.txt”, no need
   to prompt for them or pass in filename
   */
   public Game()
   {
      computer = new ComputerBoard("compFleet.txt");
      player = new UserBoard("userFleet.txt");
   }
   //methods
   /**
   Calls a method on the player board which makes a move against that board.
   Returns an array of two Strings. The first is the move the computer made in user
   readable form. The second is either null, or, if the move resulted in a ship being sunk, a
   string along the lines of "You sunk my Battleship!" 
   @return String[] array 
   */
   public String[] makeComputerMove()
   {
      return player.makeComputerMove();
   }
   /**
   Calls a method on the computer board which makes a move against that board.
   Returns either null, or, if the move resulted in a a ship being sunk, a string along the
   lines of "You sunk my Battleship!" 
   @param String strMove that is the move guess
   @return String message or null
   */
   public String makePlayerMove(String strMove)
   {
      Move move = null;
     
      //error handling for out of bounds
      ArrayList<String> possibleMoves = new ArrayList<String>();
      for (int i = 0; i < 10; i++)
      {
         for (int j = 1; j < 11; j++)
         {
            String pmove = "";
            pmove += (char)('A' + i);
            pmove += j;
            possibleMoves.add(pmove);
         }
      }
      //while their guess is not an option as specified in possible moves array...
      while (!possibleMoves.contains(strMove))
      {
         System.out.print("Invalid move, try again: ");
         strMove = keyboard.nextLine().toUpperCase();
         move = new Move(strMove);
      }
      move = new Move(strMove);
      
      //error handling for guessed twice
      if (!computer.moveAvailable(move))
      {
         System.out.print("That was a duplicate move. No action taken.");
         return null;
      }
      
      return computer.makePlayerMove(move);
   }
   
   /**
   Checks to see if the player has been defeated.
   Returns True if all player ships have been sunk, false otherwise
   @return boolean true if user lost
   */
   public boolean userDefeated()
   {
      return player.gameOver();
   }
   /**
   Checks to see if the computer has been defeated.
   Returns True if all computer ships have been sunk, false otherwise
   @return boolean true if computer lost
   */
   public boolean computerDefeated()
   {
      return computer.gameOver();
   }
   /**
   Returns a string representation of both boards well labelled
   @return String string boards
   */
   public String toString()
   {
      return String.format("COMPUTER\n%s\nUSER\n%s", 
                           computer.toString(), player.toString());
   }
}