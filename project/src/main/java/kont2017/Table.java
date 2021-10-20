package kont2017;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Manages the set of ordered items for a table (set of guests).
 */
public class Table implements KitchenListener{
  
//    ... fields and methods ...
    private Menu menu;
//    private Collection<Meal> meals = new ArrayList<>();
//    private Collection<Course> courses = new ArrayList<>();
    private Collection<MenuItem> menuItems = new ArrayList<>();
    private Kitchen kitchen;
    
    public void addMenuItem(MenuItem menuItem) {
    	menuItems.add(menuItem);
    	if (kitchen != null) {
			kitchen.menuItemAdded(this, menuItem);
		}
    }
    
    public void removeMenuItem(MenuItem m) {
    	if (!menuItems.contains(m)) {
			throw new IllegalStateException();
		}
    }
    /**
     * Sets the Kitchen that should be notified when items are added.
     * Note that this method may be called several times with different Kitchen objects.
     * @param kitchen
     */
    public void setKitchen(Kitchen kitchen) {
    	if (kitchen != null) {
			kitchen.removeKitchenListener(this);
		}
    	this.kitchen = kitchen;
    	kitchen.addKitchenListener(this);
    }
  
    	
//    public void addMeal(Meal m) {
//    	meals.add(m);
//    }
//    public void removeMeal(Meal m) {
//    	if (!meals.contains(m)) {
//			throw new IllegalStateException();
//		}
//    	meals.remove(m);
//    }
//    public void addCourse(Course c) {
//    	courses.add(c);
//    }
//    public void removeCourse(Course c) {
//    	if (!courses.contains(c)) {
//			throw new IllegalStateException();
//		}
//    	courses.remove(c);
//    }
    /**
     * Initializes a new Table with a Menu that provides the prices for the Courses and Meals
     * @param menu
     */
    public Table(Menu menu) {
    	this.menu = menu;
    }
    /**
     * Computes the total price for all the added items. Prices are provided by the Menu.
     * @return the total price
     * @throws IllegalStateException when the price of an item cannot be provided by the Menu
     */
    public double getPrice() throws IllegalStateException {
    	double price = 0.0;
    	try {
    		for (MenuItem menuItem : menuItems) {
				if (menuItem instanceof Meal) {
					price += menu.getPrice((Meal) menuItem);
				}
				if (menuItem instanceof Course) {
					price += menu.getPrice((Course) menuItem);
				}
			}
//			for (Course course : courses) {
//				price += menu.getPrice(course);
//			}
//			for (Meal meal: meals) {
//				price += menu.getPrice(meal);
//			}
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage());
		}
    	return price;
    }

	@Override
	public void courseReady(Table table, Course course) {
		if (table == this) {
			
		}
	}
}