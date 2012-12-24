package window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import graph.components.Graph;
import graph.components.Node;

public class Window {

    private JFrame frame;
    private JTextField textField;
    private Graph g;
    private ArrayList<Node> nodes;

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
	btnParse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    
		}
	});
	frame.getContentPane().add(btnParse, BorderLayout.WEST);
	
	final JTextPane textPane = new JTextPane();
	frame.getContentPane().add(textPane, BorderLayout.CENTER);
	
	JButton btnSearch = new JButton("Search");
	btnSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    nodes = g.getNeighbours(textField.getText());
		    for(int i = 0; i < nodes.size(); i++) {
			textPane.setText(textPane.getText() + "\n" + nodes.get(i).getName());
		    }
		}
	});
	frame.getContentPane().add(btnSearch, BorderLayout.EAST);
    }

}
