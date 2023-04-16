package fp.types;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import fp.utils.Checkers;

public class Match implements Comparable<Match> {

	//properties

	private Integer year;
	private LocalDateTime dateTime;
	private String stage;
	private Location location;
	private String homeTeamName;
	private Integer homeTeamGoals;
	private Integer awayTeamGoals;
	private String awayTeamName;
	private String winConditions;
	private Integer attendance;
	private Integer halfTimeHomeGoals;
	private Integer halfTimeAwayGoals;
	private List<String> referees; //Main referee, assistant1, assistant2
	private Integer roundId;
	private Integer matchId;
	private String homeTeamInitials;
	private String awayTeamInitials;
	private Double firstShotMinute;

	//constructors
	
	//1. With all parameters
	
	public Match(Integer year,LocalDateTime dateTime, String stage, Location location, String homeTeamName, Integer homeTeamGoals, Integer awayTeamGoals, String awayTeamName,String winConditions, Integer attendance, Integer halfTimeHomeGoals, Integer halfTimeAwayGoals, List<String> referees, Integer roundId, Integer matchId, String homeTeamInitials, String awayTeamInitials, Double firstShotMinute) {
		
		if(year != null) {	
			Checkers.check("The year is not correct", year>=1930 && year<=LocalDate.now().getYear() && (year-1930)%4==0 && year==dateTime.getYear());
		}

		if(homeTeamGoals != null) {
			Checkers.check("The home team goals are not correct", homeTeamGoals>=0);
		}
		
		if(awayTeamGoals != null) {
			Checkers.check("The away team goals are not correct", awayTeamGoals>=0);
		}

		if(attendance != null) {
			Checkers.check("The attendace is not correct", attendance>=0);
		}

		if(halfTimeHomeGoals != null) {
			Checkers.check("The half-time home team goals are not correct", halfTimeHomeGoals>=0 && halfTimeHomeGoals<=homeTeamGoals);
		}

		if(halfTimeAwayGoals != null) {
			Checkers.check("The half-time away team goals are not correct", halfTimeAwayGoals>=0 && halfTimeAwayGoals<=awayTeamGoals);
		}
		
		if(roundId != null) {
			Checkers.check("The round id is not correct", roundId>=0);
		}
		
		if(matchId != null) {
			Checkers.check("The match id is not correct", matchId>=0);
		}

		if(firstShotMinute != null) {
			Checkers.check("The minute of the first shot is not correct", firstShotMinute>=0.0);
		}

		Checkers.check("The date is not correct", !dateTime.isAfter(LocalDateTime.now()));
		Checkers.check("The initials of the teams are not correct", homeTeamInitials.length()==3 && awayTeamInitials.length()==3);

		this.year = year;
		this.dateTime = dateTime;
		this.stage = stage;
		this.location= location;
		this.homeTeamName = homeTeamName;
		this.homeTeamGoals = homeTeamGoals;
		this.awayTeamGoals = awayTeamGoals;
		this.awayTeamName = awayTeamName;
		this.winConditions = winConditions;
		this.attendance = attendance;
		this.halfTimeHomeGoals = halfTimeHomeGoals;
		this.halfTimeAwayGoals = halfTimeAwayGoals;
		this.referees = referees;
		this.roundId = roundId;
		this.matchId = matchId;
		this.homeTeamInitials = homeTeamInitials;
		this.awayTeamInitials = awayTeamInitials;
		this.firstShotMinute = firstShotMinute;
	}

	//2. With some parameters
	
	public Match(LocalDateTime dateTime, String stage, String homeTeamName, Integer homeTeamGoals, Integer awayTeamGoals, String awayTeamName, Integer matchId) {

		if(year != null) {	
			Checkers.check("The year is not correct", year>=1930 && year<=LocalDate.now().getYear() && (year-1930)%4==0 && year==dateTime.getYear());
		}
		if(homeTeamGoals != null) {
			Checkers.check("The home team goals are not correct", homeTeamGoals>=0);
		}
		
		if(awayTeamGoals != null) {
			Checkers.check("The away team goals are not correct", awayTeamGoals>=0);
		}
		if(matchId != null) {
			Checkers.check("The match id is not correct", matchId>=0);
		}

		this.dateTime = dateTime;
		this.stage = stage;
		this.homeTeamName = homeTeamName;
		this.homeTeamGoals = homeTeamGoals;
		this.awayTeamGoals = awayTeamGoals;
		this.awayTeamName = awayTeamName;
		this.matchId = matchId;
	}

	//derived properties

	public MatchResult getResult() {
		
		MatchResult res = MatchResult.DRAW;
		if (homeTeamGoals > awayTeamGoals) {
			
			return MatchResult.LOCAL_WINS;
		}
		else if (homeTeamGoals < awayTeamGoals) {
			
			return MatchResult.VISITANT_WINS;
		}
		return res;	
	}

	public Boolean getRecent() {
		
		Boolean res = true;
		if (dateTime.isBefore(LocalDateTime.of(2000, 1, 1, 0, 0))) {
			
			return false;
		}
		return res;
	}

	//getters and setters

	public Integer getYear() {
		return year;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getStage() {
		return stage;
	}

	public Location getLocation() {
		return location;
	}

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public Integer getHomeTeamGoals() {
		return homeTeamGoals;
	}

	public Integer getAwayTeamGoals() {
		return awayTeamGoals;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public String getWinConditions() {
		return winConditions;
	}

	public Integer getAttendance() {
		return attendance;
	}

	public Integer getHalfTimeHomeGoals() {
		return halfTimeHomeGoals;
	}

	public Integer getHalfTimeAwayGoals() {
		return halfTimeAwayGoals;
	}

	public List<String> getReferees() {
		return referees;
	}

	public Integer getRoundId() {
		return roundId;
	}

	public Integer getMatchId() {
		return matchId;
	}

	public String getHomeTeamInitials() {
		return homeTeamInitials;
	}

	public String getAwayTeamInitials() {
		return awayTeamInitials;
	}

	public Double getFirstShotMinute() {
		return firstShotMinute;
	}
	
	public void setHomeTeamGoals(Integer homeTeamGoals) {
		Checkers.check("The goals are not correct", homeTeamGoals>=0);
		this.homeTeamGoals = homeTeamGoals;
	}

	public void setAwayTeamGoals(Integer awayTeamGoals) {
		Checkers.check("The goals are not correct", awayTeamGoals>=0);
		this.awayTeamGoals = awayTeamGoals;
	}

	public void setWinConditions(String winConditions) {
		this.winConditions = winConditions;
	}

	public void setHalfTimeHomeGoals(Integer halfTimeHomeGoals) {
		Checkers.check("The goals are not correct", halfTimeHomeGoals>=0);
		this.halfTimeHomeGoals = halfTimeHomeGoals;
	}

	public void setHalfTimeAwayGoals(Integer halfTimeAwayGoals) {
		Checkers.check("The goals are not correct", halfTimeAwayGoals>=0);
		this.halfTimeAwayGoals = halfTimeAwayGoals;
	}

	public void setHomeTeamInitials(String homeTeamInitials) {
		Checkers.check("The initials of the team are not correct", homeTeamInitials.length()==3);
		this.homeTeamInitials = homeTeamInitials;
	}

	public void setAwayTeamInitials(String awayTeamInitials) {
		Checkers.check("The initials of the team are not correct", awayTeamInitials.length()==3);
		this.awayTeamInitials = awayTeamInitials;
	}
	
	//string representation

	@Override
	public String toString() {
		
		return "WorldCupMatch [year=" + year + ", dateTime=" + dateTime + stage + ", location="
				+ location + ", homeTeamName=" + homeTeamName + ", homeTeamGoals=" + homeTeamGoals + ", awayTeamGoals="
				+ awayTeamGoals + ", awayTeamName=" + awayTeamName + ", winConditions=" + winConditions
				+ ", attendance=" + attendance + ", halfTimeHomeGoals=" + halfTimeHomeGoals + ", halfTimeAwayGoals="
				+ halfTimeAwayGoals + ", referees=" + referees + ", roundId=" + roundId + ", matchId=" + matchId
				+ ", homeTeamInitials=" + homeTeamInitials + ", awayTeamInitials=" + awayTeamInitials
				+ ", firstShotMinute=" + firstShotMinute + ", result=" + getResult() + ", recent=" + getRecent() + "]";
	}
	
	public String shortFormat() {
		return stage + "(" + dateTime.getYear() + ") ==> " + homeTeamName + " " + homeTeamGoals + "-" + awayTeamGoals + " " + awayTeamName + " (" + getResult() + ")";
	}
	
	//equality criteria
	
	@Override
	public int hashCode() {
		return Objects.hash(matchId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		return Objects.equals(matchId, other.matchId);
	}

	//natural order criteria
	
	public int compareTo(Match m) {
		Integer res = getDateTime().compareTo(m.getDateTime());
		return res;
	}

	//methods

	public void addReferee(String r) {
		referees.add(r);
	}

	public void removeReferee() {
		
		if (referees.size()>1){
			
			referees.remove(-1);
		}
		else if(referees.size()==1){
			
			referees.remove(0);
		}
	}
}
