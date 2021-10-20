package eksamensforelesning;

public interface Employee{

	void setAvailable(boolean isAvailable);
	boolean isAvailable();
	
	Hospital getEmployer();
	void medicate(Patient patient, Medication medication);
	
}
