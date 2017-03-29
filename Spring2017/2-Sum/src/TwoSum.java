// Greg Paolo Violan 011706641
// March 19. 2017
// CECS328

// Two Sum goes through array A and finds two numbers that add to the target sum.
// I found the HashMap and HashEntry class from http://www.algolist.net/Data_structures/Hash_table/Simple_example
public class TwoSum 
{
	// Declaring a hash table with implemented HashMap
	private static HashMap hashTable;
	public static void main(String[] args) 
	{
		int[] A = {2, 13, 8, 38, 63, 9, 11, 4, 1, 3};			// Array A set of integers
		int targetSum = 72;										// The target sum of the two integers
		int sum = 0;											// Declaring sum variable holder
		boolean found = false;									// Boolean used if sum is found
		
		hashTable = new HashMap();								// Instantiating a HashMap
		
		for(int i = 0; i < A.length; i++)						// Putting all integers in the hash table
			hashTable.put(i, A[i]);
		
		// Used to add all the values in hash table to find the target sum
		for(int i = 0; i < A.length; i++)
		{
			for(int j = 0; j < A.length; j++)
			{
				// Logical flow: != -1 since -1 is an empty hash, hash i != hash i+j ( don't want to add same hashes )
				if (hashTable.get(i) != -1 && hashTable.get(i + j) != -1 && hashTable.get(i) != hashTable.get(i + j))
				{
					sum = hashTable.get(i) + hashTable.get(i + j);			// Get the sum of the two integers
					//System.out.println(sum + " = " + hashTable.get(i) + " + " + hashTable.get(i + j)); used for testing/debugging purposes
					
					if(sum == targetSum)
					{
						System.out.println("Found: " + sum + " = " + hashTable.get(i) + " + " + hashTable.get(i + j));
						found = true;
					}
				}
			} // end of j loop
		} // end of i loop
		
		if(!found)
			System.out.println("Target sum " + targetSum + " was not found");		// if target sum is not found print statement
	}
}