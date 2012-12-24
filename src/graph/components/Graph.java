/**
 * File: Graph.java
 * Created on 24/12/2012 by Marc
 * 
 * Class that represents a graph
 */
package graph.components;

import java.util.ArrayList;


public class Graph {

    private ArrayList<Node> nodes;
    
    public Graph() {
	nodes = new ArrayList<Node>();
    }
    
    public void addNode(Node n) {
	nodes.add(n);
    }
    
    public ArrayList<Node> getNeighbors(String name) {
	int node = 0;
	for(int i = 0; i < nodes.size(); i++) {
	    if(nodes.get(i).isThisNode(name)) {
		node = i;
		break;
	    }
	}
	return nodes.get(node).getNeighbors();
	//return new ArrayList<Node>();
    }
    
    
}
