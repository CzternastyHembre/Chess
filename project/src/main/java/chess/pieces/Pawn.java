package chess.pieces;

import java.util.List;
import javafx.util.Pair;

public class Pawn extends Piece{
	
	public Pawn(int x, int y, int color, Piece[][] board) {
		super(x, y, color, board, 'P');
		if (color == 0) {
			this.filePath += "pawn-white.png";
		} else {
			this.filePath += "pawn-black.png";			
		}

	}

	@Override
	public List<Pair<String, String>> getPath(){
		this.clearPath();
		
		int ydir = 1;
		if (this.isWhite()) {
			ydir = -1;
		}
		
		
//Move piece
		int targetY = y + ydir;
		
		if (this.isInBoard(x, targetY)) {
				
			if (this.board[targetY][x] == null) {//One up
				Pair<String, String> p = new Pair<>("" + x + y, "" + x + targetY);
				super.path.add(p);

				targetY = y + ydir * 2;
				if (this.isInBoard(x, targetY)) {//Check y + 2*ydir inside board
					if (this.board[targetY][x] == null && (y == 1 || y == board.length - 2)) {//Checks if the pawn is at the second last row, and its not piece 2 up
						Pair<String, String> p2 = new Pair<>("" + x + y, "" + x + targetY);					
						super.path.add(p2);
					}
				}	
			}
//Take
			for (int i = -1; i <= 1; i += 2) {
				int targetX = x + i;
				targetY = y + ydir;
				
				if (this.isInBoard(targetX)) {
					if (this.board[targetY][targetX] != null) {					
						if (board[targetY][targetX].getColor() != this.getColor()) {// Not the same color
							Pair<String, String> p = new Pair<>("" + x + y, "" + targetX + targetY);
							super.path.add(p);
						}
					}
				}
			}
		}
		
		return path;
	}
	
}
