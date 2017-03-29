import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class MedianTest {

	public static void main(String[] args) {
		File file = new File("Problem_Set 02_dataset_01.txt");
		double [] array = new double[1000];
		int i = 0;
		
		try
		{
			Scanner scnr = new Scanner(file);
			do
			{
				String read = scnr.nextLine();
				array[i] = Double.parseDouble(read);
//				System.out.println(array[i]);
				i++;
			}while(scnr.hasNextLine());
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FNFE");
		}
		
		Arrays.sort(array);
		double median;
		
//		for(int i1 = 0; i1 < array.length; i1++){
//			System.out.println(array[i1]);
//		}
		
		if(array.length%2 == 0) // even
		{
			median = ( array[array.length/2] + array[array.length/2+1] ) / 2;
		}
		else
		{
			median = ( array[array.length/2] );
		}
		
		System.out.println("Median: " + median);
	}

}
