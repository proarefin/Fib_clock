import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class FibonacciClock {

	static final int NONE = 0; // white
	static final int MINUTE = 1; // green
	static final int HOUR = 2; // red
	static final int BOTH = 3; // blue

	private Square square5;
	private Square square3;
	private Square square2;
	private Square square1_1;
	private Square square1_2;

	public ClockGui cg;// Clock GUI object
	ClockThread thread;

	private String minuteDetails;
	private String hourDetails;
	private String minuteMoreDetails;

	Calendar calendar;
	private int hourCount;
	private int minuteCount;
	public int hour;
	public int minute;

	final JTextArea details;
	final JButton btnShowPattern;

	StringBuilder hoursB;
	StringBuilder minutesB;
	
	List<Square> objPattern0; 
	List<Square> objPattern1;
	List<Square> objPattern2;


	public FibonacciClock() {
		cg = new ClockGui();

		details = cg.getDetails();
		
		square5 = new Square(NONE, cg.getSquare5(), 5);
		square3 = new Square(NONE, cg.getSquare3(), 3);
		square2 = new Square(NONE, cg.getSquare2(), 2);
		square1_1 = new Square(NONE, cg.getSquare1_1(), 1);
		square1_2 = new Square(NONE, cg.getSquare1_2(), 1);

		objPattern0 = new ArrayList<Square>();
		objPattern0.add(square5);
		objPattern0.add(square3);
		objPattern0.add(square2);
		objPattern0.add(square1_1);
		objPattern0.add(square1_2);
		
		objPattern1 = new ArrayList<Square>();
		objPattern1.add(square1_2);
		objPattern1.add(square5);
		objPattern1.add(square3);
		objPattern1.add(square2);
		objPattern1.add(square1_1);

		objPattern2 = new ArrayList<Square>();
		objPattern2.add(square1_1);
		objPattern2.add(square2);
		objPattern2.add(square5);
		objPattern2.add(square3);
		objPattern2.add(square1_2);
		
		calendar = GregorianCalendar.getInstance();
		this.hour = calendar.get(Calendar.HOUR);
		
		this.minute = calendar.get(Calendar.MINUTE) / 5;

		hoursB = new StringBuilder();
		minutesB = new StringBuilder();

		//JButton to cheeck patters 
		btnShowPattern = cg.getBtnShowPattern();

		btnShowPattern.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				//System.out.print(btnShowPattern.getText() + hour + ":" + minute);
				if (btnShowPattern.getText() == "Show New Pattern") {
					hour = cg.getHour();
					minute = cg.getMinute() / 5;
					generateColorPattern(objPattern1);
					btnShowPattern.setText("Show Next Pattern");
				} 
				else if(btnShowPattern.getText() == "Current Pattern")
				{
					//buildColorClock();
					calendar=GregorianCalendar.getInstance();
					hour=calendar.get(Calendar.HOUR);
					minute=calendar.get(Calendar.MINUTE) / 5;
					
					generateColorPattern(objPattern0);
					btnShowPattern.setText("Show New Pattern");
				}
				else 
				{
					hour = cg.getHour();
					minute = cg.getMinute() / 5;
					if (hour == 10 || hour == 5 || minute == 10 || minute == 5)
						generateColorPattern(objPattern0);//buildColorClock();
					else
						generateColorPattern(objPattern2);
					
					btnShowPattern.setText("Current Pattern");
				}

			}
		});

		thread = new ClockThread(this); // Calling Thread

	}

	// Select color for Hour representation(Red)
	private void setHour(StringBuilder sB, Square square) {
		int value = square.getValue();
		if (hour >= value) {
			hour -= value;
			square.setColour(HOUR);
			sB.append(value);
			hourCount += value;
		} else {
			sB.append(0);
			//square.setColour(NONE);
		}
	}
	
	//create sequence for minute
	private void setminute(StringBuilder sB, Square square) {
		int value = square.getValue();
		if (minute >= value) {
			minute -= value;
			setMinuteOrBothColour(square);
			sB.append(value);
			minuteCount += value;
		} else {
			sB.append(0);
			//square.setColour(NONE);
		}
	}
		
	// Build time's different Color Pattern
	public void generateColorPattern(List<Square> obj) {
		
		hoursB.setLength(0);
		minutesB.setLength(0);

		hourCount = 0;
		minuteCount = 0;

		for (Square Sq : obj) {
			Sq.setColour(NONE);
			setHour(hoursB, Sq);
			System.out.print("\n Is: " + obj.lastIndexOf(Sq));
			if (obj.lastIndexOf(Sq) < 4)
				hoursB.append("+");
		}
		hoursB.append(" = ");
		hoursB.append(hourCount);
		hourDetails = hoursB.toString();

		for (Square Sq : obj) {
			setminute(minutesB, Sq);
			if (obj.lastIndexOf(Sq) < 4)
				minutesB.append("+");
		}
		minutesB.append("=");
		minutesB.append(minuteCount);
		minutesB.append("*5");
		minuteDetails = minutesB.toString();
		minuteMoreDetails = String.valueOf(minuteCount * 5);

		showProcessDetail();

		hoursB.setLength(0);
		minutesB.setLength(0);
	}

	// Decide the blue (Hour and Minutes) Or Only minute color(Green)
	private void setMinuteOrBothColour(Square square) {
		if (square.getColour() == HOUR) {
			square.setColour(BOTH);
		} else {
			square.setColour(MINUTE);
		}
	}

	// Build the clock live display
	public void showProcessDetail() {

		StringBuilder sB = new StringBuilder();

		sB.append("White = null");
		sB.append("\n");
		sB.append("Green = minute");
		sB.append("\n");
		sB.append("Red   = hour");
		sB.append("\n");
		sB.append("Blue  = both");
		sB.append("\n");
		sB.append("\n");
		sB.append("Hour:");
		sB.append("\n");
		sB.append(hourDetails);
		sB.append("\n");
		sB.append("\n");
		sB.append("Minute:");
		sB.append("\n");
		sB.append(minuteDetails);
		sB.append("\n");
		sB.append("          = ");
		sB.append(minuteMoreDetails);
		details.setText(sB.toString());

		hourDetails = "";
		minuteDetails = "";
		minuteMoreDetails = "";
		sB.setLength(0);
	}
	
	//Main Start()
	public static void main(String[] args) {
		new FibonacciClock();
	}

}
