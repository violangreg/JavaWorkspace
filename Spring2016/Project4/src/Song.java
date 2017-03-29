/**
 * Song class is for constructing a Song object to be used on the Heap. It has a title, artist, album, and a rating.
 * There is a get methods for each one, also this class implements Comparable for compareTo, for comparing songs in the heap.
 * It also overrides toString for displaying
 * @author Greg Violan, 011706641
 */
public class Song implements Comparable<Song> {
	/**
	 * Song constructor
	 * @param t, title of song
	 * @param a, artist of song
	 * @param al, album of song
	 * @param r, rating of song
	 */
	public Song(String t, String a, String al, int r){
		title = t;
		artist = a;
		album = al;
		rating = r;
	}
	/**
	 * Gets the title of the song
	 * @return title, title of the song
	 */
	public String getTitle(){
		return title;
	}
	/**
	 * Gets the artist of the song
	 * @return artist, artist of the song
	 */
	public String getArtist(){
		return artist;
	}
	/**
	 * Gets the album of the song
	 * @return album, album of the song
	 */
	public String getAlbum(){
		return album;
	}
	/**
	 * Gets the rating of the song
	 * @return rating, rating of the song
	 */
	public int getRating(){
		return rating;
	}
	/**
	 * Overrides compareTo, if both song's rating are the same, compare song titles.
	 * @param s, the song to compare to
	 * @return compare, an integer used by comparing in the heap
	 */
	@Override
	public int compareTo(Song s){
		int compare = s.rating - rating;
		if(compare == 0){
			compare = title.toLowerCase().compareTo(s.title.toLowerCase()); //Disregard capitals
		}
		return compare;
	}
	/**
	 * Overrides toString for easier display
	 * @return title, title of the song
	 * @return artist, artist of the song
	 * @return album, album of the song
	 * @return rating, rating of the song
	 */
	@Override
	public String toString(){
		return title + ", " + artist + ", " + album + ", " + Integer.toString(rating);
	}
	/**
	 * Title of the song
	 */
	private String title;
	/**
	 * Artist of the song
	 */
	private String artist;
	/**
	 * Album of the song
	 */
	private String album;
	/**
	 * Rating of the song
	 */
	private int rating;
}

