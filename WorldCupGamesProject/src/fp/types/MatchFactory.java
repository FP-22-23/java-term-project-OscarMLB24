package fp.types;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import fp.utils.Checkers;

public class MatchFactory {
	
	public static Matches readMatches(String file) {
	
		Matches res = null;
		try {
			Stream<Match> sm = Files.lines(Paths.get(file)).skip(1).map(MatchFactory::parseMatch);
			res = new Matches(sm);
		}
		catch(IOException e){
			
			System.out.println("Error with the file" + file);
			e.printStackTrace();
		}
		return res;
	}
	
	private static Match parseMatch(String s) {

		String[] values = s.split(";");
		Checkers.check("Not valid format", values.length==21);
		List<String> referees = new ArrayList<String>();

		Integer year = Integer.valueOf(values[0].replace("(", "").trim());	
		LocalDateTime dateTime = LocalDateTime.parse(values[1].trim(),DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm"));
		String stage = String.valueOf(values[2].trim());
		Location location = new Location(values[3],values[4]);
		String homeTeamName = String.valueOf(values[5].trim());
		Integer homeTeamGoals = Integer.valueOf(values[6].trim());
		Integer awayTeamGoals = Integer.valueOf(values[7].trim());
		String awayTeamName = String.valueOf(values[8].trim());
		String winConditions = String.valueOf(values[9].trim());
		Integer attendance = Integer.valueOf(values[10].trim());
		Integer halfTimeHomeGoals = Integer.valueOf(values[11].trim());
		Integer halfTimeAwayGoals = Integer.valueOf(values[12].trim());

		referees.add(values[13]);
		referees.add(values[14]);
		referees.add(values[15]);

		Integer roundId = Integer.valueOf(values[16].trim());
		Integer matchId = Integer.valueOf(values[17].trim());
		String homeTeamInitials = String.valueOf(values[18].trim());
		String awayTeamInitials = String.valueOf(values[19].trim());
		Double firstShotMinute = Double.valueOf(values[20].replace(")", "").trim());

		Checkers.check("The year is not correct", year>=1930 && year<=LocalDate.now().getYear() && (year-1930)%4==0 && year==dateTime.getYear());
		Checkers.check("The date is not correct", !dateTime.isAfter(LocalDateTime.now()));
		Checkers.check("The goals are not correct", homeTeamGoals>=0 && awayTeamGoals>=0);
		Checkers.check("The attendace is not correct", attendance>=0);
		Checkers.check("The half-time goals are not correct", halfTimeHomeGoals>=0 && halfTimeAwayGoals>=0 && halfTimeHomeGoals<=homeTeamGoals && halfTimeAwayGoals<=awayTeamGoals);
		Checkers.check("The round/match id is not correct", roundId>=0 && matchId>=0);
		Checkers.check("The initials of the teams are not correct", homeTeamInitials.length()==3 && awayTeamInitials.length()==3);
		Checkers.check("The minute of the first shot is not correct", firstShotMinute>=0 && firstShotMinute<=90);

		return new Match(year,dateTime,stage,location,homeTeamName,homeTeamGoals,awayTeamGoals,awayTeamName,winConditions,attendance,halfTimeHomeGoals,halfTimeAwayGoals,referees,roundId,matchId,homeTeamInitials,awayTeamInitials, firstShotMinute);
	}
}
