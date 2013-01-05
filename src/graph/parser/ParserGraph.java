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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import algorithm.Pair;

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
            String[] overcost;
            int nRel;
            int nDefinedCost;
            int nLines;
            float walCost = -1;
            boolean bus = false;
            boolean subway = false;
            boolean overCost = false;
            ArrayList<Node> nodeArray = new ArrayList<Node>();
            ArrayList<Line> costArray;
            ArrayList<Pair> overArray = new ArrayList<Pair>();
            line = file.readLine();
            // For all the line of the text.
            while (line != null) {
                // If the line starts with // is a comment and we skip it.
                if (line.startsWith("//")) {
                    line = file.readLine();
                    continue;
                }
                //We compare if the city has overcosts
                overcost = line.split(" ");
                if (overcost[0].equals("overcost")) {
                    overArray = new ArrayList<Pair>();
                    overCost = true;
                    int nOver = Integer.parseInt(overcost[1]);
                    line = file.readLine();
                    for (int i = 0; i < nOver; i++) {
                        overcost = line.split(" ");
                        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                        // get current date time with Date()
                        Date date = new Date();
                        // Compare if the actual date is between date of over
                        // cost.
                        if (dateFormat.format(date).compareTo(overcost[1]) > 0
                                && dateFormat.format(date).compareTo(
                                        overcost[2]) < 0) {
                            overArray.add(new Pair(Float
                                    .parseFloat(overcost[3]), overcost[0]));
                        }
                        line = file.readLine();
                    }
                } else {
                    line = file.readLine();
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
                                String busLine = relation[2 + 2 * k];
                                float actCost = 0;
                                if (overCost) {
                                    for (Pair pairLine : overArray) {
                                        if (pairLine.getN().equals(busLine)) {
                                            actCost = pairLine.getC();
                                            continue;
                                        }
                                    }
                                }
                                costArray
                                        .add(new Line(
                                                busLine,
                                                Transports.BUS,
                                                Float.parseFloat(relation[2 + 2 * k + 1])
                                                        + actCost));
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