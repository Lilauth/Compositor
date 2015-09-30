
public class Sound {
	public Sound(Notas nota, Duracion duracion) {
		super();
		this.nota = nota;
		this.duracion = duracion;
	}
	private Notas nota;
	private Duracion duracion;
	
	public String getSymbol(){
		return nota.getSymbol() + duracion.getSymbol();
	}
}
