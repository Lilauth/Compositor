import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ImageTool extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImageTool(URL url) {
		ImageIcon icon = new ImageIcon(url);
		JLabel label = new JLabel(icon, JLabel.CENTER);
		add(label);
		
		System.out.println("Image width: " + icon.getIconWidth());
		System.out.println("Image height: " + icon.getIconHeight());
	}
	
}

public class Test {		
	
		 public static void main(String[] args) throws MalformedURLException {
			 ImageIcon image = new ImageIcon("images/clavesol.png");
			 
			 JFrame frame = new JFrame();
			 frame.setSize(100, 100);
			 frame.setLayout(new GridLayout(3, 1));
			 JPanel panel = new JPanel();
			 JButton buttonSol = new JButton("-------------------------------------------");
			 JButton buttonLa = new JButton("                                            ");
			 JButton buttonSi = new JButton("-------------------------------------------");
			 panel.add(buttonSol);
			 panel.add(buttonLa);
			 panel.add(buttonSi);
			 frame.add(panel);
			// frame.pack();
			 frame.setVisible(true);
			 
		 }

}
