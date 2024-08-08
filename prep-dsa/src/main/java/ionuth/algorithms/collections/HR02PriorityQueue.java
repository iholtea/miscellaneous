package ionuth.algorithms.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Priority queue - a queue where each element has a "priority" associated with it
 * and an element with a high priority is served before one with lower priority.
 * there is an implementation java.util.PriorityQueue<E>
 * 
 * Q: but what is this priority ? 
 * A: the natural order of the object, the compareTo of the object
 * 		or a Comparator<T> which can be passed as argument to the PriorityQueue
 * 
 * NOTE - you will get items from the top of the queue, using poll() 
 * 		so first element has to be the one with high priority, so the 
 * 		compareTo() has to be "reversed" - highest element is first in the queue
 * 
 * The problem:
 * We have students to be sorted by they CGPA.
 * If CGPA-s are equal, sort them by name
 * Input - a series of events in the form
 * 		"ENTER Mark 3.8 24"
 *		"ENTER Shafaet 3.7 35"
 *		"SERVED"
 *		"SERVED"
 * where 2nd token is name, 3rd token is CGPA, 4th token is id
 * 
 * on ENTER add the student to a queue
 * on SERVED get and remove the student with the highest priority
 * 
 * Output:
 * 		return a list of the remaining students ordered by their priority
 * 
 */

class Student implements Comparable<Student> {
	
	int id;
	String name;
	double cgpa;

	public Student(int id, String name, double cgpa) {
		super();
		this.id = id;
		this.name = name;
		this.cgpa = cgpa;
	}
	public int getID() {
		return id;
	}
	public String getName() {
		return name;
	}
	public double getCGPA() {
		return cgpa;
	}
	
	@Override
	public int compareTo(Student other) {
		if( this.cgpa > other.cgpa ) {
			return -1;
		} else if( this.cgpa < other.cgpa ) {
			return 1;
		} else {
			return this.name.compareTo(other.name);
		}
	}
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", cgpa=" + cgpa + "]";
	}
	
	
	
}

class Priorities {
	
	PriorityQueue<Student> pq = new PriorityQueue<Student>();
	
	public List<Student> getStudents(List<String> events) {
		
		for( String event : events ) {
			String[] tokens = event.split(" ");
			if( "ENTER".equals(tokens[0]) ) {
				String name = tokens[1];
				double cgpa = Double.parseDouble(tokens[2]);
				int id = Integer.parseInt(tokens[3]);
				pq.add(new Student(id,name,cgpa));
			} else if( "SERVED".equals(tokens[0])) {
				pq.poll();
			}
		}
		
		List<Student> students = new ArrayList<Student>();
		while( !pq.isEmpty() ) {
			students.add(pq.poll());
		}
		
		return students;
	}
	
}

public class HR02PriorityQueue {
	
	public static void main(String[] args) {
		
		List<String> events = Arrays.asList(
			"ENTER John 3.75 50",
			"ENTER Mark 3.8 24",
			"ENTER Shafaet 3.7 35",
			"SERVED",
			"SERVED",
			"ENTER Samiha 3.85 36",
			"SERVED",
			"ENTER Ashley 3.9 42",
			"ENTER Maria 3.6 46",
			"ENTER Anik 3.95 49",
			"ENTER Dan 3.95 50",
			"SERVED" );
		
		Priorities priorities = new Priorities();
		List<Student> students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }		
	}
	
}
