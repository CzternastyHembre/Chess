package play2048;



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
		game = new Game(4,4);
//		game.getBoard()[0][0] = new Tile(1);
//		for (int y = 0; y < game.getHeigth(); y++) {
//			for (int x = 0; x < game.getWidth(); x++) {
//				if (y + x != 0) {
//					if (y % 2 == 0) {
//						game.getBoard()[y][x] = new Tile(y*game.getWidth() + x);										
//					} else {						
//						game.getBoard()[y][game.getWidth()-1-x] = new Tile(y*game.getWidth() + x);										
//					}
//				} 
//			}
//		}
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
		anchorPane.setPrefHeight(heigth + 50);
		
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
		Label scoreLabel = new Label();
		scoreLabel.setPrefWidth(width);
		scoreLabel.setPrefHeight(50);
		scoreLabel.setTranslateX(50);
		scoreLabel.setFont(new Font(20));
		scoreLabel.setTranslateY(heigth );
		board.getChildren().add(scoreLabel);
	}
	
	private void drawTiles() {
		for (int y = 0; y < game.getHeigth(); y++) {
			for (int x = 0; x < game.getWidth(); x++) {
				Pane tile = (Pane) board.getChildren().get(y * game.getWidth() + x);
				tile.getChildren().clear();
				String style = "-fx-border-color: grey; -fx-border-width: 10px;";
				if (game.getBoard()[y][x] != null) {
					Label text = new Label("" + game.getBoard()[y][x].getValue());
					int l = game.getBoard()[y][x].getLength();
					text.setFont(new Font(80 - l*10));
					text.setTranslateX(30/l);
					text.setTranslateY(-5 + l*5);
					tile.getChildren().add(text);
					if (game.getBoard()[y][x].getValue() > 64) {
						text.setStyle("-fx-text-fill: #f9f6f2;");						
					}
	
					style += "-fx-background-color: #" + game.getBoard()[y][x].getColor() + ";";
				}
				tile.setStyle(style);
			}
		}
		Label scoreLabel = (Label) board.getChildren().get(board.getChildren().size()-1);
		scoreLabel.setText("SCORE: " + game.getScore());
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
    		switch (event.getCode()) {
    		case K:
    			Label scoreLabel = (Label) board.getChildren().get(board.getChildren().size()-1);
    			scoreLabel.setText("KARAN ER XXXXX-HOMO");
    			break;
    		default:
    			break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }


	


}

