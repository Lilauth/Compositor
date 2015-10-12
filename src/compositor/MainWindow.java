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

import compositor.enums.Notes;
import compositor.enums.Durations;

public class MainWindow {	
	
	private Frame frame = new Frame("Laboratorio de Software");
	
	private Panel topPanel = new Panel();
	private Panel centerPanel = new Panel();
	private Panel bottomPanel = new Panel();
	
	private Panel buttonsPanel = new Panel();
	private Panel durationsPanel = new Panel();

	public Melody melody = new Melody();
	private Button closeButton = new Button("Cerrar");
	private Button removeSoundButton = new Button("Borrar");
	private Button playButton = new Button("Sonar");
	private Durations currentDuration = Durations.NEGRA;
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
		
		
		for (final Durations duration: Durations.values()){
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
		
		
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(pentagram, BorderLayout.CENTER);
		centerPanel.add(durationsPanel, BorderLayout.SOUTH);

		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(melodyTF, BorderLayout.NORTH);
		bottomPanel.add(closeButton, BorderLayout.CENTER);
		
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setSize(850, 215);
	}
	
	public void noteChoosed(Notes note){
		Sound sound = new Sound(
			note,
			currentDuration
		);
		melody.addSound(sound);
		melodyTF.setText(melody.toString());
		pentagram.repaint();
		(new Player()).play(sound.getSymbol());
	}
	
	public static void main(String[] args) {
		MainWindow myWindow = new MainWindow();
		myWindow.start();
	}
}

