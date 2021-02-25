package chess;



import java.io.FileInputStream;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.*; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class GameController {
	
	private Game game;
	
	@FXML
	Pane board;
	
	@FXML
	Label feedBackLabel;
	
	@FXML
	AnchorPane anchorPane;
	
	@FXML
	TextField textField;
	
	@FXML
	Button buttonMove;

	@FXML
	private void initialize() {
		game = new Game(8);
		game.init8();
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
		for (Label label : labels) {
			board.getChildren().add(label);
		}
	}
	
	@FXML
	public void onEnter() {
//		List<String> tiles = null;
//		if (textField.getText().length() == 2) {
//			tiles = game.getPosPathsLAN(textField.getText());
//		} else {			
//		}
		game.moveFromStringLAN(textField.getText());
		textField.setText(null);
		drawPieces();
//		markTiles(tiles);
	}
	
	@FXML
	public void resetGame() {
		game.resetGame8();
		this.drawPieces();
	}
	
	public void moveFromMovePair(Pair<String, String> move) {
		game.movePiece(move);
		feedBackLabel.setText(null);
		String color;
		int turn = game.getTurn();
		if (game.isGameOver()) {
			if (game.checkInChess(turn)) {
				if (1 - turn == 0){color = "white";}else{color="black";}
				feedBackLabel.setText("Checkmate, " + color + " won");
//				System.out.println("Checkmate, " + color + " won");				
			} else {
//				System.out.println("Stalemate LOL");
				feedBackLabel.setText("Draw, It's a stalemate");
			}
		}

		drawPieces();
	}
	
	private void showPosTiles(List<Pair<String, String>> tiles) {
		drawPieces();
		markTiles(tiles);
	}
	
	private void drawPieces() {
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
	}
	
	private void markTiles(List<Pair<String, String>> tiles) {
		double boardSize = anchorPane.getPrefWidth();
		int tileSize = (int) (boardSize / game.getSize());
		String letters = "abcdefgh";
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
			tile.setOnMouseClicked(e -> this.moveFromMovePair(move));
		}
	}	
}
