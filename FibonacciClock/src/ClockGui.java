import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Font;


public class ClockGui extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel square5;
	private JPanel square3;
	private JPanel square2;
	private JPanel square1_1;
	private JPanel square1_2;
	
	private JLabel  jlabel;
	
	private JTextArea details;
	

	public ClockGui() {
		setTitle("Fibonacci Clock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		square5 = new JPanel();
		square5.setBounds(125, 5, 200, 200);
		square5.setBorder(new LineBorder(new Color(0, 0, 0)));
		square5.setBackground(Color.ORANGE);
		square5.setOpaque(true);
	    getContentPane().add(square5);
		
		square3 = new JPanel();
		square3.setBounds(5, 85, 120, 120);
		square3.setBorder(new LineBorder(new Color(0, 0, 0)));
		square3.setBackground(Color.BLACK);
		square3.setOpaque(true);
		getContentPane().add(square3);

		square2 = new JPanel();
		square2.setBounds(5, 5, 80, 80);
		square2.setBorder(new LineBorder(new Color(0, 0, 0)));
		square2.setBackground(Color.CYAN);
		square2.setOpaque(true);
		getContentPane().add(square2);
		
		square1_1 = new JPanel();
		square1_1.setBounds(85, 5, 40, 40);
		square1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		square1_1.setBackground(Color.YELLOW);
		square1_1.setOpaque(true);
		getContentPane().add(square1_1);
		
		square1_2 = new JPanel();
		square1_2.setBounds(85, 45, 40, 40);
		square1_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		square1_2.setBackground(Color.MAGENTA);
		square1_2.setOpaque(true);
		getContentPane().add(square1_2);
		
		details = new JTextArea();
		details.setFont(new Font("Courier", Font.PLAIN, 13));
		details.setBounds(330, 5, 150, 200);
		details.setEditable(false);
		getContentPane().add(details);
			

		
		setSize(500,250);
		setVisible(true);
		setResizable(false);
	}
	
	public JTextArea getDetails() {
		return details;
	}
	
	public JPanel getSquare5() {
		return square5;
	}

	public JPanel getSquare3() {
		return square3;
	}

	public JPanel getSquare2() {
		return square2;
	}

	public JPanel getSquare1_1() {
		return square1_1;
	}

	public JPanel getSquare1_2() {
		return square1_2;
	}
}
