package gameOfLife;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class Game {

	private List<Cell> cellList = new ArrayList<>();
	private int w;
	private int h;
	
	public Game(int w, int h) {
		this.w = w;
		this.h = h;
		for (int i = 0; i < w*h; i++) {
			cellList.add(new Cell());
		}
		
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) { 
						
						int targetI = (x + i) + (y+j)*w;
						int currentI = x + y*w;
						if (targetI >= 0 && targetI < w*h) {
							if (i != 0 || j != 0) {
								Cell current = cellList.get(currentI);
								Cell node = cellList.get(targetI);
								
								current.addNabo(node);
							}	
							
						}
					}
				}
				
			}
		}
	}
	
	public int getHeigth() {
		return h;
	}
	
	public int getWidth() {
		return w;
	}
	
	public static void main(String[] args) {
		Game g = new Game(3,3);
		System.out.println(g.cellList.get(3).getNaboer().size());
	}
	
	
}
