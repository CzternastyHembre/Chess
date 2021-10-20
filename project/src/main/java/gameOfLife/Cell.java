package gameOfLife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Cell {
	private List<Cell> naboer = new ArrayList();
	private boolean alive = false;
	int naboerAlive;
	
	public Cell() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public Collection<Cell> getNaboer() {
		return new ArrayList<>(naboer);
	}
	
	
	public void addNabo(Cell...cells) {
		naboer.addAll(Arrays.asList(cells));
		
//		for (Cell cell : naboer) {
//			if (!cell.getNaboer().contains(this)) {
//				cell.addNabo(this);
//			}
//		}
	}
	
	public void upDate() {
		naboerAlive = (int) naboer.stream()
			.filter(n -> n.alive)
			.count();
	}
}
