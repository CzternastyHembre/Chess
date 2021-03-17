package chess;

import java.util.List;

import javafx.util.Pair;

public interface IntPiece {
	List<Pair<String, String>> getPath();
	
	int getX();
	int getY();

	void setX(int x);
	void setY(int y);
}
