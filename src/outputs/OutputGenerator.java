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
	    int iter;
	    int finIter = 0;
	    /*
	     * All transports enabled.
	     */
	    BitSet b = new BitSet(3);
	    boolean[][] init = new boolean[][]{{false,false,false},{false,false,true},{false,true,false},{false,true,true},{true,false,false},{true,false,true},{true,true,false},{true,true,true}};
	    b.clear();
	    for(int k = 0; k < init.length; k++){
		b.set(0,init[k][0]);
		b.set(1,init[k][1]);
		b.set(2,init[k][2]);
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
			    astar = new AStar(graph,graph.getNodebyAlias(iniNode), graph.getNodebyAlias(actNodes.get(j)), h, 20, 20);
			    file.write(iniNode + " ");
			    file.write(actNodes.get(j) + " ");
			    time = new Time();
			    time.setScale("millisecond");
			    nodes = astar.getPath(b);
			    actTime = time.elapsedTime();
			    if (maxTime < actTime) maxTime = actTime;
			    if (finMaxTime < maxTime) finMaxTime = maxTime;
			    averageTime += actTime;
			    file.write(String.valueOf(actTime));
			    file.newLine();
			    iter++;
			}
		    }
		}
		file.write("Average time of calculate path: ");
		finAverageTime += averageTime/iter;
		finIter++;
		file.write(String.valueOf(averageTime/iter));
		file.newLine();
		file.write("Max time of calculate path: ");
		file.write(String.valueOf(maxTime));
		file.newLine();
		file.write(dashed);
		file.newLine();
	    }
	    file.write("Average of average of calculate path: ");
	    file.write(String.valueOf(finAverageTime/finIter));
	    file.newLine();
	    file.write("Max of max of calculate path: ");
	    file.write(String.valueOf(finMaxTime));
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
	String city = "SimpleSimpsonsCity2";
	OutputGenerator oG = new OutputGenerator();
	pG = new ParserGraph();
	graph = pG.parseTxtFile(city.concat(".txt"));
	h = new HeuristicD(); 	
	oG.generateTXT(city.concat("output.txt"), graph);    	
    }
}