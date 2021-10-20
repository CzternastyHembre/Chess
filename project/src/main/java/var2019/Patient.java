package var2019;

import java.util.Collection;
import java.util.Iterator;

/** * A patient has a set of (health) conditions (of type String) that needs to be treated.
 * Supports iterating over the patient's conditions.
 */
public class Patient implements Iterable<String>{
    // Add fields, constructors, and methods here: // 2a
    // Support iteration // 2a
	private Collection<String> conditions;
	
	public Patient(Collection<String> conditions) {
		this.conditions = conditions;
	}
	 /**
	* Indicates if this patient has conditions that needs to be treated.
	* @return true if this patient has conditions that needs to be treated,
	* false otherwise.
	*/
	public boolean requiresTreatment() { // 2a
		return !conditions.isEmpty();
	}
	
	public int sizeOfConditions() {
		return conditions.size();
	}
	
	public void removeCondition(String condition) {
		conditions.stream().filter(cond -> cond.equals(condition))
		.forEach(cond -> conditions.remove(cond));
	}
	
	@Override
	public Iterator<String> iterator() {
		return conditions.iterator();
	}

}