import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClockThread extends Thread{
	FibonacciClock fibClock;
	private final static int seconds =1000;
	Calendar calendar;
	
	public ClockThread(FibonacciClock fibClock) {
		this.fibClock = fibClock;
		this.calendar=GregorianCalendar.getInstance();
		fibClock.generateColorPattern(fibClock.objPattern0);
		
		this.start();
		
	}
	public void run() {
		while(true) {
			try {
				//Calendar for extract current time zone. Little Old fashion but effective.
				//Java 8 time feature is not safe to use in Thread.
			calendar =GregorianCalendar.getInstance();
			fibClock.hour=calendar.get(Calendar.HOUR);
			fibClock.minute=calendar.get(Calendar.MINUTE) / 5;
			
			System.out.print("\nIs: "+ (calendar.get(Calendar.MINUTE))%5);
			
			if((calendar.get(Calendar.MINUTE))%5 == 0 && calendar.get(Calendar.SECOND)%10 == 0)
			{
				fibClock.generateColorPattern(fibClock.objPattern0);
				//fibClock.buildColorClock();
			}
			
			showTimeOnTitle(calendar);
			
			Thread.sleep(seconds);
			
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
