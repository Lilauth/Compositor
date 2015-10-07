import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.List;

import org.jfugue.player.Player;
import org.jfugue.theory.Note;



public class VentanaPrincipal{	

	private Frame frame;
	private Button charly;
	private Button cerrar;
	private Notas notaActual;
	private Duracion duracionActual;
	List<Button> notas = new ArrayList<Button>();
	List<Sound> sounds = new ArrayList<Sound>();
	List<Button> duraciones = new ArrayList<Button>();
	private Button agregar;
	private Button sonar;
	
	public VentanaPrincipal() {
		frame = new Frame("Laboratorio de Software");
		
		for (Notas n: Notas.values()){
			Button button = new Button(n.name());
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					notaActual = n;
				}
			});
			notas.add(button);
		}
		
		for (Duracion d: Duracion.values()){
			Button button = new Button(d.name());
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					duracionActual = d;
				}
			});
			notas.add(button);
		}
		
		agregar = new Button("Agregar Sonido");
		agregar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sounds.add(new Sound(notaActual, duracionActual));
			}
		});
		
		sonar = new Button("Sonar");
		sonar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> symbols = new ArrayList<String>();
				for (Sound s: sounds){
					symbols.add(s.getSymbol());
				}
				(new Player()).play(String.join(" ", symbols));
			}
		});
		
		cerrar=new Button("Cerrar");
		cerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
	
	 public void mousePressed(MouseEvent e) {
	       System.out.println("Mouse pressed; # of clicks: ");
	    }
	
	public void comenzar() {
		Panel panelNotas = new Panel();
		Panel panelDuracion = new Panel();
		for (Button button: notas){
			frame.add(button);
		}
		for (Button button: duraciones){
			frame.add(button);
		}
		frame.setLayout(new GridLayout(2,7));
		frame.add(panelNotas);
		frame.add(panelDuracion);
		frame.add(cerrar, BorderLayout.WEST);
		frame.add(agregar);
		frame.add(sonar);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(700, 200);
	}
	
	public static void main(String[] args) {
		VentanaPrincipal miVentana = new VentanaPrincipal();
		miVentana.comenzar();
	}
        
	}

