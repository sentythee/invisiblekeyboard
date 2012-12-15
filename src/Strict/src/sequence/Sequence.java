package sequence;

import java.util.LinkedList;
import java.util.List;

public class Sequence {
	
	public List<Unit> parts = new LinkedList();
	
	public void addUnit(Unit unit) {
		parts.add(unit);
	}
	
	public static class Unit {
		public String word, terminator;
		
		public Unit(String word, String terminator) {
			System.out.println(word);
			this.word = word;
			this.terminator = terminator;
		}
	}

}
