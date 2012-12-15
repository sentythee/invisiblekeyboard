

public class Main {
	
	public static void main(String[] args) {
		Trainer trainer = new Trainer();
		trainer.suggest(new int[] {4, 5, 3, 6, 9, 4, 5, 3, 5, 9, 4, 5, 3, 2, 2, 8}, "the, them three.");
		Agent agent = trainer.getAgent();
		
		// type "them, three"... "three" will not be shown yet
		System.out.println(agent.feed(new int[] {4, 5, 3, 5, 6, 9, 4, 5, 3, 2, 2}));
		
		// show the current word if there's a match (should be "three")
		System.out.println(agent.query());
		
		// print a terminator to finalize and print "three"
		System.out.println(agent.feed(new int[] {8}));
		
		agent.dump();
	}

}
