/**
 * File: AStar.java
 * Created on 24/12/2012 by Marc
 * 
 * Class that represents the A* algorithm
 */
package algorithm;

import graph.components.Graph;
import graph.components.Node;
import graph.components.Transports;

import java.util.ArrayList;
import java.util.BitSet;

public class AStar {
    /**
     * Variable to hold a graph for the heuristic function.
     */
    private Graph graph;
    /**
     * Node source for the A*.
     */
    private Node source;
    /**
     * Node destination for the A*.
     */
    private Node destination;
    /**
     * OrderedList to save the nodes in f(x) order.
     */
    private OrderedList list;
    /**
     * Heuristic used in the A*.
     */
    private Heuristic h;
    
    /**
     * Number of maximum transfers.
     */
    private int maxTransfers;
    
    /**
     * Default constructor of the class.
     */
    public AStar() {
	graph = new Graph();
	source = new Node();
	destination = new Node();
	list = new OrderedList();
    }
    
    /**
     * Constructor with all the parameters to run the A*.
     * @param g Graph used for the A*.
     * @param s node source.
     * @param d node destination.
     * @param heu heuristic to check.
     */
    public AStar(Graph g, Node s, Node d, Heuristic heu, int t) {
	graph = g;
	source = s;
	destination = d;
	list = new OrderedList();
	h = heu;
	maxTransfers = t;
    }
    
    /**
     * Setter of the source Node.
     * @param s new source node.
     */
    public void setSource(Node s) {
	source = s;
    }
    
    /**
     * Setter of the destination Node.
     * @param d new destination node.
     */
    public void setDestination(Node d) {
	destination = d;
    }
    
    /**
     * Function that calculates the A*.
     * @param b types of transports to use.
     * @return The path with then nodes to go fomr source to destination.
     */
    public ArrayList<Node> getPath(BitSet b){
	ArrayList<Node> path = new ArrayList<Node>();
	ArrayList<Node> auxl = new ArrayList<Node>();
	ArrayList<Node> visited = new ArrayList<Node>();
	Node aux;
	float cost = 0;
	float oldcost;
	Triplet t;
	
	// We add the source in a clear path to start.
	path.add(source);
	// We add the new triplet that holds the f(x), the g(x) and the path in our ordered list.
	list.add(new Triplet(cost + h.Calculate(source, destination), 0, path));
	// While the list holds an element or the first element is equals to the destination.
	while(!list.empty() && !list.First().getFirst().equals(destination)) {
	    // Get the first element of the list.
	    t = list.getFirst();
	    // Get the last node in that path.
	    aux = t.getFirst();
	    // Mark the node as visited.
	    visited.add(aux);
	    // Get the neighbors of the node.
	    auxl = aux.getNeighbors();
	    // For every neighbor.
	    for(int i = 0; i < auxl.size(); i++) {
		// If the neighbor has been visited we skip it.
		if(visited.contains(auxl.get(i))) continue;
		// For the different types of transports.
		for(int j = 0; j < b.length(); j++) {
		    // If the transport method is selected.
		    if(b.get(j)) {
			// Add the node to the path.
                	path = new ArrayList<Node>(t.getPath());
                	path.add(auxl.get(i));
                	// Get the cost to the node.
                	cost = aux.costTo(auxl.get(i), Transports.values()[j]);
                	// If the system of transport exist from the parent to the neighbor.
                	if(cost != -1) {
                	    // We get the accumulated cost from the source node to this.
                	    oldcost = t.getGx();
                	    // Add the node to the ordered list.
                	    Triplet nt = new Triplet(cost + oldcost + h.Calculate(auxl.get(i), destination, Transports.values()[j]), cost + oldcost, path, t.getTransfers(), t.getLastTransport());
                	    if(nt.updateTransport(Transports.values()[j]) <= maxTransfers) {
                		list.addWithoutRep(nt);
                	    }
                	}
		    }
		}
	    }
	}
	if(list.empty()) {
	    return new ArrayList<Node>();
	}
	return list.First().getPath();
    }
}
