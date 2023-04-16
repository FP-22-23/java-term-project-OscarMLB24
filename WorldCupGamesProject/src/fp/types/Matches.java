package fp.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.utils.Checkers;

public class Matches {
	 
	//properties
	
	private SortedSet<Match> matches;
	
	//constructors
	
	//1. With no parameters
	
	public Matches() {
		matches = new TreeSet<Match>();
	}
	
	//2. With a stream
	
	public Matches(Stream<Match> sm) {
		matches = sm.collect(Collectors.toCollection(TreeSet::new));
	}
	
	//string conversion
	
	@Override
	public String toString() {
		return "Matches [matches=" + matches + "]";
	}
	
	//equality criteria
	
	@Override
	public int hashCode() {
		return Objects.hash(matches);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matches other = (Matches) obj;
		return Objects.equals(matches, other.matches);
	}
	
	//methods
	
	//1. Number of matches in the set
	
	public Integer getNumberMatches() {
		return matches.size();
	}
	
	//2. Add a match to the set
	
	public void addMatch(Match m) {
		matches.add(m);
	}
	
	//3. Add several matches to the set
	
	public void addMatches(List<Match> l) {
		matches.addAll(l);
	}
	
	//4. Remove a match from the set
	
	public void removeMatch(Match m) {
		matches.remove(m);
	}
	
	//5. Check if there is a match with a specific value in one of their variables
	   
	public Boolean checkMatchData(String variable, String s) {

		Boolean res = false;
		
		for(Match m: matches) {

			if(variable == "year"){
				res = checkInteger(s,m.getYear());
				break;
			}
			if(variable.equals("homeTeamGoals")){
				res = checkInteger(s,m.getHomeTeamGoals());
				break;
			}
			if(variable.equals("awayTeamGoals")){
				res = checkInteger(s,m.getAwayTeamGoals());
				break;
			}
			if(variable.equals("attendance")){
				res = checkInteger(s,m.getAttendance());
				break;
			}
			if(variable.equals("halfTimeHomeGoals")){
				res = checkInteger(s,m.getHalfTimeHomeGoals());
				break;
			}
			if(variable.equals("halfTimeAwayGoals")){
				res = checkInteger(s,m.getHalfTimeAwayGoals());
				break;
			}
			if(variable.equals("roundId")){
				res = checkInteger(s,m.getRoundId());
				break;
			}
			if(variable.equals("matchId")){
				res = checkInteger(s,m.getMatchId());
				break;
			}
			
			///////////////////////////////////////////////
			
			if(variable.equals("stage")) {
				res = checkString(s,m.getStage());
				break;
			}
			if(variable.equals("homeTeamName")) {
				res = checkString(s,m.getHomeTeamName());
				break;
			}
			if(variable.equals("awayTeamName")) {
				res = checkString(s,m.getAwayTeamName());
				break;
			}
			if(variable.equals("winConditions")) {
				res = checkString(s,m.getWinConditions());
				break;
			}
			if(variable.equals("homeTeamInitials")) {
				res = checkString(s,m.getHomeTeamInitials());
				break;
			}
			if(variable.equals("awayTeamInitials")) {
				res = checkString(s,m.getAwayTeamInitials());
				break;
			}
			///////////////////////////////////////////////
			
			if(variable.equals("firstShotMinute")) {
				if(m.getFirstShotMinute()==Double.valueOf(s)) {
					res = true;
					break;
				}
			}
			
			if(variable.equals("location")) {
				String[] data = s.replace("(", "").replace(")", "").replace("[", "").replace("]", "").split(",");
				if(m.getLocation().getStadium()==data[0].strip() && m.getLocation().getCity()==data[1]) {
					res=true;
					break;
				}
			}
			
			if(variable.equals("referees")) {
				String[] data= s.replace("(", "").replace(")", "").replace("[", "").replace("]", "").split(",");
				if(m.getReferees().contains(data[0].strip()) && m.getReferees().contains(data[1].strip()) && m.getReferees().contains(data[2].strip())) {
					res=true;
					break;
				}
			}
			
			if(variable.equals("dateTime")) {
				LocalDateTime dt = LocalDateTime.parse(s.trim(),DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm"));
				if(m.getDateTime()==dt) {
					res=true;
					break;
				}
			}

		}
		
		return res;
	}
	
	private Boolean checkInteger(String value,Integer i) {

		Boolean res = false;
		Checkers.check("Not valid integer value", i>0);

		if(Integer.valueOf(value) == i) {
			res = true;
		}
		return res;
	}
	
	private Boolean checkString(String value,String i) {
		
		Boolean res = false;

		if(value.toLowerCase().equals(i.toLowerCase())) {
			res = true;
		}
		return res;
	}
	
	public Double averageValue(String variable) {
	
		Integer total = 0;
		Double sum = 0.0;
		
		for(Match m: matches){
			
			if(variable.equals("attendance") && m.getAttendance()!=null) {
				
				total++;
				sum += m.getAttendance();
			}
			
			if(variable.equals("homeTeamGoals") && m.getHomeTeamGoals()!=null) {
				
				total++;
				sum += m.getHomeTeamGoals();
			}
			
			if(variable.equals("awayTeamGoals") && m.getAwayTeamGoals()!=null) {
				
				total++;
				sum += m.getAwayTeamGoals();
			}
			
			if(variable.equals("halfTimeHomeGoals") && m.getHalfTimeHomeGoals()!=null) {
				
				total++;
				sum += m.getHalfTimeHomeGoals();
			}
			
			if(variable.equals("halfTimeAwayGoals") && m.getHalfTimeAwayGoals()!=null) {
				
				total++;
				sum += m.getHalfTimeAwayGoals();
			}
		}
		return sum/total;
	}
	 
}
