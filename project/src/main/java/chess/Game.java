package chess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.util.Pair;

public class Game {
	private Piece[][] board;
	private int size;
	private List<Pair<String, String>> path = new ArrayList<Pair<String, String>>();
	private int turn = 0;
	private List<Pair<String, String>> moves = new ArrayList<>();
	private List<Piece> takenPieces = new ArrayList<>();
	private List<Pair<String, String>> posibleMoves = new ArrayList<>();


	public Game(int size) {
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
	
	public void init5() {
		
		//init Pawns
		for (int i = 0; i < board.length; i++) {
			board[1][i] = new Pawn(i, 1, 1, this.board);//Black
			board[board.length-2][i] = new Pawn(i, board.length-2, 0, this.board);//White
		}
		//init Knights
		board[0][1] = new Knight(1, 0, 1, this.board);//Black
		board[0][6-3] = new Knight(6-3, 0, 1, this.board);//Black
		board[7-3][1] = new Knight(1, 7-3, 0, this.board);//White
		board[7-3][6-3] = new Knight(6-3, 7-3, 0, this.board);//White
		
		//init Bish
		
		//init Rook
		board[0][0] = new Rook(0, 0, 1, this.board);//Black
		board[0][7-3] = new Rook(7-3, 0, 1, this.board);//Black
		board[7-3][0] = new Rook(0, 7-3, 0, this.board);//White
		board[7-3][7-3] = new Rook(7-3, 7-3, 0, this.board);//White
		
		//init Queen
		
		//init King
		board[0][2] = new King(2, 0, 1, this.board);//Black
		board[7-3][2] = new King(2, 7-3, 0, this.board);//White
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
	
	public void moveFromStringLAN(String LAN) {
		this.movePiece(this.convertLANtoPair(LAN));
	}
	public List<Pair<String, String>> getPosPathsLAN(String ss) {
		String letters = "abcdefgh";
		String startLetter = ("" + ss.charAt(0)).toLowerCase();
		char startNumber = ss.charAt(1);
		
		if (!letters.contains(startLetter) || !Character.isDigit(startNumber)) {
			throw new IllegalArgumentException("Not a valid move");
		}
		
		int startX = letters.indexOf("" + startLetter); 
		int startY = this.size - Integer.parseInt("" + startNumber);
		
		ss = "" + startX + startY;
		return this.getPosPaths(ss);
	}
	public List<Pair<String, String>> getPosPaths(String ss) {
		List<Pair<String, String>> moves = new ArrayList<>();
		this.checkMate();
		
		for (Pair<String, String> move : this.posibleMoves) {
			if (move.getKey().equals(ss)) {
				Pair<String, String> p = new Pair<String, String>(move.getKey(), move.getValue());
				moves.add(p);
			}
		}
		return moves;

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
		
		//TODO: mekk queening, må fikse undolastmove!!
//		if (this.board[endY][endX].getType() == 'P' && (endY == 0 || endY == this.board.length - 1)) {
//			this.board[endY][endX] = new Queen(endX, endY, 0, this.board);
//		}
	}
	
	public void movePiece(Pair<String, String> move) {
		this.moveFromPair(move);
		if (this.checkMate()) {
			if (checkInChess(turn)) {
				System.out.println("Checkmate; " + (1-turn) + " Won");				
			} else {
				System.out.println("Stalemate LOL");
			}
		} else {
			if (this.checkInChess(1- turn)) {
				this.undoLastMove();
				throw new IllegalStateException("Du er i sjakk mann");
			}
		}

	}
	
	public void movesFromStringLongLAN(String moves) {
		String[] moveList = moves.split(" ");
		for (int i = 0; i < moveList.length; i++) {
			moveFromStringLAN(moveList[i]);
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
	

	public static void main(String[] args) {
		Game b = new Game(8);
		b.init8();
		System.out.println(b);
		b.getPiece(0, 6).moveTo(0, 4);
		System.out.println(b);
		
//		System.out.println(b.getBoard()[7][1].getPath());
//		System.out.println(b.getBoard()[1][1].getPath());
//		b.getBoard()[6][0].moveTo(0, 4);
//		b.getBoard()[1][1].moveTo(1, 3);
//		System.out.println(b);
//		System.out.println(b.getBoard()[4][0].getPath());
//		System.out.println(b.getBoard()[3][1].getPath());
//		b.getBoard()[4][0].moveTo(1, 3);
//		System.out.println(b);
//		b.getBoard()[3][1].moveTo(1, 2);
//		b.getBoard()[2][1].moveTo(1, 1);
//		System.out.println(b);
//		
//		System.out.println(b);
//		System.out.println(b.getBoard()[0][1].getPath());
//		b.getBoard()[0][1].moveTo(2, 2);
//		
//		System.out.println(b);
//		System.out.println(b.getBoard()[2][2].getPath());
//		b.getBoard()[2][2].moveTo(0, 3);
//		
//		System.out.println(b);
//		System.out.println(b.getBoard()[3][0].getPath());
//		b.getBoard()[3][0].moveTo(1, 1);
//
//		System.out.println(b);
//		System.out.println(b.getBoard()[1][1].getPath());
		
//		System.out.println(b.getPiece(2, 7).getPath());
//		b.getPiece(3, 6).moveTo(3, 4);
//		b.getPiece(1, 6).moveTo(1, 4);
//		System.out.println(b);
//		System.out.println(b.getPiece(2, 7).getPath());
//
//		b.getPiece(2, 7).moveTo(5, 4);
//		System.out.println(b);
//		System.out.println(b.getPiece(5, 4).getPath());
		
//		System.out.println(b.getPiece(0, 0).getPath());
//		b.getPiece(0, 1).moveTo(0, 3);
//		System.out.println(b);
//		System.out.println(b.getPiece(0, 0).getPath());
//		
//		b.getPiece(0, 0).moveTo(0, 2);
//		System.out.println(b);
//		System.out.println(b.getPiece(0, 2).getPath());
//		
//		b.getPiece(0, 2).moveTo(4, 2);
//		System.out.println(b);
//		System.out.println(b.getPiece(4, 2).getPath());

//		System.out.println(b.getPiece(3, 0).getPath());
//		b.getPiece(3, 1).moveTo(3, 3);
//		System.out.println(b);
//		System.out.println(b.getPiece(3, 0).getPath());
//
//		b.getPiece(3, 0).moveTo(3, 2);
//		System.out.println(b);
//		System.out.println(b.getPiece(3, 2).getPath());
//		
//		System.out.println(b.getPiece(4, 1).getPath());
//		b.getPiece(4, 1).moveTo(4, 3);
//		System.out.println(b);
//		System.out.println(b.getPiece(4, 0).getPath());
//
//		b.getPiece(4, 0).moveTo(4, 1);
//		System.out.println(b);
//		System.out.println(b.getPiece(4, 1).getPath());
		
//		b.moveFromStringLongAN("f2f3");
//		System.out.println(b);
//		b.moveFromStringLongAN("e7e5");
//		System.out.println(b);
//		b.moveFromStringLongAN("g2g4");
//		System.out.println(b);
//		b.moveFromStringLongAN("d8h4");
//		System.out.println(b);

//		b.movesFromStringLongLAN("f2f3 e7e5 g2g4 d8h4");
//		System.out.println(b);
		
//e4 e5 Nf3 Nc6 Bc4 Nf6 Ng5 d5 exd5 Na5 Bb5+ c6 dxc6 bxc6 Be2 h6 Nf3 e4 Ne5
		
//		String skoleMatt = "e2e4 e7e5 g1f3 b8c6 f1c4 g8f6 f3g5 f6e4 d1h5 h8g8 h5f7";
//		b.movesFromStringLongLAN(skoleMatt);
//		System.out.println(b);
//		b.checkMate();
		
//		System.out.println(b.getAllPaths(0));
//		b.moveFromStringLongAN("e2e4");
//		System.out.println(b);
//		System.out.println(b.getAllPaths(0));
//		b.getKing(0);
//		b.moveFromStringLongAN("e7e6");
//		b.moveFromStringLongAN("e1e2");
//		System.out.println(b);
//		b.getKing(0);
//		b.getKing(1);

//		String skoleMatt1 = "e2e4 e7e5 g1f3 b8c6 f1c4 g8f6 f3g5 f6e4";
//		String skoleMatt2 = "d1h5 h8g8 h5f7";
//		b.movesFromStringLongLAN(skoleMatt1);
//		System.out.println(b);
//		System.out.println(b.checkMate());
//		
//
//		System.out.println(b);
//		b.movesFromStringLongLAN(skoleMatt2);
//		
//		System.out.println(b.checkMate());

//		b.checkMate();
//		b.checkMate();
		
//		String sjekkSjakk = "e2e4 e7e5 d1h5 b8a6 h5f7";
//		b.movesFromStringLongLAN(sjekkSjakk);
//		System.out.println(b);
//
//		String sjekkSjakk = "e2e4 e7e5 d1h5 f7f6 g7g6";
//		b.movesFromStringLongLAN(sjekkSjakk);
//		System.out.println(b.getPiece(0, 1).getPath());
		
//		String veryLongGame = "d2d4 g8f6 c2c4 e7e6 g1f3 b7b6 a2a3 c8b7 b1c3 d7d5 c4d5 f6d5 d1c2 d5c3 b2c3 c7c5 e2e4 b8d7 c1f4 c5d4 c3d4 a8c8 c2b3 f8e7 f1d3 d7f6 b3b5 d8d7 f3e5 d7b5 d3b5 e8f8 f2f3 f6e8";
//		b.movesFromStringLongLAN(veryLongGame);
//		System.out.println(b.getLANArr());

	}
}
