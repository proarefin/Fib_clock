import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClockThread extends Thread{
	FibonacciClock fibClock;
	private final static int ONE_SECOND = 1000;
	
	public ClockThread(FibonacciClock fibClock) {
		this.fibClock = fibClock;
		start();
		
	}
	public void run() {
		while(true) {
			try {
				//Calendar for extract current time zone. Little Old fashion but effective.
				//Java 8 time feature is not safe to use in Thread.
			Calendar calendar =GregorianCalendar.getInstance();
			
			fibClock.buildColorClock(calendar);
			showTimeOnTitle(calendar);
			
			Thread.sleep(ONE_SECOND);
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void showTimeOnTitle(Calendar calendar) {
		fibClock.cg.setTitle("Fibonacci Clock - " + (new SimpleDateFormat("hh:mm:ss a")).format(calendar.getTime()));
		//System.out.print("\nDate And Time Is: " + calendar.getTime());
		}

}
