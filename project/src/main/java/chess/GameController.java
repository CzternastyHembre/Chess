package chess;
//TODO
//Castle, en pessant, queening, puzzles(?), validating/testing


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.*; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

import chess.saveHandler.ChessSaveHandler;

public class GameController {
	
	private Game game;
	
	@FXML
	Pane board;
	
	@FXML
	Pane controlPane;
	
	@FXML
	Label feedBackLabel;
	
	@FXML
	AnchorPane anchorPane;
	
	@FXML
	TextField textField;
	
//	@FXML
//	Button buttonMove;
//	
//	@FXML
//	ComboBox<String> dropDownPuzzles;
//	
	@FXML
	TextField saveText;

	@FXML
	ComboBox<String> dropDown;
	
	@FXML 
	Label moveLabel;
	
	@FXML
	private void initialize() {
		initialize(8);
	}
	
	private void initialize(int s) {
		game = new Game(s);
		game.init(s);
//		String veryLongGame = "d2d4 g8f6 c2c4 e7e6 g1f3 b7b6 a2a3 c8b7 b1c3 d7d5 c4d5 f6d5 d1c2 d5c3 b2c3 c7c5 e2e4 b8d7 c1f4 c5d4 c3d4 a8c8 c2b3 f8e7 f1d3 d7f6 b3b5 d8d7 f3e5 d7b5 d3b5 e8f8 f2f3 f6e8";
//		game.movesFromStringLongLAN(veryLongGame);

		
		createBoard();
		drawPieces();
	}
	
	private void createBoard() {
		board.getChildren().clear();
		double boardSize = anchorPane.getPrefWidth();
		int tileSize = (int) (boardSize / game.getSize());
		List<Label> labels = new ArrayList<>();
		String col;
		int fontSize = 18;
		
		for (int y = 0; y < game.getSize(); y++) {
			for (int x = 0; x < game.getSize(); x++) {
				Pane tile = new Pane();
				tile.setTranslateX(tileSize * x);
				tile.setTranslateY(tileSize * y);
				tile.setPrefWidth(tileSize);
				tile.setPrefHeight(tileSize);
				board.getChildren().add(tile);
				if ((x + y) % 2 == 0) {
					col = "#b58863";//Light
				} else {
					col = "#f0d9b5";//Dark
				}

				if (y == game.getSize() - 1) {//Kordinater nederst
					char c = (char) (x + 97);
					Label l = new Label("" + c);
					l.setFont(new Font(fontSize));
					l.setTranslateX(tileSize*x);
					l.setTranslateY(game.getSize() * tileSize - fontSize - 7);
					l.setTextFill(Color.valueOf(col));	
					labels.add(l);
				} 
				if (x == game.getSize() - 1) {//Kordinater til høyre
					Label l = new Label("" + (game.getSize() - y));
					l.setFont(new Font(fontSize));
					l.setTranslateX(tileSize * game.getSize() - fontSize + 5);
					l.setTranslateY(y * tileSize - 3);
					l.setTextFill(Color.valueOf(col));	
					labels.add(l);					
				}
			}
		}
		for (Label label : labels) {//Adding the labels last so i dont mess whit the indexses
			board.getChildren().add(label);
		}
	}
//	@FXML
//	public void loadPuzzle() {
//		if (game.getSize() != 8) {
//			feedBackLabel.setText("Unable to load on this type of board"); //TODO
//			return;
//		}
//		String name = dropDownPuzzles.getValue();
//		try {
//			game.loadPuzzle(name);	
//			feedBackLabel.setText("Game load success");
//		} catch (Exception e) {
//			feedBackLabel.setText(e.getMessage());
//		}
//		drawPieces();
//	}
//	
	@FXML
	public void onEnter() {
//		List<String> tiles = null;
//		if (textField.getText().length() == 2) {
//			tiles = game.getPosPathsLAN(textField.getText());
//		} else {			
//		}
		try {
			String stringLAN = textField.getText();
			Pair<String, String> move = game.convertLANtoPair(stringLAN);
//			moveFromPair(move);
			game.movePiece(move);
			drawPieces();
			feedBackLabel.setText(null);
		} catch (Exception e) {
			feedBackLabel.setText(e.getMessage());
		}
//		markTiles(tiles);
	}
	
	@FXML
	public void resetGame() {
		try {
			initialize(game.getSize());//Resets the game in the current size
			feedBackLabel.setText("Game reset success");
			moveLabel.setText(null);
		} catch (Exception e) {
			feedBackLabel.setText(e.getMessage());
		}
	}
	
	@FXML
	public void loadGame() {
//		if (game.getSize() != 8) {
//			feedBackLabel.setText("Unable to load on this type of board"); //TODO make a initalize with sizes...
//			return;
//		}
		String dropdownName = dropDown.getValue();
		String name = dropdownName.substring(6, dropdownName.length());//TODO prefix length
		try {
			if (ChessSaveHandler.canGetGame(name)) { //Doesnt reset the game if it doesnt find the game
				int s = ChessSaveHandler.getGameSize(name);
				initialize(s);				
			}
			game.loadGame(name);
			
			//Does the last move here so the movelabel get updated TODO make a method in game to get the movetring....
			Pair<String, String> lastMove = game.getMoves().get(game.getMoves().size() - 1);
			game.undoLastMove();
			game.setGameOver(false);
			this.moveFromPair(lastMove);
			
			if (feedBackLabel.getText() == null) {
				feedBackLabel.setText("Game load success");				
			}
		} catch (Exception e) {
			feedBackLabel.setText(e.getMessage());
		}
		drawPieces();
	}
	
	
	
	@FXML
	public void saveGame() {
//		if (game.getSize() != 8) {
//			feedBackLabel.setText("Unable to save this type of board"); //TODO
//			return;
//		}
		try {
			game.saveGame(saveText.getText());
			feedBackLabel.setText("Game save success");
			saveText.setText(null);
			drawPieces();
		} catch (Exception e) {
			this.feedBackLabel.setText(e.getMessage());
		}
	}
	
	@FXML
	public void init8by8() {
		initialize(8);
	}
	@FXML
	public void init5by5() {
		initialize(5);		
	}
	
	public void moveFromPair(Pair<String, String> move) {
		try {
			game.movePiece(move);
			String moveLAN = game.convertPairToLAN(move);
			int moveNr = (int) (game.getMoves().size() + 1) / 2;
			String moveString = moveNr + ". " + moveLAN.substring(0, 2) + "-" + moveLAN.substring(2,4);
			String color;
			feedBackLabel.setText(null);
			int turn = game.getTurn();
			if (game.isGameOver()) {
				if (game.checkInChess(turn)) {
					if (1 - turn == 0){color = "white";}else{color="black";}
					feedBackLabel.setText("Mate " + color + " won");
					moveString += "#";
//				System.out.println("Checkmate, " + color + " won");				
				} else {
//				System.out.println("Stalemate LOL");
					feedBackLabel.setText("Draw, It's a stalemate");
				}	
			}  else if (game.checkInChess(turn)) {
				moveString += "+";
			}
			drawPieces();
			moveLabel.setText(moveString);
		} catch (Exception e) {
			feedBackLabel.setText(e.getMessage());
		}
	}
	
	private void showPosTiles(List<Pair<String, String>> tiles) {
		drawPieces();
		markTiles(tiles);
	}
	
	private void drawPieces() {
//		moveLabel.setText(null);
		double boardSize = anchorPane.getPrefWidth();
		int tileSize = (int) (boardSize / game.getSize());
		for (int y = 0; y < game.getSize(); y++) {
			for (int x = 0; x < game.getSize(); x++) {
				Pane tile = (Pane) board.getChildren().get(y * game.getSize() + x); // Have to set the color to upate all the pieces to see path
				if ((x + y) % 2 == 0) {
					tile.setStyle("-fx-background-color: #f0d9b5;");	
				} else {
					tile.setStyle("-fx-background-color: #b58863;");					
				}
				List<Pair<String, String>> possibleTiles = game.getPosPaths("" + x + y);
				tile.setOnMouseClicked(event -> showPosTiles(possibleTiles));

				tile.getChildren().clear();
				if (game.getPiece(x, y) != null) {
					Image img = new Image(game.getPiece(x, y).getFilePath());
					ImageView imgv = new ImageView(img);
					imgv.setFitHeight(tileSize);
					imgv.setFitWidth(tileSize);
					tile.getChildren().add(imgv);
				} 
			}
		}
		markLastMove();			
		ObservableList<String> names = FXCollections.observableArrayList();
		List<String[]> games = ChessSaveHandler.getGames();
		for (String[] game : games) {
			names.add(game[1]+ "x" + game[1] + " | " + game[0]);
		}
		dropDown.setItems(names);
//		TODO
//		ObservableList<String> namesPuzzle = FXCollections.observableArrayList();
//		List<String[]> gamesPuzzle = game.getPuzzles();
//		for (String[] game : gamesPuzzle) {
//			namesPuzzle.add(game[0]);
//		}
//		dropDownPuzzles.setItems(namesPuzzle);
//
	}
	private void markLastMove() {
		if (game.getMoves().size() == 0) {
			return;
		}
		Pair<String, String> move = game.getMoves().get(game.getMoves().size() - 1);			

		int startX = Integer.parseInt("" + move.getValue().charAt(0));
		int startY = Integer.parseInt("" + move.getValue().charAt(1));
		int endX = Integer.parseInt("" + move.getKey().charAt(0));
		int endY = Integer.parseInt("" + move.getKey().charAt(1));
		String col;
		Pane tileStart = (Pane) board.getChildren().get(startY * game.getSize() + startX);
		Pane tileEnd= (Pane) board.getChildren().get(endY * game.getSize() + endX);

		
		if ((startX + startY) % 2 == 0) {
			col = "dd9769"; //Light			
		} else {
			col = "#aa6e40"; //Dark			
		}
		tileStart.setStyle("-fx-background-color: " + col);
		
		if ((endX + endY) % 2 == 0) {
			col = "#ff9769"; //Light			
		} else {
			col = "#aa6e40"; //Dark			
		}
		tileEnd.setStyle("-fx-background-color: " + col);
	}
	
//	@FXML
//	public void undoMove() {
//		try {
//			game.undoLastMove();			
//		} catch (Exception e) {
//			feedBackLabel.setText(e.getMessage());
//		}
//		drawPieces();
//	}
	
	
	private void markTiles(List<Pair<String, String>> tiles) {
//		double boardSize = anchorPane.getPrefWidth();
//		int tileSize = (int) (boardSize / game.getSize());
//		String letters = "abcdefgh";
		String col;
		for (Pair<String, String> move : tiles) {
			int x = Integer.parseInt("" + move.getValue().charAt(0));
			int y = Integer.parseInt("" + move.getValue().charAt(1));
			if ((x + y) % 2 == 0) {
				col = "#829769"; //Light
			} else {
				col = "#646e40"; //Dark
			}
			Pane tile = (Pane) board.getChildren().get(y * game.getSize() + x);
			tile.setStyle("-fx-background-color: " + col);
			tile.setOnMouseClicked(e -> this.moveFromPair(move));
		}
	}	
}
