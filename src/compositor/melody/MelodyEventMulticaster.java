package compositor.melody;

import java.awt.AWTEventMulticaster;
import java.util.EventListener;

public class MelodyEventMulticaster
                extends AWTEventMulticaster
                implements MelodyListener {
    
    protected MelodyEventMulticaster(EventListener a, EventListener b) {
        super(a, b);
    }
    
    public static MelodyListener add(MelodyListener a, MelodyListener b) {
        return (MelodyListener) addInternal(a, b);
    }
    
    public static MelodyListener remove(MelodyListener l, MelodyListener oldl) {
        return (MelodyListener) removeInternal(l, oldl);
    }
    
    protected static EventListener addInternal(EventListener a,
                                               EventListener b) {
        if (a == null) return b;
        if (b == null) return a;
        return new MelodyEventMulticaster(a, b);
    }
    protected EventListener remove(EventListener oldl) {
        if (oldl == a) return b;
        if (oldl == b) return a;
        EventListener a2 = removeInternal(a, oldl);
        EventListener b2 = removeInternal(b, oldl);
        if (a2 == a && b2 == b) return this;
        return addInternal(a2, b2);
    }

	@Override
	public void soundAdded(MelodyEvent e) {
		if (a != null) ((MelodyListener) a).soundAdded(e);
        if (b != null) ((MelodyListener) b).soundAdded(e);
	}

	@Override
	public void lastSoundRemoved(MelodyEvent e) {
		if (a != null) ((MelodyListener) a).lastSoundRemoved(e);
        if (b != null) ((MelodyListener) b).lastSoundRemoved(e);
	}
}
