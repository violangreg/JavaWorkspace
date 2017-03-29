//Greg Paolo D. Violan, 011706641, Project3 CECS277
/**
 * Computer class uses a HashMap to save the patterns of the user and then uses that to <br>
 * predicts the next move of the user with the makePrediction method, it also has storePattern method to store <br>
 * the pattern into the HashMap. It also has printPattern method for debugging 
 */

/**
 * imports necessary java io and utilities from library
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;
@SuppressWarnings("serial")
public class Computer implements Serializable {
	/**
	 * makePrediction method uses the current pattern of the user to predict the next move
	 * @param s, the pattern of the user
	 * @return prediction, the computer's prediction against the player 
	 */
	//INCOMPLETE
	public int makePrediction(String s){
		int prediction = 0;
		
		s = s.substring(0, 3); //cutting the pattern
		
		Pattern rock = new Pattern(s + "r");
		Pattern paper = new Pattern(s + "p");
		Pattern scissors = new Pattern(s + "s");
		
		Random rand = new Random();
		int rockAndPaper = rand.nextInt(2) + 1;
		int paperAndScissors = rand.nextInt(3) + 2;
		
		int rockAndScissors = rand.nextInt(2) + 1;
		if(rockAndScissors == 2){
			rockAndScissors = 3;
		}
		
		if(patterns.containsKey(rock) && patterns.containsKey(paper) && patterns.containsKey(scissors)){
			if(patterns.get(rock) > patterns.get(paper) && patterns.get(rock) > patterns.get(scissors)){
				prediction = 2;
			}
			else if(patterns.get(paper) > patterns.get(rock) && patterns.get(paper) > patterns.get(scissors)){
				prediction = 3;
			}
			else if(patterns.get(scissors) > patterns.get(rock) && patterns.get(scissors) > patterns.get(paper)){
				prediction = 1;
			}
			else if(patterns.get(rock) > patterns.get(paper) && patterns.get(rock) == patterns.get(scissors)){
				prediction = rockAndPaper;
			}
			else if(patterns.get(rock) == patterns.get(paper) && patterns.get(rock) > patterns.get(scissors)){
				prediction = paperAndScissors; // PAPER AND SCISSORS
			}
			else if(patterns.get(paper) > patterns.get(rock) && patterns.get(paper) == patterns.get(scissors)){
				prediction = rockAndPaper; // PAPER AND SCISSORS
			}
			else if(patterns.get(paper) == patterns.get(rock) && patterns.get(paper) > patterns.get(scissors)){
				prediction = paperAndScissors;
			}
			else if(patterns.get(scissors) > patterns.get(paper) && patterns.get(scissors) == patterns.get(rock)){
				// ROCK AND SCISSORS
				prediction = rockAndPaper;
			}
			else if(patterns.get(scissors) == patterns.get(paper) && patterns.get(scissors) > patterns.get(rock)){
				prediction = rockAndScissors;
			}
			else{
				prediction = rand.nextInt(3) + 1;
			}
		}
		else if(patterns.containsKey(rock) && patterns.containsKey(paper)){
			if(patterns.get(rock) > patterns.get(paper)){
				prediction = 2;
			}
			else if(patterns.get(rock) < patterns.get(paper)){
				prediction = 3;
			}
			else{
				prediction = paperAndScissors;
			}
		}
		else if(patterns.containsKey(rock) && patterns.containsKey(scissors)){
			if(patterns.get(rock) > patterns.get(scissors)){
				prediction = 2;
			}
			else if(patterns.get(rock) < patterns.get(scissors)){
				prediction = 1;
			}
			else{
				prediction = rockAndPaper;
			}
		}
		else if(patterns.containsKey(paper) && patterns.containsKey(scissors)){
			if(patterns.get(paper) > patterns.get(scissors)){
				prediction = 3;
			}
			else if(patterns.get(paper) < patterns.get(scissors)){
				prediction = 1;
			}
			else{
				prediction = rockAndScissors;
			}
		}
		else if(patterns.containsKey(rock)){
			prediction = 2;
		}
		else if(patterns.containsKey(paper)){
			prediction = 3;
		}
		else if(patterns.containsKey(scissors)){
			prediction = 1;
		}
		else{
			prediction = rand.nextInt(3) + 1;
		}
		
		return prediction;
	}
	/**
	 * storePattern method stores the pattern into the HashMap as keys
	 * @param s, string of the pattern
	 */
	public void storePattern(String s){
		Pattern p = new Pattern(s);
		int value = 1;
	
		if(patterns.containsKey(p)){
			value = patterns.get(p);
			++value;
		}
		patterns.put(p, value);
	}
	/**
	 * printPattern method prints out the whole HashMap and its values
	 * mainly for debugging purposes
	 */
	public void printPattern(){
		for(Pattern s : patterns.keySet()){
			String key = s.toString();
			String value = patterns.get(s).toString();
			System.out.println(key + " " + value);
		}
	}
	/**
	 * private HashMap patterns, stores the patterns here for predicting
	 */
	private HashMap<Pattern, Integer> patterns = new HashMap<Pattern, Integer>();
}