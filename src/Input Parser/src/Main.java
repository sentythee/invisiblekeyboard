

public class Main {
	
	public static void main(String[] args) {
		Agent agent = new Agent(10, 20);
		
		agent.train(new int[] {2, 3, 4, 9}, "asd ");
		agent.train(new int[] {2, 3, 2, 9}, "xyz ");
		agent.train(new int[] {6}, "q");
		agent.addUncertainty(1);
		
		agent.sendInput(new int[] {2, 3, 4, 3, 2, 9, 6});
		
		// writes exactly 20 character to the buffer :/
		char[] result = new char[20];
		agent.writeConversions(result);
		
		// should be "asdyz q" backwards
		System.out.println(new String(result));
		
		
	}

}
