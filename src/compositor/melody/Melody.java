package compositor.melody;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfugue.player.Player;

import compositor.Sound;
import compositor.enums.*;


public class Melody {
	
	private static Map<String, Note> noteBySymbol = new HashMap<String, Note>();
	static {
		for (Note note: Note.values()){
			noteBySymbol.put(note.getSymbol(), note);
		}
	}
	private static Map<String, Duration> durationBySymbol = new HashMap<String, Duration>();
	static {
		for (Duration duration: Duration.values()){
			durationBySymbol.put(duration.getSymbol(), duration);
		}
	}
	private List<Sound> sounds = new ArrayList<Sound>();
	private MelodyListener listener;

	
	public static List<Sound> parse(String str){
		List<Sound> sounds = new ArrayList<Sound>();
		for (String soundStr: str.split(" ")){
			sounds.add(new Sound(
				noteBySymbol.get(soundStr.substring(0, 1)),
				durationBySymbol.get(soundStr.substring(1, 2))
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
		if (listener != null){
			listener.soundAdded(new MelodyEvent(this, MelodyEvent.SOUND_ADDED, sound));			
		}
	}
	
	public void addSound(Note note, Duration duration){
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
			if (listener != null){
				listener.lastSoundRemoved(new MelodyEvent(this, MelodyEvent.LAST_SOUND_REMOVED, sounds.get(sounds.size() - 1)));			
			}
			sounds.remove(sounds.size() - 1);		
		}
	}
	
	public synchronized void addListener(MelodyListener listener){
		this.listener = MelodyEventMulticaster.add(this.listener, listener);
	}

	public void updateFromString(String str) {
		while (!sounds.isEmpty()){
			removeLastSound();
		}
		for (Sound sound: parse(str)){
			addSound(sound);
		}
	}

}
