package prosjekt;

import java.util.List;

import javafx.util.Pair;

public class King extends Piece{

	public King(int x, int y, int color, Piece[][] board) {
		super(x, y, color, board, 'K');
	}

	@Override
	public List<Pair<String, String>> getPath() {
		this.clearPath();
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int targetX = this.x + j;
				int targetY = this.y + i;
				if (this.isInBoard(targetX, targetY)) {
					if (i != 0 || j != 0) {
						if (this.board[targetY][targetX] == null) {
							Pair<String, String> p = new Pair<>("" + this.x + this.y,"" + targetX + targetY);
							super.path.add(p);
						} else if (this.board[targetY][targetX].getColor() != this.getColor()) {
							Pair<String, String> p = new Pair<>("" + this.x + this.y,"" + targetX + targetY);
							super.path.add(p);
						}
					}
				}
				
			}
			
		}
		
		return path;
	}

}
