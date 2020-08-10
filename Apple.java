
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Apple extends Rectangle.Double{
	boolean isBad;
	Color color;
	static final double size = 17;
	
	public Apple(double x, double y) {
		super(x, y, size, size);
		if(Math.random()<.2) {
			isBad=true;
			color = Color.green;
		}else {
			isBad=false;
			color = Color.red;
		}
	}
	
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fill(this);
	}
}
