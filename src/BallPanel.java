
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The panel that shows the Pong game
 * 
 * @author Evan LaHurd
 * @version 1.0
 */
public class BallPanel extends JPanel {
	
	/**
	 * the timer that checks certain conditions every 50 ms
	 */
	private Timer timer;
	
	/**
	 * the ball that will be bounced back and forth between players
	 */
	private Ball ball;

	/**
	 * the players' paddles
	 */
	private Paddle paddle1, paddle2;
	
	
	/**
	 * the players' scores
	 */
	private int score1, score2;
	
	/**
	 * A flag to keep track of whether or not the game is active. 
	 * Used to display different messages
	 */
	private boolean active = false;
	
	/**
	 * A flag to keep track of whether or not player 1 has scored.
	 * Used to display different messages
	 */
	private boolean player1Scored = false;
	
	/**
	 * A flag to keep track of whether or not player 2 has scored.
	 * Used to display different messages
	 */
	private boolean player2Scored = false;
	
	/**
	 * Flags to check if a key is down
	 */
	private boolean wDown = false;
	private boolean sDown = false;
	private boolean upDown = false;
	private boolean downDown = false;
	
	/**
	 * the font to be used for the score and message strings
	 */
	private Font messageFont;
	
	/**
	 * the sound clip that plays a blip every time the ball is hit
	 * by a paddle
	 */
	private Clip soundClip;
	
	
	/**
	 * constructor that initializes everything for the ball panel 
	 * 
	 * @throws FontFormatException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */
	public BallPanel() throws FontFormatException, IOException, UnsupportedAudioFileException, LineUnavailableException {
		
		final File fontFile = new File("Fonts/coolvetica rg.ttf");
		final Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
		messageFont = font.deriveFont(20f);
		
		//sets up sound clip
		File f = new File("Sounds/pong blip.wav");
		AudioInputStream ais = AudioSystem.getAudioInputStream(f);
		soundClip = AudioSystem.getClip();
	    soundClip.open(ais);
		
		//ball with x, y, incrementX, incrementY, and size
		ball = new Ball(250, 150, 10, 10, 10);
		//paddle 1 with move distance, starting Y position, paddle width,
		//and paddle height
		paddle1 = new Paddle(20, 160, 10, 80);
		//paddle 2 with move distance, starting Y position, paddle width,
		//and paddle height
		paddle2 = new Paddle(20, 160, 10, 80);
		
		score1 = 0;
		score2 = 0;
		
		setPreferredSize(new Dimension(500,440));
		setBackground(Color.black);
		
		this.addKeyListener(new MyKeyListener());
		this.setFocusable(true);
		
		timer = new Timer(50, new TimerListener());
	}	
	
	/**
	 * plays sound clip when called
	 */
	public void play() {

		  //sets sound clip back to start and plays it
	      if (soundClip != null) {
	        soundClip.setFramePosition(0);
	        soundClip.start();
	      }
	    }
	
	/**
	 * Paints everything shown on the panel
	 * 
	 * @param g Graphics component used for painting to the panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(messageFont);
		g.setColor(Color.white);
		
		g.fillRect(0, paddle1.getPaddleY(), paddle1.getPaddleWidth(), paddle1.getPaddleHeight());
		g.fillRect(490, paddle2.getPaddleY(), paddle2.getPaddleWidth(), paddle2.getPaddleHeight());
		g.fillOval(ball.getBallX(), ball.getBallY(), ball.getBallSize(), ball.getBallSize());
		
		//if the game is started, display the score
		if (active) {
			g.setColor(Color.red);
			g.drawString("Player 1: " + score1, 210, 50);
			g.setColor(Color.blue);
			g.drawString("Player 2: " + score2, 210, 75);
		}
		
		
		if(!active) {
			//before the game is started, prompt the player to start the game
			if (!player1Scored && !player2Scored) {
				g.setColor(Color.white);
				g.drawString("Press SPACE to start!", 150, 50);
			} else if (player1Scored) {
				g.setColor(Color.white);
				g.drawString("Player 1 Scored! Press SPACE to continue.", 75, 50);
			} else if (player2Scored) {
				g.setColor(Color.white);
				g.drawString("Player 2 Scored! Press SPACE to continue.", 75, 50);
			}
		}
	} 
	
	
	/**
	 * moves the ball a certain amount each timer tick
	 */
	public void moveBall() {
		int ballX = ball.getBallX();
		int ballY = ball.getBallY();
		
		ball.setBallX(ballX + ball.getIncrementX());
		ball.setBallY(ballY + ball.getIncrementY());

	}
	
	/**
	 * checks to see if the ball has collided with player 1's paddle 
	 * and adjusts the ball's direction accordingly
	 */
	public void checkLeftPaddleCollision() {
		
		if(ball.getBallX() == paddle1.getPaddleWidth() && ball.getBallY() >= paddle1.getPaddleY()
		   && ball.getBallY() <= paddle1.getPaddleY() + paddle1.getPaddleHeight() && ball
		   .getIncrementX() == -10 && ball.getIncrementY() == -10) {
			
			ball.setIncrementX(10);
			ball.setIncrementY(-10);
	
			play();
			
		}
		
		if(ball.getBallX() == paddle1.getPaddleWidth() && ball.getBallY() >= paddle1.getPaddleY() 
		   && ball.getBallY() <= paddle1.getPaddleY() + paddle1.getPaddleHeight() && ball
		   .getIncrementX() == -10 && ball.getIncrementY() == 10) {
			
			ball.setIncrementX(10);
			ball.setIncrementY(10);
			
			play();

		}
		
	}
	
	/**
	 * checks to see if the ball has collided with player 2's paddle 
	 * and adjusts the ball's direction accordingly
	 */
	public void checkRightPaddleCollision() {
		if(ball.getBallX() + ball.getBallSize() == this.getWidth() - paddle2.getPaddleWidth() && ball.getBallY() >= paddle2.getPaddleY()
		   && ball.getBallY() <= paddle2.getPaddleY() + paddle2.getPaddleHeight() && ball
		   .getIncrementX() == 10 && ball.getIncrementY() == 10) {
			
			ball.setIncrementX(-10);
			ball.setIncrementY(10);
			
			play();

		}
		
		if(ball.getBallX() + ball.getBallSize() == this.getWidth() - paddle2.getPaddleWidth() && ball.getBallY() >= paddle2.getPaddleY()
				&& ball.getBallY()<= paddle2.getPaddleY() + paddle2.getPaddleHeight() && ball
				.getIncrementX() == 10 && ball.getIncrementY() == -10) {
			
			ball.setIncrementX(-10);
			ball.setIncrementY(-10);
			
			play();

		}
	
	}

	/**
	 * checks to see if the ball has collided with the top or bottom wall 
	 * and adjusts the ball's direction accordingly
	 */
	public void checkWallCollision() {
		
		//if the ball hits the bottom wall and is moving south-east, adjust
		//direction to north-east
		if(ball.getBallY() + ball.getBallSize() == this.getHeight() 
		   && ball.getIncrementX() == 10 && ball.getIncrementY() == 10) {
			
			ball.setIncrementX(10);
			ball.setIncrementY(-10);
			
		}
		
		//if the ball hits the bottom wall and is moving south-west, adjust
		//direction to north-west
		if(ball.getBallY() + ball.getBallSize() == this.getHeight()
		   && ball.getIncrementX() == -10 && ball.getIncrementY() == 10) {
			
			ball.setIncrementX(-10);
			ball.setIncrementY(-10);
			
		}
		
		//if the ball hits the top wall and is moving northwest, adjust
		//direction to south-west
		if(ball.getBallY() == 0 && ball.getIncrementX() == -10 && ball.getIncrementY() == -10) {
			
			ball.setIncrementX(-10);
			ball.setIncrementY(10);
			
		}
		
		//if the ball hits the top wall and is moving north-east, adjust
		//direction to south-east
		if(ball.getBallY() == 0 && ball.getIncrementX() == 10 && ball.getIncrementY() == -10) {
			
			ball.setIncrementX(10);
			ball.setIncrementY(10);
			
		}
		
	}
	
	/**
	 * checks to see if a player has scored
	 */
	public void checkScore() {
		
		//ball hits right wall; player 1 has scored
		if(ball.getBallX() + ball.getBallSize() == 500) {
			
			score1++;
			ball.setBallX(250); 
			ball.setBallY(150);
			ball.setIncrementX(10);
			ball.setIncrementY(10);
			
			timer.stop();
			active = false;
			player1Scored = true;
			repaint();
			
		} 
		
		//ball hits left wall; player 2 has scored
		if(ball.getBallX() == 0) {
			
			score2++;
			ball.setBallX(250);
			ball.setBallY(150);
			ball.setIncrementX(10);
			ball.setIncrementY(10);
			
			timer.stop();
			active = false;
			player2Scored = true;
			repaint(); 
			
		}
	}
	
	/**
	 * Action Listener for the game timer
	 * 
	 * @author Evan LaHurd
	 * @version 1.0
	 */
	private class TimerListener implements ActionListener {
		
		/**
		 * On each timer tick, moves the ball, checks for scores
		 * and collisions, and repaints the panel
		 * 
		 * @param e ActionEvent that represents a tick of the timer
		 */
		public void actionPerformed(ActionEvent e) {
			
			moveBall();
			checkFlags();
			checkLeftPaddleCollision();
			checkRightPaddleCollision();
			checkWallCollision();
			checkScore();
			repaint();
			
		}
	}
	
	public void checkFlags() {
		
		int paddle1Y = paddle1.getPaddleY();
		int paddle1Speed = paddle1.getPaddleSpeed();
		int paddle2Y = paddle2.getPaddleY();
		int paddle2Speed = paddle2.getPaddleSpeed();
		
		if (upDown) {
			if (paddle2.getPaddleY() > 0 && paddle2.getPaddleY() <= 360) {
				paddle2.setPaddleY(paddle2Y - paddle2Speed);
				repaint(); 
			}
		} else {
			
			repaint();
		}
		
		if (downDown) {
			if (paddle2.getPaddleY() >= 0 && paddle2.getPaddleY() < 360) {
				paddle2.setPaddleY(paddle2Y + paddle2Speed);
				repaint(); 
			}
		} else {
			
			repaint();
		}
		
		if (wDown) {
			if (paddle1.getPaddleY() > 0 && paddle1.getPaddleY() <= 360) {
				paddle1.setPaddleY(paddle1Y - paddle1Speed);
				repaint();
			}
		} else {
			
			repaint();
		}
		
		if (sDown) {
			if (paddle1.getPaddleY() >= 0 && paddle1.getPaddleY() < 360) {
				paddle1.setPaddleY(paddle1Y + paddle1Speed);
				repaint();
			}
		} else {
			
			repaint();
		}
	}
	/**
	 * KeyAdapter to handle keyboard key presses
	 * 
	 * @author Evan LaHurd
	 * @version 1.0
	 */
	private class MyKeyListener extends KeyAdapter {
		
		/**
		 * @param ke KeyEvent that represents the press of a key
		 */
		public void keyPressed(KeyEvent ke) {
			
			
			switch(ke.getKeyCode()) {
				
				//starts game, and un-pauses after each score
				case KeyEvent.VK_SPACE:
					timer.start();
					active = true;
					player1Scored = false;
					player2Scored = false;
					break;
					
				//moves player 2's paddle up
				case KeyEvent.VK_UP:
					
					upDown = true;
					
					break;
				
				//moves player 2's paddle down
				case KeyEvent.VK_DOWN:
					
					downDown = true;
					
					break;
				
				//moves player 1's paddle up
				case KeyEvent.VK_W:
					
					wDown = true;
					
					break;
				
				//moves player 1's paddle down
				case KeyEvent.VK_S:
					
					sDown = true;
					
					break;
				
			}
		}
		
		public void keyReleased(KeyEvent ke) {
			
			switch(ke.getKeyCode()) {
				
				//moves player 2's paddle up
				case KeyEvent.VK_UP:
					
					upDown = false;
					
					break;
				
				//moves player 2's paddle down
				case KeyEvent.VK_DOWN:
					
					downDown = false;
					
					break;
				
				//moves player 1's paddle up
				case KeyEvent.VK_W:
					
					wDown = false;
					
					break;
				
				//moves player 1's paddle down
				case KeyEvent.VK_S:
					
					sDown = false;
					
					break;
			
			}
		}
		
	}

}