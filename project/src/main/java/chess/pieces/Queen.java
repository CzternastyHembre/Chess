package chess.pieces;

import java.util.List;

import javafx.util.Pair;

public class Queen extends Piece{

	public Queen(int x, int y, int color, Piece[][] board) {
		super(x, y, color, board, 'Q');
		if (color == 0) {
			this.filePath += "queen-white.png";
		} else {
			this.filePath += "queen-black.png";			
		}
	}
	
	

	@Override
	public List<Pair<String, String>> getPath() {//	Queen path is just bish + rook
		List<Pair<String, String>> pathRook = new Rook(x, y, color, board).getPath();
		List<Pair<String, String>> pathBishop = new Bishop(x, y, color, board).getPath();		
		pathRook.addAll(pathBishop);
		return pathRook;
	}

}
