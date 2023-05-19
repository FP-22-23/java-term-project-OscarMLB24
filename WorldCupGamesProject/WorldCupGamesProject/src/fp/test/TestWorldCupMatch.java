package fp.test;

import java.util.Scanner;

import fp.types.MatchFactory;
import fp.types.Matches;

public class TestWorldCupMatch {
	
	static Matches matches = MatchFactory.readMatches("data/WorldCupMatches.csv");
	
	public static void main(String[] args) {

		running();
	}
	
	private static void welcome() {
		 
		System.out.println("\n  Hello, this is my project about all the World Cup matches of football's history."
				+"\n\n	0.  End program." 
				+"\n	1.  Get an specific match."
				+ "\n	2.  Get a range of matches ordered by date."
				+ "\n	3.  Get total number of matches."
				+ "\n	4.  Check if there is a match with a specific characteristic."
				+ "\n	5.  Calculate the average of a numeric characteristic."
				+ "\n	6.  Filter the list of matches by an specific characteristic."
				+ "\n	7.  Get a dictionary which relates a country to the matches it has played."
				+ "\n	8.  Get a dictionary which relates a country to the number of won matches."
				+ "\n	9.  Get a match with a maximum or minimum characteristic after having been filtered by another characteristic."
				+ "\n	10. Get a list of matches sorted by a characteristic after having been filtered by another characteristic."
				+ "\n	11. Get all the matches in a short format."
				+ "\n	12. Get a dictionary which relates a year to the greatest attendance of that World Cup."
				+ "\n	13. Get a dictionary which relates a country to a list with the n games of that team where there have been more total goals."
				+ "\n	14. Get the team with the most or least won matches.");
	}
	
	private static void running() {
		
		Boolean run = true;
		
		while(run) {
			
			welcome();
			Integer n = askForInteger("What would you want to do? (number)\n",0,15);
			
			if(n == 0) {
				
				break;
			}
			else if(n == 1) {
				
				testF1();
			}
			else if(n == 2) {
				
				testF2();
			}
			else if(n == 3) {
				
				testF3();
			}
			else if(n == 4) {
				
				testF4();
			}
			else if(n == 5) {
				
				testF5();
			}
			else if(n == 6) {
				
				testF6();
			}
			else if(n == 7) {
				
				testF7();
			}
			else if(n == 8) {
				
				testF8();
			}
			else if(n == 9) {
				
				testF9();
			}
			else if(n == 10) {
				
				testF10();
			}
			else if(n == 11) {
				
				testF11();
			}
			else if(n == 12) {
				
				testF12();
			}
			else if(n == 13) {
				
				testF13();
			}
			else if(n == 14) {
				
				testF14();
			}
		}
	}

	@SuppressWarnings("resource")
	private static String askForString(String question) {
		
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n  " + question);
		
		return scanner.nextLine();
		
	}

	@SuppressWarnings("resource")
	private static Integer askForInteger(String question, Integer interA, Integer interB) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n  " + question);
		
		try {
			
			String res = scanner.nextLine();
			
			if(Integer.valueOf(res) >= interA && Integer.valueOf(res) < interB){
				
				return Integer.valueOf(res);
			}
			
			else {
				
				return askForInteger(question,interA,interB);
			}
		}
		catch(NumberFormatException e) {
			
			return askForInteger(question,interA,interB);
		}
	}
	
	private static void testF1() {
		
		Integer i = askForInteger("Which match position do you want to see?\n",0,matches.getNumberMatches()+1);
		
		System.out.println("\n   * " + matches.getMatch(i));
	}
	
	private static void testF2() {
		
		Integer i = askForInteger("Which is the first match position do you want to see?\n",0,matches.getNumberMatches());
		Integer e = askForInteger("Which is the last match position do you want to see?\n",1,matches.getNumberMatches()+1);
		
		System.out.println("\n   * " + matches.getMatches(i,e));
	}
	
	private static void testF3() {
		
		System.out.println("\n   * " + matches.getNumberMatches());
	}
	
	private static void testF4() {
		
		System.out.println("\n The variables to check are:"
				+ "\n\n	1.  year"
				+ "\n	2.  homeTeamGoals"
				+ "\n	3.  awayTeamGoals"
				+ "\n	4.  attendance"
				+ "\n	5.  halfTimeHomeGoals"
				+ "\n	6.  halfTimeAwayGoals"
				+ "\n	7.  matchId"
				+ "\n	8.  roundId"
				+ "\n	9.  stage"
				+ "\n	10. homeTeamName"
				+ "\n	11. awayTeamName"
				+ "\n	12. homeTeamInitials"
				+ "\n	13. awayTeamInitials"
				+ "\n	14. firstShotMinute"
				+ "\n	15. location (for the value write: stadium,city)"
				+ "\n	16. referees"
				+ "\n	17. date (example for the value: 13 Jul 1930)" 
				+ "\n	18. time (example for the value: 21:00)");
		
		String var = askForString("\n Which variable do you want to use? (exact name)\n");
		String val = askForString("\n Which value do you want to check?\n");
		
		System.out.println("\n   * " + matches.checkMatchData(var,val));
	}
	
	private static void testF5() {
		
		System.out.println("\n The variables to d the average are:\n"
				+ "\n	1.  homeTeamGoals"
				+ "\n	2.  awayTeamGoals"
				+ "\n	3.  attendance"
				+ "\n	4.  halfTimeHomeGoals"
				+ "\n	5.  halfTimeAwayGoals"
				+ "\n	6. firstShotMinute");
		
		String var = askForString("\n Which variable do you want to use? (exact name)\n");
		
		System.out.println("\n   * " + matches.averageValue(var));
	}
	
	private static void testF6() {
		
		System.out.println("\n The variables to filter are:"
				+ "\n\n	1.  year"
				+ "\n	2.  homeTeamGoals"
				+ "\n	3.  awayTeamGoals"
				+ "\n	4.  attendance"
				+ "\n	5.  halfTimeHomeGoals"
				+ "\n	6.  halfTimeAwayGoals"
				+ "\n	7.  matchId"
				+ "\n	8.  roundId"
				+ "\n	9.  stage"
				+ "\n	10. homeTeamName"
				+ "\n	11. awayTeamName"
				+ "\n	12. homeTeamInitials"
				+ "\n	13. awayTeamInitials"
				+ "\n	14. firstShotMinute"
				+ "\n	15. location (for the value write: stadium,city)"
				+ "\n	16. referees"
				+ "\n	17. date (example for the value: 13 Jul 1930)"
				+ "\n	18. time (example for the value: 21:00)");
		
		String var = askForString("\n Which variable do you want to use? (exact name)\n");
		String val = askForString("\n Which value do you want to check?\n");
		
		System.out.println("\n   * " + matches.filterBy(var,val));
	}
	
	private static void testF7() {
		
		System.out.println("\n   * " + matches.matchesPerCountry());
	}
	
	private static void testF8() {
		
		System.out.println("\n   * " + matches.numberWonMatchesPerCountry());
	}
	
	private static void testF9() { // NO FUNCIONA CON FILTER DE YEAR Y MAX/MIN DE ATTENDANCE
		
		System.out.println("\n The variables to filter are:"
				+ "\n\n	1.  year"
				+ "\n	2.  homeTeamGoals"
				+ "\n	3.  awayTeamGoals"
				+ "\n	4.  attendance"
				+ "\n	5.  halfTimeHomeGoals"
				+ "\n	6.  halfTimeAwayGoals"
				+ "\n	7.  matchId"
				+ "\n	8.  roundId"
				+ "\n	9.  stage"
				+ "\n	10. homeTeamName"
				+ "\n	11. awayTeamName"
				+ "\n	12. homeTeamInitials"
				+ "\n	13. awayTeamInitials"
				+ "\n	14. firstShotMinute"
				+ "\n	15. location (for the value write: stadium,city)"
				+ "\n	16. referees"
				+ "\n	17. date (example for the value: 13 Jul 1930)"
				+ "\n	18. time (example for the value: 21:00)");
		
		String filterVar = askForString("\n Which variable do you want to use to filter? (exact name)\n");
		String filterVal = askForString("\n Which value do you want to filter?\n");
		String maxMinVar = askForString("\n Which variable do you want to use to maximize/minimize? (exact name)\n");
		String funct = askForString("\n Do you want to maximize or minimize (write: max/min)?\n");
		
		System.out.println("\n   * " + matches.maxOrMinAfterFiltering(filterVar,filterVal,maxMinVar,funct));
	}
	
	private static void testF10() {
		
		System.out.println("\n The variables to filter are:"
				+ "\n\n	1.  year"
				+ "\n	2.  homeTeamGoals"
				+ "\n	3.  awayTeamGoals"
				+ "\n	4.  attendance"
				+ "\n	5.  halfTimeHomeGoals"
				+ "\n	6.  halfTimeAwayGoals"
				+ "\n	7.  matchId"
				+ "\n	8.  roundId"
				+ "\n	9.  stage"
				+ "\n	10. homeTeamName"
				+ "\n	11. awayTeamName"
				+ "\n	12. homeTeamInitials"
				+ "\n	13. awayTeamInitials"
				+ "\n	14. firstShotMinute"
				+ "\n	15. location (for the value write: stadium,city)"
				+ "\n	16. referees"
				+ "\n	17. date (example for the value: 13 Jul 1930)"
				+ "\n	18. time (example for the value: 21:00)");
		
		String filterVar = askForString("\n Which variable do you want to use to filter? (exact name)\n");
		String filterVal = askForString("\n Which value do you want to filter?\n");
		String sortVar = askForString("\n Which variable do you want to use to sort? (exact name)\n");
		String reversed = askForString("\n Do you want reversed order? (write: yes/no)\n");
	
		System.out.println("\n   * " + matches.sortedFiltering(filterVar,filterVal,sortVar,reversed));
	}
	
	private static void testF11() {
		
		System.out.println("\n   * " + matches.printMatches());
	}
	
	private static void testF12() {
		
		System.out.println("\n   * " + matches.maxAttendanceEachYear());
	}
	
	private static void testF13() {
		
		Integer n = askForInteger("How many matches do you want to see?\n",0,matches.getNumberMatches());
		System.out.println("\n   * " + matches.matchesWithMoreTotalGoalsPerCountry(n));
	}
	
	private static void testF14() {
		
		String funct = askForString("\n Do you want to maximize or minimize (write: max/min)?\n");
		System.out.println("\n   * " + matches.countryWithMostWonMatches(funct));
	}
}