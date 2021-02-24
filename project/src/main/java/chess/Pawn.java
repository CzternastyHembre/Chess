package chess;

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
		if (this.y + ydir >= 0 && this.y + ydir < this.board.length) {//Check y+ydir inside board
			
			if (this.board[this.y + ydir][this.x] == null) {//One up
				Pair<String, String> p = new Pair<>("" + this.x + this.y,"" + this.x + (this.y + ydir));
				super.path.add(p);
				
				if (this.y + ydir*2 >= 0 && this.y + ydir*2 < this.board.length) {//Check y + 2*ydir inside board
					if (this.board[this.y + 2*ydir][this.x] == null && (this.y == 1 || this.y == this.board.length - 2)) {//Two up
						Pair<String, String> p2 = new Pair<>("" + this.x + this.y,"" + this.x + (this.y + 2*ydir));					
						super.path.add(p2);
					}
				}	
			}
			//Take
			for (int i = -1; i < 3; i += 2) {
				if (this.x + i >= 0 && this.x + i < this.board.length) {
					if (this.board[this.y + ydir][this.x + i] != null) {					
						if (board[this.y + ydir][this.x + i].getColor() + this.getColor() == 1) {// Black(2) + White(1) = 3
							Pair<String, String> p = new Pair<>("" + this.x + this.y, "" + (this.x + i) + (this.y + ydir));
							super.path.add(p);
						}
					}
				}
			}
		}
		return path;
	}



	
}
