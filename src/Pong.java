import java.awt.FontFormatException;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;


/**
 * The driver for the Pong game
 * 
 * @author Evan LaHurd
 * @version 1.0
 */
public class Pong {
	
	/**
	 * Main method that runs the game
	 * 
	 * @param args
	 * @throws FontFormatException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */
	public static void main(String[] args) throws FontFormatException, IOException, UnsupportedAudioFileException, LineUnavailableException {
		JFrame frame = new JFrame("Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new BallPanel());
		frame.pack();
		frame.setVisible(true);
	}
}