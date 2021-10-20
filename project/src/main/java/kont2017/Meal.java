package kont2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a set of (pre-defined) Courses that are ordered as a whole
 */
public class Meal extends MenuItem implements Iterable<Course>{
	  
    private static List<Course> courses;
	
  
    public Meal(String name, String description, Course[] courses) {
        super(name, description);
        this.courses = Arrays.asList(courses);
    }
     
    
    public void addCourse(Course c) {
    	if (!courses.contains(c)) {
			courses.add(c);
		}
    }
    
    public void removeCourse(Course c) {
    	if (courses.contains(c)) {
			courses.remove(c);
		}
    }
    
    @Override
    public Iterator<Course> iterator() {
    	return courses.iterator();
    }
    
    
    public static void main(String[] args) {
    	Course c = new Course("pp","ddd");
    	Course[] a = {c};
    	
    	Meal m = new Meal("p", "dd", a);
    	m.addCourse(c);
    	
    	for (Course course : m) {
			System.out.println(course.getDescription());
		}

    }

}
  