/**
 * File: Node.java
 * Created on 07/12/2012 by Marc
 * 
 * Class that represents a graph node
 */
package graph.components;

import java.util.ArrayList;

public class Node {
    //TODO: Change NodeNames to String if it work's
    //private Map<NodeNames,Edge> neighbors;
    private ArrayList<Edge> neighbors;
    private NodeNames meName;
    
    public Node(String name, String alias) {
	meName = new NodeNames(name, alias);
	neighbors = new ArrayList<Edge>();
    }
    
    public Node() {
	meName = new NodeNames();
	neighbors = new ArrayList<Edge>();
    }
    
    public String getName() {
	return meName.getName();
    }
    public String getAlias(){
    return meName.getAlias();
    }
    
    public boolean isThisNode(String n, String a) {
	return meName.Same(n, a);
    }
    
    public boolean isThisNodebyName(String n) {
    	return meName.SameName(n);
        }
    
    public boolean isThisNodebyAlias(String a) {
    	return meName.SameAlias(a);
        }
    
    public void addNeighbor(String name, String alias, float cost) {
	neighbors.add(new Edge(this, new Node(name, alias), cost));
    }
    
    public void addNeighbor(Node n, float cost) {
	neighbors.add(new Edge(this, n, cost));
    }
    
    public void setName(String n) {
	meName.setName(n);
    }
    
    public void setAlias(String n) {
    meName.setAlias(n);
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
    
    public boolean equals(Node a) {
	return meName.Same(a.meName.getName(), a.meName.getAlias());
    }
}
