package eksamensforelesning;

public interface ObservableList {
	
	void addListener(ListListener l);
	
	void removeListener(ListListener l);
	
	void fireListChanged(Patient patient);

}
