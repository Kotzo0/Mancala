import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
import javax.swing.event.*;

/**
 * class for drawing a mancala pit
 * @author Justin Benassi, Warren Liang
 *
 */
public class PitIcon implements Icon, ChangeListener {

	private Rectangle2D.Double pit;
	private int width;
	private int height;
	private String name;
	private MancalaModel model;
	private int pitNumber;
	private Player pitPlayer;
	private int value;
	private BoardStyle style;

	/**
	 * constructor for a PitIcon
	 * @param x the origin of the icon
	 * @param y the y origin of the icon
	 * @param name the title of the pit
	 * @param number the number of this pit
	 * @param player the player that the pit belongs to
	 * @param model the model that the pit listens to for changes
	 */
	public PitIcon(int x,int y,String name,int number,Player player, MancalaModel model)
	{
		width = 100;
		height = 100;
		pit = new Rectangle2D.Double(x,height,width,height);
		this.name = name;
		pitNumber = number;
		pitPlayer = player;
		this.model = model;
		value = 0;
	}

	/**
	 * set the model for this pit
	 * @param model the model you want to assign for this pit
	 */
	public void setModel(MancalaModel model)
	{
		this.model = model;
		value = model.getPitValue(pitPlayer, pitNumber); 
	}

	@Override
	/**
	 * getter for height
	 * @return value of height
	 */
	public int getIconHeight() {

		return height;
	}

	@Override
	/**
	 * getter for width
	 * @return value of width
	 */
	public int getIconWidth() {

		return width;
	}
	/**
	 * sets the style of this pit
	 * @param style the style that this board will use
	 */
	public void setStyle(BoardStyle style) {
		this.style = style;
	}

	@Override
	/**
	 * Paints the mancala icon, should only be called by containing component
	 * @param c the component that is calling the function
	 * @param g the graphics context
	 * @param x the x origin point
	 * @param y the y origin point
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(pit);
		g2.setColor(style.setColor());
		g2.fill(pit);
		g2.setColor(Color.BLACK);
		g2.drawString(name, getIconWidth()/2 - 10, getIconHeight());
		g2.drawString(value + "",getIconWidth()/2,3*y/2+getIconHeight());
		g2.setColor(Color.BLACK);
		for(int i = 0; i < value; i++)
		{
			if (i < 5)
			g2.draw(new Ellipse2D.Double(x + i*15,height, 15, 15));
			else if (i < 10) g2.draw(new Ellipse2D.Double(x + (i-5)*15,height + 15, 15, 15));
			else g2.draw(new Ellipse2D.Double(x + (i-10)*15,height + 30, 15, 15));
		}
		c.repaint();

	}


	@Override
	/**
	 * listens for changes from model
	 */
	public void stateChanged(ChangeEvent arg0) {
		value = model.getPitValue(pitPlayer, pitNumber);

	}

}
