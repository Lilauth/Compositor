package compositor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfugue.player.Player;

import compositor.enums.*;


public class Melody {
	
	private static Map<String, Notes> noteBySymbol = new HashMap<String, Notes>();
	static {
		for (Notes note: Notes.values()){
			noteBySymbol.put(note.getSymbol(), note);
		}
	}
	private static Map<String, Durations> durationBySymbol = new HashMap<String, Durations>();
	static {
		for (Durations duration: Durations.values()){
			durationBySymbol.put(duration.getSymbol(), duration);
		}
	}
	private List<Sound> sounds = new ArrayList<Sound>();

	
	public static List<Sound> parse(String str){
		List<Sound> sounds = new ArrayList<Sound>();
		for (String soundStr: str.split(" ")){
			sounds.add(new Sound(
				noteBySymbol.get(soundStr.substring(0, 0)),
				durationBySymbol.get(soundStr.substring(1, 1))
			));
		}
		return sounds;
	}
	
	public Melody(){
		super();
	}
	public Melody(String str){
		for (Sound sound : parse(str)){
			sounds.add(sound);			
		}		
	}
	
	public void addSound(Sound sound){
		sounds.add(sound);
	}
	
	public void addSound(Notes note, Durations duration){
		addSound(new Sound(note, duration));
	}
	
	public void play(){
		(new Player()).play(this.toString());
	}
	
	@Override
	public String toString(){
		List<String> symbols = new ArrayList<String>();
		for (Sound s: sounds){
			symbols.add(s.getSymbol());
		}
		return String.join(" ", symbols);
	}
	
	public List<Sound> getSounds(){
		return sounds;
	}

	public void removeLastSound() {
		if (!sounds.isEmpty()){
			sounds.remove(sounds.size() - 1);			
		}
	}
}
