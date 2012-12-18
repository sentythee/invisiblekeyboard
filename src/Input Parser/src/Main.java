

public class Main {
	
	private final static int MEMORY = 50;
	
	public static void main(String[] args) {
		Agent agent = new Agent(10, MEMORY);
		
		agent.train(new int[] {4, 5, 3, 9, 2, 5, 6, 4, 6, 9, 4, 3, 7, 2, 6, 9, 4, 7, 3, 9}, "the quick brown fox ");
		agent.train(new int[] {3, 5, 7, 9, 6, 3, 9, 5, 4}, "who is he");
		agent.train(new int[] {3, 5, 2, 4, 9, 2, 9, 3, 7, 7, 4, 4}, "what a world");
		agent.train(new int[] {2, 3, 9, 4, 2, 4, 9, 2, 3, 9, 6, 9, 4, 6, 5, 6, 4, 9, 4, 3, 6, 6}, "as far as i could tell");
		agent.train(new int[] {}, "");
		
		agent.preferNearby(3);
		agent.addUncertainty(0.1);
		
		// "the fox is as quick as a fox"
		print(agent, new int[] {4, 5, 3, 9, 4, 7, 3, 9, 6, 3, 9, 2, 3, 9, 2, 5, 6, 4, 6, 9, 2, 3, 9, 2, 9, 4, 7, 3, 9});
		
	}
	
	private static void print(Agent agent, int[] input) {
		agent.sendInput(input);
		
		char[] result = new char[MEMORY];
		agent.writeConversions(result);
		
		for(int i = input.length-1; i >= 0; i--) {
			System.out.print(result[i]);
		}
		
		System.out.println();
		agent.reset();
	}

}
