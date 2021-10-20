package fantasyCalculator;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

public class fantasyProgram {
	
	public final static int round = 14;
	public final static int lastRound = 30;

	
	private List<Team> teams = TeamSaveHandler.fixtureReader(TeamSaveHandler.getTeams("Teams"));

	public void calculate(int n, List<Team> teams){
		//Sysout from avg score
//		teams.sort((a, b) -> (int) Math.signum((a.getAvg(round, round + n) - b.getAvg(round, round + n))));
//		for (Team team : teams) {
//			double number = team.getAvg(round, round + n);
//			DecimalFormat df = new DecimalFormat("###.#");
//			String tab = "\t";
//			if (team.getName().length() < 8) {
//				tab += "\t";
//			}
//			
//			System.out.println(team.getName() + tab + team.sumValues(round, n + round) + "\t" + df.format(number) + " (" + team.sumGames(round, round + n) + ")");
//		}
//		System.out.println("-");
//		
		//syout from most games then avgscore
		teams.sort((a, b) -> {
			if (a.sumGames(round, n + round) != b.sumGames(round, n + round)) {
				return a.sumGames(round, n + round) - b.sumGames(round, n + round);
			}
			return (int) Math.signum((a.getAvg(round, round + n) - b.getAvg(round, round + n))); 
		});
		for (Team team : teams) {
			double number = team.getAvg(round, round + n);
			DecimalFormat df = new DecimalFormat("###.#");
			String tab = "\t";
			if (team.getName().length() < 8) {
				tab += "\t";
			}
			
			System.out.println(team.getName() + tab + team.sumValues(round, n + round) + "\t" + df.format(number) + " (" + team.sumGames(round, round + n) + ")");
		}
		System.out.println();
	}
	
	public void calculate(int n) {
		calculate(n, teams);
	}
	
	public void calculateFromTabell2021(int n, int rundefrom) {
		System.out.println("Tabell fra runde " + rundefrom);
		calculate(n, TeamSaveHandler.updateValueToTable(rundefrom));
	}
	public void calculatefILE(int n, String file) {
		System.out.println("Tabell fra " + file);
		calculate(n, TeamSaveHandler.updateValueToFile(file));
	}
	
	public void coutTheAbnormalities() {
		String linje = "";
		for (int i = round; i <= lastRound; i++) {
			linje += "Runde: " + i + "\n";
			for (Team team : teams) {
				if (team.getFixture().get(i) != null) {
					if (team.getFixture().get(i).size() > 1) {
						linje += team + ": " + team.getFixture().get(i) + " ("+team.getFixture().get(i).size()+")\n";						
					}
				} else {
					linje += team + " (" + 0 + ")\n";
				}
			}
			linje += "\n";
		}
		System.out.println(linje);
	}

	public void coutTheTeam(String init) {
		teams.stream()
		.filter(team -> team.getInitals().equals(init))
		.findAny().get().toLongString();
	}

	
	
	public static void main(String[] args) {
		fantasyProgram f = new fantasyProgram();
		
		int roundsFramover = 5;
		f.calculatefILE(roundsFramover, "Teams");
		System.out.println("\n");
		f.calculateFromTabell2021(roundsFramover, 11);
//		f.calculateFromTabell2021(roundsFramover, 5);
		
		f.coutTheAbnormalities();
		
		
//		f.coutTheTeam("BG ");
		
		
		f.teams.stream()
		.forEach(team -> System.out.println(team.getName()));
	}
	
	
	
}
