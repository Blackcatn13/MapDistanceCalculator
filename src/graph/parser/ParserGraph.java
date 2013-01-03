/**
 * 
 */
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
    Graph graph;
    int nNodes;
    // Constructor
    public ParserGraph() {
	graph = new Graph();
	nNodes = 0;

    }
    public Graph ParseTxtFile (String filename){
	FileInputStream fstream;
	try{
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
	    //float funCost;
	    ArrayList<Node> nodeArray = new ArrayList<Node>();
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
		    nodeArray.get(i).setAlias("N".concat(String.valueOf(i+1)));
		    line = file.readLine();
		    nRel = Integer.parseInt(line.split(" ")[0]);
		    for (int j = 0; j < nRel; j++){
			busCost = -1;
			subCost = -1;
			walCost = -1;
			//funCost = -1;
			line = file.readLine();
			//relation = (nodeAct, nodeDest, cost)
			// N1 N2 3
			relation = line.split(" ");
			//nodeArray.get(i).addNeighbor(nodeArray.get(Integer.parseInt(relation[1].substring(1)) - 1), Float.parseFloat(relation[2]));
			nDefinedCost = 2*Integer.parseInt(relation[2]);
			for (int k = 0; k < nDefinedCost; k+=2){
			    if (relation[3+k].equals("B")){
				busCost = Float.parseFloat(relation[4+k]);
			    }
			    else if (relation[3+k].equals("S")){
				subCost = Float.parseFloat(relation[4+k]);
			    }
			    else if (relation[3+k].equals("W")){
				walCost = Float.parseFloat(relation[4+k]);
			    }
			    else if (relation[3+k].equals("F")){
				//funCost = Float.parseFloat(relation[4+k]);
			    }
			}
			//addNeighbor(String name, String alias, float Bcost, float Scost, float Wcost)
			nodeArray.get(i).addNeighbor(nodeArray.get(Integer.parseInt(relation[1].substring(1)) - 1), busCost, subCost, walCost);
		    }
		    //line = file.readLine();
		}
		line = file.readLine();
	    }
	    for(int i = 0; i < nNodes; i++) {
		graph.addNode(nodeArray.get(i));
	    }
	    file.close();
	    return graph;
	}catch (IOException e){
	    e.printStackTrace();
	    return null;
	}
    }

    public Graph ParseTxtFilewLines (String filename){
	FileInputStream fstream;
	try{
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
	    boolean bus = false;
	    boolean subway  = false;
	    ArrayList<Node> nodeArray = new ArrayList<Node>();
	    ArrayList<Line> costArray;
	    line = file.readLine();
	    while (line != null){
			if (line.startsWith("//")){
			    line = file.readLine();
			    continue;
			}
			nNodes = Integer.parseInt(line);
			line = file.readLine();
			for (int i = 0; i < nNodes; i++){
			    nodeArray.add(new Node());
			}
			for (int i = 0; i < nNodes; i++){
			    nodeArray.get(i).setName(line);
			    line = file.readLine();
			    nRel = Integer.parseInt(line);
			    //N1 N2 3 B S W 4
			    for (int j = 0; j < nRel; j++){
				costArray = new ArrayList<Line>();
				line = file.readLine();
				relation = line.split(" ");
				nodeArray.get(i).setAlias(relation[0]);
				aliasDest = relation[1];
				nDefinedCost = Integer.parseInt(relation[2]);
				//BSW
				for (int k = 0; k < nDefinedCost; k++){
				    if (relation[3+k].equals("B")){
					bus = true;
				    }
				    else if (relation[3+k].equals("S")){
					subway = true;
				    }
				    else if (relation[3+k].equals("W")){
					walCost = Float.parseFloat(relation[3+k+1]);
				    }
				    else if (relation[3+k].equals("F")){
					//funCost = Float.parseFloat(relation[3+k+1]);
				    }
				}
				if (bus){
					line = file.readLine();
					relation = line.split(" ");
				    nLines = Integer.parseInt(relation[1]);
				    for (int k = 0; k < nLines; k++){
						costArray.add(new Line(relation[2+2*k], Transports.BUS, Float.parseFloat(relation[2+2*k+1])));
				    }
				    bus  = false;
				}
				if (subway){
					line = file.readLine();
				    relation = line.split(" ");
				    nLines = Integer.parseInt(relation[1]);
				    for (int k = 0; k < nLines; k++){
						costArray.add(new Line(relation[2+2*k], Transports.SUBWAY, Float.parseFloat(relation[2+2*k+1])));
				    }
				    subway  = false;
				}
				nodeArray.get(i).addNeighbor(nodeArray.get(Integer.parseInt(aliasDest.substring(1))-1), costArray, walCost);
			    walCost = -1;
			    }
				line = file.readLine();
			}
		}
	    for(int i = 0; i < nNodes; i++) {
		graph.addNode(nodeArray.get(i));
	    }
	    file.close();
	    return graph;
	}catch (IOException e){
	    e.printStackTrace();
	    return null;
	}
    }
}
