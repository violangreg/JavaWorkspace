//Greg Paolo D. Violan, 011706641, Project6 CECS277
/**
 * imports necessary java io and utilities from library
 */
import java.net.*;
import java.util.*;
import java.io.*;
/**
 * Computer class is the server of the game, it extends a Thread so that it can grab whatever the client spits out
 * at whatever time. It has a ServerSocket for the server and uses the necessary variables to read and write from
 * and onto the client. It also creates a .dat file for the Computer object
 */
public class Computini extends Thread{
	/**
	 * Declares the ServerSocket to create the server
	 */
	private ServerSocket server;
	/**
	 * Declares Socket for the server
	 */
	private Socket sock;
	/**
	 * Declares the BufferedReader for grab whatever the Client spits out
	 */
	private BufferedReader in;
	/**
	 * Declares the PrintStream to spit info to the Client
	 */
	private PrintStream out;
	/**
	 * Declares the Computer object for predicting the next throw
	 */
	private Computer computer;
	/**
	 * Declares a File for the .dat Computer object
	 */
	private File file;
	/**
	 * Declares a string for the pattern of the of the player
	 */
	private String pattern;	
	/**
	 * Computini constructor instantiates the computer object, data.dat file, and also reads in the file
	 * if it exist. It also instantiates the pattern String and the server, socket, BufferedReader, PrintStream
	 * and lastly starts this Thread
	 */
	public Computini(){
		computer = new Computer();
		file = new File("data.dat");
		readInFile(computer, file);	
		pattern = "";
		
		try {
			server = new ServerSocket(1025);
			System.out.println("Waiting...");
			sock = server.accept();	
			System.out.println("Connected \nGame is playing");
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintStream(sock.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		this.start(); // Start this thread
	}
	/**
	 * main just creates the Computini constructor
	 * @param args
	 */
	public static void main(String[] args){
		new Computini();
	}
	/**
	 * run method runs the Thread, this thread just waits for the Client to send info
	 */
	public void run(){
		try {
			while(true){
				// Get the weapon from the Client then process it
				process(in.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	/**
	 * process method takes in the info that the client sends and uses that info to determine the computer's
	 * prediction against the player's throw.
	 * @param weapon, the weapon of the player
	 */
	public void process(String weapon){
		if(!weapon.equals("e")){
			pattern += weapon;
		}
		
		// Computer predicts the player throw
		int prediction = computerThrow();
		
		// Find the results of player throw and computer prediction
		int result = 0;
		switch(weapon){
			case "r": result = results(1, prediction);
				break;
			case "p": result = results(2, prediction);
				break;
			case "s": result = results(3, prediction);
				break;
			case "e": exitGame(computer, file);
				break;
			default: System.out.println("default");
				break;
		}
		
		// Get the results and spit out if Client won (w), lose (l), tie (t)
		switch(result){
			case 1: out.println("w");
				break;
			case 2: out.println("l");
				break;
			case 3: out.println("t");
				break;
			default: System.out.println("default");
				break;
		}
		
		// Computer's prediction
		out.println(Integer.toString(prediction));
	}
	/**
	 * computerThrow predicts the throw of the player depending on what the player's throw pattern
	 * @return prediction, the computer's prediction
	 */
	public int computerThrow(){
		int prediction = 0;
		
		if(pattern.length() == 4){
			computer.storePattern(pattern);
			prediction = computer.makePrediction(pattern);
			pattern = pattern.substring(1);	
			
		} else {
			Random rand = new Random();
			prediction = rand.nextInt(3) + 1;
		}
				
		return prediction;
	}
	/**
	 * results method evaluates the player's weapon and the computer's weapon and
	 * spits out who wins the game
	 * @param weapon, player's weapon
	 * @param prediction, computer's weapon
	 * @return results, the winner of the rps
	 */
	public int results(int weapon, int prediction){
		int result = 0;
		
		if(weapon == 1 && prediction == 1){
			result = 3;
		}
		else if(weapon == 1 && prediction == 2){
			result = 2;
		}
		else if(weapon == 1 && prediction == 3){
			result = 1;
		}
		else if(weapon == 2 && prediction == 1){
			result = 1;
		}
		else if(weapon == 2 && prediction == 2){
			result = 3;
		}
		else if(weapon == 2 && prediction == 3){
			result = 2;
		}
		else if(weapon == 3 && prediction == 1){
			result = 2;
		}
		else if(weapon == 3 && prediction == 2){
			result = 1;
		}
		else{
			result = 3;
		}
		
		return result;
	}
	/**
	 * exitGame method saves the .dat file and closes the socket and server
	 * @param computer, the computer object that will be saved onto a file
	 * @param file, the .dat file that will be written to
	 */
	public void exitGame(Computer computer, File file){
		System.out.println("Game is exiting... \nDisconnected");
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(computer);
			out.close();
			sock.close();
			server.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		System.exit(0);
	}
	/**
	 * readInFile reads in the Computer object from the .dat file if it exists and uses
	 * the Computer object for the game
	 * @param computer, the Computer object for the game
	 * @param file, the .dat file
	 */
	public void readInFile(Computer computer, File file){
		if(file.exists()){
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				computer = (Computer) in.readObject();
				in.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}