package graph.parser;

import graph.components.Transports;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import algorithm.LineSpec;

/**
 * File: ParserLineSpec.java Created on 05/01/2013 by Marc
 *
 * Parser for a LineSpec file.
 */
public class ParserLineSpec {

    /**
     * Function to get a LineSpec from a file.
     *
     * @param filename
     *            name of the file to get the line spec.
     * @return The line spec read in the file.
     */
    public LineSpec getLine(String filename) {
        ArrayList<String> nds = new ArrayList<String>();
        String name;
        Transports t;
        float cost;
        int numNodes;

        try {
            FileInputStream fstream = new FileInputStream(
                    "heuristicInit/".concat(filename));
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader file = new BufferedReader(new InputStreamReader(in));
            name = file.readLine();
            t = Transports.valueOf(file.readLine());
            cost = Float.parseFloat(file.readLine());
            numNodes = Integer.parseInt(file.readLine());
            for (int i = 0; i < numNodes; i++) {
                nds.add(file.readLine());
            }
            file.close();
            return new LineSpec(name, t, cost, numNodes, nds);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
