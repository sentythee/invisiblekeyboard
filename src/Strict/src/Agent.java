
public class Agent {
	
	private CharacterMap mMap;
	
	public Agent(CharacterMap map) {
		mMap = map;
	}
	
	public String feed(int combination[]) {
		StringBuffer result = new StringBuffer();
		
		for(int input : combination) {
			String nextWord = mMap.feed(input);
			if(nextWord != null) {
				result.append(nextWord);
			}
		}
		
		return result.toString();
	}
	
	public String query() {
		return mMap.query();
	}
	
	public void dump() {
		mMap.dump();
	}

}
