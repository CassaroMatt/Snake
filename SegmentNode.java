
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class SegmentNode extends Rectangle2D.Double{
	static final int size = 17;
	Color color;
	SegmentNode next;
	SegmentNode previous;
	
	/**
	 * creates a node at given x, y positions as well as random color
	 * @param x
	 * @param y
	 */
	public SegmentNode(double x, double y){
		super(x, y, size, size);
		color = new Color((int) (Math.random() * 0xFFFFFF));
				
	}
	
	/**
	 * draws and fills the node segment
	 * @param g
	 */
	public void drawSquareAtNode(Graphics2D g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, size, size);    
		g.setColor(Color.white);
		g.drawRect((int) x, (int) y, size, size);
	}

}
