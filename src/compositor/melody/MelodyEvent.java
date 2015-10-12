package compositor.melody;
import java.awt.AWTEvent;

import compositor.melody.Melody;
import compositor.Sound;


public class MelodyEvent extends AWTEvent {
	
	private static final long serialVersionUID = 1L;
	public static final int SOUND_ADDED = AWTEvent.RESERVED_ID_MAX + 1;
	public static final int LAST_SOUND_REMOVED = SOUND_ADDED + 1;
	
	private Sound sound;
    
	public MelodyEvent(Melody source, int id, Sound sound) {
        super(source, id);
        this.sound = sound;
    }
    
	public Sound getSound(){
		return sound;
	}
}
