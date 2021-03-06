package play2048;

public class Tile {
	private int value;
	
	public Tile() {
		boolean isTwo = Math.random() < 1;
		if (isTwo) {
			this.value = 2;
		} else {
			this.value = 4;
		}
	}
	
	public int getValue() {
		return value;
	}
	
	public void nextValue() {
		this.value = this.value*2;
	}
	
	@Override
	public String toString() {
		return "" + this.value;
	}

}
