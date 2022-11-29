import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class PongMain extends JFrame implements ActionListener
{
	private ArrayList<Updatable> stuff;
	private ArrayList<JLabel> scores;
	private int[] nums;
	int numHits;
	
	public PongMain()
	{
		stuff = new ArrayList<Updatable>();
		scores = new ArrayList<JLabel>();
		nums = new int[2];
		numHits = 0;
		
		setBounds(100,100,800,600);
		setLayout(null);
		setTitle("Pong");
		setResizable(false);
		setPreferredSize(new Dimension(800,600));
		pack();
		
		this.getContentPane().setBackground(Color.PINK);
		
		Paddle p1 = new Paddle(100,this.getContentPane().getHeight()/2);
		stuff.add(p1);
		
		Paddle p2 = new Paddle(this.getContentPane().getWidth()-100,this.getContentPane().getHeight()/2);
		stuff.add(p2);
		
		Ball ball = new Ball(this.getContentPane().getWidth()/2,this.getContentPane().getHeight()/2);
		stuff.add(ball);
		
		((Ball)(stuff.get(2))).setDx(0);
		((Ball)(stuff.get(2))).setDy(0);
		
		
		JLabel score1 = new JLabel(""+nums[0]);
		score1.setBounds(50,50,30,30);
		score1.setFont(new Font("Sansserif", Font.BOLD, 24));
		this.add(score1);
		scores.add(score1);
		
		JLabel score2 = new JLabel(""+nums[1]);
		score2.setBounds(this.getContentPane().getWidth()-50,50,30,30);
		score2.setFont(new Font("Sansserif", Font.BOLD, 24));
		this.add(score2);
		scores.add(score2);
		
		JLabel title = new JLabel("PONG");
		title.setBounds(this.getContentPane().getWidth()/2-70,50,140,50);
		title.setFont(new Font("monospaced", Font.ITALIC, 30));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVisible(true);
		this.add(title);
		scores.add(title);
		
		JLabel otherText = new JLabel("Press Space to Start");
		otherText.setBounds(this.getContentPane().getWidth()/2-120,this.getContentPane().getHeight()-100,240,50);
		otherText.setFont(new Font("monospaced", Font.ITALIC, 18));
		otherText.setHorizontalAlignment(JLabel.CENTER);
		otherText.setVisible(true);
		this.add(otherText);
		scores.add(otherText);
		
		JLabel face = new JLabel("");
		face.setBounds(this.getContentPane().getWidth()/2-120,this.getContentPane().getHeight()-200,240,50);
		face.setFont(new Font("monospaced", Font.ITALIC, 18));
		face.setHorizontalAlignment(JLabel.CENTER);
		face.setVisible(false);
		this.add(face);
		scores.add(face);
		
		for(Updatable c : stuff)
		{
			this.add((JComponent)(c));
		}
		
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) 
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==e.VK_W)
				{
					((Paddle)(stuff.get(0))).setDy(-3);
				}
				if(e.getKeyCode()==e.VK_S)
					((Paddle)(stuff.get(0))).setDy(3);
				
				if(e.getKeyCode()==e.VK_UP)
					((Paddle)(stuff.get(1))).setDy(-3);
				if(e.getKeyCode()==e.VK_DOWN)
					((Paddle)(stuff.get(1))).setDy(3);
				if(e.getKeyCode()==e.VK_SPACE)
				{
					if(((int)(2*Math.random()))==0)
					{
						((Ball)(stuff.get(2))).setDx(1);
					}
					else
					{
						((Ball)(stuff.get(2))).setDx(-1);
					}
					((Ball)(stuff.get(2))).setDy(((int)(10*Math.random()))/2-2);
					while(((Ball)(stuff.get(2))).getDy()==0)
					{
						((Ball)(stuff.get(2))).setDy(((int)(10*Math.random()))/2-2);
					}
					scores.get(2).setVisible(false);
					scores.get(3).setVisible(false);
					scores.get(4).setVisible(false);
					for(int i=0; i<nums.length; i++)
					{
						nums[i]=0;
						scores.get(i).setText(""+nums[i]);
					}
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(e.getKeyCode()==e.VK_W)
					((Paddle)(stuff.get(0))).setDy(0);
				if(e.getKeyCode()==e.VK_S)
					((Paddle)(stuff.get(0))).setDy(0);
				
				if(e.getKeyCode()==e.VK_UP)
					((Paddle)(stuff.get(1))).setDy(0);
				if(e.getKeyCode()==e.VK_DOWN)
					((Paddle)(stuff.get(1))).setDy(0);
				
			}
			
		});
		Timer t1 = new Timer(1, this);
		t1.start();
		
		
		
		setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) 
	{
		new PongMain();
		
		//setPreferredSize(new Dimension(600,500));
		//pack();
		
		//System.out.println(this.getInsets().left);
		//System.out.println(this.getInsets().right);
		//System.out.println(this.getContentPane().getWidth());

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(Updatable s : stuff)
		{
			s.updateX();
		}
		
		
		//Collision with left paddle
		if(((Ball)(stuff.get(2))).getY()+((Ball)(stuff.get(2))).getHeight()>((Paddle)(stuff.get(0))).getY()&&((Ball)(stuff.get(2))).getY()<((Paddle)(stuff.get(0))).getY()+((Paddle)(stuff.get(0))).getHeight())
		{
			if(((Ball)(stuff.get(2))).getX()<((Paddle)(stuff.get(0))).getX()+((Paddle)(stuff.get(0))).getWidth())
			{
				if(!(((Ball)(stuff.get(2))).getX()<((Paddle)(stuff.get(0))).getX()))
				{	
					if(((Ball)(stuff.get(2))).getDx()<0)
					{	
						((Ball)(stuff.get(2))).setDx(-1*((Ball)(stuff.get(2))).getDx());
						((Ball)(stuff.get(2))).setDy((int)(5*Math.random())-2);
						numHits++;
						if(numHits%5==0)
						{
							((Ball)(stuff.get(2))).setDx(((Ball)(stuff.get(2))).getDx()+1);
						}
					}
				}
			}
		}
		
		//Collision with right paddle
		if(((Ball)(stuff.get(2))).getY()+((Ball)(stuff.get(2))).getHeight()>((Paddle)(stuff.get(1))).getY()&&((Ball)(stuff.get(2))).getY()<((Paddle)(stuff.get(1))).getY()+((Paddle)(stuff.get(1))).getHeight())
		{
			if(((Ball)(stuff.get(2))).getX()+((Ball)(stuff.get(2))).getWidth()>((Paddle)(stuff.get(1))).getX())
			{
				if(!(((Ball)(stuff.get(2))).getX()+((Ball)(stuff.get(2))).getWidth()>((Paddle)(stuff.get(1))).getX()+((Paddle)(stuff.get(1))).getWidth()))
				{	
					if(((Ball)(stuff.get(2))).getDx()>0)	
					{
						((Ball)(stuff.get(2))).setDx(-1*((Ball)(stuff.get(2))).getDx());
						((Ball)(stuff.get(2))).setDy((int)(5*Math.random())-2);
						numHits++;
						if(numHits%5==0)
						{
							((Ball)(stuff.get(2))).setDx(((Ball)(stuff.get(2))).getDx()-1);
						}
					}
				}
			}
		}
		
		for(Updatable s : stuff)
		{
			s.updateY();
		}
		
		if(((Ball)(stuff.get(2))).getX()+((Ball)(stuff.get(2))).getWidth()>((Paddle)(stuff.get(0))).getX() && ((Ball)(stuff.get(2))).getX()<((Paddle)(stuff.get(0))).getX()+((Paddle)(stuff.get(0))).getWidth())
		{
			//Hit from bottom of paddle
			if(((Ball)(stuff.get(2))).getY()<((Paddle)(stuff.get(0))).getY()+((Paddle)(stuff.get(0))).getHeight() && ((Ball)(stuff.get(2))).getY()-((Paddle)(stuff.get(0))).getY()>0)
			{
				//((Ball)(stuff.get(2))).setLocation(((Ball)(stuff.get(2))).getX(), ((Paddle)(stuff.get(0))).getY()+((Paddle)(stuff.get(0))).getHeight());
				if(((Ball)(stuff.get(2))).getDy()<0)
				{
					((Ball)(stuff.get(2))).setDy(-1*((Ball)(stuff.get(2))).getDy());
					((Paddle)(stuff.get(0))).setDy(0);
				}
				else if(((Ball)(stuff.get(2))).getDy()>0)
				{
					((Ball)(stuff.get(2))).setDy(2*((Ball)(stuff.get(2))).getDy());
					((Paddle)(stuff.get(0))).setDy(0);
				}
				else
				{
					((Ball)(stuff.get(2))).setDy(1);
					((Paddle)(stuff.get(0))).setDy(0);
				}
			}
			//Hit from top of paddle
			else if(((Ball)(stuff.get(2))).getY()+((Ball)(stuff.get(2))).getHeight()>((Paddle)(stuff.get(0))).getY() && ((Ball)(stuff.get(2))).getY()-((Paddle)(stuff.get(0))).getY()<0)
			{
				//((Ball)(stuff.get(2))).setLocation(((Ball)(stuff.get(2))).getX(),((Paddle)(stuff.get(0))).getY());
				if(((Ball)(stuff.get(2))).getDy()<0)
				{
					((Ball)(stuff.get(2))).setDy(2*((Ball)(stuff.get(2))).getDy());
					((Paddle)(stuff.get(0))).setDy(0);
				}
				else if(((Ball)(stuff.get(2))).getDy()>0)
				{
					((Ball)(stuff.get(2))).setDy(-1*((Ball)(stuff.get(2))).getDy());
					((Paddle)(stuff.get(0))).setDy(0);
				}
				else
				{
					((Ball)(stuff.get(2))).setDy(-1);
					((Paddle)(stuff.get(0))).setDy(0);
				}
			}
		}
		
		if(((Ball)(stuff.get(2))).getX()+((Ball)(stuff.get(2))).getWidth()>((Paddle)(stuff.get(1))).getX() && ((Ball)(stuff.get(2))).getX()<((Paddle)(stuff.get(1))).getX()+((Paddle)(stuff.get(1))).getWidth())
		{
			if(((Ball)(stuff.get(2))).getY()<((Paddle)(stuff.get(1))).getY()+((Paddle)(stuff.get(1))).getHeight() && ((Ball)(stuff.get(2))).getY()-((Paddle)(stuff.get(1))).getY()>0)
			{
				//((Ball)(stuff.get(2))).setLocation(((Ball)(stuff.get(2))).getX(), ((Paddle)(stuff.get(1))).getY()+((Paddle)(stuff.get(1))).getHeight());
				if(((Ball)(stuff.get(2))).getDy()<0)
				{
					((Ball)(stuff.get(2))).setDy(-1*((Ball)(stuff.get(2))).getDy());
					((Paddle)(stuff.get(1))).setDy(0);
				}
				else if(((Ball)(stuff.get(2))).getDy()>0)
				{
					((Ball)(stuff.get(2))).setDy(2*((Ball)(stuff.get(2))).getDy());
					((Paddle)(stuff.get(1))).setDy(0);
				}
				else
				{
					((Ball)(stuff.get(2))).setDy(-1);
					((Paddle)(stuff.get(1))).setDy(0);
				}
			}
			else if(((Ball)(stuff.get(2))).getY()+((Ball)(stuff.get(2))).getHeight()>((Paddle)(stuff.get(1))).getY() && ((Ball)(stuff.get(2))).getY()-((Paddle)(stuff.get(1))).getY()<0)
			{
				//((Ball)(stuff.get(2))).setLocation(((Ball)(stuff.get(2))).getX(),((Paddle)(stuff.get(1))).getY());
				if(((Ball)(stuff.get(2))).getDy()<0)
				{
					((Ball)(stuff.get(2))).setDy(2*((Ball)(stuff.get(2))).getDy());
					((Paddle)(stuff.get(1))).setDy(0);
				}
				else if(((Ball)(stuff.get(2))).getDy()>0)
				{
					((Ball)(stuff.get(2))).setDy(-1*((Ball)(stuff.get(2))).getDy());
					((Paddle)(stuff.get(1))).setDy(0);
				}
				else
				{
					((Ball)(stuff.get(2))).setDy(1);
					((Paddle)(stuff.get(1))).setDy(0);
				}
			}
		}
		
		//All non-ball-paddle-collision stuff
		
		//Paddle checks
		if(((Paddle)(stuff.get(0))).getY()<0)
		{
			((Paddle)(stuff.get(0))).setLocation(((Paddle)(stuff.get(0))).getX(), 0);
		}
		else if(((Paddle)(stuff.get(0))).getY()+((Paddle)(stuff.get(0))).getHeight()>this.getContentPane().getHeight())
		{
			((Paddle)(stuff.get(0))).setLocation(((Paddle)(stuff.get(0))).getX(), this.getContentPane().getHeight()-((Paddle)(stuff.get(0))).getHeight());
		}
		
		if(((Paddle)(stuff.get(1))).getY()<0)
		{
			((Paddle)(stuff.get(1))).setLocation(((Paddle)(stuff.get(1))).getX(), 0);
		}
		else if(((Paddle)(stuff.get(1))).getY()+((Paddle)(stuff.get(1))).getHeight()>this.getContentPane().getHeight())
		{
			((Paddle)(stuff.get(1))).setLocation(((Paddle)(stuff.get(1))).getX(), this.getContentPane().getHeight()-((Paddle)(stuff.get(1))).getHeight());
		}
		
		
		//Ball bounce off frame
		if(((Ball)(stuff.get(2))).getY()<0)
		{
			//((Ball)(stuff.get(2))).setLocation(((Ball)(stuff.get(2))).getX(),0);
			((Ball)(stuff.get(2))).setDy(-1*((Ball)(stuff.get(2))).getDy());
		}
		else if(((Ball)(stuff.get(2))).getY() + ((Ball)(stuff.get(2))).getHeight()>this.getContentPane().getHeight())
		{
			((Ball)(stuff.get(2))).setDy(-1*((Ball)(stuff.get(2))).getDy());
		}
		
		//Player 2 scoring
		if(((Ball)(stuff.get(2))).getX()<0)
		{
			nums[1]++;
			scores.get(1).setText(""+nums[1]);
			((Ball)(stuff.get(2))).setLocation(this.getContentPane().getWidth()/2,this.getContentPane().getHeight()/2);
			((Ball)(stuff.get(2))).setDx(-1);
			
			((Ball)(stuff.get(2))).setDy(((int)(5*Math.random()))-2);
			while(((Ball)(stuff.get(2))).getDy()==0)
			{
				((Ball)(stuff.get(2))).setDy(((int)(5*Math.random()))-2);
			}
			
			if(nums[1]==10)
			{
				((Ball)(stuff.get(2))).setDx(0);
				((Ball)(stuff.get(2))).setDy(0);
				scores.get(2).setText("P2 Wins");
				scores.get(2).setVisible(true);
				scores.get(3).setVisible(true);
				scores.get(4).setText("(⌐■_■)");
				scores.get(4).setVisible(true);
			}
		}
		
		//Player 1 scoring
		if(((Ball)(stuff.get(2))).getX()>this.getContentPane().getWidth())
		{
			nums[0]++;
			scores.get(0).setText(""+nums[0]);
			((Ball)(stuff.get(2))).setLocation(this.getContentPane().getWidth()/2,this.getContentPane().getHeight()/2);
			((Ball)(stuff.get(2))).setDx(1);
			
			((Ball)(stuff.get(2))).setDy(((int)(5*Math.random()))-2);
			while(((Ball)(stuff.get(2))).getDy()==0)
			{
				((Ball)(stuff.get(2))).setDy(((int)(5*Math.random()))-2);
			}
			
			if(nums[0]==10)
			{
				((Ball)(stuff.get(2))).setDx(0);
				((Ball)(stuff.get(2))).setDy(0);
				scores.get(2).setText("P1 Wins");
				scores.get(2).setVisible(true);
				scores.get(3).setVisible(true);
				scores.get(4).setText("(づ｡◕‿‿◕｡)づ");
				scores.get(4).setVisible(true);
			}
		}
		
		repaint();
		
	}

}

//Should I repaint after both updates? (Should i repaint twice?
//Collision needs to occur in the frame