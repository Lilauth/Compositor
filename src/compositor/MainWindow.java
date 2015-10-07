package compositor;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import compositor.enums.Notes;
import compositor.enums.Durations;

public class MainWindow {	
	private Melody melody = new Melody();
	private Frame frame;
	private Label melodyLabel = 
			new Label("..................................................................................................");
	private Button closeButton;
	private Button addSoundButton;
	private Button removeSoundButton;
	private Button playButton;
	private Panel bottomPanel = new Panel();
	private Panel widgetsPanel = new Panel();
	private Panel visualizationPanel = new Panel();
	private Choice notesChoice = new Choice();
	private Choice durationsChoice = new Choice();
	
	public MainWindow() {
		
		frame = new Frame("Laboratorio de Software");
		
		for (final Notes n: Notes.values()){
			notesChoice.add(n.name());
		}
		
		for (final Durations n: Durations.values()){
			durationsChoice.add(n.name());
		}
		
		addSoundButton = new Button("Agregar Sonido");
		addSoundButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				melody.addSound(
					Notes.valueOf(notesChoice.getSelectedItem()),
					Durations.valueOf(durationsChoice.getSelectedItem())
				);
				melodyLabel.setText(melody.toString());
			}
		});
		
		removeSoundButton = new Button("Borrar");
		removeSoundButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				melody.removeLastSound();
				melodyLabel.setText(melody.toString());
			}
		});
		
		playButton = new Button("Sonar");
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				melody.play();
			}
		});
		
		closeButton = new Button("Cerrar");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
	
	public void start() throws IOException, InterruptedException {
		widgetsPanel.add(notesChoice);
		widgetsPanel.add(durationsChoice);
		widgetsPanel.add(addSoundButton);
		widgetsPanel.add(removeSoundButton);
		widgetsPanel.add(playButton);
		
		visualizationPanel.setLayout(new FlowLayout());
		visualizationPanel.add(melodyLabel);

		bottomPanel.add(closeButton, BorderLayout.CENTER);
		
		frame.add(widgetsPanel, BorderLayout.NORTH);
		frame.add(visualizationPanel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setSize(700, 200);
	}
	
	public static void main(String[] args) throws IOException {
		MainWindow myWindow = new MainWindow();
		try {
			myWindow.start();			
		} 
		catch (  Exception e) {
			e.printStackTrace();
		}
	}
}

