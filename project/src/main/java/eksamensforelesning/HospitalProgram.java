package eksamensforelesning;

import java.io.IOException;

public class HospitalProgram {

	public static void main(String[] args) {

		Symptom headache = new Symptom("Headache");
		Symptom fever = new Symptom("Fever");

		Disease migraine = new Disease("Migraine");
		Medication naproxen = new Medication("Naproxen", 100);
		migraine.setRemedy(naproxen);

		Disease influenza = new Disease("Influenza");
		Medication paracet = new Medication("Paracet", 90);
		migraine.setRemedy(paracet);

		headache.addKnownCause(migraine);
		fever.addKnownCause(influenza);

		Hospital stOlavs = new Hospital();
		Nurse nurse1 = new Nurse("Kari", 25, stOlavs);
		Nurse nurse2 = new Nurse("Trond", 37, stOlavs);
		Nurse nurse3 = new Nurse("Nina", 50, stOlavs);
		Doctor doctor1 = new Doctor("John", 40, stOlavs, nurse1, nurse2);
		Doctor doctor2 = new Doctor("Anne", 30, stOlavs, nurse3);

		stOlavs.addEmployee(nurse1);
		stOlavs.addEmployee(nurse2);
		stOlavs.addEmployee(nurse3);
		stOlavs.addEmployee(doctor1);
		stOlavs.addEmployee(doctor2);
		stOlavs.getWaitingList().addListener(doctor1);
		stOlavs.getWaitingList().addListener(doctor2);

		Patient patient1 = new Patient("Magnus", 23, headache, fever);

		stOlavs.admit(patient1);
		stOlavs.getPatientDB().addPatientLog(patient1);
		stOlavs.handlePatient();

		System.out.println(patient1.getCurrentMedication().getName());
		System.out.println(stOlavs.getPatientDB().getPatientLog(patient1).getGivenMedicationList());
		System.out.println(stOlavs.getPatientDB().getPatientLog(patient1).getKnownDiseasesList());

		try {
			stOlavs.getPatientDB().getPatientLog(patient1).save("./magnus.txt");
		} catch (IOException e) {
			System.out.println("Kunne ikke lagre til fil");
		}

		try {
			stOlavs.getPatientDB().getPatientLog(patient1).load("./magnus.txt");
		} catch (IOException e) {
			System.out.println("Kunne ikke hente fra fil");
		}

		System.out.println(stOlavs.getPatientDB().getPatientLog(patient1).getGivenMedicationList());
		System.out.println(stOlavs.getPatientDB().getPatientLog(patient1).getKnownDiseasesList());
	}


}
