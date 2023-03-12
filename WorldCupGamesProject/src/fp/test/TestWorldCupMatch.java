package fp.test;

import java.time.LocalDate;

import fp.types.WorldCupMatch;

public class TestWorldCupMatch {
	public static void main(String[] args) {
		WorldCupMatch m =  new WorldCupMatch(LocalDate.now(), "Final", "Argentina", 3, 3, "France");
		WorldCupMatch n =  new WorldCupMatch(LocalDate.now(), "Semifinal","Benito Villamarín","Sevilla", "Argentina", 3, 3, "Holland", "Mateu Lahoz");
		System.out.println(m.shortFormat());
		System.out.println(m.toString());
		System.out.println(m.getRecent());
		System.out.println(m.equals(n));
		System.out.println(m.compareTo(n));
	}
}
