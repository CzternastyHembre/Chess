package play2048;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.sun.tools.javac.code.Attribute.Array;

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
	
	
	public void move(int yDir, int xDir) {
	}
	
	public void moveUp() {
		move(-1, 0);
	}
	public void moveDown() {
		Tile[][] colrow = new Tile[width][heigth];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < heigth; y++) {
				colrow[x][y] = board[y][x];
			}
		}
		for (Tile[] col : colrow) {
			Arrays.sort(col, new TileComparator());
		}
		for (Tile[] row : colrow) {
			for (int i = row.length - 1; i > 0; i--) {
				if (row[i] != null && row[i-1] != null) {
					if (row[i].getValue() == row[i - 1].getValue()) {
						row[i].nextValue();
						row[i-1] = null;
						this.score += row[i].getValue();
						
					}
				}
			}
		}
		for (Tile[] col : colrow) {
			Arrays.sort(col, new TileComparator());
		}
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < heigth; y++) {
				board[y][x] = colrow[x][y];
			}
		}
		
		
	}
	public void moveRight() {
		for (Tile[] row : board) {
			Arrays.sort(row, new TileComparator());
		}
		for (Tile[] row : board) {
			for (int i = row.length - 1; i > 0; i--) {
				if (row[i] != null && row[i-1] != null) {
					if (row[i].getValue() == row[i - 1].getValue()) {
						row[i].nextValue();
						row[i-1] = null;
						this.score += row[i].getValue();

					}
				}
			}
		}	
		for (Tile[] row : board) {
			Arrays.sort(row, new TileComparator());
		}
		
	}
	public void moveLeft() {
		for (Tile[] row : board) {
			Arrays.sort(row, new TileComparator());
		}
		for (Tile[] row : board) {
			for (int i = row.length - 1; i > 0; i--) {
				if (row[i] != null && row[i-1] != null) {
					if (row[i].getValue() == row[i - 1].getValue()) {
						row[i].nextValue();
						row[i-1] = null;
						this.score += row[i].getValue();

					}
				}
			}
		}	
		for (Tile[] row : board) {
			Arrays.sort(row, new TileComparator());
		}

		for (Tile[] row : board) {
			List<Tile> rowList = Arrays.asList(row);
			Collections.reverse(rowList);
		}
			
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
		for (int i = 0; i < 100; i++) {
			g.board[0][0] = new Tile();
			g.moveDown();
			System.out.println(g);
			g.moveRight();
			System.out.println(g);
			System.out.println(g.score);
		}
		
//		g.moveLeft();
//		System.out.println(g);
		
		
//		Tile t1 = new Tile();
//		Tile t2 = new Tile();
//		
//		
//		Tile[] row = {t1,null, t2, null};
//		
//		System.out.println(row[0] + " " +row[1] + " " +row[2] + " " +row[3]);
//		Arrays.sort(row, new TileComparator());
//		System.out.println(row[0] + " " +row[1] + " " +row[2] + " " +row[3]);
		
		
		
		
//		g.move(-1, -1); TODO

	}
}
