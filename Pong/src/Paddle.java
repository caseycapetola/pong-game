import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Paddle extends JComponent implements Updatable
{
	private Rectangle rect = new Rectangle(0,0,10,40);
	private int dy;
	
	public Paddle(int x, int y)
	{
		this.setSize(new Dimension(11,41));
		this.setLocation(x,y);
		setFocusable(false);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)(g);
		g2.setColor(Color.BLACK);
		g2.fill(rect);
	}
	
	public void setDy(int dy)
	{
		this.dy = dy;
	}
	
	public int getDy()
	{
		return dy;
	}

	@Override
	public void updateY() 
	{
		this.setLocation(this.getX(), this.getY()+dy);
		
	}
	
	public void updateX()
	{
		
	}
}
