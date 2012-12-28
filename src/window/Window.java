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
import java.util.BitSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import algorithm.AStar;
import algorithm.Heuristic;
import algorithm.Heuristic1;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

public class Window {

    private JFrame frame;
    private Graph g;
    private ArrayList<Node> nodes;
    private Parser p;
    private JTextField textField;
    private JTextField textField_1;
    private AStar as;
    private Heuristic h;
    private boolean bus = false;
    private boolean subway = false;
    private boolean walk = false;

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
	
	JPanel panel_1 = new JPanel();
	frame.getContentPane().add(panel_1, BorderLayout.EAST);
	GridBagLayout gbl_panel_1 = new GridBagLayout();
	gbl_panel_1.columnWidths = new int[]{65, 0};
	gbl_panel_1.rowHeights = new int[]{0, 0, 29, 147, 0};
	gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
	gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	panel_1.setLayout(gbl_panel_1);
	
	final JRadioButton rdbtnBus = new JRadioButton("Bus");
	GridBagConstraints gbc_rdbtnBus = new GridBagConstraints();
	gbc_rdbtnBus.anchor = GridBagConstraints.WEST;
	gbc_rdbtnBus.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnBus.gridx = 0;
	gbc_rdbtnBus.gridy = 0;
	panel_1.add(rdbtnBus, gbc_rdbtnBus);
	
	final JRadioButton rdbtnSubway = new JRadioButton("Subway");
	GridBagConstraints gbc_rdbtnSubway = new GridBagConstraints();
	gbc_rdbtnSubway.anchor = GridBagConstraints.WEST;
	gbc_rdbtnSubway.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnSubway.gridx = 0;
	gbc_rdbtnSubway.gridy = 1;
	panel_1.add(rdbtnSubway, gbc_rdbtnSubway);
	
	final JRadioButton rdbtnWalking = new JRadioButton("Walking");
	GridBagConstraints gbc_rdbtnWalking = new GridBagConstraints();
	gbc_rdbtnWalking.anchor = GridBagConstraints.WEST;
	gbc_rdbtnWalking.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnWalking.gridx = 0;
	gbc_rdbtnWalking.gridy = 2;
	panel_1.add(rdbtnWalking, gbc_rdbtnWalking);
	
	final JTextPane textPane = new JTextPane();
	frame.getContentPane().add(textPane, BorderLayout.CENTER);
	
	JButton btnSearch = new JButton("Search");
	GridBagConstraints gbc_btnSearch = new GridBagConstraints();
	gbc_btnSearch.gridx = 0;
	gbc_btnSearch.gridy = 3;
	panel_1.add(btnSearch, gbc_btnSearch);
	
	JPanel panel = new JPanel();
	frame.getContentPane().add(panel, BorderLayout.NORTH);
	GridBagLayout gbl_panel = new GridBagLayout();
	gbl_panel.columnWidths = new int[]{0, 61, 163, 0, 216, 0};
	gbl_panel.rowHeights = new int[]{20, 0};
	gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	panel.setLayout(gbl_panel);
	
	JLabel lblSource = new JLabel("Source");
	GridBagConstraints gbc_lblSource = new GridBagConstraints();
	gbc_lblSource.insets = new Insets(0, 0, 0, 5);
	gbc_lblSource.anchor = GridBagConstraints.EAST;
	gbc_lblSource.gridx = 1;
	gbc_lblSource.gridy = 0;
	panel.add(lblSource, gbc_lblSource);
	
	textField = new JTextField();
	textField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	textField.setToolTipText("Origen");
	textField.setHorizontalAlignment(SwingConstants.CENTER);
	GridBagConstraints gbc_textField = new GridBagConstraints();
	gbc_textField.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField.anchor = GridBagConstraints.NORTH;
	gbc_textField.insets = new Insets(0, 0, 0, 5);
	gbc_textField.gridx = 2;
	gbc_textField.gridy = 0;
	panel.add(textField, gbc_textField);
	textField.setColumns(10);
	
	JLabel lblDestinatition = new JLabel("Destination");
	GridBagConstraints gbc_lblDestinatition = new GridBagConstraints();
	gbc_lblDestinatition.insets = new Insets(0, 0, 0, 5);
	gbc_lblDestinatition.anchor = GridBagConstraints.EAST;
	gbc_lblDestinatition.gridx = 3;
	gbc_lblDestinatition.gridy = 0;
	panel.add(lblDestinatition, gbc_lblDestinatition);
	
	textField_1 = new JTextField();
	textField_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	textField_1.setHorizontalAlignment(SwingConstants.LEFT);
	textField_1.setToolTipText("Desti");
	GridBagConstraints gbc_textField_1 = new GridBagConstraints();
	gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_1.gridx = 4;
	gbc_textField_1.gridy = 0;
	panel.add(textField_1, gbc_textField_1);
	textField_1.setColumns(10);
	
	btnSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    h = new Heuristic1();
		    as = new AStar(g, g.getNodebyAlias(textField.getText()), g.getNodebyAlias(textField_1.getText()), h);
		    BitSet b = new BitSet(3);
		    b.set(0, rdbtnBus.isSelected());
		    b.set(1, rdbtnSubway.isSelected());
		    b.set(2, rdbtnWalking.isSelected());
		    nodes = as.getPath(b); // = g.getNeighbors(textField.getText());
		    textPane.setText("");
		    for(int i = 0; i < nodes.size(); i++) {
			textPane.setText(textPane.getText() + "\n" + nodes.get(i).getAlias());
		    }
		}
	});
    }

}
