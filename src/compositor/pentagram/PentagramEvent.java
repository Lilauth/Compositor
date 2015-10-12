package compositor.pentagram;
import java.awt.AWTEvent;

import compositor.enums.Note;


public class PentagramEvent extends AWTEvent {
	
	private static final long serialVersionUID = 1L;
	public static final int NOTE_SELECTED = AWTEvent.RESERVED_ID_MAX + 1;
	
	private Note note;
    
	public PentagramEvent(Pentagram source, int id, Note note) {
        super(source, id);
        this.note = note;
    }
    
	public Note getNote(){
		return note;
	}
}
