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
	
    }
    
    public void addNode(Node n) {
	nodes.add(n);
    }
    
    public ArrayList<Node> getNeighbors(String name) {
	
	return new ArrayList<Node>();
    }
    
    
}
