package fp.types;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matches {
	 
	// properties
	
	private List<Match> matches;
	
	//constructors
	
	// 1. With no parameters
	
	public Matches() {
		matches = new ArrayList<Match>();
	}
	
	// 2. With a list
	
	public Matches(List<Match> lm) {
		matches = new ArrayList<>(lm);
	}
	
	// 3. With a stream
	
	public Matches(Stream<Match> sm) {
		matches = sm.collect(Collectors.toList());
	}

	// string conversion
	
	@Override
	public String toString() {
		return "Matches [matches=" + matches + "]";
	}
	
	// equality criteria
	
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
	
	// methods
	
	// 0. Get a match by the index
	
	public Match getMatch(int i) {
		return matches.get(i);
	}
	
	public List<Match> getMatches(int i,int e){
		return matches.subList(i, e);
	}
	
	// 1. Number of matches in the set
	
	public Integer getNumberMatches() {
		return matches.size();
	}
	
	// 2. Add a match to the set
	
	public void addMatch(Match m) {
		matches.add(m);
	}
	
	// 3. Add several matches to the set
	
	public void addMatches(List<Match> l) {
		matches.addAll(l);
	}
	
	// 4. Remove a match from the set
	
	public void removeMatch(Match m) {
		matches.remove(m);
	}
	
	public void removeMatch(int i) {
		matches.remove(i);
	}
	
	// 5. Check if there is a match with a specific value in one of their variables (exists)

	public Boolean checkMatchData(String variable, String value) {
		
		switch(variable) {

			case "year":
				
				try {
					return matches.stream()
							.anyMatch(m -> m.getYear().equals(Integer.valueOf(value)));
				}
				catch(NumberFormatException e) {
					return false;
				}
				
			case "homeTeamGoals": 
				
				try {
					return matches.stream()
							.anyMatch(m -> m.getHomeTeamGoals().equals(Integer.valueOf(value)));
				}
				catch(NumberFormatException e) {
					return false;
				}
				
			case "awayTeamGoals":
				
				try {
					return matches.stream()
							.anyMatch(m -> m.getAwayTeamGoals().equals(Integer.valueOf(value)));
				}
				catch(NumberFormatException e) {
					return false;
				}
			
			case "attendance":
				
				try {
					return matches.stream()
							.filter(m -> m.getAttendance() != null)
							.anyMatch(m -> m.getAttendance().equals(Integer.valueOf(value)));
				}
				catch(NumberFormatException e) {
					return false;
				}
			
			case "halfTimeHomeGoals":
				
				try {
					return matches.stream()
							.anyMatch(m -> m.getHalfTimeHomeGoals().equals(Integer.valueOf(value)));
				}
				catch(NumberFormatException e) {
					return false;
				}
			
			case "halfTimeAwayGoals": 
				
				try {
					return matches.stream()
							.anyMatch(m -> m.getHalfTimeAwayGoals().equals(Integer.valueOf(value)));
				}
				catch(NumberFormatException e) {
					return false;
				}
			
			case "matchId":
				
				try {
					return matches.stream()
							.anyMatch(m -> m.getMatchId().equals(Integer.valueOf(value)));
				}
				catch(NumberFormatException e) {
					return false;
				}
				
			case "roundId":
				
				try {
					return matches.stream()
							.anyMatch(m -> m.getRoundId().equals(Integer.valueOf(value)));
				}
				catch(NumberFormatException e) {
					return false;
				}
			
			case "stage":
				
				return matches.stream()
						.anyMatch(m -> m.getStage().toLowerCase().equals(value.toLowerCase()));
			
			case "homeTeamName":
				
				return matches.stream()
						.anyMatch(m -> m.getHomeTeamName().toLowerCase().equals(value.toLowerCase()));
			
			case "awayTeamName":
				
				return matches.stream()
						.anyMatch(m -> m.getAwayTeamName().toLowerCase().equals(value.toLowerCase()));
			
			case "homeTeamInitials":
				
				return matches.stream()
						.anyMatch(m -> m.getHomeTeamInitials().toLowerCase().equals(value.toLowerCase()));
			
			case "awayTeamInitials":
				
				return matches.stream()
						.anyMatch(m -> m.getAwayTeamInitials().toLowerCase().equals(value.toLowerCase()));
			
			case "firstShotMinute":
				
				try {
					return matches.stream()
							.anyMatch(m -> Math.floor(m.getFirstShotMinute()) == Math.floor(Double.valueOf(value)));
				}
				catch(NumberFormatException e) {
					return false;
				}

			 
			case "location":
				
				String[] data = value.replace("(", "").replace(")", "").replace("[", "").replace("]", "").split(",");
				
				try {
				return matches.stream()
						.anyMatch(m -> m.getLocation().getStadium().toLowerCase().trim().equals(data[0].trim().toLowerCase()) &&  m.getLocation().getCity().toLowerCase().trim().equals(data[1].trim().toLowerCase()));
				}
				catch(IndexOutOfBoundsException e) {
					return false;
				}
			
			case "referees":
				
				return matches.stream()
		                  .flatMap(m -> m.getReferees().stream())
		                  .anyMatch(r -> r.toLowerCase().contains(value.toLowerCase()));
			
			case "date":
				
				try {
					LocalDate dt = LocalDate.parse(value.trim(),DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH));
					return matches.stream()
							.anyMatch(m -> LocalDate.of(m.getDateTime().getYear(), m.getDateTime().getMonth(), m.getDateTime().getDayOfMonth()).isEqual(dt));
				}
				catch(DateTimeParseException e) {
					return false;
				}
			
			case "time":
				
				try {
					LocalTime dt = LocalTime.parse(value.trim(),DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH));
					return matches.stream()
							.anyMatch(m -> LocalTime.of(m.getDateTime().getHour(), m.getDateTime().getMinute()).equals(dt));
				}
				catch(DateTimeParseException e) {
					return false;
				}
	
			default: 
				return false;
		}
	}

	// 6. Calculate the average value of a numeric variable
	
	public Double averageValue(String variable) {
				
		switch(variable) {
		
			case "attendance":
				
				return (double) Math.round(matches.stream()
						.filter(m -> m.getAttendance() != null)
						.mapToLong(m -> m.getAttendance())
						.average().orElse(0.0) *100) /100;
			
			case "homeTeamGoals":
				
				return (double) Math.round(matches.stream()
						.mapToLong(m -> m.getHomeTeamGoals())
						.average().orElse(0.0) *100) /100;
			
			case "awayTeamGoals":
				
				return (double) Math.round(matches.stream()
						.mapToLong(m -> m.getAwayTeamGoals())
						.average().orElse(0.0) *100) /100;
			
			case "halfTimeHomeGoals":
				
				return (double) Math.round(matches.stream()
						.mapToLong(m -> m.getHalfTimeHomeGoals())
						.average().orElse(0.0) *100) /100;
			
			case "halfTimeAwayGoals":
				
				return (double) Math.round(matches.stream()
						.mapToLong(m -> m.getHalfTimeAwayGoals())
						.average().orElse(0.0) *100) /100;
			
			case "firstShotMinute":
				
				return (double) Math.round(matches.stream()
						.mapToDouble(m -> m.getFirstShotMinute())
						.average().orElse(0.0) *100) /100;
				
			default:
				return null;
		}
	}
	
	// 7. Filter the list of matches by a specific property
	
	public List<Match> filterBy(String variable, String value) {
		
		switch(variable) {
		
			case "year":
				
				try {
					return matches.stream()
							.filter(m -> m.getYear().equals(Integer.valueOf(value)))
							.toList();
				}
				catch(NumberFormatException e) {
					return new ArrayList<>(); 
				}
			
			case "homeTeamGoals":
				
				try {
					return matches.stream()
							.filter(m -> m.getHomeTeamGoals().equals(Integer.valueOf(value)))
							.toList();
				}
				catch(NumberFormatException e) {
					return new ArrayList<>();
				}
			 
			case "awayTeamGoals":
				
				try {
					return matches.stream()
							.filter(m -> m.getAwayTeamGoals().equals(Integer.valueOf(value)))
							.toList();
				}
				catch(NumberFormatException e) {
					return new ArrayList<>();
				}
			
			case "attendance":
				
				try {
					return matches.stream()
							.filter(m ->  m.getAttendance() != null && m.getAttendance().equals(Integer.valueOf(value)))
							.toList();
				}
				catch(NumberFormatException e) {
					return new ArrayList<>();
				}
			
			case "halfTimeHomeGoals":
				
				try {
					return matches.stream()
							.filter(m -> m.getHalfTimeHomeGoals().equals(Integer.valueOf(value)))
							.toList();
				}
				catch(NumberFormatException e) {
					return new ArrayList<>();
				}
			
			case "halfTimeAwayGoals":
				
				try {
					return matches.stream()
							.filter(m -> m.getHalfTimeAwayGoals().equals(Integer.valueOf(value)))
							.toList();
				}
				catch(NumberFormatException e) {
					return new ArrayList<>();
				}
			
			case "matchId":
				
				try {
					return matches.stream()
							.filter(m -> m.getMatchId().equals(Integer.valueOf(value)))
							.toList();
				}
				catch(NumberFormatException e) {
					return new ArrayList<>();
				}
			
			case "roundId":
				
				try {
					return matches.stream()
							.filter(m -> m.getRoundId().equals(Integer.valueOf(value)))
							.toList();
				}
				catch(NumberFormatException e) {
					return new ArrayList<>();
				}
			
			case "stage":
				
				return matches.stream()
						.filter(m -> m.getStage().toLowerCase().equals(value.toLowerCase()))
						.toList();
			
			case "homeTeamName":
				
				return matches.stream()
						.filter(m -> m.getHomeTeamName().toLowerCase().equals(value.toLowerCase()))
						.toList();
			
			case "awayTeamName":
				
				return matches.stream()
						.filter(m -> m.getAwayTeamName().toLowerCase().equals(value.toLowerCase()))
						.toList();
			
			case "homeTeamInitials":
				
				return matches.stream()
						.filter(m -> m.getHomeTeamInitials().toLowerCase().equals(value.toLowerCase()))
						.toList();
			
			case "awayTeamInitials":
				
				return matches.stream()
						.filter(m -> m.getAwayTeamInitials().toLowerCase().equals(value.toLowerCase()))
						.toList();
			
			case "firstShotMinute":
				
				try {
					return matches.stream()
							.filter(m -> Math.floor(m.getFirstShotMinute()) == Math.floor(Double.valueOf(value)))
							.toList();
				}
				catch(NumberFormatException e) {
					return new ArrayList<>();
				}
			
			case "location":
	
				String[] data = value.replace("(", "").replace(")", "").replace("[", "").replace("]", "").split(",");
				try {
					return matches.stream()
							.filter(m -> m.getLocation().getStadium().toLowerCase().trim().equals(data[0].trim().toLowerCase()) && m.getLocation().getCity().toLowerCase().trim().equals(data[1].trim().toLowerCase()))
							.toList();
				}
				catch(IndexOutOfBoundsException e) {
					return new ArrayList<>();
				}
				
			case "referees":
	
				return matches.stream()
		                  .filter(m -> m.getReferees().stream().anyMatch(r -> r.toLowerCase().contains(value.toLowerCase())))
		                  .toList();
			
			case "date":
				
				try {
					LocalDate dt = LocalDate.parse(value.trim(),DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH));
					return matches.stream()
							.filter(m -> LocalDate.of(m.getDateTime().getYear(), m.getDateTime().getMonth(), m.getDateTime().getDayOfMonth()).isEqual(dt))
							.toList();
				}
				catch(DateTimeParseException e) {
					return new ArrayList<>();
				}
	
			case "time":
				
				try {
					LocalTime dt = LocalTime.parse(value.trim(),DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH));
					return matches.stream()
							.filter(m -> LocalTime.of(m.getDateTime().getHour(), m.getDateTime().getMinute()).equals(dt))
							.toList();
				}
				catch(DateTimeParseException e) {
					return new ArrayList<>();
				}

			default:
				return new ArrayList<>();
		}
	}
	
	// 8. Returns a map where the keys are names of the countries and the values the matches they have played

	public Map<String, List<Match>> matchesPerCountry() {

    return matches.stream()
    		.flatMap(m -> Stream.of(
	            new AbstractMap.SimpleEntry<>(m.getHomeTeamName(), m),
	            new AbstractMap.SimpleEntry<>(m.getAwayTeamName(), m)))
	        .collect(Collectors.groupingBy(
	        			Map.Entry::getKey,
	        			TreeMap::new,
	        			Collectors.mapping(Map.Entry::getValue,
	        					   		   Collectors.toList())));
}

	// 9. Returns a map where the keys are names of the countries and the values the number of matches won

	public Map<String, Long> numberWonMatchesPerCountry() {
	
	    return matches.stream()
	    		.flatMap(m -> Stream.of(
	                    new AbstractMap.SimpleEntry<>(m.getHomeTeamName(), m.getHomeTeamGoals() > m.getAwayTeamGoals()),
	                    new AbstractMap.SimpleEntry<>(m.getAwayTeamName(), m.getAwayTeamGoals() > m.getHomeTeamGoals())))
	    		.filter(Map.Entry::getValue)
	            .collect(Collectors.groupingBy(
	            			Map.Entry::getKey,
	            			TreeMap::new,
	            			Collectors.counting()));
	}

	// 10. Returns the maximum or minimum Match depending on the specified property after having filtered the matches by a specific property value

	public Match maxOrMinAfterFiltering(String filteringVariable, String filteringValue, String  functionVariable , String function) {

		switch(function) {
		
		case "min":
			
			switch(functionVariable) {
				
				case "year":
					
					try {
					return filterBy(filteringVariable,filteringValue).stream()
							.min(Comparator.comparing(m -> m.getYear()))
							.get();
					}
					catch(NoSuchElementException e) {
						
						return null;
					}

				case "homeTeamGoals":
					
					try {
					return filterBy(filteringVariable,filteringValue).stream()
							.min(Comparator.comparing(m -> m.getHomeTeamGoals()))
							.get();
					}
					catch(NoSuchElementException e) {
						
						return null;
					}
					
				case "awayTeamGoals":
					
					try {
					return filterBy(filteringVariable,filteringValue).stream()
							.min(Comparator.comparing(m -> m.getAwayTeamGoals()))
							.get();
					}
					catch(NoSuchElementException e) {
						
						return null;
					}
					
				case "attendance":
					
					try {
					return filterBy(filteringVariable,filteringValue).stream()
							.filter(i -> i.getAttendance() != null)
							.min(Comparator.comparing(m -> m.getAttendance()))
							.get();
					}
					catch(NoSuchElementException e) {
						
						return null;
					}
					
				case "halfTimeHomeGoals":
					
					try {
					return filterBy(filteringVariable,filteringValue).stream()
							.min(Comparator.comparing(m -> m.getHalfTimeHomeGoals()))
							.get();
					}
					catch(NoSuchElementException e) {
						
						return null;
					}
					
				case "halfTimeAwayGoals":
					
					try {
					return filterBy(filteringVariable,filteringValue).stream()
							.min(Comparator.comparing(m -> m.getHalfTimeAwayGoals()))
							.get();
					}
					catch(NoSuchElementException e) {
						
						return null;
					}
					
				case "roundId":
					
					try {
					return filterBy(filteringVariable,filteringValue).stream()
							.min(Comparator.comparing(m -> m.getRoundId()))
							.get();
					}
					catch(NoSuchElementException e) {
						
						return null;
					}
					
				case "matchId":
					
					try {
					return filterBy(filteringVariable,filteringValue).stream()
							.min(Comparator.comparing(m -> m.getMatchId()))
							.get();
					}
					catch(NoSuchElementException e) {
						
						return null;
					}
					
				case "homeTeamName":
					
					try {
					return filterBy(filteringVariable,filteringValue).stream()
							.min(Comparator.comparing(m -> m.getHomeTeamName()))
							.get();
					}
					catch(NoSuchElementException e) {
						
						return null;
					}
					
				case "awayTeamName":
					
					try {
					return filterBy(filteringVariable,filteringValue).stream()
							.min(Comparator.comparing(m -> m.getAwayTeamName()))
							.get();
					}
					catch(NoSuchElementException e) {
						
						return null;
					}
					
				case "firstShotMinute":
					
					try {
					return filterBy(filteringVariable,filteringValue).stream()
							.min(Comparator.comparing(m -> m.getFirstShotMinute()))
							.get();
					}
					catch(NoSuchElementException e) {
						
						return null;
					}
					
				case "dateTime":
					
					try {
					return filterBy(filteringVariable,filteringValue).stream()
							.min(Comparator.naturalOrder())
							.get();
					}
					catch(NoSuchElementException e) {
						
						return null;
					}
					
				default:
					
					return null;
			}

		default:
			
			switch(functionVariable) {
			
			case "year":
				
				try {
				return filterBy(filteringVariable,filteringValue).stream()
						.max(Comparator.comparing(m -> m.getYear()))
						.get();
				}
				catch(NoSuchElementException e) {
					
					return null;
				}
				
			case "homeTeamGoals":
				
				return filterBy(filteringVariable,filteringValue).stream()
						.max(Comparator.comparing(m -> m.getHomeTeamGoals()))
						.get();
				
			case "awayTeamGoals":
				
				try {
				return filterBy(filteringVariable,filteringValue).stream()
						.max(Comparator.comparing(m -> m.getAwayTeamGoals()))
						.get();
				}
				catch(NoSuchElementException e) {
					
					return null;
				}
				
			case "attendance":
				
				try {
				return filterBy(filteringVariable,filteringValue).stream()
						.max(Comparator.comparing(m -> m.getAttendance()))
						.get();
				}
				catch(NoSuchElementException e) {
					
					return null;
				}
				
			case "halfTimeHomeGoals":
				
				try {
				return filterBy(filteringVariable,filteringValue).stream()
						.max(Comparator.comparing(m -> m.getHalfTimeHomeGoals()))
						.get();
				}
				catch(NoSuchElementException e) {
					
					return null;
				}
				
			case "halfTimeAwayGoals":
				
				try {
				return filterBy(filteringVariable,filteringValue).stream()
						.max(Comparator.comparing(m -> m.getHalfTimeAwayGoals()))
						.get();
				}
				catch(NoSuchElementException e) {
					
					return null;
				}
				
			case "roundId":
				
				try {
				return filterBy(filteringVariable,filteringValue).stream()
						.max(Comparator.comparing(m -> m.getRoundId()))
						.get();
				}
				catch(NoSuchElementException e) {
					
					return null;
				}
				
			case "matchId":
				
				try {
				return filterBy(filteringVariable,filteringValue).stream()
						.max(Comparator.comparing(m -> m.getMatchId()))
						.get();
				}
				catch(NoSuchElementException e) {
					
					return null;
				}
				
			case "homeTeamName":
				
				try {
				return filterBy(filteringVariable,filteringValue).stream()
						.max(Comparator.comparing(m -> m.getHomeTeamName()))
						.get();
				}
				catch(NoSuchElementException e) {
					
					return null;
				}
				
			case "awayTeamName":
				
				try {
				return filterBy(filteringVariable,filteringValue).stream()
						.max(Comparator.comparing(m -> m.getAwayTeamName()))
						.get();
				}
				catch(NoSuchElementException e) {
					
					return null;
				}
				
			case "firstShotMinute":
				
				try {
				return filterBy(filteringVariable,filteringValue).stream()
						.max(Comparator.comparing(m -> m.getFirstShotMinute()))
						.get();
				}
				catch(NoSuchElementException e) {
					
					return null;
				}
				
			case "dateTime":
				
				try {
				return filterBy(filteringVariable,filteringValue).stream()
						.max(Comparator.naturalOrder())
						.get();
				}
				catch(NoSuchElementException e) {
					
					return null;
				}
				
			default:

				return null;
			}
		}
	}
	
	
	// 11. Returns a list of Matches sorted by an specific order after having filtered the matches by a specific property value
	
	public List<Match> sortedFiltering(String filteringVariable, String filteringValue, String sortingVariable, String reversed) {
		
		switch(sortingVariable) {
			
			case "year":
				
				if(reversed.equals("yes")) {
					
					 return filterBy(filteringVariable,filteringValue).stream()
							.sorted(Comparator.comparing(Match::getYear).reversed())
							.toList();
				}
				else {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(m -> m.getYear()))
								.toList();
				}
				
			case "homeTeamGoals":
				
				if(reversed.equals("yes")) {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(Match::getHomeTeamGoals).reversed())
								.toList();
				}
				else {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(m -> m.getHomeTeamGoals()))
								.toList();
				}
				
			case "awayTeamGoals":
				
				if(reversed.equals("yes")) {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(Match::getAwayTeamGoals).reversed())
								.toList();
				}
				else {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(m -> m.getAwayTeamGoals()))
								.toList();
				}
				
			case "attendance":
				
				if(reversed.equals("yes")) {
					
					 return filterBy(filteringVariable,filteringValue).stream()
							 	.filter(i -> i.getAttendance() != null)
								.sorted(Comparator.comparing(Match::getAttendance).reversed())
								.toList();
				}
				else {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(m -> m.getAttendance()))
								.toList();
				}
				
			case "halfTimeHomeGoals":
				
				if(reversed.equals("yes")) {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(Match::getHalfTimeHomeGoals).reversed())
								.toList();
				}
				else {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(m -> m.getHalfTimeHomeGoals()))
								.toList();
				}
				
			case "halfTimeAwayGoals":
				
				if(reversed.equals("yes")) {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(Match::getHalfTimeAwayGoals).reversed())
								.toList();
				}
				else {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(m -> m.getHalfTimeAwayGoals()))
								.toList();
				}
				
			case "roundId":
				
				if(reversed.equals("yes")) {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(Match::getRoundId).reversed())
								.toList();
				}
				else {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(m -> m.getRoundId()))
								.toList();
				}
				
			case "matchId":
				
				if(reversed.equals("yes")) {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(Match::getMatchId).reversed())
								.toList();
				}
				else {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(m -> m.getMatchId()))
								.toList();
				}
				
			case "homeTeamName":
				
				if(reversed.equals("yes")) {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(Match::getHomeTeamName).reversed())
								.toList();
				}
				else {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(m -> m.getHomeTeamName()))
								.toList();
				}
				
			case "awayTeamName":
				
				if(reversed.equals("yes")) {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(Match::getAwayTeamName).reversed())
								.toList();
				}
				else {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(m -> m.getAwayTeamName()))
								.toList();
				}
				
			case "firstShotMinute":
				
				if(reversed.equals("yes")) {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(Match::getFirstShotMinute).reversed())
								.toList();
				}
				else {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.comparing(m -> m.getFirstShotMinute()))
								.toList();
				}
				
			case "dateTime":
				
				if(reversed.equals("yes")) {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.reverseOrder())
								.toList();
				}
				else {
					
					 return filterBy(filteringVariable,filteringValue).stream()
								.sorted(Comparator.naturalOrder())
								.toList();
				}
				
			default:
				
				return new ArrayList<>();
		}
	}
	
	// 12. Returns the short format of the games

	public List<String> printMatches() {
	
	    return matches.stream()
	            .collect(Collectors.mapping(Match::shortFormat, Collectors.toList()));
	}
	
	// 13. Returns a map where the keys are the years and the value is the greatest attendance of each year
	
	public Map<Integer, Integer> maxAttendanceEachYear(){
		
		return matches.stream()
				.collect(Collectors.groupingBy(Match::getYear))
				.entrySet().stream()
				.collect(Collectors.toMap(
							Map.Entry::getKey,
							v -> maxAttendance(v.getValue()),
							(oldV,newV) -> oldV,
							TreeMap::new));
	}
	
	private Integer maxAttendance(List<Match> l) {
		
		return l.stream()
				.filter(m -> m.getAttendance() != null)
				.max(Comparator.comparing(Match::getAttendance))
				.map(Match::getAttendance)
				.orElse(0);
	}
	
	// 14. Returns a SortedMap in which the keys are the nation names and the values are lists with the n matches with more total goals in their matches
	
	public SortedMap<String,List<Match>> matchesWithMoreTotalGoalsPerCountry(Integer n){
		
		return matchesPerCountry().entrySet().stream()
	            .collect(Collectors.toMap(
	            			Map.Entry::getKey,
	            			v -> getMatchesWithMoreTotalGoals(n, v.getValue()),
	            			(oldV,newV) -> oldV,
	            			TreeMap::new));
	}
	
	private List<Match> getMatchesWithMoreTotalGoals(Integer n, List<Match> l){
			
		return l.stream()
				.sorted(Comparator.comparing(Match::getTotalGoals).reversed())
				.limit(n)
				.collect(Collectors.toList());
	}
	
	// 15. Returns the team with more or less won matches
	
	public String countryWithMostWonMatches(String function) {
		
		if(function.equals("max")) {
			
			return numberWonMatchesPerCountry().entrySet().stream()
					.max(Comparator.comparing(Map.Entry::getValue))
					.get()
					.getKey();
		}
		else {
			
			return numberWonMatchesPerCountry().entrySet().stream()
					.min(Comparator.comparing(Map.Entry::getValue))
					.get()
					.getKey();
		}
	}
}