package compositor.pentagram;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Image;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import compositor.MainWindow;
import compositor.Sound;
import compositor.enums.Note;
import compositor.melody.MelodyEvent;
import compositor.melody.MelodyListener;

public class Pentagram extends JPanel implements MouseListener, MelodyListener {
	
	private static Map<Note, Integer> heightByNotes = new EnumMap<Note, Integer>(Note.class);
	static {
		heightByNotes.put(Note.MI,55);
		heightByNotes.put(Note.FA,50);
		heightByNotes.put(Note.SOL,45);
		heightByNotes.put(Note.LA,40);
		heightByNotes.put(Note.SI,34);
		heightByNotes.put(Note.DO,31);
		heightByNotes.put(Note.RE,27);
	
	}
	private static final long serialVersionUID = 1L;
	public Image background;
	public Image gKey;
	private PentagramListener listener;
	private List<Sound> sounds = new ArrayList<Sound>();
	public Pentagram(MainWindow window) {
		super();
		this.background = getImage("/compositor/images/pentagram.png");
		this.gKey = getImage("/compositor/images/clavesol.png");
		addMouseListener(this);
	}
	
	private Image getImage(String src){
		return new ImageIcon(getClass().getResource(src)).getImage();
	}
	
	private static Note inferNoteByPosition(int y){
		Note answer = null;
		int minDist = Integer.MAX_VALUE;
		for (Note note : heightByNotes.keySet()) {
			int dist = Math.abs(y - heightByNotes.get(note));
			if (dist < minDist){
				minDist = dist;
				answer = note;
			}
		}
		return answer;
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background, 10, 0, getWidth(), this.background.getHeight(this), null);
        g.drawImage(this.gKey, 20, 13, 20, 50, null);
        int i = 0;
        for (Sound sound : sounds){
        	g.drawImage(
    			getImage("/compositor/"+sound.getDuration().getImageFileName()),
    			100 + i * 20,
    			heightByNotes.get(sound.getNote()) - 25,
    			25,
    			33,
    			null
        	);
        	i++;
        }
    }
	
	public synchronized void addListener(PentagramListener listener){
		this.listener = PentagramEventMulticaster.add(this.listener, listener);
	}
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int y = e.getY();
		Note note = inferNoteByPosition(y);
		if (note != null && listener != null){
			listener.noteSelected(new PentagramEvent(this, PentagramEvent.NOTE_SELECTED, note));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void soundAdded(MelodyEvent e) {
		sounds.add(e.getSound());
		repaint();
	}

	@Override
	public void lastSoundRemoved(MelodyEvent e) {
		sounds.remove(sounds.size() - 1);
		repaint();
	}

}