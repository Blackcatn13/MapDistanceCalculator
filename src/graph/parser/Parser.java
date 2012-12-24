/**
 * 
 */
package graph.parser;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import graph.components.Edge;
import graph.components.Graph;
import graph.components.Node;

/**
 * @author xyz0k
 *
 */
public class Parser {
	Graph graph;
	int nNodes;
	// Constructor
	public Parser() {
		graph = new Graph();
		nNodes = 0;
		
	}
	public Graph ParseTxtFile (String filename) throws Exception{
		FileInputStream fstream = new FileInputStream("cities/".concat(filename));
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader file = new BufferedReader(new InputStreamReader(in));
		String line = file.readLine();
		String[] relation;
		int nRel = 0;
		//Cost
		ArrayList<Node> nodeArray = new ArrayList<Node>();
		boolean first = true;
		while (line != null){
			if (line.startsWith("//")){
				line = file.readLine();
				continue;
			}
			nNodes = Integer.parseInt(line.split(" ")[0]);
			//System.out.println(nNodes);
			for (int i = 0; i < nNodes; i++){
				nodeArray.add(new Node());
			}
			for (int i = 0; i < nNodes; i++){
				line = file.readLine();
				nodeArray.get(i).setName(line);
				line = file.readLine();
				nRel = Integer.parseInt(line.split(" ")[0]);
				for (int j = 0; j < nRel; j++){
					line = file.readLine();
					//relation = (nodeAct, nodeDest, cost)
					// N1 N2 3
					relation = line.split(" ");
					nodeArray.get(i).addNeighbor(nodeArray.get(Integer.parseInt(relation[1].substring(1)) - 1), Float.parseFloat(relation[2]));
				}
				//line = file.readLine();
			}
		line = file.readLine();
		}
		file.close();
		for(int i = 0; i < nNodes; i++) {
		    graph.addNode(nodeArray.get(i));
		}
		return graph;
	}
}
