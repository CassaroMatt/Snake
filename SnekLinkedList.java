
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class SnekLinkedList{
	
	public static int movePerInterval = 17;
	SegmentNode start;
	SegmentNode end;
	/**
	 * size of the linked list
	 */
	public int size = 0;
	/**
	 * boolean to keep track if the snek needs to be reset
	 */
	boolean toReset;
	/**
	 * Coded as such: 
	 * direction 0 = left, 1 = right, 2 = down, 3 = up
	 */
	public int direction = 0;
	static final int UP = 3;
	static final int DOWN = 2;
	static final int RIGHT = 1;
	static final int LEFT = 0;
	
	public SnekLinkedList() {
		addSegment(100,100);
	}
	
	/**
	 * draws the current segments available in the linked list
	 * @param g
	 */
	public void draw(Graphics2D g) {
		
		SegmentNode curr = start;
		start.color = new Color((int) (Math.random() * 0xFFFFFF));
		int i = 0;
        while (curr != null)
        {     	
        	curr.drawSquareAtNode(g);
            curr = curr.next;
            i++;
        }	
	}
	
	/**
	 * adds a segment to the end of the linked list at given x/y coords
	 * @param x
	 * @param y
	 */
	public void addSegment(double x, double y) {
		printList();

		if(start==null) {
			SegmentNode node = new SegmentNode(x, y);
			start = node;
			end = node;
		}else { //list is not empty		
			
			SegmentNode node = new SegmentNode(x, y);
			node.previous = end;
			end.next = node;		
			end = node;
			
		}
		size++;
	}
	
	/**
	 * removes a segment from the end of the linked list
	 */
	public void loseSegment() {
		printList();

		if(end != null && end != start) {
    		end = end.previous;
    		end.next = null;
    		size--;
		}else {
			size = 1;
		}
	}
	
	/**
	 * sets size of snek to one by getting rid of all next node references and setting end node to the start node
	 */
	public void reset() {
		toReset = false;
		end = start;
		start.next = null;		
	}

	/**
	 * checks if the head of the snek contains any other object x/y coords
	 * @param xp
	 * @param yp
	 * @return
	 */
	public boolean headContains(double xp, double yp) {

		if(new Rectangle2D.Double((int) start.x, (int) start.y, start.size, start.size).contains(xp, yp)) {
			System.out.println("Start: "+start.x+" "+start.y+ " to compare "+xp+" "+yp);
			return true;
		}		
		
		return false;
	}	
	
	/**
	 * moves Snek in the given direction
	 * @param direction 0 = left, 1 = right, 2 = down, 3 = up
	 */
	public void move() {
		moveSegments();	
		switch(direction) {		
			case 0:			
				start.x-=movePerInterval;	
				break;
			case 1:
				
				start.x+=movePerInterval;
				break;
			case 2:
				start.y+=movePerInterval;
				break;
			case 3:
				start.y-=movePerInterval;
				break;
			default:
				break;				
		}
		
		  if(start.x<0){
			  start.x = 800;
		  }
		  if(start.x>800){
			  start.x=0;
		  }
		  if(start.y>800){ //off the bottom side, wrap around
			  start.y=0;
		  }
		  if(start.y<0){ //off the top side, wrap around
			  start.y=800;
		  }	  
	}
	
	/**
	 * Shifts all the segments except the head, checks if snek needs to be reset
	 *  in case that its head intersects with another segment or if the start nodes prev. reference is null
	 */
	public void moveSegments() {
		toReset=false;

		SegmentNode curr = end;
		
		int i = size;
        while(curr.previous != null)
        {
        	curr.x = curr.previous.x;
        	curr.y = curr.previous.y;     	
        	curr = curr.previous;	       	        	  
        	i--; 
        	if((i > 2) && headContains(curr.x, curr.y)) {
  			  toReset=true;
  			  size = 1;
        	}        	
        }        
        if(toReset) {
			reset();
		}
	}
	
	 /**
     * Display the linked list
     */
    public void printList()
    {
        System.out.println("Here's Your Doubly Linked List:");
        if (start==null) 
        {
            System.out.println("Empty");
            return;
        }    
        if (start.next == null) 
        {
            System.out.println(start.x +" " +start.y);
            return;
        }
        
        System.out.print("Start ");
        SegmentNode curr = start;
        while (curr != null)
        {
 
            System.out.print(curr.x+ " "+curr.y+" <-> ");
            curr = curr.next;
        }
        System.out.println("   End");
    }
	
	/**
	 * change Snek to move in direction
	 * @param direction 0 = left, 1 = right, 2 = down, 3 = up
	 */
	public void changeDirection(int direction) {
		this.direction = direction;
	}

}
