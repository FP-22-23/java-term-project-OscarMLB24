package fp.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matches {
	 
	//properties
	
	private List<Match> matches;
	
	//constructors
	
	//1. With no parameters
	
	public Matches() {
		matches = new ArrayList<Match>();
	}
	
	//2. With a stream
	
	public Matches(Stream<Match> sm) {
		matches = sm.collect(Collectors.toList());
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
	
	//0. Get a match by the index
	
	public Match getMatch(int i) {
		return matches.get(i);
	}
	
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
	
	public void removeMatch(int i) {
		matches.remove(i);
	}
	
	//5. Check if there is a match with a specific value in one of their variables
	
	public Boolean checkMatchData(String variable, String s) {

		if(variable.equals("year")) {

			return matches.stream()
					.anyMatch(m -> m.getYear().equals(Integer.valueOf(s)));
		}
		else if(variable.equals("homeTeamGoals")) {
			
			return matches.stream()
					.anyMatch(m -> m.getHomeTeamGoals().equals(Integer.valueOf(s)));
		}
		else if(variable.equals("awayTeamGoals")) {
			
			return matches.stream()
					.anyMatch(m -> m.getAwayTeamGoals().equals(Integer.valueOf(s)));
		}
		else if(variable.equals("attendance")) {
			
			return matches.stream()
					.anyMatch(m -> m.getAttendance().equals(Integer.valueOf(s)));
		}
		else if(variable.equals("halfTimeHomeGoals")) {
			
			return matches.stream()
					.anyMatch(m -> m.getHalfTimeHomeGoals().equals(Integer.valueOf(s)));
		}
		else if(variable.equals("halfTimeAwayGoals")) {
			
			return matches.stream()
					.anyMatch(m -> m.getHalfTimeAwayGoals().equals(Integer.valueOf(s)));
		}
		else if(variable.equals("matchId")) {
			
			return matches.stream()
					.anyMatch(m -> m.getMatchId().equals(Integer.valueOf(s)));
		}
		else if(variable.equals("roundId")) {
			
			return matches.stream()
					.anyMatch(m -> m.getRoundId().equals(Integer.valueOf(s)));
		}
		else if(variable.equals("stage")) {
			
			return matches.stream()
					.anyMatch(m -> m.getStage().toLowerCase().equals(s.toLowerCase()));
		}
		else if(variable.equals("homeTeamName")) {
			
			return matches.stream()
					.anyMatch(m -> m.getHomeTeamName().toLowerCase().equals(s.toLowerCase()));
		}
		else if(variable.equals("awayTeamName")) {
			
			return matches.stream()
					.anyMatch(m -> m.getAwayTeamName().toLowerCase().equals(s.toLowerCase()));
		}
		else if(variable.equals("homeTeamInitials")) {
			
			return matches.stream()
					.anyMatch(m -> m.getHomeTeamInitials().toLowerCase().equals(s.toLowerCase()));
		}
		else if(variable.equals("awayTeamInitials")) {
			
			return matches.stream()
					.anyMatch(m -> m.getAwayTeamInitials().toLowerCase().equals(s.toLowerCase()));
		}
		else if(variable.equals("firstShotMinute")) {
			
			return matches.stream()
					.anyMatch(m -> m.getFirstShotMinute().equals(Double.valueOf(s)));
		}
		else if(variable.equals("location")) {																		// NO FUNCIONA

			String[] data = s.replace("(", "").replace(")", "").replace("[", "").replace("]", "").split(",");
			System.out.println(data[0]);
			System.out.println(data[1]);
			return matches.stream()
					.anyMatch(m -> m.getLocation().getStadium().toLowerCase().equals(data[0].trim().toLowerCase()) && m.getLocation().getCity().toLowerCase().equals(data[1].trim().toLowerCase()));
		}
		if(variable.equals("referees")) {
			
			return matches.stream()
	                  .flatMap(m -> m.getReferees().stream())
	                  .anyMatch(r -> r.contains(s));
		}
		if(variable.equals("dateTime")) {
			try {
				LocalDateTime dt = LocalDateTime.parse(s.trim(),DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm", Locale.ENGLISH));
				return matches.stream()
						.anyMatch(m -> m.getDateTime().isEqual(dt));
			}
			catch(DateTimeParseException e) {
				throw new IllegalArgumentException("La fecha proporcionada no es válida", e);
			}

		}
		else {
			return null;
		}
	}
	
	//6. Calculate the average value of a numeric variable
	
	public Double averageValue(String variable) {
	
		if(variable.equals("attendance")) {
			return matches.stream()
					.mapToLong(m -> m.getAttendance() != null ? m.getAttendance() : 0)
					.average().orElse(0.0);
		}
		else if(variable.equals("homeTeamGoals")) {
			return matches.stream()
					.mapToLong(m -> m.getHomeTeamGoals())
					.average().orElse(0.0);
		}
		else if(variable.equals("awayTeamGoals")) {
			return matches.stream()
					.mapToLong(m -> m.getAwayTeamGoals())
					.average().orElse(0.0);
		}
		else if(variable.equals("halfTimeHomeGoals")) {
			return matches.stream()
					.mapToLong(m -> m.getHalfTimeHomeGoals())
					.average().orElse(0.0);
		}
		else if(variable.equals("halfTimeAwayGoals")) {
			return matches.stream()
					.mapToLong(m -> m.getHalfTimeAwayGoals())
					.average().orElse(0.0);
		}
		else if(variable.equals("firstShotMinute")) {
			return matches.stream()
					.mapToDouble(m -> m.getFirstShotMinute())
					.average().orElse(0.0);
		}
		else {
			return null;
		}
	}
	
	//7. Filter the list of maps by a specific property
	
	public List<Match> filterBy(String variable, String s) {

		if(variable.equals("year")) {

			return matches.stream()
					.filter(m -> m.getYear().equals(Integer.valueOf(s)))
					.toList();
		}
		else if(variable.equals("HomeTeamGoals")) {
			
			return matches.stream()
					.filter(m -> m.getHomeTeamGoals().equals(Integer.valueOf(s)))
					.toList();
		}
		else if(variable.equals("AwayTeamGoals")) {
			
			return matches.stream()
					.filter(m -> m.getAwayTeamGoals().equals(Integer.valueOf(s)))
					.toList();
		}
		else if(variable.equals("attendance")) {
			
			return matches.stream()
					.filter(m -> m.getAttendance().equals(Integer.valueOf(s)))
					.toList();
		}
		else if(variable.equals("halfTimeHomeGoals")) {
			
			return matches.stream()
					.filter(m -> m.getHalfTimeHomeGoals().equals(Integer.valueOf(s)))
					.toList();
		}
		else if(variable.equals("halfTimeAwayGoals")) {
			
			return matches.stream()
					.filter(m -> m.getHalfTimeAwayGoals().equals(Integer.valueOf(s)))
					.toList();
		}
		else if(variable.equals("matchId")) {
			
			return matches.stream()
					.filter(m -> m.getMatchId().equals(Integer.valueOf(s)))
					.toList();
		}
		else if(variable.equals("roundId")) {
			
			return matches.stream()
					.filter(m -> m.getRoundId().equals(Integer.valueOf(s)))
					.toList();
		}
		else if(variable.equals("stage")) {
			
			return matches.stream()
					.filter(m -> m.getStage().toLowerCase().equals(s.toLowerCase()))
					.toList();
		}
		else if(variable.equals("homeTeamName")) {
			
			return matches.stream()
					.filter(m -> m.getHomeTeamName().toLowerCase().equals(s.toLowerCase()))
					.toList();
		}
		else if(variable.equals("awayTeamName")) {
			
			return matches.stream()
					.filter(m -> m.getAwayTeamName().toLowerCase().equals(s.toLowerCase()))
					.toList();
		}
		else if(variable.equals("homeTeamInitials")) {
			
			return matches.stream()
					.filter(m -> m.getHomeTeamInitials().toLowerCase().equals(s.toLowerCase()))
					.toList();
		}
		else if(variable.equals("awayTeamInitials")) {
			
			return matches.stream()
					.filter(m -> m.getAwayTeamInitials().toLowerCase().equals(s.toLowerCase()))
					.toList();
		}
		else if(variable.equals("firstShotMinute")) {
			
			return matches.stream()
					.filter(m -> m.getFirstShotMinute().equals(Double.valueOf(s)))
					.toList();
		}
		else if(variable.equals("location")) {																		// NO FUNCIONA

			String[] data = s.replace("(", "").replace(")", "").replace("[", "").replace("]", "").split(",");
			System.out.println(data[0]);
			System.out.println(data[1]);
			return matches.stream()
					.filter(m -> m.getLocation().getStadium().toLowerCase().equals(data[0].trim().toLowerCase()) && m.getLocation().getCity().toLowerCase().equals(data[1].trim().toLowerCase()))
					.toList();
		}
		if(variable.equals("referees")) {

			return matches.stream()
	                  .filter(m -> m.getReferees().stream().anyMatch(r -> r.contains(s)))
	                  .collect(Collectors.toList());
		}
		if(variable.equals("dateTime")) {
			try {
				LocalDateTime dt = LocalDateTime.parse(s.trim(),DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm", Locale.ENGLISH));
				return matches.stream()
						.filter(m -> m.getDateTime().isEqual(dt))
						.toList();
			}
			catch(DateTimeParseException e) {
				throw new IllegalArgumentException("La fecha proporcionada no es válida", e);
			}
		}
		else {
			return null;
		}
	}
	
	//8. Returns a map where the keys are names of the countries and the values the matches they have played
	 
	public Map<String, List<Match>> matchesPerCountry(){
	
		Map<String, List<Match>> res = new HashMap<>();
		
		for(Match m: matches) {
			if(res.containsKey(m.getHomeTeamName())) {
				res.get(m.getHomeTeamName()).add(m);
			}
			else {
				res.put(m.getHomeTeamName(), new ArrayList<Match>());
				res.get(m.getHomeTeamName()).add(m);
			}
			if (res.containsKey(m.getAwayTeamName())) {
				res.get(m.getAwayTeamName()).add(m);
			}
			else {
				res.put(m.getAwayTeamName(), new ArrayList<Match>());
				res.get(m.getAwayTeamName()).add(m);
			}
		}
		return res;
	}

	/*public Map<String, List<Match>> matchesPerCountry2() {
    return matches.stream()
        .flatMap(m -> Stream.of(
            new AbstractMap.SimpleEntry<>(m.getHomeTeamName(), m),
            new AbstractMap.SimpleEntry<>(m.getAwayTeamName(), m)
        ))
        .collect(Collectors.groupingBy(
            Map.Entry::getKey,
            TreeMap::new,
            Collectors.mapping(Map.Entry::getValue, Collectors.toList())
        ));
}*/

	//9. Returns a map where the keys are names of the countries and the values the number of matches won

	public Map<String, Integer> wonMatchesPerCountry(){

		Map<String, Integer> res = new HashMap<>();

		for(Match m: matches) {

			Boolean winLocal = m.getHomeTeamGoals()>m.getAwayTeamGoals();

			if(winLocal) {
				if(res.containsKey(m.getHomeTeamName())) {
					res.put(m.getHomeTeamName(), res.get(m.getHomeTeamName()) + 1);
				}
				else {
					res.put(m.getHomeTeamName(), 1);
				}
			}
			else {
				if(res.containsKey(m.getAwayTeamName())) {
					res.put(m.getAwayTeamName(), res.get(m.getAwayTeamName()) + 1);
				}
				else {
					res.put(m.getAwayTeamName(), 1);
				}
			}
		}
		return res;
	}

	/*public Map<String, Long> wonMatchesPerCountry2() {
	    return matches.stream()
	            .flatMap(m -> Stream.of(
	                    new AbstractMap.SimpleEntry<>(m.getHomeTeamName(), m.getHomeTeamGoals() > m.getAwayTeamGoals()),
	                    new AbstractMap.SimpleEntry<>(m.getAwayTeamName(), m.getAwayTeamGoals() > m.getHomeTeamGoals())))
	            .filter(Map.Entry::getValue)
	            .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.counting()))
	            .entrySet().stream()
	            .sorted(Map.Entry.comparingByKey())
	            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
	}*/
}

