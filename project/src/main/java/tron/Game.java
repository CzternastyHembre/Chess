package tron;


public class Game {
	
	Tile[][] board;
	Player p;
	
	public Game(int y, int x) {
		this.board = new Tile[y][x];
		
		Player p = new Player(0,0);
		this.p = p;
		board[0][0] = p;
	}
	
	public boolean isInBounds(int[] pos) {
		return pos[0] < board.length && pos[0] >= 0 && pos[1] < board[0].length && pos[1] >= 0;
	}
	
	public void move(int [] dir) {
		int[] pos = p.getPos();
		int[] targetPos = {pos[0] + dir[0], pos[1] + dir[1]};
		if (!isInBounds(targetPos)) {
			board[pos[0]][pos[1]] = null;
			p = null;
			return;
		}
		
		p.setPos(targetPos);
		board[pos[0]][pos[1]] = null;
		board[targetPos[0]][targetPos[1]] = p;
	}
	
	public void moveLeft() {
		move(new int[]{0,-1});
	}
	public void moveRight() {
		move(new int[]{0,1});
	}
	public void moveUp() {
		move(new int[]{-1,0});
	}
	public void moveDown() {
		move(new int[]{1,0});
	}
	
	@Override
	public String toString() {
		String s = "";
		String l = "--";
		for (int y = 0; y < board.length; y++) {
			s += "|";
			for (int x = 0; x < board[y].length; x++) {
				s += board[y][x] != null ? board[y][x] : " ";
				l += y == 0 ? "-" : " ";
			}
			s += "|\n";
		}
		l += "\n";
		return l + s + l;
	}
	
	public Tile[][] getBoard() {
		return board;
	}
	
	public int getWidth() {
		return this.board[0].length;
	}
	public int getHeigth() {
		return this.board.length;
	}
	
	public static void main(String[] args) {
		Game g = new Game(10,30);
		System.out.println(g);
		
		
		
		g.moveRight();
		System.out.println(g);
		g.moveDown();
		System.out.println(g);
		g.moveLeft();
		System.out.println(g);
		g.moveUp();
		System.out.println(g);
	}
	
}
