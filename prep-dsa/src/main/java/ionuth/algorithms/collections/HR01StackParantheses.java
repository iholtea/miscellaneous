package ionuth.algorithms.collections;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * 	A string containing only parentheses is considered balanced if:
 * 		1. it is the empty string
 * 		2. if A and B are correct AB is correct
 * 		3. if A is correct (A) [A] and {A} is correct
 * 
 * 	NOTE - i do not exactly get what is A and B, but anyways
 * 
 * 	Example of balanced strings
 * 		"{}()" "[{()}]"
 * 	Example of unbalanced strings
 * 		"{})" 
 * 
 * 	Given a string determine if it is balanced or not
 */


public class HR01StackParantheses {
	
	public static boolean checkBalanced(String input) {
		Deque<Character> stack = new LinkedList<Character>();
		char ch;
		for( int idx=0; idx<input.length(); idx++) {
			ch = input.charAt(idx);
			if( ch=='(' || ch=='[' || ch=='{' ) {
				stack.push(ch);
			}
			else if( ch==')' && ( stack.isEmpty() || stack.pop()!='(' ) ) {
				return false;
			}
			else if( ch==']' && ( stack.isEmpty() || stack.pop()!='[' ) ) {
				return false;
			}
			else if( ch=='}' && ( stack.isEmpty() || stack.pop()!='{' ) ) {
				return false;
			}
		}
		if( !stack.isEmpty() ) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		List<String> input = Arrays.asList(
				"{}()",
				"({()})",
				"{}(",
				"[]"
				);
		
		for( String str : input ) {
			System.out.println( checkBalanced(str) );
		}
		
	}
	
}
