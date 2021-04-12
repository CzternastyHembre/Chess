package tron;

public abstract class Tile {
	char type;	
	
	public Tile(char type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "" + type;
	}
	
}
