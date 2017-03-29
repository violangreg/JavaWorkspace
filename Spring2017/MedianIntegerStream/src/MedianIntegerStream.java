/**
 * Name: Greg Paolo Violan ID: 011706641
 * CECS328 Data Structure and Algorithms Sec 01
 * Problem Set 02, coding question #15
 * Most credit/source used from https://gist.github.com/Vedrana/3675434
 */

//imported libraries
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


/**
 * Class description: Given a stream of unsorted integers, find the median element in sorted order at any given time.
 * @author Greg / Credit Vedrana
 */
public class MedianIntegerStream 
{
	public Queue<Integer> minHeap;
	public Queue<Integer> maxHeap;
	public int numOfElements;
	
	public static void main(String[] args) 
	{
		Scanner scnr = new Scanner(System.in);
		String fileName = null, input = null;
		File file = null;
		boolean s = false;
		MedianIntegerStream streamMedian = new MedianIntegerStream();

		// Menu
		System.out.println("Median Of Integer Stream"
				+ "\n------------------------------"
				+ "\n1. Dataset 01"
				+ "\n2. Dataset 02"
				+ "\n3. Input Stream of values manually: print by input 'p', exit by input 'e'"
				+ "\n4. Exit or input 'e'");
		
		// Input Handler
		while(!s)
		{
			input = scnr.nextLine();	
			if(input.equals("1"))
			{
				fileName = "Problem_Set 02_dataset_01.txt";
				s = true;
			}
			else if(input.equals("2"))
			{
				fileName = "Problem_Set 02_dataset_02.txt"; 
				s = true;
			}
			else if(input.equals("3"))
			{
				do
				{
					String value = scnr.nextLine();
					if(isInteger(value))
						streamMedian.addNumberToStream(Integer.parseInt(value));
					else if(value.equals("p"))
						System.out.println("Median: " + streamMedian.getMedian());
					else if(value.equals("e"))
						System.exit(0);
					else
						System.out.println("Value is not numeric");
				}while(true);
				
			}
			else if(input.equals("4") || input.equals("e"))
				System.exit(0);
			else
				System.out.println("Invalid input");
		}
		
		if(s)
		{
			file = new File(fileName);
			
			try
			{
				scnr = new Scanner(file);
				do
				{
					String read = scnr.nextLine();
					System.out.println(read);
					streamMedian.addNumberToStream(Integer.parseInt(read));
				}while(scnr.hasNextLine());
			}catch(FileNotFoundException e){
				System.out.println("FNFE");
			}
			
			System.out.println("Median: " + streamMedian.getMedian());
		}
		
	}
	
	/**
	 * Constructor initialing max and min heaps 
	 */
	public MedianIntegerStream() 
	{
		minHeap = new PriorityQueue<Integer>();
		maxHeap = new PriorityQueue<Integer>(10, new MaxHeapComparator()); 
		numOfElements = 0;
	}
	
	/**
	 * Uses two heaps, a max and a min to figure out the median on the fly by inserting and swapping between heaps using the number of elements
	 * @param num
	 */
	public void addNumberToStream(Integer num) 
	{
		maxHeap.add(num);									// add the value into the maxheap first
		if (numOfElements%2 == 0) 							// check if the number of elements/size is even
		{
			if (minHeap.isEmpty()) 							// check if minheap is empty
			{
				numOfElements++;							// increment size
				return;										// return
			}
			else if (maxHeap.peek() > minHeap.peek()) {		// check if maxheap's root is greater than minheap's root
				Integer maxHeapRoot = maxHeap.poll();		// remove the root of maxheap
				Integer minHeapRoot = minHeap.poll();		// remove the root of minheap
				maxHeap.add(minHeapRoot);					// switch the root of maxheap with the minheaps' root
				minHeap.add(maxHeapRoot);					// vice versa
			} 
		} else {											// if the number of elements/size is not even
			minHeap.add(maxHeap.poll());					// just add the maxheap's root into the minheap
		}
		numOfElements++;									// increment size
	}
	
	/**
	 * return the root of maxheap (since this will be the median) if the number of elements/size is not even
	 * else return (root of the max heap + root of the minheap / 2)
	 * @return the median
	 */
	public Double getMedian() 
	{
		if (numOfElements%2 != 0)
			return new Double(maxHeap.peek());
		else
			return (maxHeap.peek() + minHeap.peek()) / 2.0; 
	}
	
	/**
	 * compares two integers, if they are less than or greater than or equal to. 
	 * Return 0 if its equal to, negative if less than, positive if greater than.
	 */
	private class MaxHeapComparator implements Comparator<Integer> 
	{
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	}
	
	/**
	 * checks to see if the string is an integer
	 * @param s, the string
	 * @return true or false
	 */
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
}
