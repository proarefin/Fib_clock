import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;


public class Square {
		
	private int colour;
	private JPanel square;
	private int value;
	
	final int NONE = 0; //white
	final int MINUTE = 1; //green
	final int HOUR = 2; //red
	final int BOTH = 3; //blue
		
	private static final Map<Integer, Color> colourMap;
	static {
		colourMap = new HashMap<Integer, Color>();
		colourMap.put(FibonacciClock.NONE, Color.WHITE);
		colourMap.put(FibonacciClock.MINUTE, Color.GREEN);
		colourMap.put(FibonacciClock.HOUR, Color.RED);
		colourMap.put(FibonacciClock.BOTH, Color.BLUE);
	}
	
	public Square(int colour, JPanel square, int value) {
		this.colour = colour;
		this.square = square;
		this.value = value;
		square.setBackground(colourMap.get(colour));
	}
	
	public int getValue() {
		return value;
	}
	
	public void setColour(int colour) {
		this.colour = colour;
		square.setBackground(colourMap.get(colour));
	}
	
	public int getColour() {
		return colour;
	}
		
}