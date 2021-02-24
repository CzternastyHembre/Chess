package chess;

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
		
		int[] xDir = {-2, -1, 1, 2};
		int[] yDir = {1, 2, 2, 1};

		for (int i = 0; i < yDir.length; i++) {
			for (int j = -1 ; j <= 1; j += 2) {
				int targetX = this.x + xDir[i];
				int targetY = this.y + j*yDir[i];
				
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
//		System.out.println(path);
		return path;
	}	

}