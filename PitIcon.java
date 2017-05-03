import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
import javax.swing.event.*;

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
	
	public void setModel(MancalaModel model)
	{
		this.model = model;
		value = model.getPitValue(pitPlayer, pitNumber); 
	}

	@Override
	public int getIconHeight() {
		
		return height;
	}

	@Override
	public int getIconWidth() {
		
		return width;
	}
	
	public void setStyle(BoardStyle style) {
		this.style = style;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(pit);
		g2.setColor(style.setColor());
		g2.fill(pit);
		g2.setColor(Color.BLACK);
		g2.drawString(name, getIconWidth()/2 - 10, getIconHeight());
		g2.drawString(value + "",getIconWidth()/2,y/2+getIconHeight());
		c.repaint();
		
	}


	@Override
	public void stateChanged(ChangeEvent arg0) {
		value = model.getPitValue(pitPlayer, pitNumber);
		
	}

}
