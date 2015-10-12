package compositor.enums;


public enum Note {
	DO("C"),
	RE("D"),
	MI("E"),
	FA("F"),
	SOL("G"),
	LA("A"),
	SI("B");
	
	private String symbol;
	
	Note(String symbol){
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
	
	public String getImageFileName(){
		return String.format("../images/%s.png", name().toLowerCase());
	}
	
}
