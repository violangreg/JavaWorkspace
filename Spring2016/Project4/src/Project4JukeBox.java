/**
 * imported necessary libraries
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Project4JukeBox class is the main class for a Play list. It uses a heap of song objects and reads the files from a existing file
 * in playlist.txt. It has 8 methods used to make the main. First is the menu method where it prompts the user what they want to do.
 * Next displays the song list. One displays the current song. Another one adds a song to the play list. The other one plays the next song
 * in the playlist. The last functional method for the main is to re-rate the song. The last method just checks for input.
 * @author Greg Violan, 011706641
 *
 */
public class Project4JukeBox {
/**
 * main method of the class that functions and put everything together.
 * It declares a heap for song object. It also reads an existing file, and uses the methods to put everything together.
 * @param args
 */
	public static void main(String[] args) {
		Heap<Song> song = new Heap<Song>();
		File f = new File("playlist.txt");
		
		try{
			Scanner scnr = new Scanner(f);
			do{
				String read = scnr.nextLine();
				String[] tokens = read.split(","); // tokens of task name
				
				Song s = new Song(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]));
				song.addNode(s);
			
			}while(scnr.hasNextLine());
		}catch(FileNotFoundException e){
			System.out.println("FNFE");
		}
		
		boolean s = false;
		do{
			int func = jukeBoxMenu(); 
			
			if(func == 1){
				displayJukeBox(song);
			}
			else if(func == 2){
				displayCurrentSong(song);
			}
			else if(func == 3){
				addToJukeBox(song);
			}
			else if(func == 4){
				playNextSong(song);
			}
			else if(func == 5){
				reRateSong(song);
			}
			else {
				System.out.println("Quiting.. Thank you");
				System.exit(0);
			}
		}while(!s);

	}
/**
 * A menu method to prompt the user for which function to do. 
 * @return input, the input the user chose.
 */
	public static int jukeBoxMenu(){
		System.out.println("-- JUKEBOX MENU --");
		System.out.println("1. Display the list of songs \n2. Display current song \n"
				+ "3. Add a new song to the task list \n4. Play next song \n5. Re-rate next song"
				+ "\n6. Quit\n*Song is displayed by title, artist, album, rating.");
		int input = checkInt(1, 6);
		
		return input;
	}
/**
 * Displays the whole play list. If the playlist is empty, it says so otherwise
 * @param song, the heap that contains all the song objects
 */
	public static void displayJukeBox(Heap<Song> song){
		if(song.isEmpty()){
			System.out.println("Play list is empty.\n");
		}
		else{
			System.out.println("-- PLAY LIST --");
			song.printHeap();
		}
	}
/**
 * Displays the current song of the playlist. If the playlist is empty, it says so otherwise
 * @param song, the heap that contains all the song objects.
 */
	public static void displayCurrentSong(Heap<Song> song){
		if(song.isEmpty()){
			System.out.println("Play list is empty, there is no current song.");
		}
		else{
			System.out.println("-- Current song -- \n" + song.getNodeAt(0).toString() + "\n");
		}
	}
/**
 * Adds a song to the heap.
 * @param song, the heap that contains all the song objects.
 */
	public static void addToJukeBox(Heap<Song> song){
		Song s = makeSong();
		System.out.println(s.toString() + "\nwas added to the play list.\n");
		song.addNode(s);
	}
/**
 * plays the next song in the jukebox (heap)
 * @param song, the heap that contains all the song objects.
 */
	public static void playNextSong(Heap<Song> song){
		if(song.isEmpty()){
			System.out.println("Play list is empty, cannot remove anymore.");
		}
		else{
			song.removeMin();
			if(!song.isEmpty()){
				System.out.println("Now playing: " + song.getNodeAt(0).toString() + "\n");
			}
			else{
				System.out.println("Play list is empty, cannot remove anymore.");
			}
		}
	}
/**
 * Re-rates the current song. 
 * @param song, the heap that contains all the song objects
 */
	public static void reRateSong(Heap<Song> song){
		if(song.isEmpty()){
			System.out.println("Play list is empty, cannot re-rate anything");
		}
		else{
			System.out.println("Re-rating song: " + song.getNodeAt(0).toString() + "\n"
					+ "Enter new rating (1-5):");
			int newRating = checkInt(1, 5);
			Song c = song.getNodeAt(0);
			song.removeMin();
			Song s = new Song(c.getTitle(), c.getArtist(), c.getAlbum(), newRating);
			song.addNode(s);
			System.out.println("Re-rated: " + s.toString() + "\n");
		}
	}
/**
 * Creates the song object for the add song method
 * @return the song object created
 */
	public static Song makeSong(){
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Enter the name of the song:");
		String songName = scnr.nextLine();
		
		System.out.println("Enter the name of the artist:");
		String artistName = scnr.nextLine();
		
		System.out.println("Enter the name of the album:");
		String albumName = scnr.nextLine();
		
		System.out.println("Rate the song (1-5):");
		int songRating = checkInt(1, 5);
		
		Song s = new Song(songName, artistName, albumName, songRating);
		
		return s;
	}
/**
 * Checks the input integer, for prompting the user.
 * @param low, is the lower integer of the restricted prompt
 * @param high, is the higher integer of the restricted prompt
 * @return validNum, the validated prompted integer
 */
	public static int checkInt( int low, int high ) {
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		int validNum = 0;
		
		while( !valid ) {
			if(in.hasNextInt()) {
				validNum = in.nextInt();
				if( validNum >= low && validNum <= high ){
					valid = true;
				} 
				else{
					System.out.println("**invalid input**");
				}
			}
			else{
				//clear buffer of junk input
				in.next();
				System.out.println("**invalid input**");
			}
		}
		return validNum;
	}
}
