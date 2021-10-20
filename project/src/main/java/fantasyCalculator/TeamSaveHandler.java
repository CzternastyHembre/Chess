package fantasyCalculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TeamSaveHandler {
	
	public final static String SAVEPATH = "C:\\Users\\matti\\git\\tdt4100-prosjekt-mattisch\\project\\src\\main\\java\\fantasyCalculator\\resources";
	
	public static List<Team> getTeams(String path) {
		try (Scanner sc = new Scanner(new FileReader(SAVEPATH + "\\" + path))) {
			List<Team> teams = new ArrayList<>();
			while (sc.hasNextLine()) {
				String teamString = sc.nextLine();
				String name = teamString.split(";")[0];
				String initals = teamString.split(";")[1];
				int value = Integer.parseInt(teamString.split(";")[2]);
				
				Team t = new Team(name, initals, value);
				teams.add(t);
			}
			
			return fixtureReader(teams);
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
	}
	
	public static String getName(String initals) {
		return getTeams("Teams").stream()
				.filter(g -> g.getInitals().equals(initals))
				.findFirst().get().getName();
	}
	
	public static List<Team> fixtureReader(List<Team> teams) {
//		List<Team> teams = getTeams("Teams");
		List<Team> newTeams = new ArrayList<>();
		teams.stream().forEach(te -> te.clearGames());
		String teamName;
//		String teamInitals;
		for (Team team : teams) {
			teamName = team.getName();
//			teamInitals = team.getInitals();
			try (Scanner sc = new Scanner(new FileReader(SAVEPATH + "\\" + teamName))) {
				String lag = sc.nextLine();
				
				
				while (sc.hasNextLine()) {
					String fixString = sc.nextLine();
					
					final String initals = fixString.split("\t")[2].substring(0, 3);
					int gameRound = Integer.parseInt(fixString.split("\t")[1]);
					Team versusTeam = teams.stream().filter(t -> t.getInitals().equals(initals)).findAny().orElse(null);
					if (versusTeam != null) {
						team.addGame(gameRound, versusTeam);						
					}
				}
				newTeams.add(team);
			} catch (Exception e) {
				System.out.println(e);
				}
			}
		return newTeams;
		}
	
	public static void updateTable(int n) {
		List<Team> list = TeamSaveHandler.getTeams("Teams");
		try (PrintWriter pww = new PrintWriter(new FileWriter(SAVEPATH + "\\" + "TabellRunde" + n))){
			try (Scanner pw = new Scanner(new FileReader(SAVEPATH + "\\" + "Tabell"))){
				while (pw.hasNext()) {
					String line = pw.nextLine();
					String[] lines = line.split("\t");
					pw.nextLine();
					System.out.println(line);
					String t = list.stream().filter(te -> te.getName().equals(lines[1])).findAny().get().getInitals();
					pww.println(lines[1] + ";" + t + ";" + (Integer.parseInt(lines[0].split("\\.")[0])));
				}
			}catch (Exception e) {
				System.out.println(e);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public static List<Team> updateValueToTable(int n) {
//		int n = 5;
//		updateTable(n);
		
		return fixtureReader(getTeams("TabellRunde" + n));
	}
	public static List<Team> updateValueToFile(String file) {
//		int n = 5;
//		updateTable(n);
		
		return fixtureReader(getTeams(file));
	}
	
	public static void main(String[] args) {
		List<Team> list = TeamSaveHandler.getTeams("Teams");
		TeamSaveHandler.updateTable(11);
		System.out.println("ye");
//		list = TeamSaveHandler.fixtureReader();
		
		
	}

}