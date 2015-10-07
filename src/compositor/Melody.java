package compositor;
import java.util.ArrayList;
import java.util.List;

import org.jfugue.player.Player;

import compositor.enums.*;


public class Melody {
	private List<Sound> sounds = new ArrayList<Sound>();
	
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

	public void removeLastSound() {
		if (!sounds.isEmpty()){
			sounds.remove(sounds.size() - 1);			
		}
	}
}
