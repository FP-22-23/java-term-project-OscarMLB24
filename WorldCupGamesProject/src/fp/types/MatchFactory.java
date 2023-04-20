package fp.types;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
		Integer year = null;
		LocalDateTime dateTime = LocalDateTime.parse(values[1].trim(),DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm", Locale.ENGLISH));;
		String stage = values[2].trim();
		Location location = new Location(values[3],values[4]);
		String homeTeamName = values[5].trim();
		Integer homeTeamGoals = null;
		Integer awayTeamGoals = null;
		String awayTeamName = values[8].trim();
		String winConditions = values[9].trim();
		Integer attendance = null;
		Integer halfTimeHomeGoals = null;
		Integer halfTimeAwayGoals = null;
		Integer roundId = null;
		Integer matchId = null;
		String homeTeamInitials = values[18].trim();
		String awayTeamInitials = values[19].trim();
		Double firstShotMinute = null;

		if(values[0] != "") {
			
			year = Integer.valueOf(values[0].replace("(", "").trim());	
			Checkers.check("The year is not correct", year>=1930 && year<=LocalDate.now().getYear() && (year-1930)%4==0 && year==dateTime.getYear());
		}

		if(values[6] != "") {
			
			homeTeamGoals = Integer.valueOf(values[6].trim());
			Checkers.check("The home team goals are not correct", homeTeamGoals>=0);
		}
		
		if(values[7] != "") {
			
			awayTeamGoals = Integer.valueOf(values[7].trim());
			Checkers.check("The away team goals are not correct", awayTeamGoals>=0);
		}

		if(values[10] != "") {
			
			attendance = Integer.valueOf(values[10].trim());
			Checkers.check("The attendace is not correct", attendance>=0);
		}

		if(values[11] != "") {
			
			halfTimeHomeGoals = Integer.valueOf(values[11].trim());
			Checkers.check("The half-time home team goals are not correct", halfTimeHomeGoals>=0 && halfTimeHomeGoals<=homeTeamGoals);
		}

		if(values[12] != "") {
			
			halfTimeAwayGoals = Integer.valueOf(values[12].trim());
			Checkers.check("The half-time away team goals are not correct", halfTimeAwayGoals>=0 && halfTimeAwayGoals<=awayTeamGoals);
		}

		referees.add(values[13]);
		referees.add(values[14]);
		referees.add(values[15]);

		if(values[16] != "") {
			
			roundId = Integer.valueOf(values[16].trim());
			Checkers.check("The round id is not correct", roundId>=0);
		}
		
		if(values[17] != "") {
			
			matchId = Integer.valueOf(values[17].trim());
			Checkers.check("The match id is not correct", matchId>=0);
		}

		if(values[20] != "") {
			
			firstShotMinute = Double.valueOf(values[20].replace(")", "").trim());
			Checkers.check("The minute of the first shot is not correct", firstShotMinute>=0.0);
		}

		Checkers.check("The date is not correct", !dateTime.isAfter(LocalDateTime.now()));
		Checkers.check("The initials of the teams are not correct", homeTeamInitials.length()==3 && awayTeamInitials.length()==3);
		

		return new Match(year,dateTime,stage,location,homeTeamName,homeTeamGoals,awayTeamGoals,awayTeamName,winConditions,attendance,halfTimeHomeGoals,halfTimeAwayGoals,referees,roundId,matchId,homeTeamInitials,awayTeamInitials, firstShotMinute);
	}
}
