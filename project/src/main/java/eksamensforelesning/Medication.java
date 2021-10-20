package eksamensforelesning;

public class Medication implements Comparable<Medication>
{

	private String name;
	private double price = Double.NaN;
	
	public Medication(String name) {
		this.name = name;
	}
	
	public Medication(String name, double price) {
		this(name);
		this.setPrice(price);
	}
	
	
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(double price) {
		if (price < 0) {
			throw new IllegalArgumentException();
		}
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Medication o) {
		return Double.compare(price, o.getPrice());
	}

}
