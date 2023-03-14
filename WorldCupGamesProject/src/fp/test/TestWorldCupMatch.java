package fp.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fp.types.Location;
import fp.types.WorldCupMatch;

public class TestWorldCupMatch {
	public static void main(String[] args) {
		//WorldCupMatch w = new WorldCupMatch("1930;13 Jul 1930 - 15:00;Group 1;Pocitos;Montevideo;France;4;1;Mexico; ;4444;3;0;LOMBARDI Domingo (URU);CRISTOPHE Henry (BEL);REGO Gilberto (BRA);201;1096;FRA;MEX;6,26");
		Location l = new Location("Pocitos","Monteviedo");
		List<String> list = new ArrayList<String>();
		WorldCupMatch m = new WorldCupMatch(1930,LocalDateTime.of(1930, 07, 12, 15, 0), "Group 1" ,l, "France" , 4, 1, "Mexico", "", 4444, 3, 0, list , 201, 1096, "FRA", "MEX", 6.26);
		WorldCupMatch n = new WorldCupMatch(LocalDateTime.of(1930, 07, 12, 15, 0), "Group 4", "USA", 3, 0, "Belgium", 1090);
		System.out.println(m.toString());
		m.setHomeTeamGoals(2);
		m.setAwayTeamGoals(2);
		m.setHalfTimeHomeGoals(1);
		m.setHalfTimeAwayGoals(0);
		m.setHomeTeamInitials("FRN");
		m.setAwayTeamInitials("MXC");
		System.out.println(m.toString());
		System.out.println(m.shortFormat());
		System.out.println(m.hashCode());
		System.out.println(m.equals(n));
		System.out.println(m.compareTo(n));
		m.addReferee("LOMBARDI Domingo (URU)");
		System.out.println(m.toString());
		m.removeReferee();
		System.out.println(m.toString());
	}
}
