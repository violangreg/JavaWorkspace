//Greg Paolo D. Violan, 011706641, Project6 CECS277
/**
 * Pattern class has a pattern constructor, getPattern method to return the pattern, hashCode method returns the hashCode of pattern,
 * equals method uses to compare the object, and toString method returns pattern
 */
/**
 * imports necessary java io's from the libraries
 */
import java.io.Serializable;
@SuppressWarnings("serial")
public class Pattern implements Serializable{
	/**
	 * Pattern constructor
	 * @param p, the pattern of the pattern
	 */
	public Pattern(String p){
		pattern = p;
	}
	/**
	 * getPattern method gets the pattern of the pattern
	 * @return pattern
	 */
	public String getPattern(){
		return pattern;
	}
	/**
	 * hashCode method
	 * @return hashCode 
	 */
	@Override
	public int hashCode(){
		return pattern.hashCode();
	}
	/**
	 * equals method compares the object if its a pattern return true else return false
	 * @return true or false
	 */
	@Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(!(o instanceof Pattern)){
			return false;
		}
		Pattern p = (Pattern) o;
		return pattern.equals(p.getPattern());
	}
	/**
	 * toString method returns pattern to string
	 */
	public String toString(){
		return pattern;
	}
	/**
	 * private String pattern for the pattern constructor
	 */
	private String pattern;
}