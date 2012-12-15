import sequence.Sequence;
import sequence.Sequence.Unit;
import sequence.Sequences;


public class Trainer {
	private CharacterMap mMap = new CharacterMap(10);
	
	public void suggest(int[] input, Sequence expectedOutput) {
		int index = 0;
		
		for(Unit unit : expectedOutput.parts) {
			mMap.putWord(unit.word, input, index);
			index += unit.word.length(); // only works for ASCII
			
			mMap.putTerminator(unit.terminator, input, index);
			index += unit.terminator.length();
		}
	}
	
	public void suggest(int[] input, String expectedOutput) {
		suggest(input, Sequences.fromAsciiString(expectedOutput));
	}
	
	public Agent getAgent() {
		return new Agent(mMap);
	}

}
