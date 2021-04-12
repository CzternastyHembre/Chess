package tron;

class Player extends Tile{
	int[] pos = {0,0};
	public static final char playerType = 'P';

	public int[] getPos() {
		return pos;
	}
	
	public void setPos(int[] pos) {
		this.pos = pos;
	}
	
	public Player(int y, int x) {
		super(playerType);
	}
	
	
}
