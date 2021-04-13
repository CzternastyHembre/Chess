package chess.pieces;

import java.util.List;

import javafx.util.Pair;

public class Knight extends Piece {
	
	public Knight(int x, int y, int color, Piece[][] board) {
		super(x, y, color, board, 'N');
		if (color == 0) {
			this.filePath += "knight-white.png";
		} else {
			this.filePath += "knight-black.png";			
		}

	}

	@Override
	public List<Pair<String, String>> getPath() {
		this.clearPath();
		
//All the directions in the x-axis the horse can move;
		int[] xDir = {-2, -1, 1, 2};

		for (int dx : xDir) {
			for (int i = -1; i <= 1; i += 2) {
				
				int targetX = this.x + dx;
				
				int dy = 3 - Math.abs(dx); // |dy| + |dx| = 3 -> dy = 3 - |dx|;
				int targetY = this.y + dy * i;
				
				if (this.isInBoard(targetX, targetY)) {
					if (board[targetY][targetX] == null) {
						Pair<String, String> p = new Pair<>("" + this.x + this.y, ""+ targetX + targetY);
						super.path.add(p);
					} else if (board[targetY][targetX].getColor() != this.getColor()) {
						Pair<String, String> p = new Pair<>("" + this.x + this.y, ""+ targetX + targetY);
						super.path.add(p);						
					}
				}
			}
		}
		return path;
	}	

}