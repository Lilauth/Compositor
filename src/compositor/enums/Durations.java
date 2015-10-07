package compositor.enums;

/*
 * Hola. Tengo un problema con la conexión wirelss. Sólo estoy pudiendo acceder a internet a través de la conexión cableada pero el Wi-Fi no funciona. Intenté reiniciando el router pero lo que ocurrió fue que se resetaron las configuraciones de fábrica. La red dejó de llamarse como se llamaba y pasó a llamarse WebStar y sin contraseña. Sin embargo tampoco pude acceder a internet a traves de esta red wirelss WebStar. Entré al sitio de Fibertel para configurar la red pero después de un rato me dice que hubo un problema y que el formulario no se pudo enviar., que pruebe de nuevo más tarde.
 * */

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
		return String.format("../images/%s.png", name().toLowerCase());
	}
	
}
