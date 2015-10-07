package compositor;
import compositor.enums.Durations;
import compositor.enums.Notes;


public class Sound {
	public Sound(Notes nota, Durations duracion) {
		super();
		this.nota = nota;
		this.duracion = duracion;
	}
	private Notes nota;
	private Durations duracion;
	
	public String getSymbol(){
		return nota.getSymbol() + duracion.getSymbol();
	}
}
