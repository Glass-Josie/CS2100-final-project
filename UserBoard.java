/**
User board class
is a board
finished!
*/
import java.util.*;
import java.util.Random;
public class UserBoard extends Board
{
   //variables
   private ArrayList<Move> moves;
   private Random rand;
   
   //Constructor
   /**
   Passes the filename on to the Board constructor. Initialize the Random object and
   the ArrayList of all possible Moves
   @param String filename to be opened
   */
   public UserBoard(String filename)
   {
      super(filename);
      rand = new Random();
      moves = new ArrayList<Move>();
      for (int r = 0; r < 10 ;r++)
      {
         for (int c = 0; c < 10; c++)
         {
            moves.add(new Move(r,c));
         }   
      }
   }
   
   //methods
   /**
   Computer move against UserBoard. Selects and makes a move AGAINST this board.
   Returns an array of two Strings. The first is the move the computer made in user
   readable form. The second is either null, or, if the move resulted in a ship being
   sunk, a string along the lines of "You sunk my Battleship!
   @return String[] array output
   */
   public String[] makeComputerMove()
   {
      String[] output = new String[2];
      ArrayList<String> alphabet = new ArrayList<String>(10);
      alphabet.add("A");
      alphabet.add("B");
      alphabet.add("C");
      alphabet.add("D");
      alphabet.add("E");
      alphabet.add("F");
      alphabet.add("G");
      alphabet.add("H");
      alphabet.add("I");
      alphabet.add("J");
      //get random move, converts it, puts it in first section of array
      Move randMove = moves.get(rand.nextInt(moves.size()));
      String moveSForm = randMove.toString();
      //exception handling: removing options so no repeats
      moves.remove(moves.indexOf(randMove));
      output[0] = moveSForm;
      
      CellStatus cs = applyMoveToLayout(randMove);
      
      //if that move is a...
      if (cs == CellStatus.AIRCRAFT_CARRIER)
      {
         //if this ship type was sunk after that move...
         if (getFleet().updateFleet(ShipType.ST_AIRCRAFT_CARRIER))
         {
            //make all the aircraft carrier hit cells equal to sunk
            for (int i = 0; i < 10; i++)
            {
               for(int j = 0; j < 10; j++)
               {
                  if (getLayout().get(i).get(j) == CellStatus.AIRCRAFT_CARRIER_HIT)
                     getLayout().get(i).set(j, CellStatus.AIRCRAFT_CARRIER_SUNK);
               }
            }
            output[1] = "You sunk my Aircraft Carrier!";
         }
         else
            output[1] = null;
         
      }
      else if (cs == CellStatus.BATTLESHIP)
      {
         if (getFleet().updateFleet(ShipType.ST_BATTLESHIP))
         {  
            for (int i = 0; i < 10; i++)
            {
               for(int j = 0; j < 10; j++)
               {
                  if (getLayout().get(i).get(j) == CellStatus.BATTLESHIP_HIT)
                     getLayout().get(i).set(j, CellStatus.BATTLESHIP_SUNK);
               }
            }
            output[1] = "You sunk my Battleship!";
         }
         else
            output[1] = null;
         
      }
      else if (cs == CellStatus.CRUISER)
      {
         if (getFleet().updateFleet(ShipType.ST_CRUISER))
         { 
            for (int i = 0; i < 10; i++)
            {
               for(int j = 0; j < 10; j++)
               {
                  if (getLayout().get(i).get(j) == CellStatus.CRUISER_HIT)
                     getLayout().get(i).set(j, CellStatus.CRUISER_SUNK);
               }
            }
            output[1] = "You sunk my Cruiser!";
         }
         else
            output[1] = null;
      }
      else if (cs == CellStatus.DESTROYER)
      {  
         if (getFleet().updateFleet(ShipType.ST_DESTROYER))
         { 
            for (int i = 0; i < 10; i++)
            {
               for(int j = 0; j < 10; j++)
               {
                  if (getLayout().get(i).get(j) == CellStatus.DESTROYER_HIT)
                     getLayout().get(i).set(j, CellStatus.DESTROYER_SUNK);
               }
            }
            output[1] = "You sunk my Destroyer!";
         } 
         else
            output[1] = null;
         
      }
      else
      {  
         if (getFleet().updateFleet(ShipType.ST_SUB)) 
         {  
            for (int i = 0; i < 10; i++)
            {
               for(int j = 0; j < 10; j++)
               {
                  if (getLayout().get(i).get(j) == CellStatus.SUB_HIT)
                     getLayout().get(i).set(j, CellStatus.SUB_SUNK);
               }
            }
            output[1] = "You sunk my Sub!";
         }
         else
            output[1] = null;
         
      }
      return output;
   }
   
   /**
   Returns a String representation of the ComputerBoard, displaying the second
   character of the String returned by the toString method overridden in CellStatus
   @return String userBoard
   */
   @Override
   public String toString()
   {
      String userBoard = "";
      userBoard += String.format("  1 2 3 4 5 6 7 8 9 10\n");
      for (int r = 0; r < 10; r++)
      {
         userBoard += (char)('A' + r);
         userBoard += " ";
         for (int c = 0; c < 10; c++)
         {
            userBoard += getLayout().get(r).get(c).toString().charAt(1);
            userBoard += " ";
         }
         userBoard += String.format("\n");
      }
      return userBoard;
   }
}

