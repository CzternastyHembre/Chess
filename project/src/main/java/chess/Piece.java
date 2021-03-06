package chess;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public abstract class Piece implements IntPiece{
	//Bytt til privva!

	private boolean isFresh = true;
	protected int x;
	protected int y;
	protected int color; //0 is White, 1 is Black
	protected char type;
	protected Piece[][] board;
	protected List<Pair<String, String>> path = new ArrayList<Pair<String, String>>();
	protected String filePath = "file:C:\\Users\\matti\\git\\tdt4100-prosjekt-mattisch\\project\\src\\main\\resources\\images\\";

//	private List<Piece> whitePieces = new ArrayList<>();
//	private List<Piece> blackPieces = new ArrayList<>();
//	List<Pair<Integer, Integer>> whitePath = new ArrayList<Pair<Integer, Integer>>();
//	List<Pair<Integer, Integer>> blackPath = new ArrayList<Pair<Integer, Integer>>();

	public Piece(int x, int y, int color, Piece[][] board, char type) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.board = board;
		this.type = type;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public String getPos() {
		return "" + this.x + this.y;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	
	public char getType() {
		return type;
	}
//	if (color == 0) {
//		this.addToWhitePiece();
//	} else {
//		this.addToBlackPiece();
//	}
//	
//	public void addToWhitePiece() {
//		whitePieces.add(this);
//	}
//	public void addToBlackPiece() {
//		blackPieces.add(this);
//	} 
//	
//	public List<Pair<Integer, Integer>> getAllBlackPath(){
//		for (int i = 0; i < blackPieces.size(); i++) {
//			blackPath.addAll(this.getPath());
//		}
//		System.out.println(blackPath);
//		System.out.println(blackPieces);
//		return blackPath;
//		
//	}
	public boolean isFresh() {
		return isFresh;
	}
	
	public void makeOld() {
		this.isFresh = false;
	}

	public abstract List<Pair<String, String>> getPath();

	public void moveTo(int x_to, int y_to) {
		if (!this.isLegalMove(x_to, y_to)) {
			throw new IllegalStateException("Not a legal move");
		}
		if (this.type == 'P' && (y_to == 0 || y_to == this.board.length - 1)) {
			board[y_to][x_to] = new Queen(x_to, y_to, this.color, this.board);
			board[this.y][this.x] = null;
			board[y_to][x_to].isFresh = true;
		} else {
			board[y_to][x_to] = this;
			board[this.y][this.x] = null;
			this.x = x_to;
			this.y = y_to; 			
			board[y_to][x_to].isFresh = false;
		}
		
		
//		if (this.board[endY][endX].getType() == 'P' && (endY == 0 || endY == this.board.length - 1)) {
//			this.board[endY][endX] = new Queen(endX, endY, 0, this.board);
//		}

	}
	
	public boolean isLegalMove(int x_to, int y_to) {
		List<Pair<String, String>> path = this.getPath();
		String targetEnd = ""+ x_to + y_to;
//		String targetStart = "" + this.x + this.y Note: not neded
		for (Pair<String, String> pair : path) {
			if (pair.getValue().equals(targetEnd)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isWhite() {
		if (this.color == 0) {
			return true;
		}
		return false;
	}
	
	
	public int getColor() {
		return color;
	}
	
	public void clearPath() {
		path.clear();
	}

	public boolean isInBoard(int x, int y) {
		int max = Math.max(x, y);
		int min = Math.min(x, y);
		return max < this.board.length && min >= 0;
	}
	
	
	
	public String toString() {
		if (isWhite()) {
			return ""+type;
		}
		return ""+Character.toLowerCase(type);
	}


}
