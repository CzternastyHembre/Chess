package chess;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import chess.saveHandler.ChessSaveHandler;
import chess.saveHandler.SaveHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.util.Pair;

/**
 * @author Mattis Czternasty Hembre
 * @Todo_next_time
 *Make own class for Move and Moves
 */
public class Game {
	
	private Piece[][] board;
	private int size;
	private List<Pair<String, String>> paths = new ArrayList<Pair<String, String>>();
	private int turn = 0;
	private List<Pair<String, String>> moves = new ArrayList<>();
	private List<Piece> takenPieces = new ArrayList<>();
	private List<Pair<String, String>> posibleMoves = new ArrayList<>();
	private boolean isGameOver = false;
	private List<Piece> kingList = new ArrayList<>();
	
	public Game(int size) {
		this.board = new Piece[size][size];
		this.size = size;
	}
	
	public Game() {
		this(8);
	}
	
	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	
	public boolean isGameOver() {
		return this.isGameOver;
	}

	public List<Piece> getTakenPieces() {
		return takenPieces;
	}
	
	public List<Pair<String, String>> getMoves() {
		return moves;
	}
	
	public int getTurn() {
		return turn % 2;
	}
	
	public void nextTurn() {
		turn++;
	}
	
	public void prevTurn() {
		turn--;
	}
	
	public int getSize() {
		return size;
	}
	
	/**
	 * Initiates the board
	 * @param i the size of the board
	 * @throws 
	 * if there isn't a board ready for the size
	 */
	public void init(int i) {

		switch (i) {
		case 8:
			init8();
			break;
		case 5:
			init5();
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + i);
		}
	}
	
	/**
	 * Initiates the pieces for a 8x8 board
	 */
	public void init8() {
		
		//Initiate Pawns
		for (int i = 0; i < board.length; i++) {
			board[1][i] = new Pawn(i, 1, 1, this.board);//Black
			board[board.length-2][i] = new Pawn(i, board.length-2, 0, this.board);//White
		}
		//Initiate Knights
		board[0][1] = new Knight(1, 0, 1, this.board);//Black
		board[0][6] = new Knight(6, 0, 1, this.board);//Black
		board[7][1] = new Knight(1, 7, 0, this.board);//White
		board[7][6] = new Knight(6, 7, 0, this.board);//White
		
		//Initiate Bish
		board[0][2] = new Bishop(2, 0, 1, this.board);//Black
		board[0][5] = new Bishop(5, 0, 1, this.board);//Black
		board[7][2] = new Bishop(2, 7, 0, this.board);//White
		board[7][5] = new Bishop(5, 7, 0, this.board);//White
		
		//Initiate Rook
		board[0][0] = new Rook(0, 0, 1, this.board);//Black
		board[0][7] = new Rook(7, 0, 1, this.board);//Black
		board[7][0] = new Rook(0, 7, 0, this.board);//White
		board[7][7] = new Rook(7, 7, 0, this.board);//White
		
		//Initiate Queen
		board[0][3] = new Queen(3, 0, 1, this.board);//Black
		board[7][3] = new Queen(3, 7, 0, this.board);//White
		
		//InitiateKing
		board[0][4] = new King(4, 0, 1, this.board);//Black
		board[7][4] = new King(4, 7, 0, this.board);//White
	}
	
	/**
	 * Initiates the pieces for a 5x5 board
	 */	
	public void init5() {
		
		//Initiate Pawns
		for (int i = 0; i < board.length; i++) {
			board[1][i] = new Pawn(i, 1, 1, this.board);//Black
			board[board.length-2][i] = new Pawn(i, board.length-2, 0, this.board);//White
		}
		//Initiate Knights
		board[0][6-3] = new Knight(6-3, 0, 1, this.board);//Black
		board[7-3][6-3] = new Knight(6-3, 7-3, 0, this.board);//White
		
		//Initiate Bish
		board[0][6-4] = new Bishop(6-4, 0, 1, this.board);//Black
		board[7-3][6-4] = new Bishop(6-4, 7-3, 0, this.board);//White
		
		//Initiate Rook
		board[0][7-3] = new Rook(7-3, 0, 1, this.board);//Black
		board[7-3][7-3] = new Rook(7-3, 7-3, 0, this.board);//White
		
		//Initiate Queen
		board[0][6-5] = new Queen(6-5, 0, 1, this.board);//Black
		board[7-3][6-5] = new Queen(6-5, 7-3, 0, this.board);//White
		
		//Initiate King
		board[0][0] = new King(0, 0, 1, this.board);//Black
		board[7-3][0] = new King(0, 7-3, 0, this.board);//White
	}
	
	public Piece[][] getBoard() {
		return board;
	}
	
	/**
	 * Checks if the integers is within the size of the board
	 * @param ints
	 * @return
	 */
	public boolean isInsideBoard(int...ints) {
		List<Integer> intList = new ArrayList<Integer>(ints.length);
		for (int i : ints)
		{
		    intList.add(i);
		}
		
		int max = Collections.max(intList);
		int min = Collections.min(intList);
		return max < this.getSize() && min >= 0;
	}
	
	public Piece getPiece(int x, int y) {
		return board[y][x];
	}
	
	/**
	 * Removes the last move
	 * @return
	 * The last move, or null if no pieces
	 */
	private Pair<String, String> popMove() {
		if (moves.size() > 0) {
			Pair<String, String> lastMove = moves.remove(moves.size() - 1);
			return lastMove;
		}
		return null;
	}
	
	/**
	 * Removes the last piece
	 * @return
	 * The last piece, or null if no pieces
	 */
	private Piece popPiece() {
		if (this.takenPieces.size() > 0) {
			return takenPieces.remove(takenPieces.size() - 1);
		}
		return null;
	}

	/**
	 * 
	 * @param color
	 * @return
	 * All the paths for the {@code color}
	 */
	public List<Pair<String, String>> getAllPaths(int color) {
		this.paths.clear();
		for (int i = 0; i < this.getSize(); i++) {
			for (int j = 0; j < this.getSize(); j++) {
				
				if (this.board[i][j] != null) {					
					if (this.board[i][j].getColor() == color) {
						paths.addAll(this.board[i][j].getPath());
					}
				}
			}
		}
		return paths;
	}
	
	/**
	 * Fetches the kings one time an adds it in an array and next time doesn't need to search for it
	 * @param color
	 * @return
	 * The king for the {@code color}
	 */
	public Piece getKing(int color) {
		if (kingList.size() > 0) {
			return kingList.get(color);			
		} else {
			for (int i = 0; i < this.getSize(); i++) {
				for (int j = 0; j < this.getSize(); j++) {
					if (this.board[i][j] != null) {
						if (board[i][j].getType() == 'K') {
							kingList.add(board[i][j]);
						}
					}
				}			
			}
		}
		if (kingList.get(0).getColor() == 1) { // Assumes there is only one king per color, and arranges the kings based on color (0, white, 1 Black)
			Collections.reverse(kingList);
		}
		return kingList.get(color);
	}
	
	/**
	 * @param turn
	 * @return
	 * True if any of the opposite moves can take your king
	 */
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
	
	/**
	 * Method that iterates though all possible moves and checks if you are in chess, if you are in chess after every move you can make -> checkMate
	 * @return
	 * True if you have no legal moves to do, i.e. either staleMate or checkMate
	 */
	public boolean checkMate() {
		List<Pair<String, String>> allTurnPaths = new ArrayList<>(this.getAllPaths(this.getTurn()));
		boolean mate = true;
		this.posibleMoves.clear();
		for (Pair<String, String> pair : allTurnPaths) {
			this.moveFromPair(pair);
			if (!checkInChess(1 - this.getTurn())) {//Getting the opposite move because move from pair runs the next move
				this.posibleMoves.add(pair);
				mate = false;
				this.undoLastMove();
			} else {
				this.undoLastMove();				
			}
		}
		return mate;
	}
	
	/**
	 * Adds the pieces to the takePieces List
	 * @param piece
	 */
	public void addPiece(Piece piece) {
		this.takenPieces.add(piece);
	}
	
	private void moveFromPair(Pair<String, String> move) {
		//The base moving method
		int startX = Integer.parseInt(move.getKey().charAt(0) + "");
		int startY = Integer.parseInt(move.getKey().charAt(1) + "");
		int endX = Integer.parseInt(move.getValue().charAt(0) + "");
		int endY = Integer.parseInt(move.getValue().charAt(1) + "");
		
		if (this.board[startY][startX].getColor() != this.getTurn()) {
			throw new IllegalStateException("Wrong color to move");
		}
		this.addPiece(this.board[endY][endX]);
		this.board[startY][startX].moveTo(endX, endY);
		this.nextTurn();
		moves.add(move);
		
	}
	
	public void movePiece(Pair<String, String> move) {
		if (isGameOver) {
			throw new IllegalStateException("Game is over");
		}

		this.moveFromPair(move);
		if (this.checkMate()) {
			this.isGameOver = true;
		} else {
			if (this.checkInChess(1- this.getTurn())) {
				this.undoLastMove();
				throw new IllegalStateException("You are in chess");
			}
		}

	}


	/**
	 * Undoes the last move and takes the turn back
	 */
	private void undoLastMove() {
		//Getting the last move and last piece
		Pair<String, String> lastMove = this.popMove();
		Piece lastPiece = this.popPiece();

		if (lastMove == null) {
			throw new IllegalStateException("Can't undo in this state");
		}
		//Reversing the move
		Pair<String, String> reversedMove = new Pair<>(lastMove.getValue(), lastMove.getKey());
		
		this.prevTurn();
		
		int startX = Integer.parseInt(reversedMove.getKey().charAt(0) + "");
		int startY = Integer.parseInt(reversedMove.getKey().charAt(1) + "");
		int endX = Integer.parseInt(reversedMove.getValue().charAt(0) + "");
		int endY = Integer.parseInt(reversedMove.getValue().charAt(1) + "");

		//Move the piece back and add the taken piece
		if (!board[startY][startX].isFresh()) {
			board[endY][endX] = board[startY][startX];
			board[startY][startX] = lastPiece;
			board[endY][endX].setX(endX);
			board[endY][endX].setY(endY);			
		} else {//If the piece is "fresh", it was a pawn
			Piece p = new Pawn(endX, endY, board[startY][startX].getColor(), this.board);
			board[endY][endX] = p;
			board[startY][startX] = lastPiece;
		}
	}
	
	public String getMoveString() {
		Pair<String, String> lastMove = this.getMoves().get(this.getMoves().size() - 1);
		String moveLAN = this.convertPairToLAN(lastMove);
		int moveNr = (int) (getMoves().size() + 1) / 2;
		String moveString = moveNr + ". " + moveLAN.substring(0, 2) + "-" + moveLAN.substring(2,4);	
		if (isGameOver) {
			moveString += "#";
		} else if (checkInChess(getTurn())) {
			moveString += "+";
		}
		return moveString;
	
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
		
		String letters = "abcdefghijklmnopqrstuvwxyz";
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
		
		if (!this.isInsideBoard(startX, startY) || !this.isInsideBoard(endX, endY)) {
			throw new IllegalArgumentException("Move is not inside the board");
		}
		if (this.board[startY][startX] == null) {
			throw new IllegalStateException("No piece at " + LAN.substring(0,2));							
		}

		Pair<String, String> pairMove = new Pair<>("" + startX + startY, "" + endX + endY);
		return pairMove;
	}

	private String convertPairToLAN(Pair<String, String> move) {
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
	
	public void movesFromStringLongLAN(String moves) {
		String[] moveList = moves.split(" ");
		for (int i = 0; i < moveList.length; i++) {
			moveFromStringLAN(moveList[i]);
		}
	}
		
	private String convertMovesToString(List<Pair<String, String>> moves) {
		String stringMoves = "";
		for (Pair<String, String> move : moves) {
			stringMoves += this.convertPairToLAN(move) + " ";
		}
		return stringMoves.substring(0, stringMoves.length() - 1); // Removing the last ch (" ");
	}
	
	/**
	 * Saves the game if it meets all the requirements to save
	 * @param name
	 */
	public void saveGame(String name) {
		if (name == null) {
			throw new IllegalStateException("No name set to save");			
		}
		if (name.isBlank()) {
			throw new IllegalStateException("No name set to save");						
		}
		if (this.getMoves().isEmpty()) {
			throw new IllegalStateException("No pieces moved");						
		}
		
		if (ChessSaveHandler.getGame(name) != null) {
			throw new IllegalStateException("Game name already taken");			
		}
		
		String moveString = this.convertMovesToString(this.getMoves());
		
		SaveHandler ch = new ChessSaveHandler();
		ch.save(name + ";" + getSize() + ";" + moveString);
	}
	
	/**
	 * <b>requires</b> the game to be at the right size and starting position
	 * @param name
	 */
	public void loadGame(String name) {
		String[] gameString = ChessSaveHandler.getGame(name);
		String moveString = gameString[gameString.length - 1];
		this.movesFromStringLongLAN(moveString);
	}

	/**
	 * <b>Stockfish</b> style string of the board
	 */
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
		b.getKing(0);
		System.out.println(b.kingList.size());
//		b.getPiece(4, 6).moveTo(4, 4);
//		System.out.println(b);	
		
//		b.loadGame("TESTgame");
		
//		System.out.println(b);	
//		b.resetGame8();
//		System.out.println(b);	

//		var i = 1/2;
//		System.out.println(i);
		
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
//		System.out.println(b);
//		b.saveGame("OiL");
//		System.out.println(b.getGames().size());
//		List<Pair<String, String>> stringm = b.getMoves();
//		System.out.println(b.convertMovesToString(stringm));
//		System.out.println(b.convertMovesToString(stringm).equals(veryLongGame));
//		b.saveToFile("TESTgame;" + veryLongGame);
		
//		System.out.println(b.getLANArr());

	}
}
