package fp.types;

import java.util.Objects;

import fp.utils.Checkers;

public class Location{

	//properties

	private String stadium;
	private String city;

	//constructors

	public Location() {
		stadium = "";
		city = "";
	}

	public Location(String stadium, String city) {
		
		Checkers.check("Not allowed values", stadium!=null && city!=null);
		this.stadium = stadium;
		this.city = city;
	}

	public Location(String s) {
		
		String[] values = s.split(";");
		Checkers.check("Not valid format", values.length==2);
		
		String stadium = String.valueOf(values[0].replace("(", "").trim());
		String city = String.valueOf(values[1].replace(")", "").trim());
		
		this.stadium = stadium;
		this.city = city;
	}
	
	//getters and setters

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		
		Checkers.check("Not allowed values", stadium!=null);
		this.stadium = stadium;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		
		Checkers.check("Not allowed values", city!=null);
		this.city = city;
	}
	
	//string conversion
	
	@Override
	public String toString() {
		
		return "Location [stadium=" + stadium + ", city=" + city + "]";
	}
	
	//equality criteria
	
	@Override
	public int hashCode() {
		return Objects.hash(city, stadium);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return Objects.equals(city, other.city) && Objects.equals(stadium, other.stadium);
	}
	
	//natural order criteria
	
	public Integer compareTo(Location l) {
		
		int res = getCity().compareTo(l.getCity());
		if (res==0) {
			
			res = getStadium().compareTo(l.getStadium());
		}
		return res;
	}
}
