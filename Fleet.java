/**
fleet class
has a ship
finished!
*/
public class Fleet
{
   //variables
   private Battleship battleship;
   private AircraftCarrier aircraftCarrier;
   private Cruiser cruiser;
   private Sub sub;
   private Destroyer destroyer;
   
   //constructor
   //Initializes Ship fields.
   public Fleet()
   {
      battleship = new Battleship();
      aircraftCarrier = new AircraftCarrier();
      cruiser = new Cruiser();
      sub = new Sub();
      destroyer = new Destroyer();
   }
   
   //methods
   /**
   Informs the appropriate ship that it has been hit, and returns true if this sank the
   ship, and false if it did not
   @return boolean true if ship sank
   @param Ship shipType shiptype to update
   */ 
   public boolean updateFleet(ShipType shipType)
   {
      if (shipType == ShipType.ST_AIRCRAFT_CARRIER)
      {
         return aircraftCarrier.hit();
      }
      else if (shipType == ShipType.ST_BATTLESHIP)
      {
         return battleship.hit();
      }
      else if (shipType == ShipType.ST_CRUISER)
      {
         return cruiser.hit();
      }
      else if (shipType == ShipType.ST_SUB)
      {
         return sub.hit();
      }
      else
      {
         return destroyer.hit();
      }
   }
   
   /**
   Returns true if all ships have been sunk, returns false if 
   @return boolean game over?
   */
   public boolean gameOver()
   {
      if ((battleship.getSunk())
            && (cruiser.getSunk())
               && (sub.getSunk())
                  && (destroyer.getSunk())
                     && (aircraftCarrier.getSunk()))
         return true;
      else
         return false;
   } 
}