package ionuth.hackrank.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/*
 * Invoke a private method of a private class using reflection
 */
public class HR02ReflectionPrivate {
	
	static class Inner {
	
		@SuppressWarnings("unused")
		private class Private {
			private String powerof2(int num) {
				return ((num&num-1)==0)?"power of 2":"not a power of 2";
			}
		}
	
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		try {
			int num = 32;
			Inner innerInstance = new Inner();
			Class privateCls = Inner.class.getDeclaredClasses()[0];
			Constructor privateCstr = privateCls.getDeclaredConstructors()[0];
			privateCstr.setAccessible(true);
			Object privateObj = privateCstr.newInstance(innerInstance);
			Method powerMethod = privateCls.getDeclaredMethods()[0];
			powerMethod.setAccessible(true);
			System.out.println( num + " is " + powerMethod.invoke(privateObj, 32 ));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
}
