/**
 * File: Node.java
 * Created on 07/12/2012 by Marc
 * 
 * Class that represents a graph node
 */
package graph.components;

import java.util.ArrayList;
//import java.util.Map;

public class Node {
    //TODO: Change NodeNames to String if it work's
    //private Map<NodeNames,Edge> neighbors;
    private ArrayList<Edge> neighbors;
    private NodeNames meName;
    
    public Node(String name) {
	meName = new NodeNames(name);
	neighbors = new ArrayList<Edge>();
    }
    
    public Node() {
	meName = new NodeNames();
	neighbors = new ArrayList<Edge>();
    }
    
    public String getName() {
	return meName.getName();
    }
    
    public boolean isThisNode(String n) {
	return meName.SameName(n);
    }
    
    public void addNeighbor(String name, float cost) {
	neighbors.add(new Edge(this, new Node(name), cost));
    }
    
    public void addNeighbor(Node n, float cost) {
	neighbors.add(new Edge(this, n, cost));
    }
    
    public void setName(String n) {
	meName.setName(n);
    }
    
    public ArrayList<Node> getNeighbors(){
	ArrayList<Node> nb = new ArrayList<Node>();
	for(int i = 0; i < neighbors.size(); i++) {
	    nb.add(neighbors.get(i).getNeighbor(this));
	}
	return nb;
    }
    
    public float costTo(Node n) {
	for(int i = 0; i < neighbors.size(); i++) {
	    if(neighbors.get(i).isNode(n)) {
		return neighbors.get(i).getCost();
	    }
	}
	return -1;
    }
}
