import sequence.Sequence;
import sequence.Sequence.Unit;
import sequence.Sequences;


public class Main {
	
	public static void main(String[] args) {
		Trainer trainer = new Trainer();
		trainer.suggest("the", new int[] {4, 5, 3});
		trainer.suggest("them", new int[] {4, 5, 3, 5});
		
		Sequence s = Sequences.fromAsciiString("this is a test.");
		
	}

}
