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
    private int maxLines;
    
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
     * @return The path with then nodes to go from source to destination.
     */
    public ArrayList<InfoPath> getPath(BitSet b){
	ArrayList<InfoPath> path = new ArrayList<InfoPath>();
	ArrayList<Node> auxl = new ArrayList<Node>();
	ArrayList<Node> visited = new ArrayList<Node>();
	ArrayList<Pair> costs;
	Node aux;
	float cost = 0;
	float oldcost;
	Triplet t;
	InfoPath ip;
	
	ip = new InfoPath();
	ip.setSNode(source);
	// We add the source in a clear path to start.
	path.add(ip);
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
	    for(Node n : auxl) {
		// If the neighbor has been visited we skip it.
		if(visited.contains(n)) continue;
		// For the different types of transports.
		for(int j = 0; j < b.length(); j++) {
		    // If the transport method is selected.
		    if(b.get(j)) {
			// Copy the path.
                	path = copyList(t.getPath());
                	// Update the last InfoPath with the destination node, and the transport used.
                	path.get(path.size() - 1).setDNode(n);
                	path.get(path.size() - 1).setTransport(Transports.values()[j]);
                	// Create a new InfoPath with the source node.
                	ip = new InfoPath();
                	ip.setSNode(n);
                	// Add it to the path.
                	path.add(ip);
                	// Get the cost to the node.
                	costs = aux.costTo(n, Transports.values()[j]);
                	// If the system of transport exist from the parent to the neighbor.
                	for(Pair p : costs) {
                	    path.get(path.size() - 2).setLine(p.getN());
                	    oldcost = t.getGx();
                	    Triplet nt = new Triplet(p.getC() + oldcost + h.Calculate(n, destination, Transports.values()[j]), cost + oldcost, path, t.getTransTransfers(), t.getLastTransport(), t.getLineTransfers(), t.getLastLine());
                	    if(nt.updateTransport(Transports.values()[j], p.getN(), maxTransfers, maxLines)) {
                		list.addWithoutRep(nt);
                	    }
                	}
//                	if(cost != -1) {
//                	    // We get the accumulated cost from the source node to this.
//                	    oldcost = t.getGx();
//                	    // Create a new triplet with all the info
//                	    Triplet nt = new Triplet(cost + oldcost + h.Calculate(n, destination, Transports.values()[j]), cost + oldcost, path, t.getTransfers(), t.getLastTransport());
//                	    // We check if the number of transfer realized yet is minor than the maximum.
//                	    if(nt.updateTransport(Transports.values()[j]) <= maxTransfers) {
//                		// If after all the node gets there we put it in the list.
//                		list.addWithoutRep(nt);
//                	    }
//                	}
		    }
		}
	    }
	}
	if(list.empty()) {
	    return new ArrayList<InfoPath>();
	}
	return list.First().getPath();
    }
    
    /**
     * Function to copy an ArrayList.
     * @param list list to copy.
     * @return The new ArrayList copied.
     */
    private ArrayList<InfoPath> copyList(ArrayList<InfoPath> list){
	ArrayList<InfoPath> newList = new ArrayList<InfoPath>();
	for(InfoPath p : list) {
	    newList.add(new InfoPath(p));
	}
	return newList;
    }
}
