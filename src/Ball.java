
/**
 * Class that represents the ball in the Pong game
 * 
 * @author Evan LaHurd
 * @version 1.0
 */
public class Ball {
	
	/**
	 * the x-position of the ball
	 */
	private int ballX;
	
	/**
	 * the y-position of the ball
	 */
	private int ballY;
	
	/**
	 * the amount the ball moves in the x-direction each timer tick
	 */
	private int incrementX;
	
	/**
	 * the amount the ball moves in the y-direction each timer tick
	 */
	private int incrementY;
	
	/**
	 * the size of the ball
	 */
	private int ballSize;
	
	/**
	 * The constructor for the ball
	 * 
	 * @param ballX The x-position of the ball
	 * @param ballY The y-position of the ball
	 * @param incrementX The amount the ball moves in the x-direction each timer tick
	 * @param incrementY The amount the ball moves in the y-direction each timer tick
	 * @param ballSize The size of the ball
	 */
	public Ball(int ballX, int ballY, int incrementX, int incrementY, int ballSize) {
		
		this.ballX = ballX;
		this.ballY = ballY;
		this.incrementX = incrementX;
		this.incrementY = incrementY;
		this.ballSize = ballSize;
		
	}
	
	/**
	 * Getter for the x-position of the ball
	 * 
	 * @return The x-position of the ball
	 */
	public int getBallX() {
		return ballX;
	}
	
	/**
	 * Setter for the x-position of the ball
	 * 
	 * @param ballX The new x-position of the ball
	 */
	public void setBallX(int ballX) {
		this.ballX = ballX;
	}
	
	/**
	 * Getter for the y-position of the ball
	 * 
	 * @return The y-position of the ball
	 */
	public int getBallY() {
		return ballY;
	}
	
	/**
	 * Setter for the y-position of the ball
	 * 
	 * @param ballX The new y-position of the ball
	 */
	public void setBallY(int ballY) {
		this.ballY = ballY;
	}
	
	/**
	 * Getter for the amount the ball moves in the x-direction
	 * each timer tick
	 * 
	 * @return The amount the ball moves in the x-direction
	 * each timer tick
	 */
	public int getIncrementX() {
		return incrementX;
	}
	
	/**
	 * Setter for the amount the ball moves in the x-direction
	 * each timer tick
	 * 
	 * @param incrementX The new amount the ball moves in the x-direction
	 * each timer tick
	 */
	public void setIncrementX(int incrementX) {
		this.incrementX = incrementX;
	}
	
	/**
	 * Getter for the amount the ball moves in the y-direction
	 * each timer tick
	 * 
	 * @return The amount the ball moves in the y-direction
	 * each timer tick
	 */
	public int getIncrementY() {
		return incrementY;
	}
	
	/**
	 * Setter for the amount the ball moves in the y-direction
	 * each timer tick
	 * 
	 * @param incrementY The new amount the ball moves in the y-direction
	 * each timer tick
	 */
	public void setIncrementY(int incrementY) {
		this.incrementY = incrementY;
	}
	
	/**
	 * Getter for the size of the ball
	 * 
	 * @return The size of the ball
	 */
	public int getBallSize() {
		return ballSize;
	}

}
