package chess.pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.util.Pair;

public abstract class Piece implements IntPiece{
	
	/**
	 * is "<b>fresh</b>" if the piece has newly <i>"queened"</i>
	 */
	private boolean isFresh = true;
	protected int x;
	protected int y;
	protected int color; //0 is White, 1 is Black
	protected char type;
	protected Piece[][] board;
	protected List<Pair<String, String>> path = new ArrayList<Pair<String, String>>();
	protected String filePath = "file:C:\\Users\\matti\\git\\tdt4100-prosjekt-mattisch\\project\\src\\main\\resources\\images\\";

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

	public boolean isFresh() {
		return isFresh;
	}
	
	public void makeOld() {
		this.isFresh = false;
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

	/**
	 * Returns the path for <b>this</b>
	 */
	public abstract List<Pair<String, String>> getPath();
	
	/**
	 * 
	 * Moves the piece if it is a legal move
	 * @param x_to
	 * @param y_to
	 */
	public void moveTo(int x_to, int y_to) {
		if (!this.isLegalMove(x_to, y_to)) {
			throw new IllegalStateException("Not a legal move");
		}
		if (this.type == 'P' && (y_to == 0 || y_to == this.board.length - 1)) {//check if a pawn is at the edge of the board -> queening
			board[y_to][x_to] = new Queen(x_to, y_to, this.color, this.board);
			board[this.y][this.x] = null;
			board[y_to][x_to].isFresh = true;
		} else {//
			board[y_to][x_to] = this;
			board[this.y][this.x] = null;
			this.x = x_to;
			this.y = y_to; 			
			board[y_to][x_to].isFresh = false;
		}
		
		

	}

	/**
	 * 
	 * @param x_to
	 * @param y_to
	 * @return
	 * <b>true</b> if the move from {@code this} to {@code x_to}, {@code y_to} is inside the paths from <b>this</b>
	 */
	public boolean isLegalMove(int x_to, int y_to) {
		List<Pair<String, String>> path = this.getPath();
		String targetEnd = ""+ x_to + y_to;
		for (Pair<String, String> pair : path) {
			if (pair.getValue().equals(targetEnd)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param x
	 * @param y
	 * @return 	 
	 * Method that returns true if the parameters is true coordinates is inside the board
	 */
	public boolean isInBoard(int...ints) {
		List<Integer> intList = new ArrayList<Integer>(ints.length);
		for (int i : ints)
		{
		    intList.add(i);
		}
		Math.signum(1);
		
		int max = Collections.max(intList);
		int min = Collections.min(intList);
		return max < board.length && min >= 0;
	}
	

	/**
	 * {@code toString method for the console}
	 * @return
	 * The {@code type} in lower- or upper case, depending on the {@code color}
	 */
	public String toString() {
		if (isWhite()) {
			return ""+type;
		}
		return ""+Character.toLowerCase(type);
	}


}
