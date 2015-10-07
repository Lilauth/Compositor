package compositor;

import java.awt.Choice;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import compositor.enums.Notes;

public class Pentagram extends JPanel implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	public Image background;
	private Choice notesChoice;
	public Pentagram(Choice notesChoice) {
		super();
		this.notesChoice = notesChoice;
		this.setBackground();
		addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
 
        super.paintComponent(g);
        if (this.background != null) {
            g.drawImage(this.background, 10, 20, 800, 200, null);
        }
    }

	public void setBackground() {
        this.background = new ImageIcon(this.getClass().getResource("images/pentagram.png")).getImage();
        repaint();
    }

	@Override
	public void mousePressed(MouseEvent e) {
		int y = e.getY();
		Notes[] notes = Notes.values();
		int min = 69;
		int max = 173;
		int spaceBetweenLines = (max - min) / 8;
		int spaceBetweenNotes = spaceBetweenLines / 2;
		for (int i = 0; i <= 10; i++){
			int noteHeight = min + spaceBetweenLines * (i - 1);
			Notes note = notes[(notes.length - i + 4) % notes.length];
			if (Math.abs(y - noteHeight) <= spaceBetweenNotes){
				notesChoice.select(note.name());
				break;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}