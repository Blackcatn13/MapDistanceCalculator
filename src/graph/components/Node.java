/**
 * File: Node.java
 * Created on 07/12/2012 by Marc
 * 
 * Class that represents a graph node
 */
package graph.components;

import java.util.ArrayList;
import java.util.Map;

public class Node {
    //TODO: Change NodeNames to String if it work's
    //private Map<NodeNames,Edge> neighbors;
    private ArrayList<Edge> neighbors;
    private NodeNames meName;
    
    public Node(String name) {
	meName = new NodeNames(name);
    }
    
    public Node() {
    }
    public String getName() {
	return meName.getName();
    }
    
    public boolean isThisNode(String n) {
	return meName.equals(n);
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
}
