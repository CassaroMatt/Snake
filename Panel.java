
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class Panel extends JPanel implements MouseInputListener, KeyListener {
	
	SnekLinkedList snek;
	Apple[] apples = new Apple[100];
	int frames = 0;
	int seconds = 0;

	/**
	 * Construct a panel with specified width, height, and background color
	 * @param width
	 * @param height
	 * @param bgColor
	 */
	public Panel(int width, int height, Color bgColor)  {
		setPreferredSize(new Dimension(width, height));
		setBackground(bgColor); 
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		snek = new SnekLinkedList();
		repaint();
	}

	protected void paintComponent(Graphics graphicHelper) {
		super.paintComponent(graphicHelper);
		Graphics2D g = (Graphics2D)graphicHelper;

		
		//***This area here is our "draw" method now***********
		snek.move();
		snek.draw(g);
		
		//draw score
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.setColor(Color.ORANGE);
		g.drawString(Integer.toString(snek.size), 20, 20);
		
		checkEats();
		
		spawnApple();
		drawApples(g);
		
		
		//********End of draw method************
		long start = System.currentTimeMillis();
		long delta = 0;
		long frameTime = 1000/30;
		while(delta<frameTime) {
			delta = System.currentTimeMillis() - start;
		}
		frames++;
		if(frames%30==0) {
			seconds++;
		}
		repaint();
	}
	
	private void checkEats() {
		for(int i=0; i<apples.length; i++) {
			if(apples[i] != null) {
				if(snek.headContains(apples[i].x, apples[i].y)) {
					if(apples[i].isBad) {
						snek.loseSegment();
					}else {					
						snek.addSegment(100,100);
					}
					apples[i]=null;
				}
			}
		}
	}
	
	private void drawApples(Graphics2D g) {
		for(int i=0; i<apples.length; i++) {
			if(apples[i] != null) {
				apples[i].draw(g);
			}
		}
	}
	
	private void spawnApple() {
		if(seconds%1==0 && frames%30==0) {
			for(int i=0; i<apples.length; i++) {
				if(apples[i] == null) {
					apples[i] = new Apple(Math.random() * getWidth(), Math.random() * getHeight());
					return;
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		if(arg0.getButton() == MouseEvent.BUTTON3) {
			System.out.println("Hax");
			snek.addSegment(100, 100);
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_DOWN) {
			snek.changeDirection(SnekLinkedList.DOWN);
		}else if(k.getKeyCode() == KeyEvent.VK_UP) {
			snek.changeDirection(SnekLinkedList.UP);
		}else if(k.getKeyCode() == KeyEvent.VK_LEFT) {
			snek.changeDirection(SnekLinkedList.LEFT);
		}else if(k.getKeyCode() == KeyEvent.VK_RIGHT) {
			snek.changeDirection(SnekLinkedList.RIGHT);
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

}