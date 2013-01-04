package graph.parser;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import algorithm.Position;

/**
 * @author xyz0k
 *
 */
public class ParserQuadrant {

    /**
     * Variable that hold the number of nodes.
     */
    private int nNodes;

    /**
     * Constructor of the class.
     */
    public ParserQuadrant() {
        nNodes = 0;
    }

    /**
     * Function to parse a quadrant file.
     *
     * @param filename
     *            Name of the file with the info
     * @return An array list with the position of the nodes in a quadrant.
     */
    public ArrayList<Position> quadrantInit(String filename) {
        ArrayList<Position> alPos = new ArrayList<Position>();
        try {
            // We open the file.
            FileInputStream fstream = new FileInputStream(
                    "heuristicInit/".concat(filename));
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader file = new BufferedReader(new InputStreamReader(in));
            // We get the number of nodes.
            nNodes = Integer.parseInt(file.readLine());
            String[] splitLine;
            // For every node.
            for (int i = 0; i < nNodes; i++) {
                splitLine = file.readLine().split(" ");
                // We add a new position with the x and y values of the node in
                // the file.
                alPos.add(new Position(Integer.parseInt(splitLine[1]), Integer
                        .parseInt(splitLine[2]), splitLine[0]));
            }
            // We close the file and return the Array with the positions.
            file.close();
            return alPos;
        } catch (IOException e) {
            // If an error occurred we print the error.
            e.printStackTrace();
            return null;
        }
    }
}
