package eksamensforelesning;

import java.util.ArrayList;
import java.util.List;

public class Symptom {

	private List<Disease> knownCauses = new ArrayList<>();
	private String description;

	public Symptom(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Disease> getKnownCauses() {
		return knownCauses;
	}

	public void addKnownCause(Disease disease) {
		if (!knownCauses.contains(disease)) {
			knownCauses.add(disease);
		}
	}

	@Override
	public String toString() {
		return this.getDescription();
	}



}
