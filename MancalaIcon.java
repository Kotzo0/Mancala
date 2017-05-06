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
public class MancalaIcon implements Icon, ChangeListener {
	
	private Rectangle2D.Double mancala;
	private int width;
	private int height;
	private String name;
	private Player player;
	private int value;
	private MancalaModel model;

	/**
	 * constructor for a MancalaIcon
	 * @param x the origin of the icon
	 * @param y the y origin of the icon
	 * @param name the title of the pit
	 * @param player the player that the pit belongs to
	 * @param model the model that the pit listens to for changes
	 */
	public MancalaIcon(int x, int y,String name,Player player,MancalaModel model)
	{
		width = 150;
		height =200;
		mancala = new Rectangle2D.Double(x,y,width,height);
		this.name = name;
		this.player = player;
		value = 0;
		this.model = model;
	}

	@Override
	/**
	 * getter for icon height
	 * @return the value of the icons height
	 */
	public int getIconHeight() {
		return height;
	}

	@Override
	/**
	 * getter for icon width
	 * @return the value of the icons width
	 */
	public int getIconWidth() {
		return width *3/2;
	}
	
	/**
	 * setter for model
	 * @param model the mancala model you want to give to the pit
	 */
	public void setModel(MancalaModel model)
	{
		this.model = model;
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
		g2.draw(mancala);
		g2.drawString(name, x + 30 , getIconHeight());
		g2.drawString(value + "", x + 30 , getIconHeight() + y);
		if(model.getCurrentPlayer().equals(player))
		{
			g2.drawString("Current Player", x + 30 , 2*getIconHeight() + 20);
		}
		for(int i = 0; i < value; i++)
		{
			if (i < 10)
			g2.draw(new Ellipse2D.Double(x +20 + i*15,height, 15, 15));
			else if (i < 20) g2.draw(new Ellipse2D.Double(x+20 + (i-10)*15,height + 15, 15, 15));
			else if (i < 30) g2.draw(new Ellipse2D.Double(x+20 + (i-20)*15,height + 30, 15, 15));
			else if (i < 40) g2.draw(new Ellipse2D.Double(x+20 + (i-30)*15,height + 45, 15, 15));
			else if (i < 50) g2.draw(new Ellipse2D.Double(x+20 + (i-40)*15,height + 60, 15, 15));
		}
		
		c.repaint();
		
	}

	@Override
	/**
	 * listens for changes from model
	 */
	public void stateChanged(ChangeEvent e) {
		value = model.getMancalaValue(player);
		
	}

}
