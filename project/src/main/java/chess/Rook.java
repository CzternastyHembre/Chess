package chess;

import java.util.List;

import javafx.util.Pair;

public class Rook extends Piece {

	public Rook(int x, int y, int color, Piece[][] board) {
		super(x, y, color, board, 'R');
		if (color == 0) {
			this.filePath += "rook-white.png";
		} else {
			this.filePath += "rook-black.png";			
		}
	}

	@Override
	public List<Pair<String, String>> getPath() {
		this.clearPath();
		
		for (int i = 0; i < 2; i++) {
			for (int j = -1; j <= 1; j += 2) {
				int targetX;
				int targetY;
				int dx = 1;
				int dy = 0;
				if (i == 0) {
					targetX = this.x + j;
					targetY = this.y;
				} else {		
					dy = 1;
					dx = 0;
					targetX = this.x;
					targetY = this.y + j;
				}
				while(this.isInBoard(targetX, targetY)) {
					if (this.board[targetY][targetX] == null) {
						Pair<String, String> p = new Pair<>("" + this.x + this.y,"" + targetX + targetY);
						super.path.add(p);
					} else {
						if (this.board[targetY][targetX].getColor() != this.getColor()) {
							Pair<String, String> p = new Pair<>("" + this.x + this.y,"" + targetX + targetY);
							super.path.add(p);
						}
						break;
					}
					
					targetX += j*dx;
					targetY += j*dy;
				}
			}	
		}
		return path;
	}

}
