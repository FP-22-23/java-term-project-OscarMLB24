package fp.types;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import fp.utils.Checkers;

public record WorldCupMatch(Integer year, LocalDate date, String stage, String stadium, String city, String homeTeamName, Integer homeTeamGoals, Integer awayTeamGoals, String awayTeamName, String winConditions, Integer attendance, Integer halfTimeHomeGoals, Integer halfTimeAwayGoals, String referee, String assistant1, String assistant2, String roundID, String matchId, String homeTeamInitials, String awayTeamInitials, Float firstShotMinute) {

	public WorldCupMatch(LocalDate date, String stage, String stadium, String city, String homeTeamName, Integer homeTeamGoals, Integer awayTeamGoals, String awayTeamName, String referee) {
		//NO ME DEJA PONER ESTO DELANTE DEL CONSTRUCTOR
		this(null, date, stage, stadium, city, homeTeamName, homeTeamGoals, awayTeamGoals, awayTeamName, null, null, null, null, referee, null, null, null, null, null, null, null);
		Checkers.check("The date is not correct", !date.isAfter(LocalDate.now()));
		Checkers.check("The goals are not correct", homeTeamGoals>=0 && awayTeamGoals>=0);
		
	}

	public WorldCupMatch(LocalDate date, String stage, String homeTeamName, Integer homeTeamGoals, Integer awayTeamGoals, String awayTeamName) {
		this(null, date, stage, null, null, homeTeamName, homeTeamGoals, awayTeamGoals, awayTeamName, null, null, null, null, null, null, null, null, null, null, null, null);
		Checkers.check("The date is not correct", !date.isAfter(LocalDate.now()));
		Checkers.check("The goals are not correct", homeTeamGoals>=0 && awayTeamGoals>=0);
	}

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
		if (date.isBefore(LocalDate.of(2000, 1, 1))) {
			return false;
		}
		return res;
	}

	//public List<String>[] getReferees(){
		//return [referee,assistant1,assistant2];

	public Integer getYear() {
		return year;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getStage() {
		return stage;
	}

	public String getStadium() {
		return stadium;
	}

	public String getCity() {
		return city;
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

	public String getReferee() {
		return referee;
	}

	public String getAssistant1() {
		return assistant1;
	}

	public String getAssistant2() {
		return assistant2;
	}

	public String getRoundID() {
		return roundID;
	}

	public String getMatchId() {
		return matchId;
	}

	public String getHomeTeamInitials() {
		return homeTeamInitials;
	}

	public String getAwayTeamInitials() {
		return awayTeamInitials;
	}

	public Float getFirstShotMinute() {
		return firstShotMinute;
	}

	public String shortFormat() {
		return stage + "(" + date.getYear() + ") ==> " + homeTeamName + " " + homeTeamGoals + "-" + awayTeamGoals + " " + awayTeamName + " (" + getResult() + ")";
	}

	@Override
	public String toString() {
		return "WorldCupMatch [year=" + year + ", date=" + date + ", stage=" + stage + ", stadium=" + stadium
				+ ", city=" + city + ", homeTeamName=" + homeTeamName + ", homeTeamGoals=" + homeTeamGoals
				+ ", awayTeamGoals=" + awayTeamGoals + ", awayTeamName=" + awayTeamName + ", winConditions="
				+ winConditions + ", attendance=" + attendance + ", halfTimeHomeGoals=" + halfTimeHomeGoals
				+ ", halfTimeAwayGoals=" + halfTimeAwayGoals + ", referee=" + referee + ", assistant1=" + assistant1
				+ ", assistant2=" + assistant2 + ", roundID=" + roundID + ", matchId=" + matchId + ", homeTeamInitials="
				+ homeTeamInitials + ", awayTeamInitials=" + awayTeamInitials + ", firstShotMinute=" + firstShotMinute
				+ ", result=" + getResult() + ", recent=" + getRecent() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(awayTeamName, date, homeTeamName, matchId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorldCupMatch other = (WorldCupMatch) obj;
		return Objects.equals(awayTeamName, other.awayTeamName) && Objects.equals(date, other.date)
				&& Objects.equals(homeTeamName, other.homeTeamName) && Objects.equals(matchId, other.matchId);
	}

	public int compareTo(WorldCupMatch m) {
		if (getMatchId()!=null) {
			return getMatchId().compareTo(m.getMatchId());
		}
		else {
			return getDate().compareTo(m.getDate());
		}
	}
}
