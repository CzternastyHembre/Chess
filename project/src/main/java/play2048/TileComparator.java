package play2048;

import java.util.Comparator;

public class TileComparator implements Comparator<Tile>{

	@Override
	public int compare(Tile o1, Tile o2) {
		if (o1 == null && o2 == null) {
			return 0;
		}
		return o1 == null ? -1 : 1;
	}
}