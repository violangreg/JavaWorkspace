
public class Grammar {
	String grammar;
	String[] grammarList;
	String[] grammarLine;
	public Grammar(String[] grammarLine)
	{
		// stores the non-terminal grammar
		this.grammar = grammarLine[0];
		// stores the whole line
		this.grammarLine = grammarLine;
		// omits the non-terminal grammar and the arrow ->
		String[] omitGrammarAndArrow = new String[grammarLine.length-2]; 
		for(int i = 0; i < grammarLine.length-2; i++)
			omitGrammarAndArrow[i] = grammarLine[i+2];
		this.grammarList = omitGrammarAndArrow;
	}
	
	public String GetGrammar()
	{
		return grammar;
	}
	public String[] GetList()
	{
		return grammarList;
	}
	public void printList(){
		for(int i = 0; i < grammarList.length; i++)
		{
			System.out.print(grammarList[i] + " ");
		}
	}
	public void printLine(){
		for(int i = 0; i < grammarLine.length; i++)
		{
			System.out.print(grammarLine[i] + " ");
		}
	}
}
