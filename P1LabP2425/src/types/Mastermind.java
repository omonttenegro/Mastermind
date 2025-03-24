package types;

import java.util.Random;

/**
 * 
 */
public class Mastermind {

	public static final int MAX_TRIALS = 20;
	private int size;
	private Colour[] colours;
	private Code secretCode;
	private Random rd;
	private int numberOfTrials;
	private int storedTrials;
	private int score;
	private Code[] attempts;
	private int[] b;
	private int[] a;

	/**
	 * 
	 */
	public static String EOL = System.lineSeparator();

	/**
	 * 
	 * @param seed
	 * @param size
	 * @param colours
	 */

	public Mastermind(int seed, int size, Colour[] colours) {
		this.size = size;
		this.colours = colours.clone();
		this.rd = new Random(seed);
		this.score = 0;
		this.attempts = new Code[MAX_TRIALS];
		this.a = new int[MAX_TRIALS];
		this.b = new int[MAX_TRIALS];
		this.storedTrials = 0;
		this.numberOfTrials = 0;

		Colour[] secretColours = new Colour[size];
		for (int i = 0; i < size; i++) {
			secretColours[i] = colours[rd.nextInt(colours.length)];
		}
		this.secretCode = new Code(secretColours);
	}

	/**
	 * 
	 */
	public void startNewRound() {
		numberOfTrials = 0;
		storedTrials = 0;
		Colour[] secretColours = new Colour[size];
		for (int i = 0; i < size; i++) {
			secretColours[i] = colours[rd.nextInt(colours.length)];
		}
		secretCode = new Code(secretColours);

		attempts = new Code[MAX_TRIALS];
		a = new int[MAX_TRIALS];
		b = new int[MAX_TRIALS];

	}

	/**
	 * 
	 * @param trial
	 */
	public void play(Code trial) {

		for (int i = 0; i < storedTrials; i++) {
			if (attempts[i].equalCodes(trial)) {
				numberOfTrials++;
				return;
			}
		}
		int[] result = trial.howManyCorrect(secretCode);
		a[storedTrials] = result[0];
		b[storedTrials] = result[1];
		attempts[storedTrials] = trial;
		numberOfTrials++;
		storedTrials++;
		if (isRoundFinished()) {
			updateScore();
		}

	}

	/**
	 * 
	 * @return
	 */
	public boolean wasSecretRevealed() {
		if (storedTrials == 0 || attempts[storedTrials - 1] == null)
			return false;
		return attempts[storedTrials - 1].equalCodes(secretCode);
	}

	/**
	 * 
	 * @return
	 */
	public int getNumberOfTrials() {
		return numberOfTrials;
	}

	/**
	 * 
	 * @return
	 */
	public int score() {
		return score;

	}

	/**
	 * 
	 */
	public void updateScore() {
		if (wasSecretRevealed()) {
			if (numberOfTrials <= 2)
				score += 100;
			else if(numberOfTrials <= 5)
					score += 50;
			else score += 20;

		} else if (score > 20)
				score -= 20;
		else score = 0;
			
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isRoundFinished() {
		return wasSecretRevealed() || numberOfTrials == MAX_TRIALS;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Number of Trials = ").append(numberOfTrials).append(EOL);
		sb.append("Score = ").append(score).append(EOL);
		if (!wasSecretRevealed()) {
			sb.append("[?, ?, ?, ?]").append(EOL);
			sb.append(EOL);
		} else {
			sb.append(secretCode.toString() + EOL);
			sb.append(EOL);
		}
		int indiceInicial = 0;
		if (storedTrials > 10) {
			indiceInicial = storedTrials - 10;
		}
		for (int i = indiceInicial; i < storedTrials; i++) {
			sb.append(attempts[i].toString());
			sb.append("    " + a[i] + " " + b[i]).append(EOL);
		}
		return sb.toString();
	}

}
