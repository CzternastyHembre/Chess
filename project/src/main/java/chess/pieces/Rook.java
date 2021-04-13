package chess.pieces;

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
		
		for (int i = 0; i <= 1; i++) {
			for (int dir = -1; dir <= 1; dir += 2) {
				int targetX;
				int targetY;
				int dx = 1;
				int dy = 0;
				if (i == 0) { // first (x--)(y), (x++)(y), (x)(y--), (x)(y++)
					targetX = this.x + dir;
					targetY = this.y;
				} else {		
					dy = 1;
					dx = 0;
					targetX = this.x;
					targetY = this.y + dir;
				}
				while(this.isInBoard(targetX, targetY)) {
					if (this.board[targetY][targetX] == null) {
						Pair<String, String> p = new Pair<>("" + x + y,"" + targetX + targetY);
						super.path.add(p);
					} else {
						if (this.board[targetY][targetX].getColor() != this.getColor()) {
							Pair<String, String> p = new Pair<>("" + x + y,"" + targetX + targetY);
							super.path.add(p);
						}
						break;
					}
					
					targetX += dir*dx;
					targetY += dir*dy;
				}
			}	
		}
		return path;
	}

}
