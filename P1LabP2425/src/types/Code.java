package types;

import java.util.Arrays;


/**
 * 
 */
public class Code {

	private Colour[] code;

	/**
	 * 
	 * @param code 
	 */
	public Code(Colour[] code) {
		this.code = code.clone(); 
	}

	/**
	 * 
	 * @return
	 */
	public Colour[] getCode() {
		return code.clone();
	}

	/**
	 * 
	 * @return
	 */
	public Code copy() {
		return new Code(this.code);
	}

	/**
	 * 
	 * @return
	 */
	public int getLength() {
		return this.code.length;
		
	}

	/**
	 * 
	 * @param other
	 * @return
	 */
	public int[] howManyCorrect(Code other) {
		int[] clues = new int[2];
		boolean[] usedInThis = new boolean[code.length];
		for (int i = 0; i < code.length; i++) {
			if (this.getCode()[i] == other.getCode()[i]) {
				clues[0]++;
				usedInThis[i] = true;

			}
		}
		for (int i = 0; i < code.length; i++) {
			if (!usedInThis[i]) {
				for (int j = 0; j < code.length; j++) {
					if (!usedInThis[j] && this.getCode()[j] == other.getCode()[i]) {
						usedInThis[j] = true;
						clues[1]++;
						break;
					}
				}
			}
		}
		return clues;
	}

	/**
	 * 
	 * @param other
	 * @return
	 */
	public boolean equalCodes(Code other) {
		return Arrays.equals(this.getCode(), other.getCode());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(this.getCode()[0]);
		for (int i = 1; i < code.length; i++) {
			sb.append(", ").append(this.getCode()[i]);
		}
		sb.append("]");
		return sb.toString();
	}

}
