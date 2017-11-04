// LAB
public class RegEx {
	public static void main(String[] args)
	{
		String input = "345-565-7553";
		String regex = "[1-9]\\d{2}-[1-9]\\d{2}-[1-9]\\d{3}";
		System.out.println(input.matches(regex));
		
		
	}
}
