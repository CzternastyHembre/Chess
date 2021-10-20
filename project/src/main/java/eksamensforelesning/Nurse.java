package eksamensforelesning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nurse extends Person implements Employee
{
	private Hospital employer;
	private boolean isAvailable = true;

	
	public Nurse(String name, int age, Hospital employer) {
		super(name, age);
		this.employer = employer;
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

	@Override
	public void medicate(Patient patient, Medication medication) {
		if (patient.getCurrentmedication() == null) {
			patient.recieveMedication(medication);
			this.employer
			.getPatientDB()
			.getPatientLog(patient)
			.addMedication(medication);
		}
	}
	
}

