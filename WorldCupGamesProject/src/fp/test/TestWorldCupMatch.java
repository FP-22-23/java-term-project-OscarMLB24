package fp.test;

import fp.types.Match;
import fp.types.MatchFactory;
import fp.types.Matches;

public class TestWorldCupMatch {
	public static void main(String[] args) {
		
		Matches matches = MatchFactory.readMatches("data/WorldCupMatches.csv");
		
		System.out.println(matches.getNumberMatches());
		
		Match m = matches.getMatch(0);
		matches.removeMatch(0);
		System.out.println(matches.getNumberMatches());
		matches.addMatch(m);
		System.out.println(matches.getNumberMatches());
		
		//System.out.println(matches.checkMatchData("year", "2010"));
		//System.out.println(matches.averageValue("attendance"));
		//System.out.println(matches.filterBy("dateTime", "05 Jul 2014 - 13:00"));
		//System.out.println(matches.matchesPerCountry());
		//System.out.println(matches.wonMatchesPerCountry());
	}
}

