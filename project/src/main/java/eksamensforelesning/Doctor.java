package eksamensforelesning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Doctor extends Person implements Employee, ListListener
{
	private Hospital employer;
	private List<Employee> assistants;
	private boolean isAvailable = true;
	
	public Doctor(String name, int age, Employee...assistants) {
		super(name, age);
		this.assistants = new ArrayList<>(Arrays.asList(assistants));
	}
	
	public void addAssistant(Employee assistant) {
		if (!assistants.contains(assistant)) {
			assistants.add(assistant);
		}
	}

	public void removeAssistant(Employee assistant) {
		if (assistants.contains(assistant)) {
			assistants.remove(assistant);
		}

	}
	public Employee getAssistant(int n) {
		return assistants.get(n);
	}
	
	@Override
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public boolean isAvailable() {
		return isAvailable;
	}
	
	@Override
	public String toString() {
		return super.toString(); 
	}
	
	@Override
	public Hospital getEmployer() {
		// TODO Auto-generated method stub
		return employer;
	}
	
	public List<Nurse> getNurseList() {
		return assistants.stream()
				.filter(a -> a instanceof Nurse)
				.map(a -> (Nurse) a)
				.collect(Collectors.toList());
	}
	
	public Medication diagnose(Patient patient) {
		PatientLog plog = employer.getPatientDB().getPatientLog(patient);
		Iterator<Symptom> patienSymIt = patient.iterator();
		
		Symptom sym = null;
		
		
		while (patienSymIt.hasNext()) {
			sym = patienSymIt.next();
			for(Disease dis : sym.getKnownCauses()) {
				plog.addDisease(dis);
			}
		}
		return null;	
	}
	
	public boolean isAnyoneAvilable() {
		return assistants.stream()
				.anyMatch(a -> a.isAvailable());
	}

	@Override
	public void medicate(Patient patient, Medication medication) {
		this.getNurseList().stream().findAny().get().medicate(patient, medication);
	}

	@Override
	public void fireListChanged(Patient patient) {
		if (this.isAvailable) {
			this.medicate(patient, diagnose(patient));
		}
	}
	



}

