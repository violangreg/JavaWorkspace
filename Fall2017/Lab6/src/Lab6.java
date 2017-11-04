/*
Upload your file into BeachBoard dropbox (Not SVN)
*/
import java.util.*;

public class Lab6 {
	private Map<Integer, Integer> memo;

	public Lab6() {
		memo = new HashMap<Integer, Integer>();
	}

	public int Naive_fibonacci(int n) {
		if(n == 0)
			return 0;
		else if(n == 1)
			return 1;
		else
			return Naive_fibonacci(n - 1) + Naive_fibonacci(n - 2);
	}

	/*
	0. What is the runtime of this function? - 3 pts
		- O(2^n)
	*/

	/*
	1-1. Write a memoized version fibonacci function - 7 pts
		You should use Map<Integer, Integer> memo to store and retrieve the computed fibonachi(n)
	*/
	public int Memo_fibonacci(int n) {
		// Your code from here
		if(n == 0)
			return 0;
		else if(n == 1)
			return 1;
		else if(memo.containsKey(n)) 
			return memo.get(n);
		else {
			int temp = Memo_fibonacci(n - 1) + Memo_fibonacci(n - 2);
			//Storing temp to map
			memo.put(n, temp);
			return temp;
		}
		//Your code to here		
	}
	/*
	1-2. What is the runtime of this function? Assume get/put of the HashMap runs in O(1) - 3 pts
		- O(1) best case
		- O(2^n) worst case
	*/

	/*
	2-1. Write a loop version fibonacci function - 7 pts
	*/
	public int Loop_fibonacci(int n) {
		// Your code from here
		int c_acc = 0; //current acc
		int b_acc = 1; //before acc
		int n_acc = 0; //next acc
		for(int i = 0; i < n; i++){
			n_acc = c_acc + b_acc;
			b_acc = c_acc;
			c_acc = n_acc;
		}
		return c_acc;
		//Your code to here		
	}
	/*
	2-2. What is the runtime of this function? - 3 pts
		- O(n)
	*/

	/*
	3-1. Write a tail recursive fibonacci function - 9 pts
	*/
	public int Tail_fibonacci(int n) {
		return Tail_fibonacci(n, 0, 1);
	}

	private int Tail_fibonacci(int n, int a, int b) {
		// Your code from here
		int c_acc = a;
		int b_acc = b;
		int n_acc = 0;
		if(n == 0)
			return 0;
		else if(n == 1)
			return b_acc;
		else{
			n_acc = c_acc + b_acc;
			return Tail_fibonacci(n - 1, b_acc, n_acc);
		}
		//Your code to here
	}
	/*
	3-2. What is the runtime of this function? - 3 pts
		- O(n)
	*/

	/*
	4. (Extra) Write a method to compute all permutations of a string of unique characters. - 10 pts
		"a" --> ["a"]
		"ab" --> ["ab", "ba"]
		"abc" --> ["abc", "acb", ..., "cba"]
	*/
	public List<String> getPerms(String str) {
		if (str == null || str.length() == 0) return null;
		List<String> permutations = new LinkedList<String>();
		// Your code from here
		permute(str, permutations);
		//Your code to here
		return permutations;
	}
	//source from: http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
	private void permute(String str, List<String> p){
		permute(str, 0, str.length()-1, p);
	}
	   /**
     * permutation function
     * @param str string to calculate permutation for
     * @param l starting index
     * @param r end index
     */
    private void permute(String str, int l, int r, List<String> p)
    {
        if (l == r)
        	p.add(str);
        else{
            for (int i = l; i <= r; i++){
                str = swap(str, l, i);
                permute(str, l+1, r, p);
                str = swap(str, l, i);
            }
        }
    }
	
    /**
     * Swap Characters at position
     * @param a string value
     * @param i position 1
     * @param j position 2
     * @return swapped string
     */
    public String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

	public static void main(String[] args) {
		Lab6 driver = new Lab6();
		System.out.println(driver.Naive_fibonacci(10));		
		System.out.println(driver.Memo_fibonacci(10));
		System.out.println(driver.Loop_fibonacci(10));
		System.out.println(driver.Tail_fibonacci(10));
		//Extra credit
		System.out.println(driver.getPerms("abcd"));
	}
}