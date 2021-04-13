package chess.pieces;

import java.util.List;

import javafx.util.Pair;

public class Bishop extends Piece{

	public Bishop(int x, int y, int color, Piece[][] board) {
		super(x, y, color, board, 'B');
		if (color == 0) {
			this.filePath += "bishop-white.png";
		} else {
			this.filePath += "bishop-black.png";			
		}
	}

	@Override
	public List<Pair<String, String>> getPath() {
		this.clearPath();
		
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -1; j <= 1; j += 2) {
				int targetX = this.x + i;
				int targetY = this.y + j;
				while(this.isInBoard(targetX, targetY)) {
					if (this.board[targetY][targetX] == null) {
						Pair<String, String> p = new Pair<>("" + this.x + this.y,"" + targetX + targetY);
						super.path.add(p);
					} else { // Breaks if the next tile is not null but adds the move if you can take it
						if (this.board[targetY][targetX].getColor() != this.getColor()) {
							Pair<String, String> p = new Pair<>("" + this.x + this.y,"" + targetX + targetY);
							super.path.add(p);
						}
						break;
					}
					targetX += i;
					targetY += j;
				}
			}	
		}
		return path;
	}

}
