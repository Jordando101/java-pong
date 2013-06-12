
/**
 * Class that represents a paddle in the Pong game
 * 
 * @author Evan LaHurd
 * @version 1.0
 */
public class Paddle {
	
	/**
	 * the amount the paddle moves with each button press
	 */
	private final int paddleSpeed;
	
	/**
	 * the y-position of the paddle
	 */
	private int paddleY;
	
	/**
	 * the width and height of the paddle
	 */
	private int paddleWidth, paddleHeight;
	
	/**
	 * The paddle constructor
	 * 
	 * @param paddleSpeed The amount the paddle moves with each button press
	 * @param paddleY The y-position of the paddle
	 * @param paddleWidth The width of the paddle
	 * @param paddleHeight The height of the paddle
	 */
	public Paddle(int paddleSpeed, int paddleY, int paddleWidth, int paddleHeight) {
		
		this.paddleSpeed = paddleSpeed;
		this.paddleY = paddleY;
		this.paddleWidth = paddleWidth;
		this.paddleHeight = paddleHeight;
		
	}
	
	/**
	 * Getter for the y-position of the paddle
	 * 
	 * @return The y-position of the paddle
	 */
	public int getPaddleY() {
		return paddleY;
	}
	
	/**
	 * Setter for the y-position of the paddle
	 * 
	 * @param paddleY The new y-position of the paddle
	 */
	public void setPaddleY(int paddleY) {
		this.paddleY = paddleY;
	}
	
	/**
	 * Getter for paddle speed
	 * 
	 * @return The amount the paddle moves with each button press
	 */
	public int getPaddleSpeed() {
		return paddleSpeed;
	}
	
	/**
	 * Getter for paddle width
	 * 
	 * @return The width of the paddle
	 */
	public int getPaddleWidth() {
		return paddleWidth;
	}

	/**
	 * Getter for paddle height
	 * 
	 * @return The height of the paddle
	 */
	public int getPaddleHeight() {
		return paddleHeight;
	}
}
