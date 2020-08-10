import java.awt.Color;
import javax.swing.JFrame;

public class Driver {
	public static void main(String[] args) {
		//Creates the panel and renders it to the screen
		Panel panel = new Panel(800, 800, Color.black);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);  
		frame.pack();
		frame.setVisible(true); 
	}
}
