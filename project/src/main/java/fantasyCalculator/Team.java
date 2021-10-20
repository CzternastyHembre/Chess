package fantasyCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Team {
	
	Map<Integer, List<Team>> fixtures = new HashMap<>();
	private int value;
	private String name;
	private String initals;
	
	public Team(String name, String initals, int value) {
		this(name, initals);
		this.value = value;
	}
	
	private Team(String name, String initals) {
		this.name = name;
		this.initals = initals;
	}
	
	
	public Map<Integer, List<Team>> getFixture() {
		return new HashMap<>(fixtures);
	}
	
	public int getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getInitals() {
		return initals;
	}
	
	public void addGame(int round, Team team) {
//		if (this.initals.equals("ODD")) {
//			System.out.println(round + " " + team);
//		}
		List<Team> teams = new ArrayList<>();
		if (fixtures.containsKey(round)) {
			teams = fixtures.get(round);
			teams.add(team);
		}  else {
			teams.add(team);			
		}
		fixtures.put(round, teams);			
	}
	
	public int sumValues(int start, int end) {
		int sum = 0;
		for (int i = start; i < end; i++) {
			if (fixtures.containsKey(i)) {				
				List<Team> teams = fixtures.get(i);
				for (Team team : teams) {
//					System.out.println(team.getValue());
//					System.out.println(team.getValue());
					sum += team.getValue();
				}
			}
		}
		return sum;
	}
	
	public int sumGames(int start, int end) {
		int sum = 0;
		for (int i = start; i < end; i++) {
			if (fixtures.containsKey(i)) {			
				List<Team> teams = fixtures.get(i);
				sum += teams.size();
			}
		}
	
				
		return sum;
	}
	
	public double getAvg(int start, int end) {
		return (double) sumValues(start, end) / sumGames(start, end) ;
	}
	
	public void clearGames() {
		this.fixtures = new HashMap<>();
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public void toLongString() {
		fixtures.forEach((r, li) -> {
			if (r >= fantasyProgram.round) {
				System.out.println(r + ": " + this + " - " + li);
			}
		});
	}
	

}
