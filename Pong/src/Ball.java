import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

import javax.swing.JComponent;

public class Ball extends JComponent implements Updatable
{
	private Ellipse2D.Double circle = new Ellipse2D.Double(0,0,15,15);
	private int dx, dy;
	
	public Ball(int x, int y)
	{
		this.setSize(new Dimension(16,16));
		this.setLocation(x,y);
		setFocusable(false);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)(g);
		g2.setColor(Color.BLACK);
		g2.fill(circle);
	}
	
	public void setDx(int dx)
	{
		this.dx = dx;
	}
	
	public int getDx()
	{
		return dx;
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
	public void updateX() 
	{
		this.setLocation(this.getX()+dx, this.getY());
		
	}
	
	public void updateY()
	{
		this.setLocation(this.getX(), this.getY()+dy);
	}
}
