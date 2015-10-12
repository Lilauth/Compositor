package compositor.pentagram;

import java.util.EventListener;

public interface PentagramListener extends EventListener {
	public void noteSelected(PentagramEvent e);
}
