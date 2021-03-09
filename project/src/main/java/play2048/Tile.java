package play2048;

public class Tile {
	private int value;
	private static final String[] colors = {
			"A9D7A9", // -> 2
			"49D1BA", // -> 4
			"FA9A52", // -> 8
			"E0614C", // -> 16
			"D13F44", // -> 32
			"7F2B3E", // -> 64
			"3B2841", // -> 128
			"83382D", // -> 256
			"F4C117", // -> 512
			"FAE34F", // -> 1024
			"C59E49", // -> 2048
			"c09400", // -> 4096
			
	};
	private int colorIndex = 0;
	
	public Tile() {
		boolean isTwo = Math.random() < 0.9;
		if (isTwo) {
			this.value = 2;
			
		} else {
			this.value = 4;
			colorIndex++;
		}
	}
	
	public Tile(int n) {
		this.value = (int) Math.pow(2, n);
		colorIndex = n - 1;
	}
	
	public int getValue() {
		return value;
	}
	
	public void nextValue() {
		this.value = this.value*2;
		colorIndex++;
	}
	
	public String getColor() {
		int i = colorIndex % colors.length;
		return colors[i];
	}
	
	public int getLength() {
		return Integer.toString(value).length();
	}
	
	@Override
	public String toString() {
		return "" + this.value;
	}

}
