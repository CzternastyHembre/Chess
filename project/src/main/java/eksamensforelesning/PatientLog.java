package eksamensforelesning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

	public class PatientLog {

	private Collection<Disease> knownDiseasesList = new ArrayList<>();
	private Collection<Medication> givenMedicationList = new ArrayList<>();

	private final Patient patient;

	public PatientLog(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {
		return patient;
	}

	public void addMedication(Medication medication) {
		if (!givenMedicationList.contains(medication)) {
			givenMedicationList.add(medication);
		}
	}

	public void addDisease(Disease disease) {
		if (!knownDiseasesList.contains(disease)) {
			knownDiseasesList.add(disease);
		}
	}

	public Collection<Medication> getGivenMedicationList() {
		return givenMedicationList;
	}

	public Collection<Disease> getKnownDiseasesList() {
		return knownDiseasesList;
	}
}
