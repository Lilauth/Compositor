package compositor.pentagram;

/*
 * All this mess comes from here
 * http://www.javaworld.com/article/2077533/learn-java/java-tip-35--create-new-event-types-in-java.html
 * */

import java.awt.AWTEventMulticaster;
import java.util.EventListener;
public class PentagramEventMulticaster
			 	extends AWTEventMulticaster
			 	implements PentagramListener {
    
	protected PentagramEventMulticaster(EventListener a, EventListener b) {
        super(a, b);
    }
	
    public static PentagramListener add(PentagramListener a, PentagramListener b) {
        return (PentagramListener) addInternal(a, b);
    }
    
    public static PentagramListener remove(PentagramListener l, PentagramListener oldl) {
        return (PentagramListener) removeInternal(l, oldl);
    }
    
    protected static EventListener addInternal(EventListener a,
                                               EventListener b) {
        if (a == null) return b;
        if (b == null) return a;
        return new PentagramEventMulticaster(a, b);
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
	public void noteSelected(PentagramEvent e) {
		if (a != null) ((PentagramListener) a).noteSelected(e);
        if (b != null) ((PentagramListener) b).noteSelected(e);
	}
}
