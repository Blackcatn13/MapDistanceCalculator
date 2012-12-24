/**
 * File: Node.java
 * Created on 07/12/2012 by Marc
 * 
 * Class that represents a graph node
 */
package graph.components;

import java.util.Map;

public class Node {
    
    private Map<NodeNames,Edge> neighbors;
    private NodeNames meName;
    
    public Node(String name) {
	meName = new NodeNames(name);
    }
    
    public String getName() {
	return meName.getName();
    }
    
    public boolean isThisNode(String n) {
	return meName.equals(n);
    }
    
    public void addNeighbor(String name, float cost) {
	neighbors.put(new NodeNames(name), new Edge(this, new Node(name), cost));
    }
}
