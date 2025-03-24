package types;

/**
 * The colour enumeration
 * @author LABP Team
 */
public enum Colour{
	BLUE("B"),
	RED("R"),
	YELLOW("Y"),
	GREEN("G"),
	PINK("P"),
	ORANGE("O");
	
	private String rep;
	
	Colour(String s) {
		this.rep = s;
	}

	public String toString() {
		return this.rep;
	}

	public Colour[] colours() {
		return Colour.values();
	}

}
