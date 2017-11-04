import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class FirstSet {
	
	public static void main(String[] args) throws FileNotFoundException
	{			
		HashMap<String, String[]> grammarHashMap = new HashMap<String, String[]>();
		HashMap<String, Integer> nt_HashMap = new HashMap<String, Integer>();
		
		// take in a input file
		File file = new File("cfg.txt");
		// Scans file
		Scanner fileScnr = new Scanner(file);
	
		// 1st line of file.txt -- nonterminals
		String nonTerminals = fileScnr.nextLine();
		//System.out.println(nonTerminals); // debug
		
		// split nonterminals and store in an array
		String ntArr[] = nonTerminals.split("\\s+");
		
		// store it to a hashmap with 0 as value (using value as a determinator of how many lines a grammar has)
		for(int i = 0; i < ntArr.length; i++)
		{
			nt_HashMap.put(ntArr[i], 0);
		}
		
		String last_nt = "gibberish";
		// take all the lines in the .txt and convert it to a grammar
		while(fileScnr.hasNextLine())
		{
			String line[] = fileScnr.nextLine().split("\\s+");
			
			// take out the grammar name and arrow
			String[] omitGrammarAndArrow = new String[line.length-2]; 
			for(int i = 0; i < line.length-2; i++)
			{
					omitGrammarAndArrow[i] = line[i+2];
			}
			
			// if its same grammar that means it has multiple lines, we have to save how many lines a grammar has and give it a different key
			if(last_nt.equals(line[0]))
			{
				int nt_value = nt_HashMap.get(line[0]);
				String tempKey = line[0] + Integer.toString(nt_value);
				//System.out.println(tempKey); // debug
				
				//if array is empty, put in epsilon
				if(omitGrammarAndArrow.length == 0){
					String[] tempArr = {"eps"};
					grammarHashMap.put(tempKey, tempArr);
				}
				else grammarHashMap.put(tempKey, omitGrammarAndArrow);
				
				nt_value = nt_value + 1;
				nt_HashMap.put(line[0], nt_value);
			} // if not, just add onto our hashmap
			else
			{
				// first one
				int nt_value = nt_HashMap.get(line[0]);
				String tempKey = line[0] + Integer.toString(nt_value);
				grammarHashMap.put(tempKey, omitGrammarAndArrow);
				nt_value = nt_value + 1;
				nt_HashMap.put(line[0], nt_value);
			}
			
			// save last grammar name to check if it has multiple lines 
			last_nt = line[0];
		}
		
		//System.out.println(Arrays.toString(grammarHashMap.get("stmt_list1")));
		
		FindFirstSet(nt_HashMap, grammarHashMap, ntArr, 1);
		
		fileScnr.close();
		
	}

	
	public static void FindFirstSet(HashMap<String, Integer> nt_HM, HashMap<String, String[]> g_HM, String[] ntArr, int k)
	{
		
		int nt_value = nt_HM.get(ntArr[k]);
		System.out.println(nt_value);
		ArrayList<String> fArrList = new ArrayList<String>();
		for(int i = 0; i < nt_value; i++)
		{
			String tempKey = ntArr[k] + Integer.toString(i);
			String[] tempArr = g_HM.get(tempKey);
			
			
			if(IsNonTerminal(ntArr, tempArr[0]))
			{
				int found = FindElement(ntArr, tempArr[0]);
				System.out.println(found);
				//FindFirstSet(nt_HM, g_HM, ntArr, found);
			}
			else {
				fArrList.add(tempArr[0]);
			}
		}
		for(int i = 0; i < fArrList.size(); i++)
			System.out.println(fArrList.get(i));
		
		
		

	}
	
	// checks if its terminal
	public static boolean IsNonTerminal(String[] ntArr, String nonTerminal)
	{
		boolean validate = false;
		for(int i = 0; i < ntArr.length; i++)
		{
			//System.out.println(nt_arr[i]); // debug
			if(ntArr[i].equals(nonTerminal))
				validate = true;
		}
		return validate;
	}
	
	public static int FindElement(String[] arr, String target)
	{
		int found = (Integer) null;
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i].equals(target))
				found = i;
		}
		return found;
	}
	
}
