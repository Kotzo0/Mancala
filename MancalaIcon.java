import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
import javax.swing.event.*;

public class MancalaIcon implements Icon, ChangeListener {
	
	private Rectangle2D.Double mancala;
	private int width;
	private int height;
	private String name;
	private Player player;
	private int value;
	private MancalaModel model;

	
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
	public int getIconHeight() {
		return height;
	}

	@Override
	public int getIconWidth() {
		return width *3/2;
	}
	
	public void setModel(MancalaModel model)
	{
		this.model = model;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(mancala);
		g2.drawString(name, x + 30 , getIconHeight());
		g2.drawString(value + "", x + 30 , getIconHeight() + y);
		if(model.getCurrentPlayer().equals(player))
		{
			g2.drawString("Current Player", x + 30 , 2*getIconHeight() + 20);
		}
		
		c.repaint();
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		value = model.getMancalaValue(player);
		
	}

}
