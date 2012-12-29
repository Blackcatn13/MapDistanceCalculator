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
    
    public void addNeighbor(String name, String alias, float Bcost, float Scost, float Wcost) {
	neighbors.add(new Edge(this, new Node(name, alias), Bcost, Scost, Wcost));
    }
    
    public void addNeighbor(Node n, float Bcost, float Scost, float Wcost) {
	neighbors.add(new Edge(this, n, Bcost, Scost, Wcost));
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
    
    public float costTo(Node n, Transports t) {
	for(int i = 0; i < neighbors.size(); i++) {
	    if(neighbors.get(i).isNode(n)) {
		switch(t) {
		case BUS:
		    return neighbors.get(i).getBusCost();
		case SUBWAY:
		    return neighbors.get(i).getSubWCost();
		case WALK:
		    return neighbors.get(i).getWalkCost();
		}
	    }
	}
	return -1;
    }
    
    public boolean equals(Node a) {
	return meName.Same(a.meName.getName(), a.meName.getAlias());
    }
    
    public Transports getTransMinTo(Node n) {
	for(int i = 0; i < neighbors.size(); i++) {
	    if(neighbors.get(i).getNeighbor(this).equals(n)) {
		return neighbors.get(i).getTransMin();
	    }
	}
	return Transports.NOTHING;
    }
}
