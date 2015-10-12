package compositor.enums;

public enum Durations {
	REDONDA("w"),
	BLANCA("h"),
	NEGRA("q"),
	CORCHEA("i"),
	SEMICORCHEA("s"),
	FUSA("t"),
	SEMIFUSA("x");
	
	private String symbol;
	
	Durations(String symbol){
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
	
	public String getImageFileName(){
		return String.format("images/%s.png", name().toLowerCase());
	}
	
}
