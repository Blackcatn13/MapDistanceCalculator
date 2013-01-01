package window;

import graph.components.Graph;
import graph.components.Transports;
import graph.parser.ParserGraph;

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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import algorithm.AStar;
import algorithm.Heuristic;
import algorithm.HeuristicD;
import algorithm.HeuristicE;
import algorithm.InfoPath;

public class Window {

    private JFrame frame;
    private Graph g;
    private ArrayList<InfoPath> nodes;
    private ParserGraph p;
    private JTextField textField;
    private JTextField textField_1;
    private JTextPane textPane;
    private AStar as;
    private Heuristic h;

    public void WritePath(ArrayList<InfoPath> path, BitSet b, boolean alias) {
	Transports t;
	if(!path.isEmpty()) {
	    for(int i = 0; i < path.size() - 1; i++) {
		t = path.get(i).getTransport();
		switch(t) {
    	    	case BUS:
    	    	    textPane.setText(textPane.getText() + "\n" + "Take the bus");
    	    	    break;
    	    	case SUBWAY:
    	    	    textPane.setText(textPane.getText() + "\n" + "Take the subway");
    	    	    break;
    	    	case WALK:
    	    	    textPane.setText(textPane.getText() + "\n" + "Walk");
		}
		if(alias) {
        	    textPane.setText(textPane.getText() + " from " + path.get(i).getSNode().getAlias());
        	    textPane.setText(textPane.getText() + " to " + path.get(i).getDNode().getAlias());
		}
		else {
		    textPane.setText(textPane.getText() + " from " + path.get(i).getSNode().getName());
    		    textPane.setText(textPane.getText() + " to " + path.get(i).getDNode().getName());
		}
	    }
	}
	else {
	    textPane.setText(textPane.getText() + "\n" + "Don't exist a way from source to destination with the selected transports.");
	}
    }
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
	
	p = new ParserGraph();
	
	JPanel panel_2 = new JPanel();
	frame.getContentPane().add(panel_2, BorderLayout.WEST);
	GridBagLayout gbl_panel_2 = new GridBagLayout();
	gbl_panel_2.columnWidths = new int[]{59, 0};
	gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 23, 0, 0};
	gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
	gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	panel_2.setLayout(gbl_panel_2);

	JButton btnParse = new JButton("Parse");
	GridBagConstraints gbc_btnParse = new GridBagConstraints();
	gbc_btnParse.insets = new Insets(0, 0, 5, 0);
	gbc_btnParse.anchor = GridBagConstraints.NORTH;
	gbc_btnParse.gridx = 0;
	gbc_btnParse.gridy = 0;
	panel_2.add(btnParse, gbc_btnParse);
	
	final JRadioButton rdbtnHx = new JRadioButton("H(x) = 0");
	rdbtnHx.setSelected(true);
	GridBagConstraints gbc_rdbtnHx = new GridBagConstraints();
	gbc_rdbtnHx.anchor = GridBagConstraints.WEST;
	gbc_rdbtnHx.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnHx.gridx = 0;
	gbc_rdbtnHx.gridy = 4;
	panel_2.add(rdbtnHx, gbc_rdbtnHx);
	
	final JRadioButton rdbtnHxEuclidean = new JRadioButton("H(x) = Euclidean");
	GridBagConstraints gbc_rdbtnHxEuclidean = new GridBagConstraints();
	gbc_rdbtnHxEuclidean.anchor = GridBagConstraints.WEST;
	gbc_rdbtnHxEuclidean.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnHxEuclidean.gridx = 0;
	gbc_rdbtnHxEuclidean.gridy = 5;
	panel_2.add(rdbtnHxEuclidean, gbc_rdbtnHxEuclidean);
	
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
	
	textPane = new JTextPane();
	frame.getContentPane().add(textPane, BorderLayout.CENTER);
	
	JButton btnSearch = new JButton("Search");
	GridBagConstraints gbc_btnSearch = new GridBagConstraints();
	gbc_btnSearch.gridx = 0;
	gbc_btnSearch.gridy = 3;
	panel_1.add(btnSearch, gbc_btnSearch);
	
	JPanel panel = new JPanel();
	frame.getContentPane().add(panel, BorderLayout.NORTH);
	GridBagLayout gbl_panel = new GridBagLayout();
	gbl_panel.columnWidths = new int[]{0, 113, 163, 0, 216, 0};
	gbl_panel.rowHeights = new int[]{20, 0};
	gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	panel.setLayout(gbl_panel);
	
	JLabel lblSource = new JLabel("Source");
	GridBagConstraints gbc_lblSource = new GridBagConstraints();
	gbc_lblSource.fill = GridBagConstraints.HORIZONTAL;
	gbc_lblSource.anchor = GridBagConstraints.EAST;
	gbc_lblSource.insets = new Insets(0, 0, 0, 5);
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
	
	ButtonGroup group = new ButtonGroup();
	group.add(rdbtnHxEuclidean);
	group.add(rdbtnHx);
	
	JLabel lblMaxTransfers = new JLabel("Max transfers");
	GridBagConstraints gbc_lblMaxTransfers = new GridBagConstraints();
	gbc_lblMaxTransfers.insets = new Insets(0, 0, 5, 0);
	gbc_lblMaxTransfers.gridx = 0;
	gbc_lblMaxTransfers.gridy = 6;
	panel_2.add(lblMaxTransfers, gbc_lblMaxTransfers);
	
	final JSpinner spinner = new JSpinner();
	spinner.setModel(new SpinnerNumberModel(0, 0, 99999999, 1));
	GridBagConstraints gbc_spinner = new GridBagConstraints();
	gbc_spinner.insets = new Insets(0, 0, 5, 0);
	gbc_spinner.gridx = 0;
	gbc_spinner.gridy = 7;
	panel_2.add(spinner, gbc_spinner);
	
	final JCheckBox chckbxShowByAlias = new JCheckBox("Show by Alias");
	chckbxShowByAlias.setSelected(true);
	GridBagConstraints gbc_chckbxShowByAlias = new GridBagConstraints();
	gbc_chckbxShowByAlias.anchor = GridBagConstraints.WEST;
	gbc_chckbxShowByAlias.gridx = 0;
	gbc_chckbxShowByAlias.gridy = 8;
	panel_2.add(chckbxShowByAlias, gbc_chckbxShowByAlias);
	
	btnParse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    try {
			g = p.ParseTxtFile("RandomCity.txt");
		    } catch(Exception e) {
			e.printStackTrace();
		    }
		}
	});
	
	btnSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if(rdbtnHx.isSelected()) {
			h = new HeuristicD();
			h.init(g);
		    }
		    else if (rdbtnHxEuclidean.isSelected()){
			h = new HeuristicE();
			ArrayList<String> names = new ArrayList<String>();
			names.add("busquadrant.txt");
			names.add("subquadrant.txt");
			names.add("walkquadrant.txt");
			h.init(g);
			((HeuristicE) h).setDistances(names);
		    }
		    int transf = (int) spinner.getModel().getValue();
		    as = new AStar(g, g.getNodeby(textField.getText()), g.getNodeby(textField_1.getText()), h, transf);
		    BitSet b = new BitSet(3);
		    b.clear();
		    b.set(0, rdbtnBus.isSelected());
		    b.set(1, rdbtnSubway.isSelected());
		    b.set(2, rdbtnWalking.isSelected());
		    nodes = as.getPath(b);
		    textPane.setText("");
		    WritePath(nodes, b, chckbxShowByAlias.isSelected());
		}
	});
    }

}
