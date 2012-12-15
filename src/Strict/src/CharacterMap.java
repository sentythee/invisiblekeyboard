
public class CharacterMap {
	private int mPossibleInputs;
	private StatefulMap mWordMap;
	private StatefulMap mTerminatorMap;
	
	private StatefulMap mCurrentWordMap;
	private StatefulMap mCurrentTerminatorMap;
	
	public CharacterMap(int possibleInputs) {
		mPossibleInputs = possibleInputs;
		mCurrentWordMap = mWordMap = new StatefulMap();
		mCurrentTerminatorMap = mTerminatorMap = new StatefulMap();
	}
	
	private void putOption(StatefulMap map, String word, int input[], int offset) {
		int end = offset + word.length(); // only works for ASCII
		StatefulMap current = map;

		for(int i = offset; i < end; i++) {
			int in = input[i];
			
			if(current.next[in] == null) {
				current.next[in] = new StatefulMap();
			}
			
			current = current.next[in];
		}
		
		current.value = word;
	}
	
	public void putWord(String word, int input[], int offset) {
		putOption(mWordMap, word, input, offset);
	}
	
	public void putTerminator(String word, int input[], int offset) {
		putOption(mTerminatorMap, word, input, offset);
	}
	
	
	public String feed(int input) {
		StatefulMap nextWordMap = mCurrentWordMap.next[input];
		StatefulMap nextTerminatorMap = mCurrentTerminatorMap.next[input];

		// There are four cases
		
		// 1. This input isn't extending the current word or terminator
		if(nextWordMap == null && nextTerminatorMap == null) {
			// must be the current word + terminator
			String result = mCurrentWordMap.value + mTerminatorMap.value;
			
			// reset everything
			mCurrentWordMap = mWordMap;
			mCurrentTerminatorMap = mTerminatorMap;
			
			// input wasn't used... use it now
			return result + feed(input);
		}
		
		// 2. This input is definitely part of the terminator
		if(nextWordMap == null && nextTerminatorMap != null) {
			// must be the current word
			String result = mCurrentWordMap.value;
			
			// reset the word map, next input can only be the start of
			// a new word, or another terminator character
			mCurrentWordMap = mWordMap;
			
			// update the terminator map, the next character may be
			// another terminator character
			mCurrentTerminatorMap = nextTerminatorMap;
			
			return result;
		}
		
		// 3. This input is definitely part of the current word
		if(nextWordMap != null && nextTerminatorMap == null) {
			// must be terminator if a word just ended
			String result = mCurrentTerminatorMap.value;
			
			// the word is ongoing, update the word map and move on
			mCurrentWordMap = nextWordMap;
			
			// can't be part of a terminator, reset that map
			mCurrentTerminatorMap = mTerminatorMap;
			
			return result;
		}
		
		// 4. This input can be anything
		if(nextWordMap != null && nextTerminatorMap != null) {
			// update both and move on
			mCurrentWordMap = nextWordMap;
			mCurrentTerminatorMap = nextTerminatorMap;
			
			return null;
		}
		
		// impossible
		return null;
	}
	
	public String query() {
		if(mCurrentWordMap != mWordMap) {
			return mCurrentWordMap.value;
		}
		
		return mCurrentTerminatorMap.value;
	}
	
	public void dump() {
		System.out.println(" == words == ");
		mWordMap.dump("");
		System.out.println(" == terminators == ");
		mTerminatorMap.dump("");
	}
	
	private class StatefulMap {
		StatefulMap[] next = new StatefulMap[mPossibleInputs];
		String value;
		
		public void dump(String input) {
			System.out.printf("%s: %s\n", input, value == null ? "" : value);
			
			
			for(int i = 0; i < next.length; i++) {
				if(next[i] != null) {
					next[i].dump(input + i);
				}
			}
		}
	}

}
