package ionuth.algorithms.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *	Tag content extractor
 *	Given a string of text in a tag based language parse it and retrieve
 *	the contents of the tags by meeting the following rules
 *	- name of tags are case sensitive so <h1> and <H1> do not match
 *	- tags can be nested but we do not have to support mixed stuff like
 *		<h1><a>some content</a>other stuff</h1>
 *			for this we will extract just "some content" and not "other stuff" also
 *	- tags can consist of any printable character so
 *		<Some tag>some content</Some tag> is valid	
 *
 * TODO - search for solution to cover all test cases
 * 		https://www.hackerrank.com/challenges/tag-content-extractor/problem
 */

public class HR02TagExtractor {
	
	static class Info {
		String tagName;
		String tagType; // START, END
		int startIdx;
		int endIdx;
		
		@Override
		public String toString() {
			String str = "matched: " + tagName;
			str += " type: " + tagType;
			str += " start index: " + startIdx;
			str += " end index: " + endIdx;
			return str;	
		}
	}
	
	public static List<String> extractContent(String line) {
		
		String state = "";
		List<String> contents = new ArrayList<String>();
		String content = "";
		boolean matched = false;
		
		//String regexStart = "(<[\\w+\\s]+>)";
		//String regexEnd = "(</[\\w+\\s]+>)";
		String regexStart = "(<.+>)";
		String regexEnd = "(</.+>)";
		
		String regex = regexStart + "|" + regexEnd;
		Pattern pattern = Pattern.compile(regex);
		
		Deque<Info> stack = new LinkedList<Info>();
		
		Matcher matcher = pattern.matcher(line);
		
		while( matcher.find() ) {
			matched = true;
			Info info = new Info();
			String tag = matcher.group();
			if( tag.startsWith("</") ) {
				info.tagType = "END";
				info.tagName = tag.substring(2, tag.length()-1);
			} else {
				info.tagType = "START";
				info.tagName = tag.substring(1, tag.length()-1);
			}
			info.startIdx = matcher.start();
			info.endIdx = matcher.end();
			
			if(info.tagType.equals("START")) {
				if( stack.isEmpty() ) {
					state = "TOP";
				} else {
					state = "NESTED";
				}
				stack.push(info);
			} else {
				Info startTag = stack.pop();
				if( info.tagName.equals(startTag.tagName) ) {
					if( state=="TOP" || state=="NESTED") {
						content = line.substring(startTag.endIdx, info.startIdx );
						if( content.isEmpty() ) {
							content = "None";
						}
						contents.add(content);
					}
				} else {
					content = "None";
					contents.add(content);
				}
				state = "EXTRACTED";
			}
			
		}
		
		if( !matched && (line.contains("<") || line.contains(">")) ) {
			contents.add("None");
		}

		
		return contents;

	}
	
	
	public static void main(String[] args) {
		
		List<String> input = Arrays.asList(
				"4",
				"<h1>Nayeem loves counseling</h1>",
				"<h1><h2>Sanjay has no watch</h2></h1><par>So wait for a while<par>",
				"<Amee>safat codes like a ninja</amee>",
				"<SA premium>Imtiaz has a secret crash</SA premium>",
				"bla bla <h1>Content h1</h1> bla bla <h mare>content mare</h mare> bla bla"
				);
		
		List<String> input2 = Arrays.asList(
				"10",
				"<h1>some</h1>",
				"<h1>had<h1>public</h1></h1>",
				"<h1>had<h1>public</h1515></h1>",
				"<h1><h1></h1></h1>",
				"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<",
				">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
				"<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>",
				"<>hello</>",
				"<>hello</><h>dim</h>",
				"<>hello</><h>dim</h>>>>>"
				);
			
		for(String line : input) {
			List<String> contents = extractContent(line);
			for( String content : contents) {
				if(!content.isEmpty()) {
					System.out.println(content);
				}
			}
		}
		
	}
	
}
