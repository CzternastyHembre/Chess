package prosjekt;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class Board {
	private Piece[][] board;
	private int size;
	private List<Pair<String, String>> path = new ArrayList<Pair<String, String>>();
	private int turn = 0;
	private List<Pair<String, String>> moves = new ArrayList<>();
	private List<Piece> takenPieces = new ArrayList<>();
	private List<Pair<String, String>> posibleMoves = new ArrayList<>();


	public Board(int size) {
		this.board = new Piece[size][size];
		this.size = size;
	}
	
	public List<Piece> getTakenPieces() {
		return takenPieces;
	}
	
	public List<Pair<String, String>> getMoves() {
		return moves;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public void nextTurn() {
		if (this.turn == 0) {
			this.turn = 1;
		} else {
			this.turn = 0;
		}
	}
	
	public void prevTurn() {
		this.nextTurn();
	}
	
	public int getSize() {
		return size;
	}
	
	public void init8() {//init pieces in 8x8
		
		//init Pawns
		for (int i = 0; i < board.length; i++) {
			board[1][i] = new Pawn(i, 1, 1, this.board);//Black
			board[board.length-2][i] = new Pawn(i, board.length-2, 0, this.board);//White
		}
		//init Knights
		board[0][1] = new Knight(1, 0, 1, this.board);//Black
		board[0][6] = new Knight(6, 0, 1, this.board);//Black
		board[7][1] = new Knight(1, 7, 0, this.board);//White
		board[7][6] = new Knight(6, 7, 0, this.board);//White
		
		//init Bish
		board[0][2] = new Bishop(2, 0, 1, this.board);//Black
		board[0][5] = new Bishop(5, 0, 1, this.board);//Black
		board[7][2] = new Bishop(2, 7, 0, this.board);//White
		board[7][5] = new Bishop(5, 7, 0, this.board);//White
		
		//init Rook
		board[0][0] = new Rook(0, 0, 1, this.board);//Black
		board[0][7] = new Rook(7, 0, 1, this.board);//Black
		board[7][0] = new Rook(0, 7, 0, this.board);//White
		board[7][7] = new Rook(7, 7, 0, this.board);//White
		
		//init Queen
		board[0][3] = new Queen(3, 0, 1, this.board);//Black
		board[7][3] = new Queen(3, 7, 0, this.board);//White
		
		//init King
		board[0][4] = new King(4, 0, 1, this.board);//Black
		board[7][4] = new King(4, 7, 0, this.board);//White
	}
	
	public Piece[][] getBoard() {
		return board;
	}
	
	public boolean isInsideBoard(int x, int y) {
		int maxSize = Math.max(x, y);
		int minSize = Math.min(x, y);
		return maxSize > this.size && minSize >= 0;
	}
	
	public Piece getPiece(int x, int y) {
		return board[y][x];
	}
	
	public Pair<String, String> getPopMove() {
		if (moves.size() > 0) {
			Pair<String, String> lastMove = moves.remove(moves.size() - 1);
			return lastMove;
		}
		return null;
	}

	public Piece popLastPiece() {
		if (this.takenPieces.size() > 0) {
			return takenPieces.remove(takenPieces.size() - 1);
		}
		return null;
	}

	public List<Pair<String, String>> getAllPaths(int color) {
		path.clear();
		for (int i = 0; i < this.getSize(); i++) {
			for (int j = 0; j < this.getSize(); j++) {
				
				if (this.board[i][j] != null) {					
					if (this.board[i][j].getColor() == color) {
						path.addAll(this.board[i][j].getPath());
					}
				}
			}
		}
		return path;
	}
	
	public Piece getKing(int color) {
		for (int i = 0; i < this.getSize(); i++) {
			for (int j = 0; j < this.getSize(); j++) {
				if (this.board[i][j] != null) {
					if (board[i][j].getType() == 'K' && board[i][j].getColor() == color) {
						return board[i][j];
					}
				}
			}
		}
		return null;
	}
	
	public boolean checkInChess(int turn) {
		Piece king = this.getKing(turn);
		List<Pair<String, String>> allOpositePaths = this.getAllPaths(1 - turn);
		
		for (Pair<String, String> pair : allOpositePaths) {
			if (pair.getValue().equals(king.getPos())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkMate() {
		List<Pair<String, String>> allTurnPaths = new ArrayList<>(this.getAllPaths(turn));
		boolean mate = true;
		this.posibleMoves.clear();
		for (Pair<String, String> pair : allTurnPaths) {
			this.moveFromPair(pair);
			if (!checkInChess(1 - turn)) {//Getting the opposite move because movefrom pair runs nextmove
				this.posibleMoves.add(pair);
				mate = false;
				this.undoLastMove();
//				return false; 
//				return false if you dont want all possible moves, but faster runtime
			} else {
				this.undoLastMove();				
			}
		}
//		System.out.println(posibleMoves);
		return mate;
	}
	
	public void addPiece(Piece piece) {
		this.takenPieces.add(piece);
	}

	public void undoLastMove() {
		//Getting the last move and last piece
		Pair<String, String> lastMove = this.getPopMove();
		Piece lastPiece = this.popLastPiece();

		if (lastMove == null) {
			throw new IllegalStateException("Not valid in this state");
		}
		//Reversing the move
		Pair<String, String> reversedMove = new Pair(lastMove.getValue(), lastMove.getKey());
		
		this.prevTurn();
		int startX = Integer.parseInt(reversedMove.getKey().charAt(0) + "");
		int startY = Integer.parseInt(reversedMove.getKey().charAt(1) + "");
		int endX = Integer.parseInt(reversedMove.getValue().charAt(0) + "");
		int endY = Integer.parseInt(reversedMove.getValue().charAt(1) + "");

		//Move the piece back and add the taken piece
		board[endY][endX] = board[startY][startX];
		board[startY][startX] = lastPiece;
		board[endY][endX].setX(endX);
		board[endY][endX].setY(endY);
	}
	
	public void moveFromStringLongAN(String LAN) {
		this.moveFromPair(this.convertLANtoPair(LAN));
		
		if (this.checkMate()) {
			System.out.println(this);
			System.out.println("Checkmate; " + (1-turn) + " Won");
		} else {
			if (this.checkInChess(1- turn)) {
				this.undoLastMove();
				System.out.println("Du er i sjakk mann");
//				throw new IllegalStateException("Du er i sjakk mann");
			}
		System.out.print(this);	
		}
	}
	
	public Pair<String, String> convertLANtoPair(String LAN) {
		//Moving the pice for a "LAN" notation i.e. e4d5, not pxd5
		if (LAN.length() != 4) {
			throw new IllegalArgumentException("Move has to be 4 characters long");
		}
		
		String letters = "abcdefgh";
		String startLetter = ("" + LAN.charAt(0)).toLowerCase();
		String endLetter = ("" + LAN.charAt(2)).toLowerCase();
		char startNumber = LAN.charAt(1);
		char endNumber = LAN.charAt(3);
		
		//Validate move
		if (!letters.contains(startLetter) || !letters.contains(endLetter) || !Character.isDigit(startNumber) || !Character.isDigit(endNumber)) {
			throw new IllegalArgumentException("Not a valid move");
		}
		
		int startX = letters.indexOf("" + startLetter); 
		int startY = this.size - Integer.parseInt("" + startNumber);
		int endX = letters.indexOf("" + endLetter); 
		int endY = this.size - Integer.parseInt("" + endNumber);
		
		if (this.isInsideBoard(startY, endY) || this.board[startY][startX] == null) {
			throw new IllegalArgumentException("Not a valid move");			
		}

		Pair<String, String> pairMove = new Pair("" + startX + startY, "" + endX + endY);
		return pairMove;
	}
	
	public List<String> getLANArr() {
		List<Pair<String, String>> moves = this.posibleMoves;
		List<String> allLANArr = new ArrayList<>();
		for (Pair<String, String> pair : moves) {
			allLANArr.add(this.convertPairToLAN(pair));
		}
		return allLANArr;
	}

	public String convertPairToLAN(Pair<String, String> move) {
		String letters = "abcdefgh";

		int istartX = Integer.parseInt(move.getKey().charAt(0) + "");
		int startY = this.size - Integer.parseInt("" + move.getKey().charAt(1));
		int iendX = Integer.parseInt(move.getValue().charAt(0) + "");
		int endY = this.size - Integer.parseInt("" + move.getValue().charAt(1));
		
		char startX = letters.charAt(istartX);
		char endX = letters.charAt(iendX);
		
		String LAN = "" + startX + startY + endX + endY;
		return LAN;

	}
	public void moveFromPair(Pair<String, String> move) {
		//The base moving method
		int startX = Integer.parseInt(move.getKey().charAt(0) + "");
		int startY = Integer.parseInt(move.getKey().charAt(1) + "");
		int endX = Integer.parseInt(move.getValue().charAt(0) + "");
		int endY = Integer.parseInt(move.getValue().charAt(1) + "");
		
		if (this.board[startY][startX].getColor() != this.getTurn()) {
			throw new IllegalArgumentException("Wrong color to move");
		}
		this.addPiece(this.board[endY][endX]);
		this.board[startY][startX].moveTo(endX, endY);
		this.nextTurn();
		moves.add(move);
		
	}
	
	public void movesFromStringLongLAN(String moves) {
		String[] moveList = moves.split(" ");
		for (int i = 0; i < moveList.length; i++) {
			moveFromStringLongAN(moveList[i]);
		}
	}
	
	@Override
	public String toString() {
		String boardString = "";
		String line = "";
		for (int i = 0; i < size; i++) {
			line = "|";
			String row = "|";
			for (int j = 0; j < size; j++) {
				line += "----";
				if (board[i][j] == null) {
					row += " " + " " + " |";
				} else {					
					row += " " + board[i][j] + " |";
				}
			}
			line = line.substring(0, line.length()-1);
			boardString += line + "|\n";
			boardString += row + "\n";
		}
		boardString += line + "|\n";
			
		return boardString;
	}
	


}
