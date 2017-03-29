// HashMap aka HashTable, creates a table that uses Hash objects
public class HashMap 
{
    private final static int TABLE_SIZE = 128;				// Declaring table size
    HashEntry[] table;										// Declaring Hash objects array
    
    // HashMap constructor, instantiate table and null populate it
    HashMap() 
    {
          table = new HashEntry[TABLE_SIZE];				// Instantiating hashTable
          for (int i = 0; i < TABLE_SIZE; i++)				// Populate hashTable with nulls
                table[i] = null;
    }
    
    /**
     * Returns the hash value of the Hash object in the HashTable
     * @param key
     * @return if null = -1, else return hash value
     */
    public int get(int key) 
    {
          int hash = (key % TABLE_SIZE);								// get the hash of the key
          while (table[hash] != null && table[hash].getKey() != key)	// if the cell is already populated or not the value of the key, linear probe to the next cell
                hash = (hash + 1) % TABLE_SIZE;
          if (table[hash] == null)										// if hash isn't found return -1
                return -1;
          else
                return table[hash].getValue();							// else return the value of the key hash
    }
    /**
     * Create a Hash object and enter it into the table
     * @param key, int
     * @param value, int
     */
    public void put(int key, int value) 
    {
          int hash = (key % TABLE_SIZE);								// get the hash of the key
          while (table[hash] != null && table[hash].getKey() != key)	// input the hash into an empty cell
                hash = (hash + 1) % TABLE_SIZE;
          table[hash] = new HashEntry(key, value);						// assign hash object to that table
    }
}