package eksamensforelesning;

import java.util.ArrayList;
import java.util.List;

public class WaitingList implements ObservableList
{

	List<Patient> patientList = new ArrayList<>();
	List<ListListener> waitingListListeners = new ArrayList<>();

	public void addPatient(Patient patient) {
		if(!this.patientList.contains(patient)) {
			this.patientList.add(patient);
		}
	}

	public void removePatient(Patient patient) {
		if(this.patientList.contains(patient)) {
			this.patientList.remove(patient);
		}
	}

	public Patient handlePatient() {
		Patient nextPatient = this.patientList.remove(0);
		System.out.println(nextPatient.getName()+" ready to be handled");
		// send til lyttere
		return nextPatient;
	}

	@Override
	public void addListener(ListListener l) {
		if (!waitingListListeners.contains(l)) {
			waitingListListeners.add(l);
		}
	}

	@Override
	public void removeListener(ListListener l) {
		if (waitingListListeners.contains(l)) {
			waitingListListeners.remove(l);
		}
	}

	@Override
	public void fireListChanged(Patient patient) {
		waitingListListeners.forEach(w -> w.fireListChanged(patient));
	}

}
