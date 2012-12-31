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
import algorithm.Heuristic1;

import graph.components.Graph;
import graph.components.Node;
import graph.parser.ParserGraph;
import algorithm.Heuristic;
import outputs.Time;

/**
 * @author xyz0k
 *
 */
public class OutputGenerator {

	private static Graph graph;
	private static ArrayList<Node> nodes;
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
	    	float averageTime = 0;
	    	int iter = 0;
	    	/*
	    	 * All transports enabled.
	    	 */
	    	BitSet b = new BitSet(3);
        	b.clear();
        	b.set(0, true);
    	    b.set(1, true);
    	    b.set(2, true);
    	    file.write("All paths and it's time using the transport below.");
    	    file.newLine();
    	    file.write("Bus" + ": " + b.get(0));
    	    file.newLine();
    	    file.write("Subwalk" + ": " + b.get(1));
    	    file.newLine();
    	    file.write("Walk" + ": " + b.get(2));
    	    file.newLine();
    	    file.write("Heuristic usage: ");
    	    file.write(String.valueOf(h.getClass().getName()));
    	    file.newLine();
			for (int i = 0; i < graph.getGraphSize(); i++){
	    		allNodes.add("N".concat(String.valueOf(i+1)));
	    		actNodes.add("N".concat(String.valueOf(i+1)));
	    	}
			for (int i = 0; i < graph.getGraphSize(); i++){
				if(actNodes.size() > 1){
					iniNode = actNodes.get(0);
    				actNodes.remove("N".concat(String.valueOf(i+1)));
    				for (int j = 0; j < actNodes.size(); j++){
    					astar = new AStar(graph,graph.getNodebyAlias(iniNode), graph.getNodebyAlias(actNodes.get(j)), h);
    					file.write(iniNode + " ");
    		    		file.write(actNodes.get(j) + " ");
    					time = new Time();
    		        	time.setScale("millisecond");
    					nodes = astar.getPath(b);
    		    		file.write(String.valueOf(averageTime += time.elapsedTime()));
    		    		file.newLine();
    		    		iter++;
    				}
				}
			}
			file.write("Average Time: ");
			file.write(String.valueOf(averageTime/iter));
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    /**
     * Launch the application.
     * @throws Exception 
     */
    public static void main(String[] args){
    	String city = "SimpleSimpsonsCity2.txt";
    	OutputGenerator oG = new OutputGenerator();
    	pG = new ParserGraph();
    	graph = pG.ParseTxtFile(city);
    	h = new Heuristic1(); 	
    	oG.generateTXT("output.txt", graph);    	
    }
}