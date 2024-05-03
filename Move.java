/**
Move Class
finished!
*/
import java.util.*;
public class Move
{
   //variables
   private int row;
   private int col;
      
   //constructors
   /**
   @param int row index, int column index
   */
   public Move(int row, int col)
   {      
      this.row = row;
      this.col = col;    
   }
   /**
   @param String input move in letter column form
   */
   public Move(String input)
   { 
      row = input.charAt(0) - 65;
      if (input.length() == 3)
         col = 9;
      else
         col = ((int)(input.charAt(1))) - 49; 
     
   }
   
   //methods
   /**
   Accessor for row. Using 'row' rather than 'getRow' allows for more compact code when
   manipulating ArrayLists.
   @return int row index
   */
   public int row()
   {
      return row;
   }
   /**
   Accessor for col. Using 'col' rather than 'getCol' allows for more compact code when
   manipulating arrayLists.
   @return int col indici
   */
   public int col()
   {
      return col;
   }
   /**
   Returns a 2 to 3-character string consisting of a letter in the range A-J followed by a
   number in the range 1-10. Provides for ease of display of move values in an interface
   @return String formatted move
   */
   public String toString()
   {
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
      return String.format("%s%d", alphabet.get(row), col + 1);
   }
   
}