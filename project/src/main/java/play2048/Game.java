package play2048;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Game {
	private Tile[][] board;
	private int heigth;
	private int width;
	private int score;
	
	public Game(int heigth, int width) {
		this.heigth = heigth;
		this.width = width;
		board = new Tile[heigth][width];
		
		int StartingAmount = (int) width * heigth / 8;
		StartingAmount = 2;
		for (int i = 0; i < StartingAmount; i++) {
			int rYIndex = (int) (Math.random() * heigth);
			int rXIndex = (int) (Math.random() * width);
			while (board[rYIndex][rXIndex] != null) {
				rYIndex = (int) (Math.random() * heigth);
				rXIndex = (int) (Math.random() * width);				
			}
			board[rYIndex][rXIndex] = new Tile();
		}
	}
	
	public int getHeigth() {
		return heigth;
	}
	public int getWidth() {
		return width;
	}
	public int getScore() {
		return score;
	}
	public Tile[][] getBoard() {
		return board;
	}
	

	public void sortX(Comparator<Tile> comparator) {
		for (Tile[] row : board) {//Goes trough all rows an sort them so the "null" tiles are at the end
			Arrays.sort(row, comparator);
		}
	}
	
	public boolean checkIdentical(Tile[][] b1, Tile[][] b2) {
		for (int y = 0; y < heigth; y++) {
			for (int x = 0; x < width; x++) {
				if (b1[y][x] == null || b2[y][x] == null) {
					if (b1[y][x] != b2[y][x]) {
						return false;
					}
					
				} else if (b1[y][x].getValue() != b2[y][x].getValue()) {
					return false;
				}
			}
		}
		throw new IllegalArgumentException("Not allowed to move there");
	}
	
	public void sortRight() {
		sortX(new TileComparator());
	}
	public void sortLeft() {
		sortX(new TileComparator().reversed());
	}
	
	public void flipBoard() {
		Tile[][] colrow = new Tile[width][heigth];
		this.heigth = colrow.length;
		this.width = colrow[0].length;
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				colrow[x][y] = board[y][x];
			}
		}
		board = colrow;
	}
	
	public void sortY(Comparator<Tile> comparator) {
		flipBoard();
		sortX(comparator);
		flipBoard();
	}
	public void sortUp() {
		sortY(new TileComparator().reversed());		
	}
	public void sortDown() {
		sortY(new TileComparator());
	}
	
	public void addX(int dir) {
		for (Tile[] row : board) {
			for (int x = 0; x < width - 1; x++) {//Iterates through matrix
				int i = dir > 0 ? x : width - x - 1;//Counts x up if x < 0 and down from width if x > 0
				if (row[i] != null && row[i + dir] != null) {
					if (row[i].getValue() == row[i + dir].getValue()) {
						row[i].nextValue();
						row[i + dir] = null;
						score += row[i].getValue();
					}
				}
			}
		}
	}
	public void addRight() {
		addX(-1);
	}
	public void addLeft() {
		addX(1);
	}
	
	public void addY(int dir) {
		flipBoard();
		addX(dir);
		flipBoard();
	}
	public void addUp() {
		addY(1);
	}
	public void addDown() {
		addY(-1);
	}
	
	public void moveRight() {
		Tile[][] boardCopy = new Tile[heigth][width];

		for (int y = 0; y < heigth; y++) {
			for (int x = 0; x < width; x++) {
				boardCopy[y][x] = board[y][x];
			}
		}

		sortRight();
		addRight();
		sortRight();
		
		checkIdentical(boardCopy, board);
		addTile();
		
	}
	public void moveLeft() {
		Tile[][] boardCopy = new Tile[heigth][width];

		for (int y = 0; y < heigth; y++) {
			for (int x = 0; x < width; x++) {
				boardCopy[y][x] = board[y][x];
			}
		}

		sortLeft();
		addLeft();
		sortLeft();
		
		checkIdentical(boardCopy, board);
		addTile();
}
	public void moveUp() {
		Tile[][] boardCopy = new Tile[heigth][width];

		for (int y = 0; y < heigth; y++) {
			for (int x = 0; x < width; x++) {
				boardCopy[y][x] = board[y][x];
			}
		}

		sortUp();
		addUp();
		sortUp();
		
		checkIdentical(boardCopy, board);
		addTile();
	}
	public void moveDown() {
		Tile[][] boardCopy = new Tile[heigth][width];

		for (int y = 0; y < heigth; y++) {
			for (int x = 0; x < width; x++) {
				boardCopy[y][x] = board[y][x];
			}
		}

		sortDown();
		addDown();
		sortDown();
		
		checkIdentical(boardCopy, board);
		addTile();
	}

	public void addTile() {
		List<Integer> emptyIndexes = new ArrayList<>();
		for (int y = 0; y < heigth; y++) {
			for (int x = 0; x < width; x++) {
				if (board[y][x] == null) {
					emptyIndexes.add(y * width + x);
				}
			}
		}
		int l = emptyIndexes.size();
		if (l == 0) {
			throw new IllegalArgumentException("No space"); // Wont ever happen, throws beefore
		}
		int rIndex = (int) Math.floor(Math.random()*l);
		board[emptyIndexes.get(rIndex) / width][emptyIndexes.get(rIndex) % width] = new Tile();
	}
	
	@Override
	public String toString() {
		String l = "--";
		String ll = "";
		for (int y = 0; y < this.heigth; y++) {
			String s = "|";
			for (int x = 0; x < this.width; x++) {
				if (y == 0) {					
					l += "-";
				}
				if (board[y][x] == null) {
					s += " ";
				} else {
					s += board[y][x];
				}
			}
			ll += s + "|\n";
		}
		l += "\n";
		return l + ll + l;
	}
	
	public static void main(String[] args) {
		Game g = new Game(4, 4);
		System.out.println(g);
		for (int i = 0; i < 50; i++) {
			g.moveDown();
			System.out.println(g);
			g.moveRight();
			System.out.println(g);
		}
		System.out.println(g.score);
		
	}
}
