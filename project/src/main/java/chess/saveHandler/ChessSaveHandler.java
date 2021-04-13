package chess.saveHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ChessSaveHandler {

	public static final String SAVE_FILE = "src/main/resources/storage/playedGames.txt";

	public static void Save(String saveString) {
		try {
	        FileWriter fileWriter = new FileWriter(SAVE_FILE, true); //Set true for append mode
	        PrintWriter writer = new PrintWriter(fileWriter);
			writer.println(saveString);  //New line
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<String[]> getGames() {
		List<String[]> games = new ArrayList<>();
		try {
			File file = new File("src/main/resources/storage/playedGames.txt");
		    Scanner sc = new Scanner(file);
		    while (sc.hasNextLine()) {
		    	String[] game = sc.nextLine().split(";");
		    	games.add(game);
		    }
		    sc.close();
		    return games;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String[] getGame(String name) {
		if (name == null) {
			throw new IllegalStateException("No game set to load");			
		}
		List<String[]> games = ChessSaveHandler.getGames();
		for (String[] game : games) {
			if (game[0].equals(name)) {
				return game;
			}
		}
		throw new IllegalArgumentException("No game with this name");
	}

	public static int getGameSize(String name) {
		String[] gameString = ChessSaveHandler.getGame(name);
		int size = Integer.parseInt(gameString[1]);
		return size;
	}

	public static boolean canGetGame(String name) {
		return ChessSaveHandler.getGame(name) != null;
	}
		
}
