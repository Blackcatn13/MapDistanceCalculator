package window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class Window {

    private JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Window window = new Window();
		    window.frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public Window() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	textField = new JTextField();
	frame.getContentPane().add(textField, BorderLayout.NORTH);
	textField.setColumns(10);
	
	JButton btnParse = new JButton("Parse");
	frame.getContentPane().add(btnParse, BorderLayout.WEST);
	
	JTextPane textPane = new JTextPane();
	frame.getContentPane().add(textPane, BorderLayout.CENTER);
    }

}
