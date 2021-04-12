package tron;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent; 

public class GameController{

	private Game game;
	
	@FXML
	Pane board;
	
	@FXML
	AnchorPane anchorPane;
	
	@FXML
	private void initialize() {
		game = new Game(20,20);
		createBoard();
		drawTiles();
	}

	
	
	private void createBoard() {
		board.setStyle("-fx-background-color: #aeaeae;");
		board.getChildren().clear();
		
		int width = 700;
		int heigth = width * game.getHeigth() / game.getWidth();
		
		int max = (int) Math.max(width, heigth);
		int tileWidth = max / game.getWidth();
		int tileHeight = tileWidth;
		board.setPrefWidth(width);
		board.setPrefHeight(heigth);
		
		anchorPane.setPrefWidth(width);
		anchorPane.setPrefHeight(heigth);
		
		for (int y = 0; y < game.getHeigth(); y++) {
			for (int x = 0; x < game.getWidth(); x++) {
				Pane tile = new Pane();
				tile.setPrefHeight(tileHeight);
				tile.setPrefWidth(tileWidth);
				tile.setTranslateX(x * tileWidth);
				tile.setTranslateY(y * tileHeight);

				board.getChildren().add(tile);
			}
		}
	}
	
	private void drawTiles() {
		for (int y = 0; y < game.getHeigth(); y++) {
			for (int x = 0; x < game.getWidth(); x++) {
				Pane tile = (Pane) board.getChildren().get(y * game.getWidth() + x);
				tile.getChildren().clear();
				String style = "-fx-border-color: grey; -fx-border-width: 1px;";
				if (game.getBoard()[y][x] != null) {
					Label text = new Label("" + game.getBoard()[y][x]);
					text.setFont(new Font(30));
					text.setTranslateX(10);
					text.setTranslateY(0);
					tile.getChildren().add(text);
	
				}
				tile.setStyle(style);
			}
		}
	}

    @FXML  // <== perhaps you had this missing??
    public void keyPressed(KeyEvent event) {
    	try {
    		switch (event.getCode()) {
    		case LEFT, A:
    			game.moveLeft();
    			break;
    		case RIGHT, D:
    			game.moveRight();
    			break;
    		case UP, W:
    			game.moveUp();
    			break;
    		case DOWN, S:
    			game.moveDown();
    			break;
    		case R:
    			initialize();    			
    		default:
    			break;
    		}
    		drawTiles();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }


	


}

