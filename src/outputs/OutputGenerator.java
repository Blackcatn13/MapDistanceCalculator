/**
 * 
 */
package outputs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

import algorithm.AStar;
import algorithm.HeuristicD;
import algorithm.InfoPath;

import graph.components.Graph;
import graph.components.Node;
import graph.components.Transports;
import graph.parser.ParserGraph;
import algorithm.Heuristic;
import outputs.Time;

/**
 * @author xyz0k
 *
 */
public class OutputGenerator {

    private static Graph graph;
    private static ArrayList<InfoPath> nodes;
    private static ParserGraph pG;
    private static AStar astar;
    private static Heuristic h;
    private static Time time;

    /**
     * Default constructor.
     */
    public OutputGenerator(){

    }

    /**
     * Method that generate TXT output file.
     * @param filename
     */
    public void generateTXT(String filename, Graph graph){
	FileWriter fstream;
	try {
	    fstream = new FileWriter("fileOutputs/".concat(filename));
	    BufferedWriter file = new BufferedWriter(fstream);
	    ArrayList<String> actNodes = new ArrayList<String>();
	    ArrayList<String> allNodes = new ArrayList<String>();
	    String iniNode;
	    String dashed = "------------------------------------------------------";
	    float averageTime;
	    float actTime;
	    float maxTime;
	    float finAverageTime = 0;
	    float finMaxTime = 0;
	    ArrayList<InfoPath> maxNodePath = null;
	    int iter;
	    int finIter = 0;
	    int maxTransfers = 20;
	    int maxLines = 20;
	    BitSet b = new BitSet(3);
	    //Set of all transport combination.
	    boolean[][] init = new boolean[][]{{false,false,false},{false,false,true},{false,true,false},{false,true,true},{true,false,false},{true,false,true},{true,true,false},{true,true,true}};
	    int[][] maxTraLin = new int[][]{{2,0},{4,0},{0,1},{0,3},{0,5},{2,2},{3,5}};
	    b.clear();
	    file.write("Heuristic usage: ");
		file.write(String.valueOf(h.getClass().getName()));
		file.newLine();
		file.write(dashed);
		file.newLine();
	    for(int k = 0; k < init.length; k++){
		b.set(0,init[k][0]);
		b.set(1,init[k][1]);
		b.set(2,init[k][2]);
		if (init[k][0] && init[k][1] && init[k][2]){
			
		}
		averageTime = 0;
		actTime = 0;
		maxTime = 0;
		iter = 0;
		for (int i = 0; i < graph.getGraphSize(); i++){
		    allNodes.add("N".concat(String.valueOf(i+1)));
		    actNodes.add("N".concat(String.valueOf(i+1)));
		}
		for (int i = 0; i < graph.getGraphSize(); i++){
		    if(actNodes.size() > 1){
			iniNode = actNodes.get(0);
			actNodes.remove("N".concat(String.valueOf(i+1)));
			for (int j = 0; j < actNodes.size(); j++){
				astar = new AStar(graph,graph.getNodebyAlias(iniNode), graph.getNodebyAlias(actNodes.get(j)), h, maxTransfers, maxLines);
			    time = new Time();
			    time.setScale("millisecond");
			    nodes = astar.getPath(b);
			    actTime = time.elapsedTime();
			    if (maxTime < actTime){
			    	maxTime = actTime;
			    }
			    if (finMaxTime < maxTime){
			    	finMaxTime = maxTime;
			    	maxNodePath = nodes;
			    }
			    averageTime += actTime;
			    iter++;
			}
		    }
		}
		finAverageTime += averageTime/iter;
		finIter++;
	    }
	    file.write("Average calculate path: " + String.valueOf(finAverageTime/finIter));
	    file.newLine();
	    file.write("Max calculate path: " + String.valueOf(finMaxTime));
	    file.newLine();
	    file.write("Path of max details");
	    file.newLine();
		    file.write("Node start: " + maxNodePath.get(0).getSNode().getAlias());
		    file.newLine();
		    file.write("Node end: " + maxNodePath.get(maxNodePath.size()-1).getSNode().getAlias());
		    file.newLine();
		    for (int i = 0; i < maxNodePath.size()-1; i++){
		    	InfoPath ip = maxNodePath.get(i);
		    	file.write("\t" + ip.getSNode().getName()+ " ("+ip.getSNode().getAlias() + ")" + " to " + ip.getSNode().getName()+ " (" + maxNodePath.get(i+1).getSNode().getAlias()+ ")");
		    	file.newLine();
		    }
	    file.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Launch the application.
     * @throws Exception 
     */
    public static void main(String[] args){
	String city = "ciutat60";
	OutputGenerator oG = new OutputGenerator();
	pG = new ParserGraph();
	graph = pG.parseTxtFilewLines(city.concat(".txt"));
	h = new HeuristicD(); 	
	oG.generateTXT(city.concat("output.txt"), graph);    	
    }
    
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
}