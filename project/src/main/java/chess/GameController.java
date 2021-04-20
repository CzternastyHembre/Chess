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
	
	@FXML
	TextField saveText;

	@FXML
	ComboBox<String> dropDown;
	
	@FXML 
	Label moveLabel;
	
	@FXML
	public void initialize() {
		initialize(8);
	}
	
	private void initialize(int s) {
		game = new Game(s);
		game.init(s);
		createBoard();
		drawPieces();
	}
	
	@FXML
	public void init8by8() {
		initgame(8);
	}
	
	@FXML
	public void init5by5() {
		initgame(5);
	}
	
	private void initgame(int i) {
		resetGame(i);
		feedBackLabel.setText(null);		
	}

	@FXML
	public void resetGame() {
		resetGame(game.getSize());//Resets the game in the current size
	}
	
	private void resetGame(int i) {
		try {
			initialize(i);
			feedBackLabel.setText("Game reset success");
			moveLabel.setText(null);
		} catch (Exception e) {
			feedBackLabel.setText(e.getMessage());
		}
	}

	/**
	 * Creates the board and makes all the tiles and coordinates
	 */
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
				
				col = (x + y) % 2 == 0 ? "#b58863" : "#f0d9b5";		//Checks if the square is light or dark...

				if (y == game.getSize() - 1) {//Coordinates at the bottom
					char c = (char) (x + 97);
					Label l = new Label("" + c);
					l.setFont(new Font(fontSize));
					l.setTranslateX(tileSize*x);
					l.setTranslateY(game.getSize() * tileSize - fontSize - 7);
					l.setTextFill(Color.valueOf(col));	
					labels.add(l);
				} 
				
				if (x == game.getSize() - 1) {//Coordinates at the right
					Label l = new Label("" + (game.getSize() - y));
					l.setFont(new Font(fontSize));
					l.setTranslateX(tileSize * game.getSize() - fontSize + 5);
					l.setTranslateY(y * tileSize - 3);
					l.setTextFill(Color.valueOf(col));	
					labels.add(l);					
				}
			}
		}
		for (Label label : labels) {//Adding the labels last so i don't mess width the indexes for the tiles
			board.getChildren().add(label);
		}
		drawGamesList();
	}
	
	/**
	 * Draws/Updates all the pieces on the board and sets new onclickFunctions on all tiles
	 */
	private void drawPieces() {
		double boardSize = anchorPane.getPrefWidth();
		int tileSize = (int) (boardSize / game.getSize());
		for (int y = 0; y < game.getSize(); y++) {
			for (int x = 0; x < game.getSize(); x++) {
				Pane tile = (Pane) board.getChildren().get(y * game.getSize() + x); // Have to set the color to update all the pieces to see path

				tile.setStyle("-fx-background-color: " + ((x + y) % 2 == 0 ? "#f0d9b5;" : "#b58863;")); // Updates the background color of the tile
				
				List<Pair<String, String>> possibleTiles = game.getPosPaths("" + x + y);
				tile.getChildren().clear();
				
				if (game.getPiece(x, y) == null) {//Doesn't need to mark tiles, just update the board;
					tile.setOnMouseClicked(event -> drawPieces());					
				} else {
					tile.setOnMouseClicked(event -> showPosibleTiles(possibleTiles));
					Image img = new Image(game.getPiece(x, y).getFilePath());
					ImageView imgv = new ImageView(img);
					imgv.setFitHeight(tileSize);
					imgv.setFitWidth(tileSize);
					tile.getChildren().add(imgv);
				} 
			}
		}
		markLastMove();
	}
	
	private void moveFromPair(Pair<String, String> move) {
		try {
			game.movePiece(move);
			String color;
			feedBackLabel.setText(null);
			int turn = game.getTurn();
			if (game.isGameOver()) {
				if (game.checkInChess(turn)) {
					if (1 - turn == 0){color = "white";}else{color="black";}
					feedBackLabel.setText("Mate " + color + " won");
				} else {
					feedBackLabel.setText("Draw, It's a stalemate");
				}	
			}
			drawPieces();
			moveLabel.setText(game.getMoveString());
		} catch (Exception e) {
			feedBackLabel.setText(e.getMessage());
		}
	}
	
	@FXML
	public void onEnterMove() {
		try {
			String stringLAN = textField.getText();
			Pair<String, String> move = game.convertLANtoPair(stringLAN);
			moveFromPair(move);
			textField.setText(null);
		} catch (Exception e) {
			feedBackLabel.setText(e.getMessage());
		}
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

		col = (startX + startY) % 2 == 0 ? "dd9769" : "#aa6e40";	//Checks if the square is light or dark...
		tileStart.setStyle("-fx-background-color: " + col);
		
		col = (endX + endY) % 2 == 0 ? "dd9769" : "#aa6e40";	//Checks if the square is light or dark...
		tileEnd.setStyle("-fx-background-color: " + col);
	}
		
	private void markTiles(List<Pair<String, String>> tiles) {
		String col;
		for (Pair<String, String> move : tiles) {
			int x = Integer.parseInt("" + move.getValue().charAt(0));
			int y = Integer.parseInt("" + move.getValue().charAt(1));

			Pane tile = (Pane) board.getChildren().get(y * game.getSize() + x);

			col = (x + y) % 2 == 0 ? "#829769" : "#646e40"; 	//Checks if the square is light or dark...
			tile.setStyle("-fx-background-color: " + col);
			tile.setOnMouseClicked(e -> this.moveFromPair(move));
		}
	}

	/**
	 * Draws the pieces and tiles, the marks the tiles
	 * @param tiles
	 */
	private void showPosibleTiles(List<Pair<String, String>> tiles) {
		drawPieces();//Draws the pieces again so the last marked tiles gets removed
		markTiles(tiles);
	}

	@FXML
	public void loadGame() {
		String dropdownName = dropDown.getValue();
		if (dropdownName == null) {
			feedBackLabel.setText("No game set to load");
			return;
		}
		String name = dropdownName.substring(6, dropdownName.length());//TODO prefix length
		try {
			if (ChessSaveHandler.canGetGame(name)) { //Doesn't reset the game if it doesn't find the game
				int s = ChessSaveHandler.getGameSize(name);
				initialize(s);				
			}
			game.loadGame(name);
			moveLabel.setText(game.getMoveString());
			
			feedBackLabel.setText("Game load success");				
		} catch (Exception e) {
			feedBackLabel.setText(e.getMessage());
		}
		drawPieces();
	}
	
	@FXML
	public void saveGame() {
		try {
			game.saveGame(saveText.getText());
			feedBackLabel.setText("Game save success");
			saveText.setText(null);
			drawPieces();
			drawGamesList();
		} catch (Exception e) {
			this.feedBackLabel.setText(e.getMessage());
		}
	}

	private void drawGamesList() {
		ObservableList<String> names = FXCollections.observableArrayList();
		List<String[]> games = ChessSaveHandler.getGames();
		for (String[] game : games) {
			names.add(game[1]+ "x" + game[1] + " | " + game[0]);
		}
		dropDown.setItems(names);	
	}

}
	