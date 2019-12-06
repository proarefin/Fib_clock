
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

	private String minuteDetails;
	private String hourDetails;
	private String minuteMoreDetails;

	private int hourCount;
	private int minuteCount;
	private int hour;
	private int minute;

	ClockThread thread;

	final JTextArea details;

	public FibonacciClock() {
		cg = new ClockGui();

		details = cg.getDetails();

		square5 = new Square(NONE, cg.getSquare5(), 5);
		square3 = new Square(NONE, cg.getSquare3(), 3);
		square2 = new Square(NONE, cg.getSquare2(), 2);
		square1_1 = new Square(NONE, cg.getSquare1_1(), 1);
		square1_2 = new Square(NONE, cg.getSquare1_2(), 1);

		thread = new ClockThread(this); // Calling  the Thread

	}
	
	//Select color for Hour representation(Red) 
	private void setHour(StringBuilder sB, Square square) {
		int value = square.getValue();
		if (hour >= value) {
			hour -= value;
			square.setColour(HOUR);
			sB.append(value);
			hourCount += value;
		} else {
			sB.append(0);
		}
	}
	
	//process color for minute representation(Green) 
	private void setminute(StringBuilder sB, Square square) {
		int value = square.getValue();
		if (minute >= value) {
			minute -= value;
			setMinuteOrBothColour(square);
			sB.append(value);
			minuteCount += value;
		} else {
			sB.append(0);
		}
	}
	
	//Add live time on Title Bar
	public void showTimeOnTtile(Calendar calendar) {
		cg.setTitle("Fibonacci Clock - " + (new SimpleDateFormat("hh:mm:ss a")).format(calendar.getTime()));
		System.out.print("\nDate And Time Is: " + calendar.getTime());
	}
	
	//Core process of the program. Generate the appropriate color sequences.
	public void buildColorClock(Calendar calendar) {

		hour = calendar.get(Calendar.HOUR);
		minute = calendar.get(Calendar.MINUTE) / 5;

		StringBuilder hoursB = new StringBuilder();
		StringBuilder minutesB = new StringBuilder();

		hourCount = 0;
		minuteCount = 0;

		setHour(hoursB, square5);
		hoursB.append("+");
		setHour(hoursB, square3);
		hoursB.append("+");
		setHour(hoursB, square2);
		hoursB.append("+");
		setHour(hoursB, square1_1);
		hoursB.append("+");
		setHour(hoursB, square1_2);
		hoursB.append(" = ");
		hoursB.append(hourCount);
		hourDetails = hoursB.toString();

		setminute(minutesB, square5);
		minutesB.append("+");
		setminute(minutesB, square3);
		minutesB.append("+");
		setminute(minutesB, square2);
		minutesB.append("+");
		setminute(minutesB, square1_1);
		minutesB.append("+");
		setminute(minutesB, square1_2);
		minutesB.append("=");
		minutesB.append(minuteCount);
		minutesB.append("*5");
		minuteDetails = minutesB.toString();
		minuteMoreDetails = String.valueOf(minuteCount * 5);
	
		showProcessDetail();// Display live changes
	}
	
	//Decide the blue (Hour and Minutes) Or Only minute color(Green)
	private void setMinuteOrBothColour(Square square) {
		if (square.getColour() == HOUR) {
			square.setColour(BOTH);
		} else {
			square.setColour(MINUTE);
		}
	}
	
	//Build the clock live display 
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

	}

	public static void main(String[] args) {
		new FibonacciClock();
	}

}
