/**
super class called board with abstract classes
finished!
*/
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public abstract class Board
{

   //variables
   private ArrayList<ArrayList<CellStatus>> layout;
   private Fleet fleet;
   public final static int SIZE = 10;
      
   
   //constructor
   /**Takes filename as a parameter. Initializes layout, initially setting all cells to
   CellStatus.NOTHING. Gets information from file and add ships to the layout.
   Initializes Fleet
   @param String filename file to be opened
   */
   public Board(String filename)
   {
      
      // create ArrayList of ArrayLists (think of this as the rows)
      layout = new ArrayList<>(SIZE); 

      //System.out.println("***************************");
   
      // now create the columns
      for (int r = 0; r < 10; r++) 
      {
         ArrayList<CellStatus> temp = new ArrayList<>(SIZE);
         for (int c = 0; c < SIZE; c++)
             temp.add(CellStatus.NOTHING);
         layout.add(temp);
      }
      
      
      //initializing file stuff
      //File myFile = null;
      Scanner inputFile = null; 
        
      //opening file to read and catching exception
      try
      {
          inputFile = new Scanner(new File(filename));  
      }
      catch(IOException e)
      {
          System.out.println("The file was not found, exiting");
          System.exit(1);
      }

      while (inputFile.hasNext())
      {  
          /**
          reading every line and splitting it up 
          using its contents to create 
          the starting and stopping points of each ship
          */
          
          String line = inputFile.nextLine();
          String[] splitArray = line.split(" ");
          String ship = splitArray[0].substring(0,1);
          int startRow = splitArray[1].charAt(0) - 65;
          int startCol = Integer.parseInt(splitArray[1].substring(1)) - 1;
          int endRow = splitArray[2].charAt(0) - 65;
          //placing ship
          //vertically
          if (startRow != endRow)
          {
             if (ship.equals("C"))
             {
                for (int i = startRow; i < (startRow + 3); i++)
                {
                   layout.get(i).set(startCol, CellStatus.CRUISER);
                   
                }
             }
             else if (ship.equals("A"))
             {
                for (int i = startRow; i < (startRow + 5); i++)
                {
                   layout.get(i).set(startCol, CellStatus.AIRCRAFT_CARRIER);
                }
             }
             else if (ship.equals("B"))
             { 
                for (int i = startRow; i < (startRow + 4); i++)
                {
                   layout.get(i).set(startCol, CellStatus.BATTLESHIP);
                }
             }
             else if (ship.equals("D"))
             {
                for (int i = startRow; i < (startRow + 2); i++)
                {
                   layout.get(i).set(startCol, CellStatus.DESTROYER);
                }
             }
             else
             {             
                for (int i = startRow; i < (startRow + 3); i++)
                {
                   layout.get(i).set(startCol, CellStatus.SUB);
                }
             }
             
          }
          else //horizontally
          { 
             if (ship.equals("C"))
             {
                for (int i = startCol; i < (startCol + 3); i++)
                {
                   layout.get(startRow).set(i, CellStatus.CRUISER);
                }
             }
             else if (ship.equals("A"))
             {
                for (int i = startCol; i < (startCol + 5); i++)
                {
                   layout.get(startRow).set(i, CellStatus.AIRCRAFT_CARRIER);
                }
             }
             else if (ship.equals("B"))
             {
                for (int i = startCol; i < (startCol + 4); i++)
                {
                   layout.get(startRow).set(i, CellStatus.BATTLESHIP);
                }
             }
             else if (ship.equals("D"))
             {
                for (int i = startCol; i < (startCol + 2); i++)
                {
                   layout.get(startRow).set(i, CellStatus.DESTROYER);
                }
             }
             else
             {
                for (int i = startCol; i < (startCol + 3); i++)
                {
                   layout.get(startRow).set(i, CellStatus.SUB);
                }
             }  
          }
      }
         
      //closing file
      inputFile.close();
       
      //initializing fleet
      fleet = new Fleet();
   }
   
   //methods
   /**
   Applies a move to layout. If the targeted cell does not contain a ship, it is set to
   CellStatus.NOTHING_HIT. If it contains a ship, the cell is changed from, for instance,
   CellStatus.SUB to CellStatus.SUB_HIT. It returns the original CellStatus of the
   targeted cell
   @param Move move object
   @return CellStatus object of former cell status
   */
   public CellStatus applyMoveToLayout(Move move)
   {
      //for hits
      if (layout.get(move.row()).get(move.col()) == CellStatus.SUB)
      {
         
         layout.get(move.row()).set(move.col(), CellStatus.SUB_HIT); 
         return CellStatus.SUB;
      }
      else if (layout.get(move.row()).get(move.col()) == CellStatus.DESTROYER)
      {
         //fleet.updateFleet(ShipType.ST_DESTROYER);
         layout.get(move.row()).set(move.col(), CellStatus.DESTROYER_HIT); 
         return CellStatus.DESTROYER;
      }

      else if (layout.get(move.row()).get(move.col()) == CellStatus.AIRCRAFT_CARRIER)
      {
         //fleet.updateFleet(ShipType.ST_AIRCRAFT_CARRIER);
         layout.get(move.row()).set(move.col(), CellStatus.AIRCRAFT_CARRIER_HIT); 
         return CellStatus.AIRCRAFT_CARRIER;
      }
      else if (layout.get(move.row()).get(move.col()) == CellStatus.BATTLESHIP)
      {
         //fleet.updateFleet(ShipType.ST_BATTLESHIP);
         layout.get(move.row()).set(move.col(), CellStatus.BATTLESHIP_HIT); 
         return CellStatus.BATTLESHIP;
      }
      else if (layout.get(move.row()).get(move.col()) == CellStatus.CRUISER)
      {
         //fleet.updateFleet(ShipType.ST_CRUISER);
         layout.get(move.row()).set(move.col(), CellStatus.CRUISER_HIT); 
         return CellStatus.CRUISER;
      }
      //for misses
      else
      {      
         layout.get(move.row()).set(move.col(), CellStatus.NOTHING_HIT);
         return CellStatus.NOTHING;
      }
               
   }
   /**
   Takes a move as a parameter and determines if the spot is available. (any CellStatus
   that isn’t hit or sunk)
   @return boolean true is available
   @param Move move object
   */
   public boolean moveAvailable(Move move)
   {
      //checking first value of enum type to see if its available  
         if (layout.get(move.row()).get(move.col()).toString().charAt(0) == 'o')
         return true;
      else
         return false;
   }
   /**
   Returns a reference to the layout (not a deep copy!)
   @return ArrayList<ArrayList<CellStatus>>
   */
   public ArrayList<ArrayList<CellStatus>> getLayout()
   {
      return layout;
   }
   /**
   Returns a reference to the Fleet object (not a deep copy!)
   @return Fleet fleet
   */
   public Fleet getFleet()
   {
      return fleet;
   }
   /**
   Returns true if all ships have been sunk, false otherwise. Hint: Fleet has this info.
   @return boolean true if game over
   */
   public boolean gameOver()
   {
      if (fleet.gameOver())
         return true;
      else
         return false;
   }
   
}