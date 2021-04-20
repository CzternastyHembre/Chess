package chess.pieces;

import org.junit.jupiter.api.Assertions;

import chess.Game;

public class MovementTest{
	
	private static void checkPos(int x, int y, Piece p, Game game) { // Sjekker om brikken har riktig posisjon og er på brette i samme
		Assertions.assertEquals(x, p.getX());
		Assertions.assertEquals(y, p.getY());
		Assertions.assertEquals(game.getPiece(x, y), p);	
		System.out.println(game);
	}
	
	public static void moveLines(Game game, Piece p) {
		int s = game.getSize();
		
		int x = p.getX();
		int y = p.getY();	
		
		for (int i = y + 1; i < s; i++) {//Move down (y++)
			int j = i;
			Assertions.assertDoesNotThrow(() -> p.moveTo(x, j));
//			p.moveTo(x, i);
			checkPos(x, i, p, game);
		}
		MovementTest.moveBack(x, y, p);
		
		for (int i = y - 1; i > 0; i--) {//Move up (y--)
			int j = i;
			Assertions.assertDoesNotThrow(() -> p.moveTo(x, j));
//			p.moveTo(x, i);
			checkPos(x, i, p, game);
		}
		MovementTest.moveBack(x, y, p);

		for (int i = x + 1; i < s; i++) {//Move right (x++)
			int j = i;
			Assertions.assertDoesNotThrow(() -> p.moveTo(j, y));
//			p.moveTo(i, y);
			checkPos(i, y, p, game);
		}

		for (int i = x - 1; i > 0; i--) {//Move left (x--)
			int j = i;
			Assertions.assertDoesNotThrow(() -> p.moveTo(j, y));
//			p.moveTo(i, y);
			checkPos(i, y, p, game);
		}
		MovementTest.moveBack(x, y, p);
	}
	
	public static void moveDiagonal(Game game, Piece p) {
		int s = game.getSize();
		
		int x = p.getX();
		int y = p.getY();	
		
		for (int i = 1; i + Math.max(x, y) < s; i++) {// x++ y++
			int x_to = x + i;
			int y_to = y + i;
			Assertions.assertDoesNotThrow(() -> p.moveTo(x_to, y_to));
//			p.moveTo(x + i, y + i);
			checkPos(x + i, y + i, p, game);
		}
		MovementTest.moveBack(x, y, p);
		
		for (int i = 1; Math.min(x, y) - i >= 0 ; i++) {// x-- y--
			int x_to = x - i;
			int y_to = y - i;
			Assertions.assertDoesNotThrow(() -> p.moveTo(x_to, y_to));
//			p.moveTo(x - i, y - i);
			checkPos(x - i, y - i, p, game);			
		}
		MovementTest.moveBack(x, y, p);

		for (int i = 1; y + i < s && x - i >= 0 ; i++) {// x-- y++
			p.moveTo(x - i, y + i);
			checkPos(x - i, y + i, p, game);			
		}
		MovementTest.moveBack(x, y, p);
		
		for (int i = 1; x + i < s && y - i >= 0  ; i++) {// x++ y--
			p.moveTo(x + i, y - i);
			checkPos(x + i, y - i, p, game);
		}
		MovementTest.moveBack(x, y, p);		
	}
	
	private static void moveBack(int x_to, int y_to, Piece p) {//Function that moves the piece back if it is not already there
		if (x_to == p.getX() && y_to == p.getY()) {
			return;
		}
		p.moveTo(x_to, y_to);
	}

	public static void moveIllegalLines(Game game, Piece p) {
		int s = game.getSize();
		
		int x = p.getX();
		int y = p.getY();	
		
		for (int i = y + 1; i < s; i++) {//Move down (y++)
			int y_to = i; // Cant parse local variable?
			Assertions.assertThrows(IllegalStateException.class, () -> p.moveTo(x, y_to));
		}
		
		for (int i = y - 1; i > 0; i--) {//Move up (y--)
			int y_to = i;
			Assertions.assertThrows(IllegalStateException.class, () -> p.moveTo(x, y_to));
		}

		for (int i = x + 1; i < s; i++) {//Move right (x++)
			int x_to = i;
			Assertions.assertThrows(IllegalStateException.class, () -> p.moveTo(x_to, y));
		}

		for (int i = x - 1; i > 0; i--) {//Move left (x--)
			int x_to = i;
			Assertions.assertThrows(IllegalStateException.class, () -> p.moveTo(x_to, y));
		}
	}

	public static void moveIllegalDiagonal(Game game, Piece p) {
		int s = game.getSize();
		
		int x = p.getX();
		int y = p.getY();	
		
		for (int i = 1; i + Math.max(x, y) < s; i++) {// x++ y++
			int x_to = x + i;
			int y_to = y + i;
			Assertions.assertThrows(IllegalStateException.class, () -> p.moveTo(x_to, y_to));
		}
		MovementTest.moveBack(x, y, p);
		
		for (int i = 1; Math.min(x, y) - i >= 0 ; i++) {// x-- y--
			int x_to = x - i;
			int y_to = y - i;
			Assertions.assertThrows(IllegalStateException.class, () -> p.moveTo(x_to, y_to));
		}
		MovementTest.moveBack(x, y, p);

		for (int i = 1; y + i < s && x - i >= 0 ; i++) {// x-- y++
			int x_to = x - i;
			int y_to = y + i;
			Assertions.assertThrows(IllegalStateException.class, () -> p.moveTo(x_to, y_to));
		}
		MovementTest.moveBack(x, y, p);
		
		for (int i = 1; x + i < s && y - i >= 0  ; i++) {// x++ y--
			int x_to = x + i;
			int y_to = y - i;
			Assertions.assertThrows(IllegalStateException.class, () -> p.moveTo(x_to, y_to));
		}
		MovementTest.moveBack(x, y, p);		
	}

}
