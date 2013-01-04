package graph.parser;

import graph.components.Graph;
import graph.components.Line;
import graph.components.Node;
import graph.components.Transports;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author xyz0k
 *
 */
public class ParserGraph {

    /**
     * Graph to save the nodes parsed.
     */
    Graph graph;

    /**
     * Variable to hold the number of nodes.
     */
    int nNodes;

    /**
     * Constructor of the parser.
     */
    public ParserGraph() {
        graph = new Graph();
        nNodes = 0;

    }

    /**
     * Function to parse a file and save all the nodes in the graph.
     *
     * @param filename
     *            Name of the file with the graph saved.
     * @return The graph initialized with all the info.
     */
    @Deprecated
    public Graph parseTxtFile(String filename) {
        FileInputStream fstream;
        try {
            // We open the file with all the information.
            fstream = new FileInputStream("cities/".concat(filename));
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader file = new BufferedReader(new InputStreamReader(in));
            String line;
            line = file.readLine();
            String[] relation;
            int nRel = 0;
            int nDefinedCost;
            float busCost;
            float subCost;
            float walCost;
            float funCost;

            ArrayList<Node> nodeArray = new ArrayList<Node>();
            // While the file have lines to read.
            while (line != null) {
                // If the line start with // is a comment and we skip it.
                if (line.startsWith("//")) {
                    line = file.readLine();
                    continue;
                }
                // We get the number of nodes of the graph.
                nNodes = Integer.parseInt(line.split(" ")[0]);
                // Create the same number of nodes.
                for (int i = 0; i < nNodes; i++) {
                    nodeArray.add(new Node());
                }
                // For every node.
                for (int i = 0; i < nNodes; i++) {
                    line = file.readLine();
                    // We read the name of the node and assign it.
                    nodeArray.get(i).setName(line);
                    // We put an alias to the node.
                    nodeArray.get(i)
                            .setAlias("N".concat(String.valueOf(i + 1)));
                    line = file.readLine();
                    // We get the number of neighbors the node have.
                    nRel = Integer.parseInt(line.split(" ")[0]);
                    // For every neighbor.
                    for (int j = 0; j < nRel; j++) {
                        // Set the cost to -1.
                        busCost = -1;
                        subCost = -1;
                        walCost = -1;
                        // funCost = -1;
                        line = file.readLine();
                        // We get the relation line.
                        relation = line.split(" ");
                        // We get the number of defined costs.
                        nDefinedCost = 2 * Integer.parseInt(relation[2]);
                        // And get this costs.
                        for (int k = 0; k < nDefinedCost; k += 2) {
                            if (relation[3 + k].equals("B")) {
                                busCost = Float.parseFloat(relation[4 + k]);
                            } else if (relation[3 + k].equals("S")) {
                                subCost = Float.parseFloat(relation[4 + k]);
                            } else if (relation[3 + k].equals("W")) {
                                walCost = Float.parseFloat(relation[4 + k]);
                            } else if (relation[3 + k].equals("F")) {
                                // This is an implemented but contemplated
                                // option.
                                funCost = Float.parseFloat(relation[4 + k]);
                            }
                        }
                        // When we have the cost we add the neighbor to the
                        // node.
                        nodeArray.get(i).addNeighbor(
                                nodeArray.get(Integer.parseInt(relation[1]
                                        .substring(1)) - 1), busCost, subCost,
                                walCost);
                    }
                }
                // We read the next line of the text.
                line = file.readLine();
            }
            // When read all the text, we add all the nodes to the graph.
            for (int i = 0; i < nNodes; i++) {
                graph.addNode(nodeArray.get(i));
            }
            // Close the file and return the graph initialized.
            file.close();
            return graph;
        } catch (IOException e) {
            // If an exception occurs print the error and don't return anything.
            e.printStackTrace();
            return null;
        }
    }

    /**
     * New function to parse the file with the Line system.
     *
     * @param filename
     *            Name of the file with the information.
     * @return The graph initialized with all the info.
     */
    public Graph parseTxtFilewLines(String filename) {
        FileInputStream fstream;
        try {
            // Open the file with the info.
            fstream = new FileInputStream("cities/".concat(filename));
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader file = new BufferedReader(new InputStreamReader(in));
            String line;
            String aliasDest;
            String[] relation;
            int nRel;
            int nDefinedCost;
            int nLines;
            float walCost = -1;
            float funCost = -1;
            boolean bus = false;
            boolean subway = false;
            ArrayList<Node> nodeArray = new ArrayList<Node>();
            ArrayList<Line> costArray;
            line = file.readLine();
            // For all the line of the text.
            while (line != null) {
                // If the lie starts with // is a comment and we skip it.
                if (line.startsWith("//")) {
                    line = file.readLine();
                    continue;
                }
                // We get the number of nodes.
                nNodes = Integer.parseInt(line);
                line = file.readLine();
                // We create the nNodes.
                for (int i = 0; i < nNodes; i++) {
                    nodeArray.add(new Node());
                }
                // For every Node.
                for (int i = 0; i < nNodes; i++) {
                    nodeArray.get(i).setName(line);
                    line = file.readLine();
                    // We get the number of relations.
                    nRel = Integer.parseInt(line);
                    // N1 N2 3 B S W 4
                    // For every relation.
                    for (int j = 0; j < nRel; j++) {
                        costArray = new ArrayList<Line>();
                        line = file.readLine();
                        relation = line.split(" ");
                        nodeArray.get(i).setAlias(relation[0]);
                        aliasDest = relation[1];
                        // We get the number of defined costs.
                        nDefinedCost = Integer.parseInt(relation[2]);
                        // BSW
                        // We check what relations are between the nodes.
                        for (int k = 0; k < nDefinedCost; k++) {
                            if (relation[3 + k].equals("B")) {
                                bus = true;
                            } else if (relation[3 + k].equals("S")) {
                                subway = true;
                            } else if (relation[3 + k].equals("W")) {
                                // If the walking cost exist we save it.
                                walCost = Float.parseFloat(relation[3 + k + 1]);
                            } else if (relation[3 + k].equals("F")) {
                                funCost = Float.parseFloat(relation[3 + k + 1]);
                            }
                        }
                        // If the exist the bus relation.
                        if (bus) {
                            line = file.readLine();
                            relation = line.split(" ");
                            // We get the number of lines between the nodes.
                            nLines = Integer.parseInt(relation[1]);
                            // For every line.
                            for (int k = 0; k < nLines; k++) {
                                // We add the line with the name of the Line,
                                // the type of transport (BUS), and the cost.
                                costArray
                                        .add(new Line(
                                                relation[2 + 2 * k],
                                                Transports.BUS,
                                                Float.parseFloat(relation[2 + 2 * k + 1])));
                            }
                            bus = false;
                        }
                        // We do the same with the SUBWAY.
                        if (subway) {
                            line = file.readLine();
                            relation = line.split(" ");
                            nLines = Integer.parseInt(relation[1]);
                            for (int k = 0; k < nLines; k++) {
                                costArray
                                        .add(new Line(
                                                relation[2 + 2 * k],
                                                Transports.SUBWAY,
                                                Float.parseFloat(relation[2 + 2 * k + 1])));
                            }
                            subway = false;
                        }
                        // When we have all the Lines between the node and it
                        // neighbor we add it.
                        nodeArray.get(i)
                                .addNeighbor(
                                        nodeArray.get(Integer
                                                .parseInt(aliasDest
                                                        .substring(1)) - 1),
                                        costArray, walCost);
                        walCost = -1;
                    }
                    line = file.readLine();
                }
            }
            // When we have all the info we add it to the graph.
            for (int i = 0; i < nNodes; i++) {
                graph.addNode(nodeArray.get(i));
            }
            // We close the file and return the graph.
            file.close();
            return graph;
        } catch (IOException e) {
            // If an error have occurred we print the error.
            e.printStackTrace();
            return null;
        }
    }
}