package kont2017;

import java.util.Iterator;
import java.util.Map;

/**
 * Manages the set of Courses and Meals offered and their prices.
 */
public class Menu {
  
	private Map<Meal, Double> meals;
	private Map<Course, Double> courses;
	
	private Map<MenuItem, Double> menuItems;
	
    /**
     * Gets the price for a Course.
     * @param course
     * @return the price
     * @throws IllegalArgumentException if this Menu doesn't include the provided Course
     */
    public double getPrice(Course course) throws IllegalArgumentException {
    	if (!menuItems.containsKey(course)) {
			throw new IllegalArgumentException();
		}
    	return menuItems.get(course);
    }
  
    /**
     * Sets/changes the price of the provided Course.
     * @param course
     * @param price
     */
//    public void updatePrice(Course course, double price) {
//    	courses.put(course, price);
//    }

    public void updatePrice(MenuItem menuItem, double price) {
	menuItems.put(menuItem, price);
}
     
    /**
     * Gets the price for a Meal. If the registered price is 0.0,
     * the price is computed as the sum of the prices of the Meal's courses.
     * @param meal
     * @return
     * @throws IllegalArgumentException if this Menu doesn't include the provided Meal,
     *  or if a price of a Course is needed, but is missing
     */
    public double getPrice(Meal meal) throws IllegalArgumentException {
    	if (!menuItems.containsKey(meal)) {
			throw new IllegalArgumentException();
		}
    	double price = menuItems.get(meal);
    	if (price == 0.0) {
			for (Course course : meal) {
				price += getPrice(course);
			}
		}
    	return price;
    }
  
    /**
     * Sets/changes the price of the provided Meal.
     * @param meal
     * @param price
     */
//    public void updatePrice(Meal meal, double price) {
//    	meals.put(meal, price);
//    }
}