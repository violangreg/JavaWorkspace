// Greg Paolo Violan
// 011706641
// CECS424 Sec 01
// 10-8-2017
import java.io.*;
import java.util.*;
//finds the first and follow set from a file with non-terminals on first line and its production rule below it
public class Lab3_ {
	private static int row = -1;
	private static ArrayList<String> non_terminal = new ArrayList<String>();
	private static ArrayList<ArrayList<String>> stem = new ArrayList<ArrayList<String>>();
	private static HashMap<String, ArrayList<String>> first_set_stem = new HashMap<String, ArrayList<String>>();
	private static HashMap<String, ArrayList<String>> first_set = new HashMap<String, ArrayList<String>>();
	private static HashMap<String, ArrayList<String>> follow_set = new HashMap<String, ArrayList<String>>();
	private static HashMap<String, ArrayList<String>> follow_set_final = new HashMap<String,ArrayList<String>>();

	public static void main(String[] args) throws IOException {
		String line = null;
		FileReader file = new FileReader(args[0]);
		BufferedReader file_br = new BufferedReader(file);
		
		//non_terminal flag: reads first line of file for non_terminals
		boolean flag = false;	
		
		//store all non_terminal and production rule to map
		while ((line = file_br.readLine()) != null) {
			StringTokenizer input = new StringTokenizer(line);
			
			if (flag == false) {
				while(input.hasMoreTokens())
					non_terminal.add(input.nextToken(" "));
				flag = true;
			} else {
				String firstToken = input.nextToken();
				stem.add(row, new ArrayList<String>());
				stem.get(row).add(firstToken);
				
				//adding first token for first_set of map
				first_set_stem.putIfAbsent(firstToken, new ArrayList<String>());		
				String nextToken = "";
				// omit anything before/and arrow
				input.nextToken();
				//if its empty, add eps
				if (!input.hasMoreElements()) {
					nextToken = "eps";
					first_set_stem.get(firstToken).add(nextToken);
					stem.get(row).add(nextToken);
				} 
				else {
					nextToken=input.nextToken();
					first_set_stem.get(firstToken).add(nextToken);
					stem.get(row).add(nextToken);
					while(input.hasMoreTokens()) {
						stem.get(row).add(input.nextToken());
					}
				}		
			}
			row++;
		}
		
		//call first function to get first_set to add to first_set multimap
		for(String key : first_set_stem.keySet()){
			ArrayList<String> temp = new ArrayList<String>();
			FirstSet(temp,key);
			first_set.put(key,new ArrayList<String>());
			for(int i = 0; i < temp.size(); i++) {
				if(!first_set.get(key).contains(temp.get(i))) {
					first_set.get(key).add(temp.get(i));
				}
			}
		}
		
		//initialize the follow set and final follow set
		for(String key : first_set.keySet()) {
			follow_set.put(key,new ArrayList<String>());
			follow_set_final.put(key,new ArrayList<String>());
		}
		
		//calculate the follow set
		for (int k = 0; k < 2; k++) {
			for(int i = 0; i < stem.size(); i++) {
				int size = stem.get(i).size();
				for (int j = size-1; j >= 0; j--) {
					String lastIndexValue = stem.get(i).get(j);
					//first case when the last index is terminal
					if(!non_terminal.contains(lastIndexValue)){
						//this condition to avoid this type of stem (stem -> eps)
						if(size > 2) {
							if(non_terminal.contains(stem.get(i).get(j - 1))) {
								follow_set.get(stem.get(i).get(j-1)).add(lastIndexValue);
							}
						}
						break;
					}
					else {
						//second case when the last index is nonterminal
						if(!(follow_set.get(lastIndexValue).containsAll(follow_set.get(stem.get(i).get(0))))) {
							follow_set.get(lastIndexValue).addAll(follow_set.get(stem.get(i).get(0)));
						}
						if (size > 2) {
							if (non_terminal.contains(stem.get(i).get(j-1))){
								if (!(follow_set.get(stem.get(i).get(j-1)).containsAll(first_set.get(lastIndexValue)))) {
									follow_set.get(stem.get(i).get(j-1)).addAll(first_set.get(lastIndexValue));
								}
							}
							if (!first_set.get(lastIndexValue).contains("eps")) {
								break;
							}
							else {
								//not breaking the inner loop since first set of last nonterminal has "eps"
								//we have to consider the index which is next to it
								//decreasing the size of the statement when coming back to the loop
								size--;		
							}
						}
						else {
							break;
						}
					}
				}
			}
		}		
		
		//copying follow_set values into follow_set_final to avoid repeating values from rerunning the calculation twice 
		for(String key : follow_set.keySet()) {
			for (int j = 0; j < follow_set.get(key).size(); j++) {
				String addString = follow_set.get(key).get(j);
				if (!(addString.equalsIgnoreCase("eps")) && !follow_set_final.get(key).contains(addString)){
					follow_set_final.get(key).add(addString);
				}
			}
		}
		
		//display first set
		for (String key : first_set.keySet()) {
			String output = Arrays.toString(first_set.get(key).toArray()).substring(1, first_set.get(key).toString().length()-1);
			System.out.println("First(" + key + ") = {" + output + "}");
		}
		System.out.println();
		//display follow set
		for (String key : follow_set_final.keySet()) {
			String output = Arrays.toString(follow_set_final.get(key).toArray()).substring(1, follow_set_final.get(key).toString().length()-1);
			System.out.println("Follow(" + key + ") = {" + output + "}");
		}
	}
	
	//find first set for each stem
	public static void FirstSet(ArrayList<String> list, String key) 
	{
		for(int i = 0; i < first_set_stem.get(key).size(); i++) {
			if (non_terminal.contains(first_set_stem.get(key).get(i))) {
				FirstSet(list, first_set_stem.get(key).get(i));
			}
			else{
				list.add(first_set_stem.get(key).get(i));
			}
		}
	}
	
}



