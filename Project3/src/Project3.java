//Greg Paolo D. Violan, 011706641, Project3 CECS277
/**
 * Name: Greg Paolo D. Violan
 * Date: 3/2/16 last updated
 * CECS 277 PROJECT 3: ROCK PAPER SCISSORS AI PREDICTOR GAME. <br>
 * This program uses a HashMap to store the patterns of the user's inputs, it then uses those patterns to predict the next move of the user. <br>
 * This class has 8 methods main(), intro(), humanThrow(), computerThrow(), storing(), results(), displayScore(), saveGame() <br>
 * @author Greg Paolo Violan, 011706641
 */

/**
 * imported necessary io's and utilities
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Scanner;
public class Project3 {
	/**
	 * Main method of project 3 that has the local variables scores and constructed computer class <br>
	 * Main uses 7 methods, intro(), humanThrow(), computerThrow(), storing(), results(), displayScore(), saveGame() <br>
	 * @param args
	 */
	public static void main(String[] args) {
		Computer computer = new Computer();
		File f = new File("data.dat");
		String fourThrows = "";
		int humanScore = 0;
		int computerScore = 0;
		int tieScore = 0;
		
		//PROMPTS USER DIFFICULTY
		int mode = intro();
		
		//IF VETERAN MODE USE FILE
		if(mode == 2){
			if(f.exists()){
				try{
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
					computer = (Computer) in.readObject();
					in.close();

					System.out.println("-- VETERAN --");
					
				}catch(IOException e){
					System.out.println("Error processing file");
				}catch(ClassNotFoundException e){
					System.out.println("Cou1ld not find class.");
				}
			}
			else{
				System.out.println("VETERAN unavialable.. no data found");
			}
		}
		
		if(mode != 2) System.out.println("-- BEGINNER --");
		
		boolean s = false;
		while(!s){
			//PROMPT HUMMAN TO THROW
			int humanThrow = humanThrow();
			
			//STORE HUMAN THROWS
			if(humanThrow == 1)fourThrows = storing(computer, 1, fourThrows);
			else if(humanThrow == 2) fourThrows = storing(computer, 2, fourThrows);
			else if(humanThrow == 3) fourThrows = storing(computer, 3, fourThrows);
			else if(humanThrow == 4) {
				displayScore(humanScore, computerScore, tieScore);
				saveGame(computer, f);
			}
			//COMPUTER PREDICTS AND THROWS
			int computerThrow = computerThrow(computer, fourThrows);
			
			//CUT LAST THROW OF PATTERN
			if(fourThrows.length() == 4) fourThrows = fourThrows.substring(1);
			
			//DISPLAY RESULTS
			int scorePoints = results(humanThrow, computerThrow);
			
			//STORE AND DISPLAY SCORE
			if(scorePoints == 1) ++humanScore;
			else if(scorePoints == 2) ++computerScore;
			else ++tieScore;
			displayScore(humanScore, computerScore, tieScore);
		}
	}
	/**
	 * Introduces the user to the game and user choose difficulty
	 * @return mode of the game, 1 for beginner and 2 for veteran
	 */
	public static int intro(){
		Scanner scnr = new Scanner(System.in);
		String input;
		int mode = 0;
		System.out.println("Welcome to ROCK @ PAPER [] SCISSORS < game!");
		System.out.println("Computer difficulty: \n1. BEGINNER \n2. VETERAN");
		
		boolean s = false;
		while(!s){
			input = scnr.nextLine();
			if(input.equals("1")){
				mode = 1;
				s = true;
			}
			else if(input.equals("2")){
				mode = 2;
				s = true;
			}
			else System.out.println("**invalid input**");
		}
		
		System.out.println("");
		return mode;
	}
	/**
	 * humanThrow method prompts the user what to throw
	 * @return weapon, the user's throw input
	 */
	public static int humanThrow(){
		Scanner scnr = new Scanner(System.in);
		int weapon = 0;
		
		boolean s = false;
		System.out.println("-- THROW -- \n1. ROCK \n2. PAPER \n3. SCISSORS \n4. CANCEL");
		while(!s){
			String choice = scnr.nextLine();
			if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")){
				if(choice.equals("1")){
					System.out.println("You threw: ROCK");
					weapon = 1;
				}
				else if(choice.equals("2")){
					System.out.println("You threw: PAPER");
					weapon = 2;
				}
				else if(choice.equals("3")){
					System.out.println("You threw: SCISSORS");
					weapon = 3;
				}
				else{
					weapon = 4;
				}
				
				s = true;
			}else System.out.println("**invalid input**");
		}
		
		return weapon;
	}
	/**
	 * computerThrow method uses the fourThrows local variable from main and uses it to predict the next throw
	 * @param c, the computer object
	 * @param currentPattern, the pattern to predict
	 * @return prediction, the computer's throw to win the game!
	 */
	public static int computerThrow(Computer c, String currentPattern){
		int prediction = 0;
		
		if(currentPattern.length() < 4){
			Random rand = new Random();
			prediction = rand.nextInt(3) + 1;
		}
		else if(currentPattern.length() == 4){
			prediction = c.makePrediction(currentPattern);
		}
		
		if(prediction == 1) System.out.println("Computer threw: ROCK");
		else if(prediction == 2) System.out.println("Computer threw: PAPER");
		else if(prediction == 3) System.out.println("Computer threw: SCISSORS");
		
		return prediction;
	}
	/**
	 * storing method saves the user's throw and creates a pattern
	 * @param c, the computer object
	 * @param weapon, weapon of the user
	 * @param fourThrows, the current pattern
	 * @return fourThrows, the current pattern
	 */
	public static String storing(Computer c, int weapon, String fourThrows){
		if(weapon == 1){
			if(fourThrows.length() < 4){
				fourThrows += "r";
			}
		}
		else if(weapon == 2){
			if(fourThrows.length() < 4){
				fourThrows += "p";
			}
		}
		else if(weapon == 3){
			if(fourThrows.length() < 4){
				fourThrows += "s";
			}
		}
		
		if(fourThrows.length() == 4){
			c.storePattern(fourThrows);
		}
		
		return fourThrows;
	}
	/**
	 * results method shows who wins and displays it
	 * @param human, the human throw
	 * @param computer, the computer throw
	 * @return score, the result
	 */
	public static int results(int human, int computer){
		int score; //1: humanScore++, 2: computerScore++, 3: tieScore;
		if(human == 1 && computer == 1){
			System.out.println("Results: ROCK vs ROCK");
			System.out.println("-- Tie! --");
			score = 3;
		}
		else if(human == 1 && computer == 2){
			System.out.println("Results: ROCK vs PAPER");
			System.out.println("-- Computer Won! --");
			score = 2;
		}
		else if(human == 1 && computer == 3){
			System.out.println("Results: ROCK vs SCISSORS");
			System.out.println("-- You won! --");
			score = 1;
		}
		else if(human == 2 && computer == 1){
			System.out.println("Results: PAPER vs ROCK");
			System.out.println("-- You won! --");
			score = 1;
		}
		else if(human == 2 && computer == 2){
			System.out.println("Results: PAPER vs PAPER");
			System.out.println("-- Tie! --");
			score = 3;
		}
		else if(human == 2 && computer == 3){
			System.out.println("Results: PAPER vs SCISSORS");
			System.out.println("-- Computer Won! --");
			score = 2;
		}
		else if(human == 3 && computer == 1){
			System.out.println("Results: SCISSORS vs ROCK");
			System.out.println("-- Computer Won! --");
			score = 2;
		}
		else if(human == 3 && computer == 2){
			System.out.println("Results: SCISSORS vs PAPER");
			System.out.println("-- You won! --");
			score = 1;
		}
		else{
			System.out.println("Results: SCISSORS vs SCISSORS");
			System.out.println("-- Tie! --");
			score = 3;
		}
		
		return score;
	}
	/**
	 * displayScore method keeps track of the score of the game and just display it
	 * @param humanScore, the player's score
	 * @param computerScore, the computer's score
	 * @param tieScore, the ties between player and computer
	 */
	public static void displayScore(int humanScore, int computerScore, int tieScore){
		System.out.println("Your score: " + humanScore);
		System.out.println("Computer score: " + computerScore);
		System.out.println("Tie score: " + tieScore);
	}
	/**
	 * saveGame method prompts the user to save it or not then exits the game
	 * @param computer, the computer object
	 * @param f, the file variable from main
	 */
	public static void saveGame(Computer computer, File f){
		Scanner scnr = new Scanner(System.in);
		boolean s = false;
		
		System.out.println("\nDo you want to save? \n1. YES \n2. NO");
		
		while(!s){
		String input = scnr.nextLine();
			if(input.equals("1")){
				try{
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
					out.writeObject(computer);
					out.close();
				}catch(IOException e){
					System.out.println("Error processing file");
				}
				System.out.println("Thanks for playing");
				System.exit(0);
			}
			else if(input.equals("2")){
				System.out.println("Thanks for playing");
				System.exit(0);
			}
			else System.out.println("**invalid input**");
		}
	}
}