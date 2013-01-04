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

    /**
     * Frame of the window.
     */
    private JFrame frame;

    /**
     * Graph of the application.
     */
    private Graph g;

    /**
     * Array with all the info of the path.
     */
    private ArrayList<InfoPath> nodes;

    /**
     * Object of the class parser to parse the graph.
     */
    private ParserGraph p;

    /**
     * Text field.
     */
    private JTextField textField;

    /**
     * Text field.
     */
    private JTextField textField1;

    /**
     * Text Panel.
     */
    private JTextPane textPane;

    /**
     * Holder of an A* class object.
     */
    private AStar as;

    /**
     * Holder of a Heuristic.
     */
    private Heuristic h;

    /**
     * Function to write the path info in the Text Panel.
     *
     * @param path
     *            The Array with the path info.
     * @param b
     *            The type of transports used.
     * @param alias
     *            If the info is displayed by the alias or the name of the node.
     */
    public void writePath(ArrayList<InfoPath> path, BitSet b, boolean alias) {
        Transports t;
        float costTot = 0;
        if (!path.isEmpty()) {
            for (int i = 0; i < path.size() - 1; i++) {
                t = path.get(i).getTransport();
                switch (t) {
                    case BUS:
                        textPane.setText(textPane.getText() + "\n"
                                + "Take the " + path.get(i).getLine()
                                + " (bus)");
                        break;
                    case SUBWAY:
                        textPane.setText(textPane.getText() + "\n"
                                + "Take the " + path.get(i).getLine()
                                + " (subway)");
                        break;
                    case WALK:
                        textPane.setText(textPane.getText() + "\n" + "Walk");
                    default:
                        textPane.setText(textPane.getText() + "\n"
                                + "An Alien abducted you and teleported");
                }
                if (alias) {
                    textPane.setText(textPane.getText() + " from "
                            + path.get(i).getSNode().getAlias());
                    textPane.setText(textPane.getText() + " to "
                            + path.get(i).getDNode().getAlias());
                } else {
                    textPane.setText(textPane.getText() + " from "
                            + path.get(i).getSNode().getName());
                    textPane.setText(textPane.getText() + " to "
                            + path.get(i).getDNode().getName());
                }

            }
            costTot = path.get(path.size() - 2).getCost();
            textPane.setText(textPane.getText() + "\n" + "Cost to travel: "
                    + costTot);
        } else {
            textPane.setText(textPane.getText()
                    + "\n"
                    + "Don't exist a way from source to destination with the selected transports.");
        }
    }

    /**
     * Launch the application.
     *
     * @param args
     *            Arguments to the window.
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

        JPanel panel2 = new JPanel();
        frame.getContentPane().add(panel2, BorderLayout.WEST);
        GridBagLayout gblpanel2 = new GridBagLayout();
        gblpanel2.columnWidths = new int[] {59, 0};
        gblpanel2.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 23, 0, 0};
        gblpanel2.columnWeights = new double[] {0.0, Double.MIN_VALUE};
        gblpanel2.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel2.setLayout(gblpanel2);

        JButton btnParse = new JButton("Parse");
        GridBagConstraints gbctnParse = new GridBagConstraints();
        gbctnParse.insets = new Insets(0, 0, 5, 0);
        gbctnParse.anchor = GridBagConstraints.NORTH;
        gbctnParse.gridx = 0;
        gbctnParse.gridy = 0;
        panel2.add(btnParse, gbctnParse);

        final JRadioButton rdbtnHx = new JRadioButton("H(x) = 0");
        rdbtnHx.setSelected(true);
        GridBagConstraints gbcrdbtnHx = new GridBagConstraints();
        gbcrdbtnHx.anchor = GridBagConstraints.WEST;
        gbcrdbtnHx.insets = new Insets(0, 0, 5, 0);
        gbcrdbtnHx.gridx = 0;
        gbcrdbtnHx.gridy = 2;
        panel2.add(rdbtnHx, gbcrdbtnHx);

        JPanel panel1 = new JPanel();
        frame.getContentPane().add(panel1, BorderLayout.EAST);
        GridBagLayout gblpanel1 = new GridBagLayout();
        gblpanel1.columnWidths = new int[] {65, 0};
        gblpanel1.rowHeights = new int[] {0, 0, 29, 147, 0};
        gblpanel1.columnWeights = new double[] {0.0, Double.MIN_VALUE};
        gblpanel1.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE};
        panel1.setLayout(gblpanel1);

        final JRadioButton rdbtnBus = new JRadioButton("Bus");
        GridBagConstraints gbcrdbtnBus = new GridBagConstraints();
        gbcrdbtnBus.anchor = GridBagConstraints.WEST;
        gbcrdbtnBus.insets = new Insets(0, 0, 5, 0);
        gbcrdbtnBus.gridx = 0;
        gbcrdbtnBus.gridy = 0;
        panel1.add(rdbtnBus, gbcrdbtnBus);

        final JRadioButton rdbtnSubway = new JRadioButton("Subway");
        GridBagConstraints gbcrdbtnSubway = new GridBagConstraints();
        gbcrdbtnSubway.anchor = GridBagConstraints.WEST;
        gbcrdbtnSubway.insets = new Insets(0, 0, 5, 0);
        gbcrdbtnSubway.gridx = 0;
        gbcrdbtnSubway.gridy = 1;
        panel1.add(rdbtnSubway, gbcrdbtnSubway);

        final JRadioButton rdbtnWalking = new JRadioButton("Walking");
        GridBagConstraints gbcrdbtnWalking = new GridBagConstraints();
        gbcrdbtnWalking.anchor = GridBagConstraints.WEST;
        gbcrdbtnWalking.insets = new Insets(0, 0, 5, 0);
        gbcrdbtnWalking.gridx = 0;
        gbcrdbtnWalking.gridy = 2;
        panel1.add(rdbtnWalking, gbcrdbtnWalking);

        textPane = new JTextPane();
        frame.getContentPane().add(textPane, BorderLayout.CENTER);

        JButton btnSearch = new JButton("Search");
        GridBagConstraints gbcbtnSearch = new GridBagConstraints();
        gbcbtnSearch.gridx = 0;
        gbcbtnSearch.gridy = 3;
        panel1.add(btnSearch, gbcbtnSearch);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        GridBagLayout gblpanel = new GridBagLayout();
        gblpanel.columnWidths = new int[] {0, 113, 163, 0, 216, 0};
        gblpanel.rowHeights = new int[] {20, 0};
        gblpanel.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE};
        gblpanel.rowWeights = new double[] {0.0, Double.MIN_VALUE};
        panel.setLayout(gblpanel);

        JLabel lblSource = new JLabel("Source");
        GridBagConstraints gbclblSource = new GridBagConstraints();
        gbclblSource.fill = GridBagConstraints.HORIZONTAL;
        gbclblSource.anchor = GridBagConstraints.EAST;
        gbclblSource.insets = new Insets(0, 0, 0, 5);
        gbclblSource.gridx = 1;
        gbclblSource.gridy = 0;
        panel.add(lblSource, gbclblSource);

        textField = new JTextField();
        textField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        textField.setToolTipText("Origen");
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbctextField = new GridBagConstraints();
        gbctextField.fill = GridBagConstraints.HORIZONTAL;
        gbctextField.anchor = GridBagConstraints.NORTH;
        gbctextField.insets = new Insets(0, 0, 0, 5);
        gbctextField.gridx = 2;
        gbctextField.gridy = 0;
        panel.add(textField, gbctextField);
        textField.setColumns(10);

        JLabel lblDestinatition = new JLabel("Destination");
        GridBagConstraints gbclblDestinatition = new GridBagConstraints();
        gbclblDestinatition.insets = new Insets(0, 0, 0, 5);
        gbclblDestinatition.anchor = GridBagConstraints.EAST;
        gbclblDestinatition.gridx = 3;
        gbclblDestinatition.gridy = 0;
        panel.add(lblDestinatition, gbclblDestinatition);

        textField1 = new JTextField();
        textField1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        textField1.setHorizontalAlignment(SwingConstants.LEFT);
        textField1.setToolTipText("Desti");
        GridBagConstraints gbctextField1 = new GridBagConstraints();
        gbctextField1.fill = GridBagConstraints.HORIZONTAL;
        gbctextField1.gridx = 4;
        gbctextField1.gridy = 0;
        panel.add(textField1, gbctextField1);
        textField1.setColumns(10);

        ButtonGroup group = new ButtonGroup();

        final JRadioButton rdbtnHxEuclidean = new JRadioButton(
                "H(x) = Euclidean");
        GridBagConstraints gbcrdbtnHxEuclidean = new GridBagConstraints();
        gbcrdbtnHxEuclidean.anchor = GridBagConstraints.WEST;
        gbcrdbtnHxEuclidean.insets = new Insets(0, 0, 5, 0);
        gbcrdbtnHxEuclidean.gridx = 0;
        gbcrdbtnHxEuclidean.gridy = 3;
        panel2.add(rdbtnHxEuclidean, gbcrdbtnHxEuclidean);
        group.add(rdbtnHxEuclidean);
        group.add(rdbtnHx);

        JLabel lblMaxTransfers = new JLabel("Max trans. transfers");
        GridBagConstraints gbclblMaxTransfers = new GridBagConstraints();
        gbclblMaxTransfers.insets = new Insets(0, 0, 5, 0);
        gbclblMaxTransfers.gridx = 0;
        gbclblMaxTransfers.gridy = 4;
        panel2.add(lblMaxTransfers, gbclblMaxTransfers);

        final JSpinner spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel(0, 0, 99999999, 1));
        GridBagConstraints gbcspinner = new GridBagConstraints();
        gbcspinner.insets = new Insets(0, 0, 5, 0);
        gbcspinner.gridx = 0;
        gbcspinner.gridy = 5;
        panel2.add(spinner, gbcspinner);

        JLabel lblMaxLineTransfers = new JLabel("Max line transfers");
        GridBagConstraints gbclblMaxLineTransfers = new GridBagConstraints();
        gbclblMaxLineTransfers.insets = new Insets(0, 0, 5, 0);
        gbclblMaxLineTransfers.gridx = 0;
        gbclblMaxLineTransfers.gridy = 6;
        panel2.add(lblMaxLineTransfers, gbclblMaxLineTransfers);

        final JSpinner spinner1 = new JSpinner();
        spinner1.setModel(new SpinnerNumberModel(0, 0, 99999999, 1));
        GridBagConstraints gbcspinner1 = new GridBagConstraints();
        gbcspinner1.insets = new Insets(0, 0, 5, 0);
        gbcspinner1.gridx = 0;
        gbcspinner1.gridy = 7;
        panel2.add(spinner1, gbcspinner1);

        final JCheckBox chckbxShowByAlias = new JCheckBox("Show by Alias");
        chckbxShowByAlias.setSelected(true);
        GridBagConstraints gbcchckbxShowByAlias = new GridBagConstraints();
        gbcchckbxShowByAlias.anchor = GridBagConstraints.WEST;
        gbcchckbxShowByAlias.gridx = 0;
        gbcchckbxShowByAlias.gridy = 8;
        panel2.add(chckbxShowByAlias, gbcchckbxShowByAlias);

        btnParse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                g = p.parseTxtFilewLines("cityy.txt");
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbtnHx.isSelected()) {
                    h = new HeuristicD();
                    h.init(g);
                } else if (rdbtnHxEuclidean.isSelected()) {
                    h = new HeuristicE();
                    ArrayList<String> names = new ArrayList<String>();
                    names.add("busquadrant.txt");
                    names.add("subquadrant.txt");
                    names.add("walkquadrant.txt");
                    h.init(g);
                    ((HeuristicE) h).setDistances(names);
                }
                int transtransf = (int) spinner.getModel().getValue();
                int linetransf = (int) spinner1.getModel().getValue();
                String initName = textField.getText().toLowerCase();
                String finalName = textField1.getText().toLowerCase();
                StringMatch strMatch = new StringMatch(g);
                if (!strMatch.stringMatchName(initName)) {
                    System.out.println(initName + " incorrect");
                    initName = strMatch.stringMatching(initName);
                    System.out.println("Using " + initName + " instead");
                }
                if (!strMatch.stringMatchName(finalName)) {
                    System.out.println(finalName + " incorrect");
                    finalName = strMatch.stringMatching(finalName);
                    System.out.println("Using " + finalName + " instead");
                }
                as = new AStar(g, g.getNodeby(initName),
                        g.getNodeby(finalName), h, transtransf, linetransf);
                BitSet b = new BitSet(3);
                b.clear();
                b.set(0, rdbtnBus.isSelected());
                b.set(1, rdbtnSubway.isSelected());
                b.set(2, rdbtnWalking.isSelected());
                nodes = as.getPath(b);
                textPane.setText("");
                writePath(nodes, b, chckbxShowByAlias.isSelected());
            }
        });
    }

}
