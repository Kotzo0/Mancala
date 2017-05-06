import java.awt.Color;

/**
 * Concrete strategy; simply chooses a color for a mancala board
 * @author Justin Benassi
 *
 */
public class BoardStyleA implements BoardStyle{
	/**
	 * sets the board color to red
	 * @return the color for the board, Red in this case
	 */
	public Color setColor() {
		return Color.RED;
	}
}
