package eksamensforelesning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Patient extends Person implements Iterable<Symptom> {
	
	private List<Symptom> symptoms;
	private Medication currentmedication;

	public Patient(String name, int age, Symptom...symptoms) {
		super(name, age);
		this.symptoms = new ArrayList<>(Arrays.asList(symptoms));
	}

	@Override
	public Iterator<Symptom> iterator() {
		return symptoms.iterator();
	}
	
	public void recieveMedication(Medication m) {
		if (currentmedication != null) {
			throw new IllegalStateException();
		}
		this.currentmedication = m;			
	}
	
	public Medication getCurrentmedication() {
		return currentmedication;
	}
	
	public void stopMedication() {
		currentmedication = null;
	}
	
	
	
}
