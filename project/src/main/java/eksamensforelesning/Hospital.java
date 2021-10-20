package eksamensforelesning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hospital {

	private List<Employee> employees = new ArrayList<>();
	private PatientDatabase patientDB = new PatientDatabase();
	private WaitingList waitingList = new WaitingList();

	public PatientDatabase getPatientDB() {
		return patientDB;
	}

	public WaitingList getWaitingList() {
		return waitingList;
	}

	public void admit(Patient patient) {
		this.waitingList.addPatient(patient);
		System.out.println(patient.getName()+" admitted");
	}

	public void handlePatient() {
		this.waitingList.handlePatient();
	}

	public void addEmployee(Employee employee) {
		if (!employees.contains(employee)) {
			employees.add(employee);
		}
	}

	public void removeEmployee(Employee employee) {
		if (employees.contains(employee)) {
			employees.remove(employee);
		}
	}
	public static void main(String[] args) {
		
	}
}
