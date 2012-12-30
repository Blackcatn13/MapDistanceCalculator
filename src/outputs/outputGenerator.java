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
public class outputGenerator {

	private static Graph graph;
	private static ArrayList<Node> nodes;
	private static ParserGraph pG;
	private static AStar astar;
	private static Heuristic h;
	private static Time time;
	
	/**
	 * Default constructor.
	 */
	public outputGenerator(){
		
	}
	
	/**
	 * Method that generate TXT output file.
	 * @param filename
	 */
	public void generateTXT(String filename, long elapsedTime, ArrayList<Node> path, Graph graph){
		FileWriter fstream;
		try {
			fstream = new FileWriter("fileOutputs/".concat(filename));
			BufferedWriter file = new BufferedWriter(fstream);
			file.write(String.valueOf(elapsedTime));
			file.newLine();
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
    public static void main(String[] args) throws Exception {
    	String city = "RandomCity.txt";
    	outputGenerator oG = new outputGenerator();
    	pG = new ParserGraph();
    	graph = pG.ParseTxtFile(city);
    	h = new Heuristic1();
    	for (int i = 0; i < graph.getGraphSize(); i++){
    		astar = new AStar(graph,graph.getNodebyAlias("N".concat(String.valueOf(i+1))), graph.getNodebyAlias("N5"), h);
    		BitSet b = new BitSet(3);
        	b.clear();
        	b.set(0, true);
    	    b.set(1, true);
    	    b.set(2, true);
    	    nodes = astar.getPath(b);
        	time = new Time();
    		astar.getPath(b);
        	oG.generateTXT("N".concat(String.valueOf(i+1)).concat("-"+"N5"), time.elapsedTime(), nodes, graph);    	
    	} 	
    }
}