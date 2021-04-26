package chess.saveHandler;


public interface SaveHandler {
	
	String SAVE_FILE = "src/main/resources/storage/playedGames.txt";

	void save(String saveString);
	String[] getGame(String name);
	boolean canGetGame(String name);
}
