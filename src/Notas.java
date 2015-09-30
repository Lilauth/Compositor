
public enum Notas {
	DO("C"),
	RE("D"),
	MI("E"),
	FA("F"),
	SOL("G"),
	LA("A"),
	SI("B");
	
	private String symbol;
	
	Notas(String symbol){
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}	

}
