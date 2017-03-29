// Hash object class, key and a value pair
public class HashEntry
{	  // Declaring the key and value variables 
      private int key, value;
      // Hash object constructor
      HashEntry(int key, int value) 
      {
            this.key = key;
            this.value = value;
      }     
      /**
       * Returns the key
       * @return key, int
       */
      public int getKey() 
      {
            return key;
      }
      /**
       * Returns the value
       * @return value, int
       */
      public int getValue() 
      {
            return value;
      }
}