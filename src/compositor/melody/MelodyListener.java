package compositor.melody;
import java.util.EventListener;

public interface MelodyListener extends EventListener {
	public void soundAdded(MelodyEvent e);
	public void lastSoundRemoved(MelodyEvent e);
}