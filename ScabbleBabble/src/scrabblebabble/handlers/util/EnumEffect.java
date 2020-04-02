package scrabblebabble.handlers.util;

public enum EnumEffect {
	
	EMPTY("L", 1),
	START("W", 2),
	DOUBLE_LETTER("L", 2),
	TRIPLE_LETTER("L", 3),
	DOUBLE_WORD("W", 2),
	TRIPLE_WORD("W", 3);
	
	String type;
	int level;
	
	EnumEffect(String typeIn, int levelIn) {
		this.type = typeIn;
		this.level = levelIn;
	}
	
	public String type() {
		return this.type;
	}
	
	public int level() {
		return this.level;
	}
	
}
