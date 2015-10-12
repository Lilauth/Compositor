package compositor;

import java.awt.Choice;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Image;
import java.util.EnumMap;
import java.util.Map;

import compositor.enums.Durations;
import compositor.enums.Notes;

public class Pentagram extends JPanel implements MouseListener {
	
	private static Map<Notes, Integer> heightByNotes = new EnumMap<Notes, Integer>(Notes.class);
	static {
		heightByNotes.put(Notes.MI,55);
		heightByNotes.put(Notes.FA,50);
		heightByNotes.put(Notes.SOL,45);
		heightByNotes.put(Notes.LA,40);
		heightByNotes.put(Notes.SI,34);
		heightByNotes.put(Notes.DO,31);
		heightByNotes.put(Notes.RE,27);
	
	}
	private static final long serialVersionUID = 1L;
	public Image background,
				 gKey;
	private MainWindow window;
	public Pentagram(MainWindow window) {
		super();
		this.background = getImage("images/pentagram.png");
		this.gKey = getImage("images/clavesol.png");
		this.window = window;
		addMouseListener(this);
	}
	
	private Image getImage(String src){
		return new ImageIcon(getClass().getResource(src)).getImage();
	}
	
	private static Notes inferNoteByPosition(int y){
		Notes answer = null;
		int minDist = Integer.MAX_VALUE;
		for (Notes note : heightByNotes.keySet()) {
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
        for (Sound sound : window.melody.getSounds()){
        	g.drawImage(getImage(sound.getDuration().getImageFileName()), 100 + i * 20, heightByNotes.get(sound.getNote()) - 25, 25, 33, null);
        	i++;
        }
    }
	
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int y = e.getY();
		Notes note = inferNoteByPosition(y);
		if (note != null){
			window.noteChoosed(note);		
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

}