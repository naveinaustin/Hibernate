import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternExample
{
	public static void main(String[] args) 
	{
		//String filter = "***jf**kl*****s*j*******f****";
		//String filter = "+++jf++kl+++++s+j+++++++f+++";
		//String filter = ".....jf....kl....sdfd..................";
		//String filter = "????sdfs?sf??wrwerw??????ewrwrwrw??????";
		//String filter = "????sdfs?sf??wrwerw??????ewrwrwwwwrw??????";
		
		//String filter = "?....??***+++**??aaaa?++...++??aaaa??++??**?aa?*??.??aa++?...";
		String filter = "\\\\\\aaa\\ddddd\\\\ddsafadf\\\\";
		
		System.out.println("Filter: " + filter);
		
		//System.out.println("New Filter: " + replaceSeriesOfCharsWithSingleChar(filter, "a", "?", "*", ".", "+"));
		System.out.println("New Filter: " + replaceSeriesOfCharsWithSingleChar(filter, "\\"));
	}

	/*public static String replaceSeriesOfCharsWithSingleChar(String input, String character)
	{
		String result = input;
		String pattern = "";
		String find = "";
		String replace = "";
		boolean specialCharacter = false;
		
		if(character.equals("*") || character.equals(".") || character.equals("?") || character.equals("+"))
		{
			pattern = "(\\" + character + ")+";
			find = "\\" + character;
			replace = "\\\\" + character;
			specialCharacter = true;
		}
		else
		{
			pattern = "(" + character + ")+";
			replace = character;
		}
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(input);
		
		String group = "";
		
		while(m.find())
		{
			//System.out.println(m.group());
			group = m.group();
			if(specialCharacter)
			{
				group = group.replaceAll(find, replace);
			}
			result = result.replaceFirst(group, character);
		}
		
		return result;
	}*/
	
	/**
	 * This code will replace a series of characters with a single character depending on
	 * what you have specified.
	 * Examples:
	 *  Input String: 
	 *  	String input = "?....??***+++**??aaaa?++...++??aaaa??++??**?aa?*??.??aa++?..."
	 *  
	 * 	Example 1:
	 * 		replaceSeriesOfCharsWithSingleChar(input, "*")
	 * 		Output - ?....??*+++*??aaaa?++...++??aaaa??++??*?aa?*??.??aa++?...
	 * 		All continous characters of "*" are replace with a single "*"
	 * 
	 *  Example 2:
	 * 		replaceSeriesOfCharsWithSingleChar(input, "?")
	 * 		Output - ?....?***+++**?aaaa?++...++?aaaa?++?**?aa?*?.?aa++?...
	 * 		All continous characters of "*" are replace with a single "*"
	 * 	
	 *  Example 3:
	 * 		replaceSeriesOfCharsWithSingleChar(input, "?", "*", "a")
	 * 		Output - ?....?*+++*?a?++...++?a?++?*?a?*?.?a++?...
	 * 		All continuous characters of "*" are replace with a single "*"
	 * 		 
	 * Please note this can be used to replace almost all characters.
	 * The special characters now are just *, ?, . and +.
	 * 
	 */
	public static String replaceSeriesOfCharsWithSingleChar(String input, String... characters)
	{
		String result = input;
		String pattern = "";
		String find = "";
		String replace = "";
		boolean specialCharacter = false;
		
		for(String character : characters)
		{
		
			if(!character.equals("\\"))
			{
				if(character.equals("*") || character.equals(".") || character.equals("?") || character.equals("+"))
				{
					pattern = "(\\" + character + ")+";
					find = "\\" + character;
					replace = "\\\\" + character;
					specialCharacter = true;
				}
				else
				{
					pattern = "(" + character + ")+";
					replace = character;
				}
				
				Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(input);
				
				String group = "";
				
				while(m.find())
				{
					//System.out.println(m.group());
					group = m.group();
					if(specialCharacter)
					{
						group = group.replaceAll(find, replace);
					}
					result = result.replaceFirst(group, character);
				}
			}
		}
		
		return result;
	}
}
