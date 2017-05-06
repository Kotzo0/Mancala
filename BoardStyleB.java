import java.awt.Color;

/**
 * Concrete strategy; simply chooses a color for a mancala board
 * @author Justin Benassi
 *
 */
public class BoardStyleB implements BoardStyle{

	/**
	 * sets the board color to blue
	 * @return the color for the board, Blue in this case
	 */
	public Color setColor() {
		return Color.BLUE;
	}
}
