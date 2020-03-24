package scabblebabble.handlers.util;

public enum EnumLetter {

	//LETTER("LETTER", ID, SCORE, QUANTIY_START);
	BLANK("?", 0, 0, 2),
	A("A", 1, 1, 9),
	B("B", 2, 3, 2),
	C("C", 3, 3, 2),
	D("D", 4, 2, 4),
	E("E", 5, 1, 12),
	F("F", 6, 4, 2),
	G("G", 7, 2, 3),
	H("H", 8, 4, 2),
	I("I", 9, 1, 9),
	J("J", 10, 8, 1),
	K("K", 11, 5, 1),
	L("L", 12, 1, 4),
	M("M", 13, 3, 2),
	N("N", 14, 1, 6),
	O("O", 15, 1, 8),
	P("P", 16, 3, 2),
	Q("Q", 17, 10, 1),
	R("R", 18, 1, 6),
	S("S", 19, 1, 4),
	T("T", 20, 1, 6),
	U("U", 21, 1, 4),
	V("V", 22, 4, 2),
	W("W", 23, 4, 2),
	X("X", 24, 8, 1),
	Y("Y", 25, 4, 2),
	Z("Z", 26, 10, 1);	
	
	
	private String LETTER;
	private int ID;
	private int SCORE;
	private int QUANTITY;
	
	EnumLetter(String letterIn, int idIn, int scoreIn, int quantityIn) {
		this.LETTER = letterIn;
		this.ID = idIn;
		this.SCORE = scoreIn;
		this.QUANTITY = quantityIn;
	}
	
	public String letter() {
		return this.LETTER;
	}
	
	public int id() {
		return this.ID;
	}
	
	public int score() {
		return this.SCORE;
	}
	
	public int quantity() {
		return this.QUANTITY;
	}
	
	/**
	 * Iterates the values and returns an instance of the Enum with that id
	 * 
	 * @param idIn
	 * @return
	 */
	public EnumLetter getById(int idIn) {
		for (EnumLetter e : EnumLetter.values()) {
			if (e.id() == idIn) {
				return e;
			}
		}

		throw new ArrayIndexOutOfBoundsException();
	}
	
	/** 
	 * Iterates the values and returns an instance of the Enum with that letter
	 * 
	 * @param letterIn
	 * @return
	 */
	public EnumLetter getByLetter(String letterIn) {
		for (EnumLetter e : EnumLetter.values()) {
			if (e.letter() == letterIn) {
				return e;
			}
		}

		throw new ArrayIndexOutOfBoundsException();
	}
}
