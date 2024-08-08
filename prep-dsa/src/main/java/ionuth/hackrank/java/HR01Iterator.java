package ionuth.hackrank.java;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Given a list which contain numbers, then a string of "#####" and then strings
 * Create a Iterator which will return all the strings after the "######" line 
 * 
 */
public class HR01Iterator {
	
	@SuppressWarnings("rawtypes")
	static Iterator func(ArrayList mylist){
		Iterator it=mylist.iterator();
		while(it.hasNext()){
			Object element = it.next();
			if(element instanceof String)
				break;
		}
      return it;
   }
	
   @SuppressWarnings({ "unchecked", "rawtypes" })
   public static void main(String []args){
	   ArrayList mylist = new ArrayList();
       mylist.add(Integer.valueOf(42));
       mylist.add(Integer.valueOf(10));
       mylist.add("###");
       mylist.add("Hello");
       mylist.add("Java");
       mylist.add("Vasile");
       
      
       Iterator it=func(mylist);
       while(it.hasNext()){
    	   Object element = it.next();
    	   System.out.println((String)element);
       }
   
   }
	
}
