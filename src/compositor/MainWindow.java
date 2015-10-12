package compositor;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.jfugue.player.Player;

import compositor.enums.Duration;
import compositor.melody.Melody;
import compositor.pentagram.Pentagram;
import compositor.pentagram.PentagramEvent;
import compositor.pentagram.PentagramListener;

public class MainWindow implements PentagramListener {	
	
	private Frame frame = new Frame("Laboratorio de Software");
	
	private Panel topPanel = new Panel();
	private Panel centerPanel = new Panel();
	private Panel bottomPanel = new Panel();
	
	private Panel buttonsPanel = new Panel();
	private Panel durationsPanel = new Panel();

	public Melody melody = new Melody();
	private Button closeButton = new Button("Cerrar");
	private Button updateButton = new Button("Actualizar");
	private Button removeSoundButton = new Button("Borrar");
	private Button playButton = new Button("Escuchar");
	private Duration currentDuration = Duration.NEGRA;
	private Pentagram pentagram = new Pentagram(this);
	private TextField melodyTF = new TextField();
	
	public void start() {
		
		topPanel.setLayout(new BorderLayout());
		removeSoundButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				melody.removeLastSound();
				melodyTF.setText(melody.toString());
				pentagram.repaint();
			}
		});
		buttonsPanel.add(removeSoundButton);
		
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				melody.play();
			}
		});
		buttonsPanel.add(playButton);
		
		topPanel.add(buttonsPanel, BorderLayout.NORTH);
		
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				melody.updateFromString(melodyTF.getText());
			}
		});
		
		for (final Duration duration: Duration.values()){
			JButton button = new JButton();
			button.setIcon(new ImageIcon(getClass().getResource(duration.getImageFileName())));
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					currentDuration = duration;
				}
			});
			durationsPanel.add(button);			
		}
		
		pentagram.addListener(this);
		melody.addListener(pentagram);
	
		
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(pentagram, BorderLayout.CENTER);
		centerPanel.add(durationsPanel, BorderLayout.SOUTH);

		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(melodyTF, BorderLayout.NORTH);
		bottomPanel.add(updateButton, BorderLayout.CENTER);
		bottomPanel.add(closeButton, BorderLayout.SOUTH);
		
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setSize(850, 220);
	}
	
	public static void main(String[] args) {
		MainWindow myWindow = new MainWindow();
		myWindow.start();
	}

	@Override
	public void noteSelected(PentagramEvent e) {
		Sound sound = new Sound(e.getNote(), currentDuration);
		melody.addSound(sound);
		melodyTF.setText(melody.toString());
		(new Player()).play(sound.getSymbol());
		
	}
}

