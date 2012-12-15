
public class Main {
	
	public static void main(String[] args) {
		Trainer trainer = new Trainer();
		trainer.suggest("the", new int[] {4, 5, 3});
		
		Agent agent = trainer.getAgent();
		System.out.println(agent.guess(new int[] {4, 5, 3}));
	}

}
