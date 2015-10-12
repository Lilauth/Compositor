package compositor;
import compositor.enums.Duration;
import compositor.enums.Note;


public class Sound {
	public Sound(Note nota, Duration duracion) {
		super();
		this.nota = nota;
		this.duracion = duracion;
	}
	private Note nota;
	private Duration duracion;
	
	public String getSymbol(){
		return nota.getSymbol() + duracion.getSymbol();
	}
	
	public Note getNote(){
		return nota;
	}
	
	public Duration getDuration(){
		return duracion;
	}
}
