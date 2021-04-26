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
 *
 *@TODO next time:
 *
 *Make own class for Move and Moves
 *Castle, en pessant, puzzles(?)
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
	
	private void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	
	public boolean isGameOver() {
		return this.isGameOver;
	}
	
	public List<Pair<String, String>> getMoves() {
		return new ArrayList<>(moves);
	}
	
	public int getTurn() {
		return turn % 2;
	}
	
	private void nextTurn() {
		turn++;
	}
	
	private void prevTurn() {
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
		List<Integer> intList = new ArrayList<Integer>();
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
	private List<Pair<String, String>> getAllPaths(int color) {
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
	private Piece getKing(int color) {
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
		if (kingList.get(0).getColor() == 1) { // Assumes there is only one king per color, and two colors, and arranges the kings based on color (0, white, 1 Black)
			Collections.reverse(kingList);
		}
		return kingList.get(color);
	}
	
	/**
	 * @param turn
	 * @return
	 * True if any of the opposite moves can take your king
	 */
	private boolean checkInChess(int turn) {
		Piece king = this.getKing(turn);
		List<Pair<String, String>> allOpositePaths = this.getAllPaths(1 - turn);
		
		for (Pair<String, String> pair : allOpositePaths) {
			if (pair.getValue().equals(king.getPos())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkInChess() {
		return checkInChess(getTurn());
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
		setGameOver(mate);
		return mate;
	}
	
	/**
	 * Adds the pieces to the takePieces List
	 * @param piece
	 */
	private void addPiece(Piece piece) {
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
		if (!this.checkMate()) {
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
		Pair<String, String> lastMove = getMoves().get(moves.size() - 1);
		String moveLAN = this.convertPairToLAN(lastMove);
		int moveNr = (turn + 1) / 2;
		String moveString = moveNr + ": " + moveLAN.substring(0, 2) + "-" + moveLAN.substring(2,4);	
		if (isGameOver && checkInChess()) {
			moveString += "#";//Check-mate symbol
		} else if (checkInChess()) {
			moveString += "+";//Check symbol
		}//Stale-mate symbol doesn't exist :(
		return moveString;
	
	}
	
	public void moveFromStringLAN(String LAN) {
		this.movePiece(this.convertLANtoPair(LAN));
	}
	
	/**
	 * Takes in a string with a coordinates on the board 
	 * @param ss
	 * @return
	 * All the paths it can take in a List
	 */
	public List<Pair<String, String>> getPosPathsLAN(String ss) {
		String letters = "abcdefgh";
		String startLetter = ("" + ss.charAt(0)).toLowerCase();
		char startNumber = ss.charAt(1);
		
		if (!letters.contains(startLetter) || !Character.isDigit(startNumber)) {
			throw new IllegalArgumentException("Not a valid input");
		}
		
		int startX = letters.indexOf("" + startLetter); 
		int startY = this.size - Integer.parseInt("" + startNumber);
		
		ss = "" + startX + startY;
		return new ArrayList<>(this.getPosPaths(ss));
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
		return new ArrayList<>(moves);

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
		if (moves.isEmpty()) {
			throw new IllegalStateException("No pieces moved");						
		}
		
		SaveHandler ch = new ChessSaveHandler();
				
		if (ch.canGetGame(name)) {
			throw new IllegalStateException("Game name already taken");			
		}
		
		String moveString = this.convertMovesToString(moves);
		
		ch.save(name + ";" + getSize() + ";" + moveString);
	}
	
	/**
	 * <b>requires</b> the game to be at the right size and starting position
	 * @param name
	 */
	public void loadGame(String name) {
		SaveHandler ch = new ChessSaveHandler();
		String[] gameString = ch.getGame(name);
		String moveString = gameString[gameString.length - 1];
		this.movesFromStringLongLAN(moveString);
	}
	
	
	/**
	 * LAN is a chess notation (Long algebraic notation) where you include where the piece starts and ends...
	 * Would have been easier to have a class for the moves 
	 * The last methods is for reading and converting the moves
	 * @param LAN
	 * @return
	 */
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
}
