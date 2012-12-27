package window;

import graph.components.Graph;
import graph.components.Node;
import graph.parser.Parser;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import algorithm.AStar;
import algorithm.Heuristic;
import algorithm.Heuristic1;

public class Window {

    private JFrame frame;
    private Graph g;
    private ArrayList<Node> nodes;
    private Parser p;
    private JTextField textField;
    private JTextField textField_1;
    private AStar as;
    private Heuristic h;

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
	
	p = new Parser();
	
	JButton btnParse = new JButton("Parse");
	btnParse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    try {
			g = p.ParseTxtFile("RandomCity.txt");
		    } catch(Exception e) {
			e.printStackTrace();
		    }
		}
	});
	frame.getContentPane().add(btnParse, BorderLayout.WEST);
	
	final JTextPane textPane = new JTextPane();
	frame.getContentPane().add(textPane, BorderLayout.CENTER);
	
	JButton btnSearch = new JButton("Search");
	btnSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    h = new Heuristic1();
		    as = new AStar(g, g.getNodebyAlias(textField.getText()), g.getNodebyAlias(textField_1.getText()), h);
		    nodes = as.getPath(); // = g.getNeighbors(textField.getText());
		    textPane.setText("");
		    for(int i = 0; i < nodes.size(); i++) {
			textPane.setText(textPane.getText() + "\n" + nodes.get(i).getAlias());
		    }
		}
	});
	frame.getContentPane().add(btnSearch, BorderLayout.EAST);
	
	JPanel panel = new JPanel();
	frame.getContentPane().add(panel, BorderLayout.NORTH);
	GridBagLayout gbl_panel = new GridBagLayout();
	gbl_panel.columnWidths = new int[]{217, 216, 0};
	gbl_panel.rowHeights = new int[]{20, 0};
	gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
	gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	panel.setLayout(gbl_panel);
	
	textField = new JTextField();
	textField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	textField.setToolTipText("Origen");
	textField.setHorizontalAlignment(SwingConstants.CENTER);
	GridBagConstraints gbc_textField = new GridBagConstraints();
	gbc_textField.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField.anchor = GridBagConstraints.NORTH;
	gbc_textField.insets = new Insets(0, 0, 0, 5);
	gbc_textField.gridx = 0;
	gbc_textField.gridy = 0;
	panel.add(textField, gbc_textField);
	textField.setColumns(10);
	
	textField_1 = new JTextField();
	textField_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	textField_1.setHorizontalAlignment(SwingConstants.LEFT);
	textField_1.setToolTipText("Desti");
	GridBagConstraints gbc_textField_1 = new GridBagConstraints();
	gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_1.gridx = 1;
	gbc_textField_1.gridy = 0;
	panel.add(textField_1, gbc_textField_1);
	textField_1.setColumns(10);
    }

}
