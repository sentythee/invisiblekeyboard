import java.util.Arrays;


public class Agent {
	/** calculate output for this many previous characters */
	private final int mMemorySize;
	
	/** number of different types of taps */
	private final int mPossibleInputs;
	private final int mPossibleOutputs = 128; // assume ASCII
	
	/** contains the last-entered taps */
	private int mMemory[];
	/** points to the last-entered tap in mMemory[] */
	private int mCurrentInput;
	/** current storing this many previous test inputs */
	private int mStoredInputs;
	
	/** 
	 * Training data, frequency of taps and preceding taps.
	 * 
	 * If input i is given, the nth previous tap was key k on input p this many times:
	 *     mSampleCounts[i][n][p][k]
	 * 
	 * n = 0, p = 1 means that i mapped to k that many times.
	 */
	private int mSampleCounts[][][][];
	private int mSampleTotals[][][];
	
	/** mOutputProbs[nth prev input][key] = P(nth prev input == key)*/
	private double mOutputProbs[][];
	
	public Agent(int possibleInputs, int memorySize) {
		mPossibleInputs = possibleInputs;
		mMemorySize = memorySize;
		
		mMemory = new int[memorySize];
		mCurrentInput = 0;
		
		mSampleCounts = new int[mPossibleInputs][mMemorySize][mPossibleInputs][mPossibleOutputs];
		mSampleTotals = new int[mPossibleInputs][mMemorySize][mPossibleInputs];
		mOutputProbs = new double[mMemorySize][mPossibleOutputs];
		
		// probabilities are just multiplied, need a positive, non-zero value here
		for(double[] inner : mOutputProbs) {
			Arrays.fill(inner, 1.0);
		}
	}
	
	public void train(int input[], String sequence) {
		char chars[] = sequence.toCharArray();
		
		for(int index = 0; index < input.length; index++) {
			int currentTap = input[index];
			
			for(int previous = 0; previous <= index; previous++) {
				int previousTap = input[previous];
				char previousChar = chars[previous];
				
				int memoryIndex = (index - previous) % mMemorySize;
				
				mSampleCounts[currentTap][memoryIndex][previousTap][previousChar] += 1;
				mSampleTotals[currentTap][memoryIndex][previousTap] += 1;
			}
		}
	}
	
	/**
	 * Add a fixed number to all sample categories.
	 * 
	 * Dumb method to ensure that no probabilities are zero while
	 * still favoring the combinations that came up while training.
	 * 
	 * @param uncertainty 
	 */
	public void addUncertainty(int uncertainty) {
		for(int currentTap = 0; currentTap < mSampleCounts.length; currentTap++) {
			for(int nthPrevIndex = 0; nthPrevIndex < mSampleCounts[0].length; nthPrevIndex++) {
				for(int previousTap = 0; previousTap < mSampleCounts[0][0].length; previousTap++) {
					for(int previousChar = 0; previousChar < mSampleCounts[0][0][0].length; previousChar++) {
						mSampleCounts[currentTap][nthPrevIndex][previousTap][previousChar] += 1;
					}
					
					mSampleTotals[currentTap][nthPrevIndex][previousTap] += mSampleCounts[0][0][0].length;
				}
			}
		}
	}
	
	private double calculateProbability(int latestTap, int nthPrevTap, int previousTap, char expectedChar) {
		int total = mSampleTotals[latestTap][nthPrevTap][previousTap];
		int matching = mSampleCounts[latestTap][nthPrevTap][previousTap][expectedChar];
		
		return (double) matching / total;

	}
	
	public char[] test(int input) {
		mMemory[mCurrentInput] = input;
		mStoredInputs = Math.min(mStoredInputs + 1, mMemorySize);
		
		char result[] = new char[mMemorySize];
		
		// reset probabilities of current output array
		Arrays.fill(mOutputProbs[mCurrentInput], 1.0);
		
		int mostPrevIndex = Math.min(mStoredInputs, mOutputProbs.length);
		
		// update probabilities on up to mostPrevIndex previous inputs
		for(int prevIndex = 0; prevIndex < mostPrevIndex; prevIndex++) {
			int inputIndex = (mMemorySize + mCurrentInput - prevIndex) % mMemorySize;
			
			double mostLikelyProb = 0;
			char mostLikelyChar = 0;
			
			for(char possibleChar = 0; possibleChar < mPossibleOutputs; possibleChar++) {
				mOutputProbs[inputIndex][possibleChar] *= calculateProbability(input, prevIndex, mMemory[inputIndex], possibleChar);
				
				double currentProb = mOutputProbs[inputIndex][possibleChar];
				if(currentProb > mostLikelyProb) {
					mostLikelyProb = currentProb;
					mostLikelyChar = possibleChar;
				}
				
			}
			
			result[prevIndex] = mostLikelyChar;
			
		}
		
		mCurrentInput = (mCurrentInput + 1) % mMemorySize;
		
		return result;
	}
	
}
