

public class Main {
	
	public static void main(String[] args) {
		Agent agent = new Agent(10, 20);
		agent.train(new int[] {2, 3, 4, 9}, "asd ");
		agent.train(new int[] {2, 3, 2, 9}, "xyz ");
		agent.train(new int[] {6}, "q");
		agent.addUncertainty(1);
		
		char[] res = agent.test(2);
		res = agent.test(3);
		res = agent.test(2);
		res = agent.test(2);
		res = agent.test(3);
		res = agent.test(4);
		System.out.println(res[0]);
		System.out.println(res[1]);
		System.out.println(res[2]);
		System.out.println(res[3]);
		System.out.println(res[4]);
		System.out.println(res[5]);
		
		
	}

}
