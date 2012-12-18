import java.util.Arrays;


public class Agent {
	private final static int HISTORY = 0;
	private final static int FUTURE = 1;
	
	/** calculate output for this many previous characters */
	private final int mMemorySize;
	
	/** number of different types of taps */
	private final int mPossibleInputs;
	private final int mPossibleOutputs = 128; // assume ASCII
	
	/** contains the last-entered taps */
	private int mMemory[];
	/** points to the last-entered tap in mMemory[] */
	private int mCurrentInput;
	
	private int mSamples;
	
	/** 
	 * Training data, frequency of taps and preceding/future taps.
	 * 
	 * If input i is given, the nth previous tap was key k on input p this many times:
	 *     mSampleCounts[HISTORY][i][n][p][k]
	 * 
	 * n = 0, p = i means that i mapped to k that many times.
	 * 
	 * s/previous/later/ for mSampleCounts[FUTURE]
	 */
	private double mSampleCounts[][][][][];
	private double mSampleTotals[][][];
	
	/** mOutputProbs[nth prev input][key] = P(nth prev input == key)*/
	private double mOutputProbs[][];
	
	public Agent(int possibleInputs, int memorySize) {
		mPossibleInputs = possibleInputs;
		mMemorySize = memorySize;
		
		mMemory = new int[memorySize];
		mCurrentInput = 0;
		mSamples = 0;
		
		mSampleCounts = new double[2][mPossibleInputs][mMemorySize][mPossibleInputs][mPossibleOutputs];
		mSampleTotals = new double[mPossibleInputs][mMemorySize][mPossibleInputs];
		mOutputProbs = new double[mMemorySize][mPossibleOutputs];
		
		// probabilities are just multiplied, need a positive, non-zero value here
		for(double[] inner : mOutputProbs) {
			Arrays.fill(inner, 1.0);
		}
	}
	
	public void train(int input[], String sequence) {
		char chars[] = sequence.toCharArray();
		
		if(chars.length != input.length) {
			System.err.println("input sizes don't match: " + sequence);
		}
		
		for(int index = 0; index < input.length; index++) {
			int currentTap = input[index];
			
			for(int previous = 0; previous <= index; previous++) {
				int previousTap = input[previous];
				char previousChar = chars[previous];
				int indexDiff = index - previous;
				
				if(indexDiff >= mMemorySize) {
					break;
				}
				
				mSampleCounts[HISTORY][currentTap][indexDiff][previousTap][previousChar] = 1;
				mSampleCounts[FUTURE][currentTap][indexDiff][previousTap][chars[index]] = 1;
				mSampleTotals[currentTap][indexDiff][previousTap] = 1;
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
		for(int currentTap = 0; currentTap < mPossibleInputs; currentTap++) {
			for(int indexDiff = 0; indexDiff < mMemorySize; indexDiff++) {
				for(int previousTap = 0; previousTap < mPossibleInputs; previousTap++) {
					for(int previousChar = 0; previousChar < mPossibleOutputs; previousChar++) {
						mSampleCounts[HISTORY][currentTap][indexDiff][previousTap][previousChar] += uncertainty;
						mSampleCounts[FUTURE][currentTap][indexDiff][previousTap][previousChar] += uncertainty;
					}
					
					mSampleTotals[currentTap][indexDiff][previousTap] += uncertainty;
				}
			}
		}
	}
	
	/**
	 * Simple exponential preference
	 */
	public void preferNearby() {
		for(int currentTap = 0; currentTap < mPossibleInputs; currentTap++) {
			for(int indexDiff = 0; indexDiff < mMemorySize; indexDiff++) {
				for(int previousTap = 0; previousTap < mPossibleInputs; previousTap++) {
					for(int previousChar = 0; previousChar < mPossibleOutputs; previousChar++) {
						double power = -indexDiff;
						mSampleCounts[HISTORY][currentTap][indexDiff][previousTap][previousChar] *= Math.pow(2, power);
						mSampleCounts[FUTURE][currentTap][indexDiff][previousTap][previousChar] *= Math.pow(2, power);
					}
					
				}
			}
		}
	}
	
	private double calculateHistoryProbability(int latestTap, int nthPrevTap, int previousTap, char expectedChar) {
		double total = mSampleTotals[latestTap][nthPrevTap][previousTap];
		double matching = mSampleCounts[HISTORY][latestTap][nthPrevTap][previousTap][expectedChar];
		
		return matching / total;

	}
	
	private double calculateFutureProbability(int latestTap, int nthFutureTap, int previousTap, char expectedChar) {
		double total = mSampleTotals[latestTap][nthFutureTap][previousTap];
		double matching = mSampleCounts[FUTURE][latestTap][nthFutureTap][previousTap][expectedChar];
		
		return matching / total;

	}
	
	private void updateOutputProbs(int input) {
		// reset probabilities of current output array
		Arrays.fill(mOutputProbs[mCurrentInput], 1.0);
		
		// update probabilities on up to mostPrevIndex previous inputs
		for(int prevIndex = 0; prevIndex < mSamples; prevIndex++) {
			int inputIndex = (mMemorySize + mCurrentInput - prevIndex) % mMemorySize;
			
			for(char possibleChar = 0; possibleChar < mPossibleOutputs; possibleChar++) {
				mOutputProbs[inputIndex][possibleChar] *= calculateHistoryProbability(input, prevIndex, mMemory[inputIndex], possibleChar);
				mOutputProbs[mCurrentInput][possibleChar] *= calculateFutureProbability(input, prevIndex, mMemory[inputIndex], possibleChar);
			}
			
		}
	}
	
	public void reset() {
		mSamples = 0;
	}
	
	public void writeConversions(char output[]) {
		for(int prevIndex = 0; prevIndex < mMemorySize; prevIndex++) {
			int inputIndex = (mMemorySize + mCurrentInput - prevIndex) % mMemorySize;
			
			double mostLikelyProb = 0;
			char mostLikelyChar = 0;
			
			for(char possibleChar = 0; possibleChar < mPossibleOutputs; possibleChar++) {
				double currentProb = mOutputProbs[inputIndex][possibleChar];
				if(currentProb > mostLikelyProb) {
					mostLikelyProb = currentProb;
					mostLikelyChar = possibleChar;
				}
				
			}
			
			output[prevIndex] = mostLikelyChar;
		}
	}
	
	public void sendInput(int input) {
		mCurrentInput = (mCurrentInput + 1) % mMemorySize;
		mMemory[mCurrentInput] = input;
		mSamples = Math.min(mSamples + 1, mMemorySize);
		
		updateOutputProbs(input);
		
	}
	
	public void sendInput(int input[]) {
		for(int i : input) {
			sendInput(i);
		}
	}
	
}
