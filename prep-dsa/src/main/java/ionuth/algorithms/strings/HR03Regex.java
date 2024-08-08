package ionuth.algorithms.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Regular expressions 
 * 		check if a string is a valid IP v4
 * 		remove duplicate words from strings
 */
public class HR03Regex {
	
	/*
	 *	IP V4 regular expression
	 *	IP addresses are between 0.0.0.0 and 255.255.255
	 *	We've have to support both leading 0-s or lack of leading 0-s
	 *	so both 2.3.4.5 and 002.003.004.005	 
	 */
	public static String getIPV4Regex() {
		
		
		// we might have 0 or 1 first digit
		// then any digit
		// then might have the 3-d digit
		String str01 = "[01]?\\d\\d?"; 
		
		// matches 200 to 249
		String str02 = "2[0-4]\\d";
		
		// matches 250-255 
		String str03 = "25[0-5]";
		
		// match 000 to 255
		String grpNoDot = "(" + str01 + "|" + str02 + "|" + str03 + ")";
		
		// add the .
		String grp = grpNoDot + "\\.";
		
		// repeat each group 3 times
		String pattern = "(" + grp + ")" + "{3}";
		
		// add the forth group without the .
		pattern += grpNoDot;
		
		// add begin and end line boundaries
		//pattern = "^" + pattern + "$";
		
		String fullPattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}" 
				+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
		
		System.out.println(pattern);
		System.out.println(fullPattern.equals(pattern) );
		
		return pattern;
		
	}
	
	/*
	 *	use RegEx to eliminate duplicate words from a text
	 *	Example:
	 *	input  = "I love to go by the sea and Love TO write code"
	 *	output = "I love to go by the sea and  write code"
	 *	Note:
	 *	we have to eliminate Love as duplicate of love, so case insensitive
	 */
	public static String removeDuplicateWordsRegex(String input) {
		
		
		// \b match only the beginning of word instead of somewhere in the middle
		// 		so in "this is a" string "is" is matched just once
		// \w+ match one or more characters
		String wordPattern = "\\b(\\w+)";
		
		// \s+ match one or more space characters
		// \1 match the word remembered previously
		// \b make like previously make sure is not part of some longer word
		// match one ore more occurences of this (\s+\1\b)+
		
		String regex = wordPattern + "(\\s+\\1\\b)+";
		
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		while(matcher.find()) {
			input = input.replace(matcher.group(), matcher.group(1));
		}
		
		return input;
	}
	
	public static void main(String[] args) {
		
		getIPV4Regex();
		
		System.out.println("--------------------");
		
		String str = "Goodbye bye bye world world world";
		System.out.println( removeDuplicateWordsRegex(str) );
		
		str = "Hello hello Ab aB";
		System.out.println( removeDuplicateWordsRegex(str) );
	}
	
}
