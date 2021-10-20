package eksamensforelesning;

public class Disease {

	private Medication remedy;
	private String name;

	public Disease(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Medication getRemedy() {
		return remedy;
	}

	public void setRemedy(Medication remedy) {
		this.remedy = remedy;
	}

	@Override
	public String toString() {
		return this.getName();
	}

}
