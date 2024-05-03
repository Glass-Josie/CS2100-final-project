/**
computer board class 
is a board
finished!
*/
import java.util.*;
import java.io.*;
public class ComputerBoard extends Board
{
   //Constructor
   /**
   @param String filename to be opened
   */
   public ComputerBoard(String filename)
   {
      super(filename); 
   }
   
   //methods
   /**
   Takes a move and makes it AGAINST this computerboard. Takes in move to be applied.
   Returns either null, or, if the move sank a ship, a String along the lines of "You sank
   My Battleship!"
   if a ship was sunk, update the layout to change _HIT values to _SUNK values
   @param Move move against board
   @return String null or message
   */
   public String makePlayerMove(Move move)
   {
      CellStatus cs = applyMoveToLayout(move);
      
      //if that move is a...
      if (cs == CellStatus.AIRCRAFT_CARRIER)
      {
         // if this ship type was sunk after that move...
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
            return "You sunk my Aircraft Carrier!";
         }
         else
            return null;
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
            return "You sunk my Battleship!";
         }
         else
            return null;
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
            return "You sunk my Cruiser!";
         }
         else
            return null;
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
            return "You sunk my Destroyer!";
         }
         else
            return null;
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
            return "You sunk my Sub!";
         }
         else
            return null; 
      } 
   }
   
   /**
   Returns a String representation of the ComputerBoard, 
   displaying the first character
   of the String returned by the toString method overridden in CellStatus
   */
   @Override
   public String toString()
   {
      String compBoard = "";
      compBoard += String.format("  1 2 3 4 5 6 7 8 9 10\n");
      for (int r = 0; r < 10; r++)
      {
         compBoard += (char)('A' + r);
         compBoard += " ";
         for (int c = 0; c < 10; c++)
         {
            compBoard += getLayout().get(r).get(c).toString().charAt(0);
            compBoard += " ";
         }
         compBoard += String.format("\n");
      }
      return compBoard;

   }
   
}