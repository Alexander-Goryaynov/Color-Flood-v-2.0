import java.awt.Graphics;
import javax.swing.JPanel;

/*
 * Array Panel is responsible for drawing the game field
 * 
 * author: Alexander Goryaynov
 */

public class ArrayPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private ArrayField arr;
	
	public ArrayPanel(ArrayField array) {          
		arr = array;   
	}
	
	public void paint(Graphics g)  {
		super.paint(g);		                                       
		arr.drawArray(g, this.getWidth(), this.getHeight());      
	}
	
}
